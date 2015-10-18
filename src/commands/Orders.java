package commands;

import org.jibble.pircbot.PircBot;

public class Orders extends Command {

    public Orders(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = false;
        trigger = ".orders";
        name = "Orders";
        description = "Keep up to date with what's going on.";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        sendMessage(channel, "Go here and do the things: http://inara.cz/wing-board/242/525");
    }
}