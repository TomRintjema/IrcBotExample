package commands;

import org.jibble.pircbot.PircBot;

public class Time extends Command {

    public Time(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = false;
        trigger = ".time";
        name = "Time";
        description = "Displays time for an example";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        String time = new java.util.Date().toString();
        sendMessage(sender, "The time is now " + time);
    }
}