package commands;
import java.util.ArrayList;
import java.util.List;
import org.jibble.pircbot.PircBot;
public class Help extends Command {

	public List<Command> commandListForHelp = new ArrayList<Command>();
	
    public Help(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = true;
        trigger = ".help";
        name = "Help";
        description = "Lists commands.";
    }

    public void execute(String channel, String sender, String login, String hostname, String message) {
		
		if (message.equalsIgnoreCase(".help")) {
			//Help list ALL commands
			ListCommands(channel);
			return;
		}
		String commandToHelpWith = message.substring(6);
		ListSpecificCommand(channel, commandToHelpWith);
	}
	
	void ListCommands(String channel) {
		String commands = "Commands installed are :";
			
		for (Command c : commandListForHelp) {
			commands += " " + c.name;
		}
		
		sendMessage(channel, commands);
	}
	
	void ListSpecificCommand(String channel, String command) {
		Command toUse = null;
		for (Command c : commandListForHelp) {
			if (c.name.equalsIgnoreCase(command)) {
				toUse = c;
			}
		}
		
		if (toUse != null) {
			String commandDescription = toUse.description;
			sendMessage(channel, commandDescription);
		}
	}
}