package model;

import model.Property.Property;

public class Trade {
    private final Property property;
    private final int resourceAmount;
    private final int price;
    private final String senderMessage;
    private final Kingdom sender;
    private final Kingdom receiver;

    private String receiverMessage;

    private boolean accept;

    private static Trade getTradeById(String id) {
        return null;
    }

    public Trade(Property property, int resourceAmount, int price, String message, Kingdom sender, Kingdom receiver) {
        this.property = property;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.senderMessage = message;
        this.sender = sender;
        this.receiver = receiver;
        this.accept = false;
        receiverMessage = "";
    }

    public String isAccepted() {
        if (accept)
            return "true";
        return "false";
    }

    public Property getProperty() {
        return property;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public Kingdom getSender() {
        return sender;
    }

    public Kingdom getReceiver() {
        return receiver;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void doTrade(String receiverMessage) {
        property.addValue(-1 * this.resourceAmount);
        receiver.addToProperty(property);
        property.addValue(2 * resourceAmount);
        sender.addToProperty(property);
        sender.addGold(-1 * price);
        receiver.addGold(price);
        sender.getTrades().remove(this);
        receiver.getTrades().remove(this);
        sender.setTradesHistory(this);
        receiver.setTradesHistory(this);
        this.accept = true;
        this.receiverMessage = receiverMessage;
    }
}
