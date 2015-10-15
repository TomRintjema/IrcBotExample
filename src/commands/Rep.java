package commands;

import org.jibble.pircbot.PircBot;

public class Rep extends Command {

    public Rep(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = false;
        trigger = ".rep";
        name = "Rep";
        description = "Displays systems to grind rep for rank progression";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        sendMessage(sender, "Tun for Feds and Tamar for Empire. Do the donate missions.");
    }
}