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

    public String toString() {
        String output = "";
        output += "To : " + name + " From : " + sender + " : " + order;
        return output;
    }
}