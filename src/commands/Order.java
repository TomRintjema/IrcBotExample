package commands;

/**
 * Created by Forer on 10/20/2015.
 */
public class Order {
    String name;   //Person who gets the order.
    String sender; //Person who sent the order.
    String order;  //The thing what the person should do.

    public Order (String name, String sender, String order) {
        this.name = name;
        this.sender = sender;
        this.order = order;
    }

    public Order(String justReadOrder) {
        //This constructor is mainly for taking orders from file.
        this.name = justReadOrder.split("\\s+")[0];
        this.sender = justReadOrder.split("\\s+")[1];
        this.order = justReadOrder.substring(name.length() + sender.length() + 2);
    }

    public String toString() {
        String output = "";
        output += "To : " + name + " From : " + sender + " : " + order;
        return output;
    }

    public String toWrite() {
        String output = "";
        output += name;
        output += " ";
        output += sender;
        output += " ";
        output += order;
        output += "\n";
        return output;
    }


}