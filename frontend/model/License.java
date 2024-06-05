package model;

/**
 *
 * @author Thecho
 */
public class License {

    private int id;
    private String creationDate;
    private String expirationDate;
    private boolean expired;
    private int daysToExpiration;

    @Override
    public String toString() {
        String returnable = "";
        returnable += "\nid: " + id + "\n";
        returnable += "creation date: " + creationDate + "\n";
        returnable += "expiration date: " + expirationDate + "\n";
        returnable += "expired: " + expired + "\n";
        returnable += "days to expiration: " + daysToExpiration;
        return returnable;
    }

    public License(
            int id,
            String creationDate,
            String expirationDate,
            boolean expired,
            int daysToExpiration
    ) {
        this.id = id;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.expired = expired;
        this.daysToExpiration = daysToExpiration;
    }

    public int getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    
    public boolean expired() {
        return expired;
    }
    
    public int daysToExpiration() {
        return daysToExpiration;
    }
}
