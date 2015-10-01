package commands;
import java.util.ArrayList;
import java.util.List;
import org.jibble.pircbot.PircBot;
public class Help extends Command {

	public List<Command> commandListForHelp = new ArrayList<Command>();
	
    public Help(PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = false;
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
		
	}
	
	void ListCommands(String channel) {
		String commands = "Commands installed are :";
			
		for (Command c : commandListForHelp) {
			commands += " " + c.name;
		}
		
		sendMessage(channel, commands);
	}
}