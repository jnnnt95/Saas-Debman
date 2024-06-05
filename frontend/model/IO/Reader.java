package model.IO;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import control.MainController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.Client;
import model.Debt;
import model.Deposit;
import model.License;
import model.User;
import model.enums.Request;
import model.Parameter;

/**
 *
 * @author Thecho
 */
public class Reader extends Commons {

    private Reader() {

    }

    private static final String ROOT_DIR;
    private static String SESSION_KEY;
    private static boolean SESSION_KEY_SET;
    
    static {
        ROOT_DIR = "Data";
        SESSION_KEY_SET = false;
    }

    public static void setSessionKey(String sessionKey) {
        if (!SESSION_KEY_SET) {
            SESSION_KEY = sessionKey;
            SESSION_KEY_SET = true;
        }
    }

    public static String getAssociatedUsers() throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        Parameter servicesUserIdParam = setParam("servicesUserId", String.valueOf(MainController.getUser().getServicesUserId()));

        params.add(servicesUserIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getAssociatedUsers, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();

        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return null;
        } else {
            String associatedUsersPrintable = "<html>Información de usuarios asociados a esta licencia:<br><br>";
            JsonArray users;
            users = digestedJson.get("associatedUsers").getAsJsonArray();
            for (JsonElement user : users) {
                JsonObject current = user.getAsJsonObject();
                String name;
                String phone;
                String email;
                int typeInt;
                String type;

                name = current.get("name").getAsString();
                phone = current.get("phone").getAsString();
                email = current.get("email").getAsString();
                typeInt = current.get("type").getAsInt();

                switch (typeInt) {
                    //Services user
                    case 4:
                        type = "Usuario administrador";
                        break;
                    //Services subuser
                    case 5:
                        type = "Subusuario";
                        break;
                    default:
                        type = "N/A";
                        break;
                }

                associatedUsersPrintable += "<strong>" + type + "</strong>: " + name + " | " + phone + " | " + email + "<br>";
            }
            associatedUsersPrintable += "</html>";
            return associatedUsersPrintable;
        }
    }

    public static boolean newSubuserUsernameExists(String username) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter usernameParam = setParam("username", username);

        params.add(usernameParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.verifyUsernameExistence, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }

        return digestedJson.get("usernameExists").getAsBoolean();
    }
    
    public static boolean newPhoneExists(String phone) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter phoneParam = setParam("phone", phone);

        params.add(phoneParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.verifyPhoneExistence, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }

        return digestedJson.get("phoneExists").getAsBoolean();
    }
    
    public static boolean newEmailExists(String email) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter emailParam = setParam("email", email);

        params.add(emailParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.verifyEmailExistence, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }

        return digestedJson.get("emailExists").getAsBoolean();
    }

    public static ArrayList<String[]> getDepositClients(String date) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        Parameter servicesUserIdParam = setParam("servicesUserId", String.valueOf(MainController.getUser().getServicesUserId()));
        Parameter dateParam = setParam("date", date);

        params.add(servicesUserIdParam);
        params.add(dateParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getDepositClients, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return null;
        } else {
            ArrayList<String[]> depositClients;
            depositClients = new ArrayList<>();

            JsonArray rawDepositClients = digestedJson.get("depositClients").getAsJsonArray();
            for (JsonElement rawDepositClient : rawDepositClients) {
                JsonObject current = rawDepositClient.getAsJsonObject();
                String[] data = new String[4];
                data[0] = current.get("id").getAsString();
                data[1] = current.get("name").getAsString();
                data[2] = current.get("nick").getAsString();
                data[3] = current.get("totalDepositOnDate").getAsString();
                depositClients.add(data);
            }
            return depositClients;
        }
    }

    public static void openExportedData() throws IOException {
        Desktop.getDesktop().
                open(new File(ROOT_DIR
                        + "\\printable data.xls"));
    }

    public static List<Client> getClients() throws IOException, ParseException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter userServicesIdParam = setParam("userServicesId", String.valueOf(MainController.getUser().getServicesUserId()));

        params.add(userServicesIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getClients, params);

        JsonArray jsonData = null;
        try {
            jsonData = new JsonParser().parse(jsonResponse).getAsJsonArray();
        } catch (IllegalStateException e) {
            JsonObject jsonError = new JsonParser().parse(jsonResponse).getAsJsonObject();
            showErrorMessage(jsonError);
        }

        return getClientList(jsonData);
    }

    private static List<Client> getClientList(JsonArray jsonData) throws ParseException {
        int id;
        String name;
        String nick;
        String phone;
        String commentary;
        float interestRate;
        float interestRateOnDefault;
        int daysToDefault;
        String creatorName;
        int creatorId;
        boolean isDisabled;

        List<Client> clients;
        clients = new ArrayList<>();

        for (JsonElement clientPlaceholder : jsonData) {
            JsonObject jsonClient = clientPlaceholder.getAsJsonObject();

            id = jsonClient.get("id").getAsInt();
            name = jsonClient.get("name").getAsString();
            nick = jsonClient.get("nick").getAsString();
            phone = jsonClient.get("phone").getAsString();
            commentary = jsonClient.get("commentary").getAsString();
            interestRate = jsonClient.get("interestRate").getAsFloat();
            interestRateOnDefault = jsonClient.get("interestRateOnDefault").getAsFloat();
            daysToDefault = MainController.getUser().getDaysToDefault();
            creatorName = jsonClient.get("creatorName").getAsString();
            creatorId = jsonClient.get("creatorId").getAsInt();
            isDisabled = jsonClient.get("disabledClient").getAsBoolean();

            Client client = new Client(
                    id,
                    name,
                    nick,
                    phone,
                    commentary,
                    interestRate,
                    interestRateOnDefault,
                    daysToDefault,
                    creatorName,
                    creatorId,
                    isDisabled
            );

            clients.add(client);
        }

        return clients;
    }

    public static List<Debt> getClientDebts(Client client) throws IOException, ParseException {
        List<Parameter> params;
        params = new ArrayList<>();
        Parameter clientIdParam = setParam("clientId", String.valueOf(client.getId()));

        params.add(clientIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getClientDebts, params);

        JsonArray jsonData = null;
        try {
            jsonData = new JsonParser().parse(jsonResponse).getAsJsonArray();
        } catch (IllegalStateException e) {
            JsonObject jsonError = new JsonParser().parse(jsonResponse).getAsJsonObject();
            showErrorMessage(jsonError);
        }

        int clientId = client.getId();
        List<Debt> debts;
        debts = new ArrayList<>();

        int id;
        int amount;
        int deposit;
        float baseInterestRate;
        float interestRateOnDefault;
        int daysToDefault;
        String creationDate;
        String paidDate;
        String creatorName;
        int creatorId;
        boolean inDefault;

        float normalInterest;
        float interestOnDefault;
        float payableInterest;
        boolean paid;
        boolean isVoid;

        Debt debt;

        for (JsonElement debtPlaceholder : jsonData) {
            JsonObject debtAsJson = debtPlaceholder.getAsJsonObject();
            daysToDefault = debtAsJson.get("daysToDefault").getAsInt();
            baseInterestRate = debtAsJson.get("interestRate").getAsFloat();
            interestRateOnDefault = debtAsJson.get("interestRateOnDefault").getAsFloat();

            normalInterest = MainController.calculateDebtInterest(debtAsJson.get("amount").getAsInt(), baseInterestRate);
            interestOnDefault = MainController.calculateDebtInterest(debtAsJson.get("amount").getAsInt(), interestRateOnDefault);
            inDefault = false;
            creationDate = debtAsJson.get("creationDate").getAsString();
            if (!debtAsJson.get("paidDate").isJsonNull()) {
                paidDate = debtAsJson.get("paidDate").getAsString();
                if (paidDate.equals("null") || paidDate.equals("")) {
                    paidDate = null;
                }
            } else {
                paidDate = null;
            }
            paid = paidDate != null;
            if (paid) {
                long daysUntilPayment;
                daysUntilPayment = MainController.daysOfDifference(
                        MainController.parseStringToDate(creationDate),
                        MainController.parseStringToDate(paidDate));
                if (daysUntilPayment > daysToDefault) {
                    payableInterest = interestOnDefault;
                } else {
                    payableInterest = normalInterest;
                }
            } else {
                long daysUntilToday;
                daysUntilToday = MainController.daysOfDifference(
                        MainController.parseStringToDate(creationDate),
                        new Date());
                if (daysUntilToday > daysToDefault) {
                    payableInterest = interestOnDefault;
                    inDefault = true;
                } else {
                    payableInterest = normalInterest;
                }
            }

            id = debtAsJson.get("id").getAsInt();
            amount = debtAsJson.get("amount").getAsInt();
            deposit = debtAsJson.get("deposit").getAsInt();
            creatorName = debtAsJson.get("creatorName").getAsString();
            creatorId = debtAsJson.get("creatorId").getAsInt();
            isVoid = debtAsJson.get("isVoid").getAsBoolean();

            debt = new Debt(
                    //Id
                    id,
                    //Client id
                    clientId,
                    //Amount
                    amount,
                    //Deposit
                    deposit,
                    //Interest
                    payableInterest,
                    //Interest rate
                    baseInterestRate,
                    //Interest rate on default
                    interestRateOnDefault,
                    //Days to default
                    daysToDefault,
                    //Debt creation date
                    creationDate,
                    //paid_date, for new: null
                    paidDate,
                    //created by
                    creatorName,
                    //creator id
                    creatorId,
                    //in default
                    inDefault,
                    //void state
                    isVoid
            );
            debts.add(debt);
        }

        return debts;
    }

    public static List<Deposit> getClientDeposits(Client client) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter clientIdParam = setParam("clientId", String.valueOf(client.getId()));

        params.add(clientIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getClientDeposits, params);

        JsonArray jsonData = null;
        try {
            jsonData = new JsonParser().parse(jsonResponse).getAsJsonArray();
        } catch (IllegalStateException e) {
            JsonObject jsonError = new JsonParser().parse(jsonResponse).getAsJsonObject();
            showErrorMessage(jsonError);
        }

        List<Deposit> deposits;
        deposits = new ArrayList<>();

        int id;
        float amount;
        String receptionDate;
        String receiverName;
        int receiverId;
        boolean isVoid;

        for (JsonElement depositPlaceholder : jsonData) {
            JsonObject depositAsJson = depositPlaceholder.getAsJsonObject();

            id = depositAsJson.get("id").getAsInt();
            amount = depositAsJson.get("amount").getAsInt();
            receptionDate = depositAsJson.get("receptionDate").getAsString();
            receiverName = depositAsJson.get("receiverName").getAsString();
            receiverId = depositAsJson.get("receiverId").getAsInt();
            isVoid = depositAsJson.get("isVoid").getAsBoolean();

            Deposit deposit;
            deposit = new Deposit(
                    //Id
                    id,
                    //Amount
                    amount,
                    //Reception date
                    receptionDate,
                    //Receiver name
                    receiverName,
                    //Receiver Id
                    receiverId,
                    //void state
                    isVoid
            );
            deposits.add(deposit);
        }

        return deposits;
    }

    public static int subusersAllowed(int userId) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));

        params.add(userIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getSubusersAllowed, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();

        return digestedJson.get("subusersAllowed").getAsInt();
    }

    public static int subusersCreated(int userId) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));

        params.add(userIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getSubusersCreated, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        return digestedJson.get("subusersCreated").getAsInt();
    }

    /**
     * Returns an object with the content that defines the current user/subuser
     * of the application; ends execution if current user cannot be
     * authenticated.
     *
     * @param username
     * @param password
     * @return the user or finishes the current execution if no user can be
     * authenticated
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static User authenticate(String username, char[] password) throws NoSuchAlgorithmException, IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        char[] passCopy = new char[password.length];
        for (int i = 0; i < password.length; i++) {
            passCopy[i] = password[i];
        }
        MainController.flush(password);
        password = null;
        Parameter usernameParam = setParam("username", username);
        Parameter passwordParam = setParam("password", MainController.encryptPassword(passCopy));
        Parameter sessionKeyParam = setParam("sessionKey", MainController.getSessionKey());
        MainController.flush(passCopy);
        passCopy = null;

        params.add(usernameParam);
        params.add(passwordParam);
        params.add(sessionKeyParam);

        String jsonResponse;
        jsonResponse = apply(Request.login, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return null;
        } else {
            return getUserInstance(digestedJson);
        }
    }

    private static User getUserInstance(JsonObject jsonData) {
        int id;
        int servicesUserId;
        String username;
        String phone;
        String type;
        String email;
        String dateCreated;
        String names;
        String surnames;
        float preferredInterestRate;
        float preferredInterestRateOnDefault;
        int daysToDefault;
        License license;
        List<User> subusers;

        id = jsonData.get("id").getAsInt();
        servicesUserId = jsonData.get("servicesUserId").getAsInt();
        username = jsonData.get("username").getAsString();
        phone = jsonData.get("phone").getAsString();
        type = jsonData.get("type").getAsString();
        email = jsonData.get("email").getAsString();
        dateCreated = jsonData.get("creationDate").getAsString();
        names = jsonData.get("names").getAsString();
        surnames = jsonData.get("surnames").getAsString();
        preferredInterestRate = jsonData.get("preferredInterestRate").getAsFloat();
        preferredInterestRateOnDefault = jsonData.get("preferredInterestRateOnDefault").getAsFloat();
        daysToDefault = jsonData.get("daysToDefault").getAsInt();
        license = getLicenseInstance(jsonData.get("license").getAsJsonObject());
        if (type.equals("services_user")) {
            subusers = getSubuserList(jsonData.get("subusers").getAsJsonArray());
        } else {
            subusers = null;
        }

        User user;
        user = new User(
                id,
                servicesUserId,
                username,
                phone,
                type,
                email,
                dateCreated,
                names,
                surnames,
                preferredInterestRate,
                preferredInterestRateOnDefault,
                daysToDefault,
                license,
                subusers
        );

        return user;
    }

    private static List<User> getSubuserList(JsonArray jsonData) {
        List<User> subusers;
        subusers = new ArrayList<>();

        int id;
        int servicesUserId;
        String username;
        String phone;
        String type;
        String email;
        String dateCreated;
        String names;
        String surnames;
        float preferredInterestRate;
        float preferredInterestRateOnDefault;
        int daysToDefault;
        License nullLicense;
        List<User> nullSubusers;

        for (JsonElement subuserPlaceholder : jsonData) {
            JsonObject subuser = subuserPlaceholder.getAsJsonObject();

            id = subuser.get("id").getAsInt();
            servicesUserId = subuser.get("servicesUserId").getAsInt();
            username = subuser.get("username").getAsString();
            phone = subuser.get("phone").getAsString();
            type = subuser.get("type").getAsString();
            email = subuser.get("email").getAsString();
            dateCreated = subuser.get("creationDate").getAsString();
            names = subuser.get("names").getAsString();
            surnames = subuser.get("surnames").getAsString();
            preferredInterestRate = subuser.get("preferredInterestRate").getAsFloat();
            preferredInterestRateOnDefault = subuser.get("preferredInterestRateOnDefault").getAsFloat();
            daysToDefault = subuser.get("daysToDefault").getAsInt();
            nullLicense = null;
            nullSubusers = null;

            subusers.add(new User(
                    id,
                    servicesUserId,
                    username,
                    phone,
                    type,
                    email,
                    dateCreated,
                    names,
                    surnames,
                    preferredInterestRate,
                    preferredInterestRateOnDefault,
                    daysToDefault,
                    nullLicense,
                    nullSubusers
            ));

            subusers.get(subusers.size() - 1).setDisabledSubuser(subuser.get("disabledSubuser").getAsBoolean());
        }

        return subusers;
    }

    private static License getLicenseInstance(JsonObject jsonData) {
        int id;
        String creationDate;
        String expirationDate;
        boolean expired;
        int daysToExpiration;

        id = jsonData.get("id").getAsInt();
        creationDate = jsonData.get("creationDate").getAsString();
        expirationDate = jsonData.get("expirationDate").getAsString();
        expired = jsonData.get("expiredLicense").getAsBoolean();
        daysToExpiration = jsonData.get("daysToExpiration").getAsInt();

        return new License(
                id,
                creationDate,
                expirationDate,
                expired,
                daysToExpiration
        );
    }

    public static int cashInRegister(String date) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        Parameter servicesUserIdParam = setParam("servicesUserId", String.valueOf(MainController.getUser().getServicesUserId()));
        Parameter dateParam = setParam("date", date);

        params.add(servicesUserIdParam);
        params.add(dateParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getCashInRegister, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return -1;
        } else {
            if (!digestedJson.get("cashInRegister").isJsonNull()) {
                return digestedJson.get("cashInRegister").getAsInt();
            } else {
                return 0;
            }
        }
    }

    public static String getUserInfo(int userId) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        Parameter userIdParam = setParam("userId", String.valueOf(userId));

        params.add(userIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getUserInfo, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return null;
        } else {

            String names = digestedJson.get("names").getAsString();
            String surnames = digestedJson.get("surnames").getAsString();
            String username = digestedJson.get("username").getAsString();
            String phone = digestedJson.get("phone").getAsString();
            String email = digestedJson.get("email").getAsString();
            String creationDate = digestedJson.get("creationDate").getAsString();
            String userType = digestedJson.get("userType").getAsString();

            String userInfo;

            switch (userType) {
                case "services_user":
                    userType = "Usuario administrador";
                    break;
                case "services_subuser":
                    userType = "Subusuario";
                    break;
                default:
                    userType = "No especificado";
                    break;
            }

            userInfo = "<html>"
                    + "<strong><i>Los siguientes son los datos asociados a su cuenta:</i></strong><br><br>"
                    + "<i><strong><span style='color:gray'>Nombres:</span></strong> " + names + "<i><br>"
                    + "<i><strong><span style='color:gray'>Apellidos:</span></strong> " + surnames + "<i><br>"
                    + "<i><strong><span style='color:gray'>Nombre de usuario:</span></strong> " + username + "<i><br>"
                    + "<i><strong><span style='color:gray'>Teléfono:</span></strong> " + phone + "<i><br>"
                    + "<i><strong><span style='color:gray'>Email:</span></strong> " + email + "<i><br>"
                    + "<i><strong><span style='color:gray'>Fecha de creación de usuario:</span></strong> " + MainController.parseToViewDate(creationDate) + "<i><br>"
                    + "<i><strong><span style='color:gray'>Tipo de usuario:</span></strong> " + userType + "<i><br>"
                    + "</html>";

            return userInfo;
        }
    }

    public static boolean passwordCorrect(char[] password) throws NoSuchAlgorithmException, IOException {

        List<Parameter> params;
        params = new ArrayList<>();

        Parameter passwordParam = setParam("password", MainController.encryptPassword(password));
        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));

        params.add(passwordParam);
        params.add(userIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.verifyPassword, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }

        return digestedJson.get("passwordVerified").getAsBoolean();
    }

    public static int getDebtLinkedDeposits(Debt debt) throws IOException {
        int debtId = debt.getId();

        List<Parameter> params;
        params = new ArrayList<>();
        Parameter debtIdParam = setParam("debtId", String.valueOf(debtId));

        params.add(debtIdParam);

        addSessionData(params);

        String jsonResponse;
        jsonResponse = apply(Request.getDebtTotalLinkedDeposits, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return -1;
        } else {
            if (!digestedJson.get("totalLinkedDeposits").isJsonNull()) {
                return digestedJson.get("totalLinkedDeposits").getAsInt();
            } else {
                return 0;
            }
        }
    }

    public static String getRequestToken() throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        
        String token = null;
        JsonObject digestedJson = null;
        
        do {
            token = MainController.generateRandomKey(30);

            Parameter tokenParam = setParam("token", token);

            params.add(tokenParam);

            String jsonResponse;
            jsonResponse = apply(Request.verifyToken, params);
            digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        } while (!digestedJson.get("verifiedToken").getAsBoolean());

        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return null;
        }
        else {
            return token;
        }
    }
    
    public static boolean verifyUpdated(String key) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        Parameter keyParam = setParam("key", key);

        params.add(keyParam);

        String jsonResponse;
        jsonResponse = apply(Request.verifyUpdated, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return false;
        } else {
            if (!digestedJson.get("verified").isJsonNull()) {
                return digestedJson.get("verified").getAsBoolean();
            } else {
                return false;
            }
        }
    }
}
