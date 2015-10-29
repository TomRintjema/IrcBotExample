package commands;

import org.jibble.pircbot.PircBot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Deeds extends Command {
    File witnessLog;

    public Deeds(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = false;
        trigger = ".deeds";
        name = "Deeds";
        description = "Displays deeds";

    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        sendMessage(sender, "Deeds is broken for now. If you really want to know ask TomR.");
    }
    
}