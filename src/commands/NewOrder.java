package commands;

import org.jibble.pircbot.PircBot;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class NewOrder extends Command {

    public NewOrder(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = true;
        trigger = ".NewOrder ";
        name = "NewOrder";
        description = "Sets a new order";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
        String time = new java.util.Date().toString();
        String order = time + " <" + sender + "> " + message.substring(8) + "\r\n";
		if (sender == "Paramemetic" ) {
            try {
                FileWriter fileWriter = new FileWriter("OrdersLog.txt", true);
                BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                bufferWriter.write(order);
                bufferWriter.close();
            } catch (IOException e) {
            e.printStackTrace();
		    }
		    sendMessage(channel, "Understood!");
		}
    }
}

