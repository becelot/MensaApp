package bt.MensaApp.Model.JSON;

/**
 * Server configuration file. Contains the host, port and API mapping for the JSON HTTP Proxy.
 */

public class JSONServerConfig {
    public static final String HOST = "infinite-bastion-24337.herokuapp.com";
    public static final Integer PORT = 80;
    public static final String MENSA_API_HOST = "/getMensa?uni=%s";
    public static final String MENU_API_HOST = "/getMenus?uni=%s&mensa=%s";
    public static final String UNIVERSITY_API_KEY = "/getUni";
}
