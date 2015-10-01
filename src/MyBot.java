import commands.Command;
import commands.Deeds;
import commands.Time;
import commands.Witness;
import commands.Help;
import org.jibble.pircbot.PircBot;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends PircBot {

    List<Command> commandList = new ArrayList<Command>();

    public MyBot() {

        // bot name on IRC
        this.setName("MyBot");

        // list commands
        commandList.add(new Deeds(this));
        commandList.add(new Witness(this));
        commandList.add(new Time(this));
		
		//bleh I don't like doin' this but
		Help help = new Help(this);
		commandList.add(help);
		help.commandListForHelp = commandList;
		
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        for (Command com : commandList) {
            com.evaluate(channel, sender, login, hostname, message);
        }
    }
}
