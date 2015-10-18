package Builds;

/**
 * Created by Forer on 10/13/2015.
 */
public class ShipBuild {
    public String shipType;
    public String buildName;
    public String buildURL;

    public ShipBuild (String type, String name, String url) {
        shipType = type;
        buildName = name;
        buildURL = url;
    }

    public String toString() {
        return "| " + shipType + " | " + buildName + " | " + buildURL + " | ";
    }
}
