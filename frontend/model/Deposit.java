package model;

/**
 *
 * @author Thecho
 */
public class Deposit {
    private final int id;
    private final float amount;
    private final String receptionDate;
    private final String receiverName;
    private final int receiverId;
    private boolean isVoid;
    
    public Deposit(
            int id,
            float amount,
            String receptionDate,
            String receiverName,
            int receiverId,
            boolean isVoid
    ) {
        this.id = id;
        this.amount = amount;
        this.receptionDate = receptionDate;
        this.receiverName = receiverName;
        this.receiverId = receiverId;
        this.isVoid = isVoid;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public int getReceiverId() {
        return receiverId;
    }
    
    public boolean isVoid() {
        return isVoid;
    }
    
    public void voidDeposit() {
        this.isVoid = true;
    }
}
