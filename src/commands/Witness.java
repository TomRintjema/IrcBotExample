package commands;

import org.jibble.pircbot.PircBot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Witness extends Command {

    public Witness(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = true;
        trigger = ".witness ";
        name = "Witness";
        description = "Uhh, it does something?";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        String time = new java.util.Date().toString();
        String witness = time + " <" + sender + "> " + message.substring(8) + "\r\n";

        try {
            FileWriter fileWriter = new FileWriter("WitnessLog.txt", true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(witness);
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random rand = new Random();
        int val = rand.nextInt(10);
        if (val == 9) {
            sendMessage(channel, "Perfect in every way!");
        } else {
            sendMessage(channel, "Mediocre!");
        }
    }
}