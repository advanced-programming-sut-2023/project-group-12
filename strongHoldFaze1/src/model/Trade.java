package model;

public class Trade {
    private String resourceType;

    private int resourceAmount;

    private int price;

    private String message;

    private User sender;

    private User receiver;

    private boolean accept;

    public Trade(String resourceType, int resourceAmount, int price, String message, User sender) {
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.message = message;
        this.sender = sender;
        this.receiver = null;
        this.accept = false;
    }
    public boolean isAccepted () {
        return accept;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
