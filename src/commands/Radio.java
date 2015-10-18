package commands;

import org.jibble.pircbot.PircBot;

public class Radio extends Command {

    public Radio(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = false;
        trigger = ".radio";
        name = "Radio";
        description = "Listen to some music while you space truck.";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        sendMessage(channel, "Listen to this: https://cp8.shoutcheap.com:2199/start/shazbotsyndicate/");
    }
}