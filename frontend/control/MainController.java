package control;

import java.awt.Dimension;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Client;
import model.IO.Reader;
import model.Parameter;
import view.MainViews.FullSizeMainView;
import view.pop_up_view.OmachiView;
import view.MainViews.PopUpMainView;
import model.User;
import model.enums.OperationCode;

public class MainController {

    private static FullSizeMainView fullSizeViewport;
    private static PopUpMainView popUpSizeViewport;
    private static OmachiView loadingView;

    private static Dimension fullSizeDimension;
    private static Dimension popUpSizeDimension;

    private static String sessionKey;

    private static LogInController login;
    private static QueryClientController queryClient;
    private static ClientInfoController clientInfo;
    private static AddClientController addClient;
    private static ModifyClientController modifyClient;
    private static DetailedHistoryController detailedHistory;
    private static PerformDepositController performDeposit;
    private static AddDebtController addDebt;
    private static DepositClientsOnDateController depositClientsOnDate;
    private static ModifyUserController modifyUser;
    private static ModifyGeneralSettingsController modifyGeneralSettings;
    private static ModifyPasswordController modifyPassword;
    private static AddSubuserController addSubuser;
    private static SetSubuserAbilityController setSubuserAbility;

    private static User user;
    private static List<Client> clients;

    private static DecimalFormat amountFormater;
    private static SimpleDateFormat dateFormater;
    private static Calendar calendar;
    
    private static JFrame genericFrame;

    public static void start()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        setSessionKey();

        fullSizeViewport = new FullSizeMainView(sessionKey);
        popUpSizeViewport = new PopUpMainView(sessionKey);

        fullSizeViewport.setPopUpSizeViewport(popUpSizeViewport);
        popUpSizeViewport.setFullSizeViewport(fullSizeViewport);

        loadingView = new OmachiView();

        fullSizeDimension = new Dimension(
                fullSizeViewport.container.getSize().width,
                fullSizeViewport.container.getSize().height);
        popUpSizeDimension = new Dimension(
                popUpSizeViewport.container.getSize().width,
                popUpSizeViewport.container.getSize().height);

        loadingView.mainContainer.setSize(popUpSizeDimension);
        startLoading(sessionKey);

        amountFormater = new DecimalFormat("###,###.##");
        dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        calendar = Calendar.getInstance();

        login = new LogInController(sessionKey);
        login.getView().mainContainer.setSize(popUpSizeDimension);
        queryClient = new QueryClientController(sessionKey);
        clientInfo = new ClientInfoController(sessionKey);
        addClient = new AddClientController(sessionKey);
        modifyClient = new ModifyClientController(sessionKey);
        detailedHistory = new DetailedHistoryController(sessionKey);
        performDeposit = new PerformDepositController(sessionKey);
        addDebt = new AddDebtController(sessionKey);
        depositClientsOnDate = new DepositClientsOnDateController(sessionKey);
        modifyUser = new ModifyUserController(sessionKey);
        modifyGeneralSettings = new ModifyGeneralSettingsController(sessionKey);
        modifyPassword = new ModifyPasswordController(sessionKey);
        addSubuser = new AddSubuserController(sessionKey);
        setSubuserAbility = new SetSubuserAbilityController(sessionKey);

        addClient.getView().mainContainer.setSize(popUpSizeDimension);
        modifyClient.getView().mainContainer.setSize(popUpSizeDimension);
        detailedHistory.getView().mainContainer.setSize(fullSizeDimension);
        performDeposit.getView().mainContainer.setSize(popUpSizeDimension);
        addDebt.getView().mainContainer.setSize(popUpSizeDimension);
        depositClientsOnDate.getView().mainContainer.setSize(fullSizeDimension);
        queryClient.getView().mainContainer.setSize(fullSizeDimension);
        clientInfo.getView().mainContainer.setSize(fullSizeDimension);
        modifyUser.getView().mainContainer.setSize(popUpSizeDimension);
        modifyGeneralSettings.getView().mainContainer.setSize(popUpSizeDimension);
        modifyPassword.getView().mainContainer.setSize(popUpSizeDimension);
        addSubuser.getView().mainContainer.setSize(popUpSizeDimension);
        setSubuserAbility.getView().mainContainer.setSize(popUpSizeDimension);

