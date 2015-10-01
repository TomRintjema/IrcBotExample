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
        try {
            witnessLog = new File("WitnessLog.txt");
            if (!witnessLog.exists()) {
                witnessLog.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        StringBuilder text = new StringBuilder();
        try {
            Scanner in = new Scanner(witnessLog);
            while (in.hasNextLine()) {
                text.append(in.nextLine() + "\n");
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] texts = text.toString().split("\n");
        for (int i = 0; i < texts.length; i++) {
            sendMessage(sender, texts[i]);
        }
    }
}