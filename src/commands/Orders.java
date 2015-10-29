package commands;

import org.jibble.pircbot.PircBot;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class Orders extends Command {
	File orderLog;

    public Orders(PircBot bot) {
        super(bot);
		
		try{
            orderLog = new File("OrdersLog.txt");
            if (!orderLog.exists()) {
                orderLog.createNewFile();
            }
        } catch (IOException e) {
                e.printStackTrace();
        }

        doesMsgStartWithTrigger = false;
        trigger = ".orders";
        name = "Orders";
        description = "Keep up to date with what's going on.";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
		try{
				Scanner in = new Scanner(new File(orderLog.getName()));
				String text = "";
				while(in.hasNextLine()) { text += in.nextLine() + "\n"; }
				in.close();
				String[] texts = text.split("\n");
				sendMessage(channel, texts[texts.length - 1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}