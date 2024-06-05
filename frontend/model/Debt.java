package model;

/**
 *
 * @author admin
 */
public class Debt {
    private final int id;
    private final int clientId;
    private final int amount;
    private int deposit;
    private float interest;
    private float interestRate;
    private float interestRateOnDefault;
    private int daysToDefault;
    private float totalDebt;
    private final String creationDate;
    private String paidDate;
    private boolean paid;
    private final String createdBy;
    private final int creatorId;
    private boolean inDefault;
    private boolean isVoid;
    
    public Debt(int id,
            int clientId,
            int amount,
            int deposit,
            float interest,
            float interestRate,
            float interestRateOnDefault,
            int daysToDafault,
            String creationDate,
            String paidDate,
            String createdBy,
            int creatorId,
            boolean inDefault,
            boolean isVoid
    ) {
        this.id = id;
        this.clientId = clientId;
        this.amount = amount;
        this.deposit = deposit;
        this.interest = interest;
        this.interestRate = interestRate;
        this.interestRateOnDefault = interestRateOnDefault;
        this.daysToDefault = daysToDafault;
        this.totalDebt = 
                (amount + interest) - deposit;
        paid = totalDebt <= 0;
        this.creationDate = creationDate;
        this.paidDate = paidDate;
        this.createdBy = createdBy;
        this.creatorId = creatorId;
        this.inDefault = inDefault;
        this.isVoid = isVoid;
    }
    
    //Getters

    public String getCreatedBy() {
        return createdBy;
    }

    public int getCreatorId() {
        return creatorId;
    }
        
    public int getClientId() {
        return clientId;
    }

    public int getAmount() {
        return amount;
    }

    public String getCreationDate() {
        return creationDate;
    }
    
    public String getPaidDate() {
        return paidDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getDeposit() {
        return deposit;
    }

    public float getTotalDebt() {
        return totalDebt;
    }
    
    public int getId() {
        return id;
    }

    public float getInterest() {
        return interest;
    }
    
    public float getAmountPlusInterest() {
        return getAmount() + getInterest();
    }
    
    public boolean isInDefault() {
        return inDefault;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public float getInterestRateOnDefault() {
        return interestRateOnDefault;
    }

    public int getDaysToDefault() {
        return daysToDefault;
    }
    
    //Setters
    
    public void updateDebt(float deposit, String updateDate) {
        this.deposit += deposit;
        this.totalDebt =
                (this.amount + this.interest) - this.deposit;
        this.paid =
                this.totalDebt <= 0;
        
        if(this.paid) {
            this.paidDate = updateDate.trim();
            inDefault = false;
        }
        else {
            this.paidDate = null;
        }
    }
    
    public boolean isVoid() {
        return isVoid;
    }
    
    public void voidDebt() {
        this.isVoid = true;
    }
}
