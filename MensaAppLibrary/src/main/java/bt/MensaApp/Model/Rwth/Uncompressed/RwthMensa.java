package bt.MensaApp.Model.Rwth.Uncompressed;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.Mensa;
import bt.MensaApp.Model.Menu;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.University;
import bt.MensaApp.Net.HttpClient;

/**
 * Created by bened on 11/5/2016.
 */

public class RwthMensa extends Mensa {
    private final String HOST = "www.studierendenwerk-aachen.de";

    public RwthMensa(String name, String informationPage, University university) {
        super(name, informationPage, university);
    }

    private Document parseHtmlFast(String html) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        fac.setNamespaceAware(false);
        fac.setValidating(false);
        try {
            fac.setFeature("http://xml.org/sax/features/namespaces", false);
            fac.setFeature("http://xml.org/sax/features/validation", false);
            fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (ParserConfigurationException e) {

        }


        return fac.newDocumentBuilder().parse(new InputSource(new StringReader(html)));
    }

    private Menu extractMenu(Node menuNode) throws XPathExpressionException {
        XPathExpression menuCategoryXPath = XPathFactory.newInstance().newXPath().compile(".//span[@class=\"menue-item menue-category\"]");
        XPathExpression menuDescrXPath = XPathFactory.newInstance().newXPath().compile(".//span[@class=\"menue-item menue-desc\"]");
        XPathExpression menuPriceXPath = XPathFactory.newInstance().newXPath().compile(".//span[@class=\"menue-item menue-price\"]");

        NodeList menuDescriptionNode = ((Node)menuDescrXPath.evaluate(menuNode, XPathConstants.NODE)).getChildNodes();
        String menuName = "";

        //Ignore superscripts
        for (int menuNameLine = 0; menuNameLine < menuDescriptionNode.getLength(); menuNameLine++) {
            if (menuDescriptionNode.item(menuNameLine).getNodeType() == Node.TEXT_NODE) {
                menuName += menuDescriptionNode.item(menuNameLine).getTextContent().trim();
            }
        }

        return new Menu(menuCategoryXPath.evaluate(menuNode, XPathConstants.STRING).toString().trim(),
                menuName, menuPriceXPath.evaluate(menuNode, XPathConstants.STRING).toString().trim() );
    }

    @Override
    public List<IDataProvider> getMenus() {
        List<IDataProvider> result;
        HttpClient client = new HttpClient(HOST);
        String html;

        try {
            client.connect();

            html = client.requestData(this.getWebpage());

            //Convert html to valid xml
            html = html.replace("&", "&amp;");

            Document doc = parseHtmlFast(html);
            XPathExpression dayXPath = XPathFactory.newInstance().newXPath().compile("//div[@class=\"accordion\"]/h3//a/text()");
            XPathExpression menuTableXPath = XPathFactory.newInstance().newXPath().compile("//div[@class=\"accordion\"]/div");
            XPathExpression singleMenuXPath = XPathFactory.newInstance().newXPath().compile(".//td[@class=\"menue-wrapper\"]");

            NodeList dayList = (NodeList) dayXPath.evaluate(doc, XPathConstants.NODESET);
            NodeList menuTableList = (NodeList) menuTableXPath.evaluate(doc, XPathConstants.NODESET);

            result = new ArrayList<>();
            for (int workingDay = 0; workingDay < dayList.getLength(); workingDay++ ) {
                Node dayNode = dayList.item(workingDay);
                String d = dayNode.getTextContent().replace("\n", "");

                //Get Date
                SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(parser.parse(d.substring(d.indexOf(',')+2).toString()));

                //Check if current
                Calendar now = new GregorianCalendar() {{ add(Calendar.DAY_OF_MONTH, -1); }};
                if (!calendar.after(now)) {
                    continue;
                }
                result.add(new NavigationHeader(parser.format(calendar.getTime()) ));

                //Get all menus
                NodeList menuList = (NodeList)singleMenuXPath.evaluate( menuTableList.item(workingDay), XPathConstants.NODESET);
                for (int menuOnList = 0; menuOnList < menuList.getLength(); menuOnList++) {
                    result.add(extractMenu(menuList.item(menuOnList)));
                }
            }
        } catch (Exception e) {
            return null;
        }


        return result;
    }
}
