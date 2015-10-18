package commands;

import org.jibble.pircbot.PircBot;

public class Systems extends Command {

    public Systems(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = false;
        trigger = ".systems";
        name = "Systems";
        description = "Displays systems of note";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        sendMessage(sender, "Home: 63 G. Capricorni");
        sendMessage(sender, "Fitting: Xihe, Nisgayo");
		sendMessage(sender, "RES: IX Chakan");
		sendMessage(sender, "Compromised Nav Beacon: LFT 269, G 69-11");
		sendMessage(sender, "Mineing: Frey");
		sendMessage(sender, "Smuggling: Sothis for Feds, or Fehu for Imperial");
    }
}