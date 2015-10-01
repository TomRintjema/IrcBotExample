package commands;

import commands.Command;

import java.util.*;
import java.io.*;

public class Deeds extends Command {
    File witnessLog;

    public Deeds() {
        startorall = false;
        trigger = ".deeds";
        name = "commands.Deeds";
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

    public String Execute(String channel, String sender, String login, String hostname, String message) {
        try {
            Scanner in = new Scanner(new File(witnessLog.getName()));
            String text = "";
            while (in.hasNextLine()) {
                text += in.nextLine() + "\n";
            }
            in.close();
            String[] texts = text.split("\n");
            for (int i = 0; i < texts.length; i++) {
                sendMessage("#saelfdev", texts[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sender + ": commands.Deeds sent";
    }
}