        login();
    }
    
    public static String generateRandomKey(int lenght) {
        char[] keyOptions = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            '|', '!', '#', '%', '(', ')', '~', 
            '*', '/', '-', '@'
        };
        String sessionKeyPlaceholder;
        sessionKeyPlaceholder = "";

        Random randomizer;
        randomizer = new Random();

        char currentCharacter;
        
        for(int i = 0; i < 30; i++) {
            currentCharacter = keyOptions[randomizer.nextInt(keyOptions.length)];
            sessionKeyPlaceholder += String.valueOf(currentCharacter);
        }

        return sessionKeyPlaceholder;
    }

    private static void setSessionKey() {
        sessionKey = generateRandomKey(30);
    }

    public static void authenticate(String sessionKeyToVerify) {
        if (!sessionKeyToVerify.equals(sessionKey)) {
            alert("Error fatal", "El programa se cerrará", "Aceptar");
            System.exit(0);
        }
    }

    public static void executeOperation(OperationCode code,
            String sessionKeyToVerify)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException,
            InterruptedException {
        authenticate(sessionKeyToVerify);
        switch (code) {
            //Launching application after successful login
            case launchApplication:
                launch();
                break;
            //Updating data after client modification
            case updateQueryClientData:
                queryClient.update();
                break;
            case cancelOperation:
                cancelOperation();
                break;
            case completeOperation:
                completeOperation();
                break;
        }
    }

    private static void cancelOperation() {
        popUpSizeViewport.setEnabled(true);
        popUpSizeViewport.setVisible(false);
        fullSizeViewport.setEnabled(true);
        fullSizeViewport.setVisible(true);
    }

    private static void completeOperation() {
        popUpSizeViewport.setEnabled(true);
        popUpSizeViewport.setVisible(false);
        fullSizeViewport.setEnabled(true);
        fullSizeViewport.setVisible(true);
        try {
            queryClient.update();
        } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }
    }

    private static void launch()
            throws InterruptedException, IOException, ClassNotFoundException, SQLException {
        Reader.setSessionKey(sessionKey);
        popUpSizeViewport.setVisible(false);
        fullSizeViewport.setVisible(true);

        user = login.getUser();

        queryClient.loginUpdate();
        clientInfo.loginUpdate();
        modifyUser.loginUpdate();
        modifyGeneralSettings.loginUpdate();

        popUpSizeViewport.updateView();
        fullSizeViewport.updateView();

        switch (user.getType()) {
            case servicesUser:
                addSubuser.loginUpdate();
                fullSizeViewport.prepareViewForAdministrator();
                break;
            case servicesSubuser:
            default:
                fullSizeViewport.prepareViewForNormalUser();
                break;
        }

        try {
            changeToQueryClientMode(sessionKey);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }

    }

    public static User getUser() {
        return user;
    }

    public static Dimension getFullSizeDimension() {
        return fullSizeDimension;
    }

    public static Dimension getPopUpSizeDimension() {
        return popUpSizeDimension;
    }

    // -------------------- Change-to-methods
    // -------------------- Full Size View
    public static void changeToQueryClientMode(String sessionKey)
            throws InterruptedException,
            IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        authenticate(sessionKey);
        queryClient.update();
        fullSizeViewport.changeToQueryClientMode(queryClient.getView());
    }

    public static void changeToClientInfoMode(Client currentClient, String sessionKey) throws ParseException, IOException {
        authenticate(sessionKey);
        currentClient.sortDebts();
        clientInfo.setViewData(currentClient);
        fullSizeViewport.changeToClientInfoMode(clientInfo.getView());
    }

    public static void changeToDetailedHistoryMode(
            Client currentClient,
            String sessionKey)
            throws ParseException, IOException {
        detailedHistory.setViewData(currentClient);
        fullSizeViewport.changeToDetailedHistoryMode(detailedHistory.getView());
    }

    public static void changeToDepositClientsOnDateMode(String sessionKey)
            throws ParseException, ClassNotFoundException, SQLException, IOException {
        authenticate(sessionKey);
        depositClientsOnDate.setClients(getClients());
        depositClientsOnDate.setViewData();
        int cashInRegister = Reader.cashInRegister(MainController.getTodayDateToString());
        if(cashInRegister < 0) {
            cashInRegister = 0;
        }
        depositClientsOnDate.setCashInRegister(MainController.formatAmount(cashInRegister));
        fullSizeViewport.changeToDepositClientsOnDateMode(depositClientsOnDate.
                getView());
    }

    // -------------------- Pop Up Size View
    public static void changeToAddClientMode(String sessionKey) {
        try {
            addClient.setReady();
        } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        popUpSizeViewport.changeToCreateClientMode(addClient.getView());
    }

    public static void changeToModifyPersonalInfoMode(String sessionKey) {
        modifyUser.setUserData();
        popUpSizeViewport.changeToModifyPersonalInfoMode(modifyUser.getView());
    }

    public static void changeToModifyPasswordMode(String sessionKey) {
        modifyPassword.setReady();
        popUpSizeViewport.changeToModifyPasswordMode(modifyPassword.getView());
    }

    public static void changeToAddSubuserMode(String sessionKey) throws IOException, ClassNotFoundException, SQLException {
        addSubuser.update();
        if (addSubuser.canCreateSubuser()) {
            notify("Sobre la creación de subusuarios", "La creación de subusuarios es limitada.\n\nLe quedan " + addSubuser.getTotalLeftSubusersToCreate() + " subusuarios permitidos, no olvide que puede aumentar esta cantidad. Comuníquese con su Vendedor Debman.", "Aceptar");
            addSubuser.setReady();
            popUpSizeViewport.changeToAddSubuserMode(addSubuser.getView());
        } else {
            alert("Límite de subusarios alcanzado", "La creación de subusuarios es limitada.\n\nEl número máximo de subusuarios que puede crear ha sido alcanzado, no olvide que puede aumentar la cantidad de subusuarios permitidos. Comuníquese con su Vendedor Debman.", "Aceptar");
        }
    }

    public static void changeToModifyGeneralSettingsMode() {
        modifyGeneralSettings.setReady();
        popUpSizeViewport.changeToModifyGeneralSettingsMode(modifyGeneralSettings.getView());
    }

    public static void changeToDisableSubuserMode() throws IOException, ParseException, ClassNotFoundException, SQLException {
        setSubuserAbility.update();
        popUpSizeViewport.changeToDisableSubuserMode(setSubuserAbility.getView());
    }

    public static void changeToModifyClientMode(String sessionKey, Client currentClient) {
        authenticate(sessionKey);
        modifyClient.setReady(currentClient);
        popUpSizeViewport.changeToModifyClientMode(modifyClient.getView());
    }

    public static void changeToPerformDepositMode(Client currentClient,
            String sessionKey) throws IOException, ParseException {
        authenticate(sessionKey);
        performDeposit.setReady(currentClient);
        popUpSizeViewport.changeToPerformDepositMode(performDeposit.getView());
    }

    public static void changeToAddDebtMode(Client currentClient,
            String sessionKey) throws IOException, ParseException {
        authenticate(sessionKey);
        addDebt.setReady(currentClient);
        popUpSizeViewport.changeToAddDebtMode(addDebt.getView());
    }

    public static void startLoading(String sessionKey) {
        authenticate(sessionKey);
        popUpSizeViewport.startLoading(loadingView);
    }

    private static void login() {
        popUpSizeViewport.login(login.getView());
    }

    public static String formatAmount(int amount) {
        return amountFormater.format(amount);
    }

    public static String formatAmount(float amount) {
        return amountFormater.format(amount);
    }

    public static String getMonthName(String month) {
        if (month.equals("01")) {
            return "Ene";
        }
        if (month.equals("02")) {
            return "Feb";
        }
        if (month.equals("03")) {
            return "Mar";
        }
        if (month.equals("04")) {
            return "Abr";
        }
        if (month.equals("05")) {
            return "May";
        }
        if (month.equals("06")) {
            return "Jun";
        }
        if (month.equals("07")) {
            return "Jul";
        }
        if (month.equals("08")) {
            return "Ago";
        }
        if (month.equals("09")) {
            return "Sep";
        }
        if (month.equals("10")) {
            return "Oct";
        }
        if (month.equals("11")) {
            return "Nov";
        }
        if (month.equals("12")) {
            return "Dic";
        }
        return null;
    }

    public static boolean isNumberADigit(String s) {
        //returns true if argument s is a number
        if (s.equals("0")) {
            return true;
        }
        if (s.equals("1")) {
            return true;
        }
        if (s.equals("2")) {
            return true;
        }
        if (s.equals("3")) {
            return true;
        }
        if (s.equals("4")) {
            return true;
        }
        if (s.equals("5")) {
            return true;
        }
        if (s.equals("6")) {
            return true;
        }
        if (s.equals("7")) {
            return true;
        }
        if (s.equals("8")) {
            return true;
        }
        if (s.equals("9")) {
            return true;
        }

        //returns false if argument s is not a number
        return false;
    }

    public static String getTodayDateToString() {
        return dateFormater.format(new Date());
    }

    public static String formatDateToString(Date date) {
        return dateFormater.format(date);
    }

    public static Date parseStringToDate(String date) throws ParseException {
        return dateFormater.parse(date);
    }

    public static String parseDateToString(Date date) throws ParseException {
        return dateFormater.format(date);
    }

    public static boolean validInteger(String number) {
        number = eliminateLeadingZeros(number);

        String integerRegex;
        integerRegex = "\\A[0-9]+\\Z";

        return Pattern.matches(integerRegex, number);
    }

    public static boolean validFloatNumber(String number) {
        number = eliminateLeadingZeros(number);

        String floatRegex;
        floatRegex = "^[-+]?\\d*\\.?\\d*$";

        return Pattern.matches(floatRegex, number);
    }
    
    public static boolean validEmail(String email) {
        String emailRegex;
        emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        return Pattern.matches(emailRegex, email);
    }
            
    public static boolean validUsername(String username) {
        String usernameRegex;
        usernameRegex = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

        return Pattern.matches(usernameRegex, username);
    } 

    public static boolean validInterestRate(String number) {
        float floatNumber;
        try {
            floatNumber = Float.parseFloat(number);
        }catch(NumberFormatException e) {
            return false;
        }
        return floatNumber >= 0;
    }

    public static String eliminateLeadingZeros(String number) {
        if(number.equals("0")) {
            return number;
        }
        else {
            return number.replaceFirst("^0+", "");
        }
    }

    public static float calculateDebtInterest(int amount, float interestRate) {
        return (interestRate / 100) * amount;
    }

    public static int roundAmount(float amount) {
        int roundedAmount;
        roundedAmount = (int) Math.ceil(amount);
        while (roundedAmount % 50 != 0) {
            roundedAmount++;
        }
        return roundedAmount;
    }

    public static int roundAmount(int amount) {
        while (amount % 50 != 0) {
            amount++;
        }
        return amount;
    }

    public static long daysOfDifference(Date oldDate, Date newDate) {
        return ChronoUnit.DAYS.between(oldDate.toInstant(), newDate.toInstant());
    }

    public static boolean validAmount(float amount) {
        return amount % 50 == 0;
    }

    public static boolean validAmount(int amount) {
        return amount % 50 == 0;
    }

    public static void updateMainFrame() {
        fullSizeViewport.setUserName(user.getName());
    }

    public static String encryptPassword(char[] password) throws NoSuchAlgorithmException {
        return toHexString(getSHA(String.valueOf(password)));
    }

    //Encryption using SHA-256
    private static byte[] getSHA(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(string.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] bytes) {
        BigInteger number = new BigInteger(1, bytes);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static boolean validPassword(char[] passwordChars) {
        String PasswordRegex;
        PasswordRegex = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$";
        return Pattern.matches(PasswordRegex, String.valueOf(passwordChars));
    }

    public static String parseToViewDate(String date) {
        String[] times = date.split("-");
        String viewDate = "";
        
        for(int i = times.length - 1; i >= 0; i--) {
            viewDate += times[i] + "/";
        }
        viewDate = viewDate.substring(0, viewDate.length() - 1);
        return viewDate;
    }

    public static String parseToModelDate(String date) {
        String[] times = date.split("/");
        String viewDate;
        viewDate
                = times[2] + "-"
                + times[1] + "-"
                + times[0];
        return viewDate;
    }

    public static List<Client> getClients() throws IOException, ParseException {
        if(clients == null) {
            clients = Reader.getClients();
        }
        return clients;
    }
    
    public static void printParameters(List<Parameter> parameters) {
        for(Parameter parameter : parameters) {
            System.out.println(parameter.toString());
        }
    }

    public static void flush(char[] password) {
        for (int i = 0; i < password.length; i++) {
            password[i] = 0;
        }
    }
    /**
     * Used for asking the user for confirmation when prompt is needed.
     * @param title The title shown to user
     * @param message The body message shown to user
     * @param confirmMessage The button text for the user to return affirmative response 
     * @param refuseMessage  The button text for the user to return negative response 
     * @return Returns 0 for confirmation and 1 for refusing
     */
    public static int confirm(String title, String message, String confirmMessage, String refuseMessage) {
        if(genericFrame == null) {
            genericFrame = new JFrame();
        }
        String[] options = new String[2];
        options[0] = confirmMessage;
        options[1] = refuseMessage;
        return
                JOptionPane.showOptionDialog(genericFrame.getContentPane(), 
                        message,
                        title,
                        0, 
                        JOptionPane.QUESTION_MESSAGE,
                        null, 
                        options, 
                        null
                );
    }
    
    public static void alert(String title, String message, String acceptMessage) {
        if(genericFrame == null) {
            genericFrame = new JFrame();
        }
        String[] options = new String[1];
        options[0] = acceptMessage;
        JOptionPane.showOptionDialog(genericFrame.getContentPane(), 
                        message,
                        title,
                        0, 
                        JOptionPane.WARNING_MESSAGE,
                        null, 
                        options, 
                        null
                );
    }
    
    public static void notify(String title, String message, String acceptMessage) {
        if(genericFrame == null) {
            genericFrame = new JFrame();
        }
        String[] options = new String[1];
        options[0] = acceptMessage;
        JOptionPane.showOptionDialog(genericFrame.getContentPane(), 
                        message,
                        title,
                        0, 
                        JOptionPane.INFORMATION_MESSAGE,
                        null, 
                        options, 
                        null
                );
    }
    
    public static String inputData(String title, String message) {
        if(genericFrame == null) {
            genericFrame = new JFrame();
        }
        return
                JOptionPane.showInputDialog(
                        genericFrame.getContentPane(), 
                        message, 
                        title, 
                        JOptionPane.INFORMATION_MESSAGE
                );
    }
    
    public static Client updateClient(Client client) throws IOException, ParseException {
        Client returnableClient = null;
        for(Client updatableClient : clients) { 
            if(client.equals(updatableClient)) {
                returnableClient = updatableClient;
                returnableClient.updateDebts();
                returnableClient.updateDeposits();
                client.sortDebts();
                
                break;
            }
        }
        return returnableClient;
    }
    
    public static void updateClients() throws IOException, ParseException, ClassNotFoundException, SQLException {
        clients = Reader.getClients();
        queryClient.update();
    }
    
    public static String getSessionKey() {
        return sessionKey;
    }
}
