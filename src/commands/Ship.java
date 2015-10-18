package commands;

import Builds.ShipBuild;
import org.jibble.pircbot.PircBot;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Forer on 10/13/2015.
 */
public class Ship extends Command   {
    List<ShipBuild> shipBuildList = new ArrayList<>();

    public Ship (PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = true;
        trigger = ".ship";
        name = "Ship";
        description = "Holds Ship Builds";


        shipBuildList.add(new ShipBuild("Anaconda", "MyAnacondaDontWantNone", "https://www.youtube.com/watch?v=woPff-Tpkns"));
        shipBuildList.add(new ShipBuild("BigOlTank", "WaitWhatIsThisDoingHere", "https://www.youtube.com/watch?v=y9K18CGEeiI"));
        shipBuildList.add(new ShipBuild("Bathtub", "ScrubADubDoom", "https://youtu.be/zlptO0axoP4"));
        shipBuildList.add(new ShipBuild("Bathtub", "WhatIsWithBathtubsInSpace", "https://www.youtube.com/watch?v=n3wPBcmSb2U"));
        shipBuildList.add(new ShipBuild("BigOlTank", "WhatIsWithTHeseFuckingTanks", "https://youtu.be/GCsdKHOSRp4"));
        shipBuildList.add(new ShipBuild("Anaconda", "ThankGodANormalEliteShip", "https://youtu.be/I07xDdFMdgw"));
        shipBuildList.add(new ShipBuild("Titan", "Extreme", "https://youtu.be/-J0lT8i17ss?t=4m21s"));
        shipBuildList.add(new ShipBuild("TacoBell", "HurtsMeDeeply", "https://www.youtube.com/watch?v=XapaDWaUUE4"));
        shipBuildList.add(new ShipBuild("PizzaHut", "HurtsMeMore", "https://www.youtube.com/watch?v=8egrP9Q0REQ"));
    }

    protected void execute(String channel, String sender, String login, String hostname, String message) {
        if (message.equalsIgnoreCase(".ship")) {
            SendListOfShipFrames(channel);
            return;
        }

        if (TypeOfShipKnown(message.substring(6))) {
            ListBuildsByType(channel, message.substring(6));
        }

        if (BuildNameKnown(message.substring(6))) {
            GiveURLofBuild(channel, message.substring(6));
        }
    }

    void SendListOfShipFrames(String channel) {
        String output = "";
        List<String> typeList = new ArrayList<>();
        for (ShipBuild build : shipBuildList) {
            boolean found = false;
            for (String s : typeList) {
                if (build.shipType.equalsIgnoreCase(s)) {
                    found = true;
                }
            }

            if (!found) {
                typeList.add(build.shipType);
            }
        }

        for (String type : typeList) {
            output += " " + type;
        }

        sendMessage(channel, "I have ships by these frames:" + output);
    }

    boolean TypeOfShipKnown(String isthisashiptype) {
        for (ShipBuild build : shipBuildList) {
            if (build.shipType.equalsIgnoreCase(isthisashiptype)) {
                return true;
            }
        }
        return false;
    }

    void ListBuildsByType (String channel, String knownlistedtype) {
        String output = "";
        List<String> buildList = new ArrayList<>();
        for (ShipBuild build : shipBuildList) {
            if (build.shipType.equalsIgnoreCase(knownlistedtype)) {
                buildList.add(build.buildName);
            }
        }

        for (String build : buildList) {
            output += " " + build;
        }


        sendMessage(channel, "I have builds for " + knownlistedtype + ":" + output);
    }

    boolean BuildNameKnown(String isThisABuildName) {
        for (ShipBuild build : shipBuildList) {
            if (build.buildName.equalsIgnoreCase(isThisABuildName)) {
                return true;
            }
        }
        return false;
    }

    void GiveURLofBuild(String channel, String knownBuildName) {
        String output = "";
        for (ShipBuild build : shipBuildList) {
            if (build.buildName.equalsIgnoreCase(knownBuildName)) {
                sendMessage(channel, knownBuildName + " : " + build.buildURL);
            }
        }
    }
}
