import commands.*;
import commands.Rep;
import org.jibble.pircbot.PircBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
		//commandList.add(new Ship(this));
		
		//bleh I don't like doin' this but
		Help help = new Help(this);
		commandList.add(help);
		help.commandListForHelp = commandList;
		
		
		

		
		
		
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
