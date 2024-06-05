package model;

import java.util.List;
import model.enums.UserType;

public class User {
    
    private final int id;
    private final int servicesUserId;
    private final String username;
    private String phone;
    private final UserType type;
    private String email;
    private final String creationDate;
    private String names;
    private String surnames;
    private float preferredInterestRate;
    private float preferredInterestRateOnDefault;
    private int daysToDefault;
    private List<User> subusers;
    private boolean disabledSubuser;
    private final License license;
    
    public User(
            int id,
            int servicesUserId,
            String username,
            String phone,
            String type,
            String email,
            String dateCreated,
            String names,
            String surnames,
            float preferredInterestRate,
            float preferredInterestRateOnDefault,
            int daysToDefault,
            License license,
            List<User> subusers
    ) {
        this.id = id;
        this.servicesUserId = servicesUserId;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.creationDate = dateCreated;
        switch(type) {
            case "services_user":
                this.type = UserType.servicesUser;
                break;
            case "services_subuser":
                this.type = UserType.servicesSubuser;
                break;
            default:
                this.type = null;
                break;
        }
        this.names = names;
        this.surnames = surnames;
        this.preferredInterestRate = preferredInterestRate;
        this.preferredInterestRateOnDefault = preferredInterestRateOnDefault;
        this.daysToDefault = daysToDefault;
        this.license = license;
        this.subusers = subusers;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public UserType getType() {
        return type;
    }

    public String getNames() {
        return names;
    }

    public String getSurnames() {
        return surnames;
    }
    
    public String getName() {
        return names + " " + surnames;
    }
    
    public String getEmail() {
        return email;
    }

    public float getPreferredInterestRate() {
        return preferredInterestRate;
    }

    public float getPreferredInterestRateOnDefault() {
        return preferredInterestRateOnDefault;
    }

    public int getDaysToDefault() {
        return daysToDefault;
    }

    public License getLicense() {
        return license;
    }

    public int getServicesUserId() {
        return servicesUserId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setPreferredInterestRate(float preferredInterestRate) {
        this.preferredInterestRate = preferredInterestRate;
    }

    public void setPreferredInterestRateOnDefault(float preferredInterestRateOnDefault) {
        this.preferredInterestRateOnDefault = preferredInterestRateOnDefault;
    }

    public void setDaysToDefault(int daysToDefault) {
        this.daysToDefault = daysToDefault;
    }
    
    public void setDisabledSubuser(boolean isDisabled) {
        this.disabledSubuser = isDisabled;
    }
    
    public boolean isDisabledSubuser() {
        return disabledSubuser;
    }

    public List<User> getSubusers() {
        return subusers;
    }
    
    public void addSubuser(User newSubuser) {
        this.subusers.add(newSubuser);
    }
}
