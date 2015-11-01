package commands;

import org.jibble.pircbot.PircBot;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        description = ".orders: Lists your orders. .orders clear: Clears orders to you. .orders <Person> <Message>: Give a person an order. .orders allfrogs: Sends a message to everyone. .orders clearmyallfrogs: Removes messages to allfrogs by you.";
        fillOrdersFromDisk();
    }

    public void execute (String channel, String sender, String login, String hostname, String message) {
        if (message.equalsIgnoreCase(".orders")) {
            DisplayListOfOrders(sender);
        } else if (message.equalsIgnoreCase(".orders clear")) {
            ClearOrders(sender);
        } else if (IsAdmin(sender) && message.equalsIgnoreCase(".orders clearallfrogs")){
            AdminClearAllFrogOrders(sender);
        } else if (IsAdmin(sender) && message.equalsIgnoreCase(".orders adminlistorders")) {
            AdminListAllOrders(sender);
        } else if (message.equalsIgnoreCase(".orders clearmyallfrogs")) {
            ClearMyAllfrogs(sender);
        } else {
            AddOrder(sender, message);
        }
    }

    void ClearMyAllfrogs(String sender) {
        List<Order> orderDelete = new ArrayList<>();
        for (Order o : orderList) {
            if (o.sender.equalsIgnoreCase(sender)) {
                if (o.name.equalsIgnoreCase("allfrogs")) {
                    orderDelete.add(o);
                }
            }
        }

        for (int i = 0; i < orderDelete.size(); i++) {
            orderList.remove(orderList.get(i));
        }

        writeOrdersToDisk();
    }

    public boolean IsAdmin(String sender) {
        List<String> admins = new ArrayList<>();
        admins.add("Forer");
        admins.add("TomR");
        admins.add("Paramemetic");
        admins.add("Kowboat");

        for (String s : admins) {
            if (s.equalsIgnoreCase(sender)) {
                return true;
            }
        }

        return false;
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
        writeOrdersToDisk();
    }

    public void AdminClearAllFrogOrders(String sender) {
        List<Order> orderDelete = new ArrayList<>();
        for (Order o : orderList) {
            if (o.name.equalsIgnoreCase("allfrogs")) {
                orderDelete.add(o);
            }
        }

        sendMessage(sender, orderDelete.size() + " orders were removed.");
        //if that doesn't work then I can try the whole
        for (int i = 0; i < orderDelete.size(); i++) {
            orderList.remove(orderDelete.get(i));
        }

        writeOrdersToDisk();
    }

    public void AdminListAllOrders(String sender) {
        for (Order o : orderList) {
            sendMessage(sender, o.toString());
        }
    }

    public void AddOrder(String sender, String message) {
        String name = "";
        String order = "";
        String newMessage = message.substring(8);

        String[] splitNewMessage = newMessage.split("\\s+");

        name = splitNewMessage[0];
        int nameLength = name.length();
        if (name.length() < newMessage.length()) {
            order = newMessage.substring(nameLength + 1); //+1 for the space
            Order o = new Order(name, sender, order);
            orderList.add(o);
            sendMessage(sender, "Order added for " + name + " from " + sender + " : " + order);
        }
        writeOrdersToDisk();
    }

    public List<String> peopleWithOrders () {
        Set<String> hs = new HashSet<>();
        for (Order o : orderList) {
            hs.add(o.name);
        }
        List<String> output = new ArrayList<>();
        output.addAll(hs);
        return output;
    }

    public int numOfOrders(String sender) {
        int i = 0;
        for (Order o : orderList) {
            if (o.name.equalsIgnoreCase(sender)) {
                i++;
            }
        }
        return i;
    }

    public void writeOrdersToDisk() {
        String fileName = "Orders.txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Order o : orderList) {
                writer.write(o.toWrite());
            }
            writer.close();
        } catch (IOException exc){
            System.out.printf("Oh shit son we had an error writing %s\n", fileName);
        }
    }

    public void fillOrdersFromDisk() {
        String fileName = "Orders.txt";
        orderList.clear();
        try {
            FileReader read = new FileReader(fileName);
            BufferedReader br = new BufferedReader(read);
            String line = br.readLine();
            while (line != null) {
                orderList.add(new Order(line));
                line = br.readLine();
            }
        } catch (IOException exc) {
            System.out.printf("Oh shit son we had an error reading %s\n", fileName);
        }
    }
}
