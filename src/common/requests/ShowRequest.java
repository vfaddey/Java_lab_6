package common.requests;

public class ShowRequest extends Request {
    private int quantity = -1;

    public ShowRequest(String commandName) {
        super(commandName);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
