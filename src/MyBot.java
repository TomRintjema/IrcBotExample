import commands.*;
import org.jibble.pircbot.PircBot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyBot extends PircBot {

    List<Command> commandList = new ArrayList<Command>();
	Properties prop = new Properties();
	String reg = "";

    public MyBot() {
	
		try {
			FileInputStream fs = new FileInputStream("Setup.sec");
			prop.load(fs);
			fs.close();			
		} catch (IOException e) {
			
		}

        // bot name on IRC
        this.setName("WitnessBot");

        // list commands
        commandList.add(new Deeds(this));
        commandList.add(new Witness(this));
        commandList.add(new Time(this));
		commandList.add(new Systems(this));
		commandList.add(new Rep(this));
		commandList.add(new Radio(this));
		commandList.add(new Orders(this));
		//commandList.add(new NewOrder(this));
		//commandList.add(new Ship(this));
		
		//bleh I don't like doin' this but
		Help help = new Help(this);
		commandList.add(help);
		help.commandListForHelp = commandList;
		
		
		

		
		
		
    }

	public void onJoin(String channel, String sender, String login, String hostname) {
		//Find if user has orders waiting.
		Orders ordersActive = findOrdersCommand(commandList);
		List<String> alertThesePeople = ordersActive.peopleWithOrders();
		for (String a : alertThesePeople) {
			if (sender.equalsIgnoreCase(a)) {
				sendMessage(sender, "You have " + ordersActive.numOfOrders(sender) + " messages. Use .orders in #elite to have them PMed to you");
			}
		}
	}

	Orders findOrdersCommand (List<Command> list) {
		for (Command c : list) {
			if (c instanceof Orders) {
				return (Orders)c;
			}
		}

		return new Orders(this);
	}
	
	public void onConnect() {
		sendMessage("NickServ", "identify " + prop.getProperty("Ident"));
	}

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        for (Command com : commandList) {
            com.evaluate(channel, sender, login, hostname, message);
        }
    }
}
