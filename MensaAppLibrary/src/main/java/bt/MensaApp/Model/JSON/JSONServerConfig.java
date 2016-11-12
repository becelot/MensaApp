package bt.MensaApp.Model.JSON;

/**
 * Server configuration file. Contains the host, port and API mapping for the JSON HTTP Proxy.
 */

public class JSONServerConfig {
    public static final String HOST = "192.168.0.18";
    public static final Integer PORT = 9000;
    public static final String MENSA_API_HOST = "/getMensa?uni=%s";
    public static final String MENU_API_HOST = "/getMenus?uni=%s&mensa=%s";
    public static final String UNIVERSITY_API_KEY = "/getUni";
}
