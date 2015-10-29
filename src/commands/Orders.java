package commands;

import org.jibble.pircbot.PircBot;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Forer on 10/20/2015.
 */

public class Orders extends Command {
    List<Order> orderList = new ArrayList<>();

    public Orders (PircBot bot) {
        super(bot);

        doesMsgStartWithTrigger = true;
        trigger = ".orders";
        name = "Orders";
        description = "Keeps a log of orders for people.";
    }

    public void execute (String channel, String sender, String login, String hostname, String message) {
        if (message.equalsIgnoreCase(".orders")) {
            DisplayListOfOrders(sender);
        } else if (message.equalsIgnoreCase(".orders clear")) {
            ClearOrders(sender);
        } else {
            AddOrder(sender, message);
        }
    }

    public void DisplayListOfOrders(String sender) {
        //Get list of orders that belong to the requesting person
        List<Order> orderOutput = new ArrayList<>();

        for (Order o : orderList) {
            if (o.name.equalsIgnoreCase("allfrogs")) {
                orderOutput.add(o);
            }
        }

        for (Order o : orderList) {
            if (o.name.equalsIgnoreCase(sender)) {
                orderOutput.add(o);
            }
        }

        for (Order o : orderOutput) {
            sendMessage(sender, o.toString());
        }

        sendMessage(sender, "To clear messages type '.orders clear'");
    }

    public void ClearOrders(String sender) {
        List<Order> orderDelete = new ArrayList<>();
        for (Order o : orderList) {
            if (o.name.equalsIgnoreCase(sender)) {
                if (!o.name.equalsIgnoreCase("allfrogs")) {
                    orderDelete.add(o);
                }
            }
        }

        sendMessage(sender, orderDelete.size() + " orders were removed.");
        //if that doesn't work then I can try the whole
        for (int i = 0; i < orderDelete.size(); i++) {
            orderList.remove(orderDelete.get(i));
        }

    }

    public void AddOrder(String sender, String message) {
        String name = "";
        String order = "";
        String newMessage = message.substring(8);

        String[] splitNewMessage = newMessage.split("\\s+");

        name = splitNewMessage[0];
        int nameLength = name.length();
        order = newMessage.substring(nameLength+1); //+1 for the space
        Order o = new Order(name, sender, order);
        orderList.add(o);
        sendMessage(sender, "Order added for " + name + " from " + sender + " : " + order);
    }
}