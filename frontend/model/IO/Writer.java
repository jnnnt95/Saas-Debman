package model.IO;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import control.MainController;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.util.ArrayList;
import java.util.List;
import jxl.write.WriteException;
import model.Client;
import model.Debt;
import model.Parameter;
import model.User;
import model.enums.Request;

/**
 *
 * @author admin
 */
public class Writer extends Commons {

    private static final String ROOT_DIR = "Data";

    private Writer() {

    }

    public static void disableSubuser(User subuserToDisable) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter subuserIdParam = setParam("subuserId", String.valueOf(subuserToDisable.getId()));

        params.add(subuserIdParam);
        
        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.disableSubuser, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void enableSubuser(User subuserToDisable) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter subuserIdParam = setParam("subuserId", String.valueOf(subuserToDisable.getId()));

        params.add(subuserIdParam);
        
        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.enableSubuser, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static int recordDeposit(int clientId, int amount, String date) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter clientIdParam = setParam("clientId", String.valueOf(clientId));
        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));
        Parameter amountParam = setParam("amount", String.valueOf(amount));
        Parameter dateParam = setParam("date", date);

        params.add(clientIdParam);
        params.add(userIdParam);
        params.add(amountParam);
        params.add(dateParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.recordDeposit, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return -1;
        } else {
            return digestedJson.get("id").getAsInt();
        }
    }

    public static int addSubuser(
            String names,
            String surnames,
            String username,
            char[] password,
            String phone,
            String email
    ) throws NoSuchAlgorithmException, IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter namesParam = setParam("names", names);
        Parameter surnamesParam = setParam("surnames", surnames);
        Parameter usernameParam = setParam("username", username);
        Parameter passwordParam = setParam("password", MainController.encryptPassword(password));
        Parameter phoneParam = setParam("phone", phone);
        Parameter emailParam = setParam("email", email);
        Parameter typeParam = setParam("type", String.valueOf(5));
        Parameter parentServicesUserParam = setParam("parentServicesUser", String.valueOf(MainController.getUser().getId()));
        Parameter creationDateUserParam = setParam("creationDate", MainController.getTodayDateToString());

        params.add(namesParam);
        params.add(surnamesParam);
        params.add(usernameParam);
        params.add(passwordParam);
        params.add(phoneParam);
        params.add(emailParam);
        params.add(typeParam);
        params.add(parentServicesUserParam);
        params.add(creationDateUserParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.addSubuser, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return -1;
        } else {
            return digestedJson.get("id").getAsInt();
        }
    }

    public static void exportToExcel(Object[][] writableData) throws IOException, WriteException {
        
        String path = System.getenv("USERPROFILE") + "\\.debtorManager\\";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }

        WorkbookSettings configuration;
        configuration = new WorkbookSettings();
        configuration.setEncoding("ISO-8859-1");

        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(new File(path),
                    configuration);

            WritableSheet sheet;
            sheet = workbook.createSheet("Clientes",
                    0);

            WritableFont font;
            font = new WritableFont(WritableFont.ARIAL,
                    10,
                    WritableFont.BOLD);

            WritableCellFormat cellFormater;
            cellFormater = new WritableCellFormat(font);

            sheet.addCell(new Label(0,
                    0,
                    "Nick",
                    cellFormater));
            sheet.addCell(new Label(1,
                    0,
                    "Nombre",
                    cellFormater));
            sheet.addCell(new Label(2,
                    0,
                    "Teléfono",
                    cellFormater));
            sheet.addCell(new Label(3,
                    0,
                    "Comentario",
                    cellFormater));
            sheet.addCell(new Label(4,
                    0,
                    "Saldo",
                    cellFormater));
            sheet.addCell(new Label(5,
                    0,
                    "En mora",
                    cellFormater));

            font = new WritableFont(WritableFont.ARIAL,
                    10,
                    WritableFont.NO_BOLD);
            cellFormater = new WritableCellFormat(font);

            for (int i = 0;
                    i
                    < writableData.length;
                    i++) {

                sheet.addCell(new Label(0,
                        i
                        + 1,
                        (String) writableData[i][0],
                        cellFormater));
                sheet.addCell(new Label(1,
                        i
                        + 1,
                        (String) writableData[i][1],
                        cellFormater));
                sheet.addCell(new Label(2,
                        i
                        + 1,
                        (String) writableData[i][2],
                        cellFormater));
                sheet.addCell(new Label(3,
                        i
                        + 1,
                        (String) writableData[i][3],
                        cellFormater));
                sheet.addCell(new Number(4,
                        i
                        + 1,
                        Double.parseDouble((String) writableData[i][4]),
                        cellFormater));

                if (((String) writableData[i][5]).equals("*")) {
                    sheet.addCell(new Label(5,
                            i
                            + 1,
                            "Sí",
                            cellFormater));
                } else {
                    sheet.addCell(new Label(5,
                            i
                            + 1,
                            "No",
                            cellFormater));
                }
            }

            //Set Column width
            int nickFilter = 0;
            int nameFilter = 0;
            int commentaryFilter = 0;

            for (int i = 0;
                    i
                    < writableData.length;
                    i++) {
                if (((String) writableData[i][0]).length()
                        > nickFilter) {
                    nickFilter = ((String) writableData[i][0]).length();
                }
                if (((String) writableData[i][1]).length()
                        > nameFilter) {
                    nameFilter = ((String) writableData[i][1]).length();
                }
                if (((String) writableData[i][3]).length()
                        > commentaryFilter) {
                    commentaryFilter = ((String) writableData[i][3]).length();
                }
            }

            nickFilter++;
            nameFilter++;
            commentaryFilter++;

            sheet.setColumnView(0,
                    nickFilter);
            sheet.setColumnView(1,
                    nameFilter);
            sheet.setColumnView(2,
                    11);
            sheet.setColumnView(3,
                    commentaryFilter);
            sheet.setColumnView(4,
                    "Saldo por pagar".length()
                    + 1);
            sheet.setColumnView(5,
                    "Deudor Moroso".length()
                    + 1);

            workbook.write();
            workbook.close();
        } catch (IOException e) {
            MainController.alert("Error de exportación", "Parece que algo impidió la exportación a Excel. Asegúrese de que no haya archivos de exportación abiertos e inténtelo de nuevo.", "Aceptar");
        }
    }

    public static void modifyDebt(Debt debt, int depositId, float paidAmount) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter depositParam = setParam("deposit", String.valueOf(debt.getDeposit()));
        Parameter isPaid = setParam("isPaid", String.valueOf(debt.isPaid()));
        Parameter paidDateParam = setParam("paidDate", String.valueOf(debt.getPaidDate()));
        Parameter paymentIdParam = setParam("depositId", String.valueOf(depositId));
        Parameter debtIdParam = setParam("debtId", String.valueOf(debt.getId()));
        Parameter paidAmountParam = setParam("paidAmount", String.valueOf(paidAmount));

        params.add(depositParam);
        params.add(isPaid);
        params.add(paidDateParam);
        params.add(paymentIdParam);
        params.add(debtIdParam);
        params.add(paidAmountParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.modifyDebt, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void modifyDebt(Debt debt) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter amountParam = setParam("amount", String.valueOf(debt.getAmount()));
        Parameter depositParam = setParam("deposit", String.valueOf(debt.getDeposit()));
        Parameter interestRateParam = setParam("interestRate", String.valueOf(debt.getInterestRate()));
        Parameter interestRateOnDefaultParam = setParam("interestRateOnDefault", String.valueOf(debt.getInterestRateOnDefault()));
        Parameter daysToDefaultParam = setParam("daysToDefault", String.valueOf(debt.getDaysToDefault()));
        Parameter creationDateParam = setParam("creationDate", debt.getCreationDate());
        Parameter debtIdParam = setParam("debtId", String.valueOf(debt.getId()));

        params.add(amountParam);
        params.add(depositParam);
        params.add(interestRateParam);
        params.add(interestRateOnDefaultParam);
        params.add(daysToDefaultParam);
        params.add(creationDateParam);
        params.add(debtIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.modifyDebtForCreation, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void changePassword(char[] newPassword) throws NoSuchAlgorithmException, IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter passwordParam = setParam("password", MainController.encryptPassword(newPassword));
        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));

        params.add(passwordParam);
        params.add(userIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.changePassword, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void modifyClient(Client client) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter nameParam = setParam("name", client.getName());
        Parameter nickParam = setParam("nick", client.getNick());
        Parameter phoneParam = setParam("phone", client.getPhone());
        Parameter commentaryParam = setParam("commentary", client.getCommentary());
        Parameter interestRateParam = setParam("interestRate", String.valueOf(client.getInterestRate()));
        Parameter interestRateOnDefaultParam = setParam("interestRateOnDefault", String.valueOf(client.getInterestRateOnDefault()));
        Parameter clientIdParam = setParam("clientId", String.valueOf(client.getId()));

        params.add(nameParam);
        params.add(nickParam);
        params.add(phoneParam);
        params.add(commentaryParam);
        params.add(interestRateParam);
        params.add(interestRateOnDefaultParam);
        params.add(clientIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.modifyClientForCreation, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void modifyUser(User user) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter namesParam = setParam("names", user.getNames());
        Parameter surnamesParam = setParam("surnames", user.getSurnames());
        Parameter phoneParam = setParam("phone", user.getPhone());
        Parameter emailParam = setParam("email", user.getEmail());
        Parameter userIdParam = setParam("userId", String.valueOf(user.getId()));

        params.add(namesParam);
        params.add(surnamesParam);
        params.add(phoneParam);
        params.add(emailParam);
        params.add(userIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.modifyUser, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void modifyGeneralInfo(User user) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter preferredInterestRateParam = setParam("preferredInterestRate", String.valueOf(user.getPreferredInterestRate()));
        Parameter preferredInterestRateOnDefaultParam = setParam("preferredInterestRateOnDefault", String.valueOf(user.getPreferredInterestRateOnDefault()));
        Parameter daysToDefaultParam = setParam("daysToDefault", String.valueOf(user.getDaysToDefault()));
        Parameter userIdParam = setParam("userId", String.valueOf(user.getId()));

        params.add(preferredInterestRateParam);
        params.add(preferredInterestRateOnDefaultParam);
        params.add(daysToDefaultParam);
        params.add(userIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.modifyGeneralInfo, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void disableClient(Client client) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter clientIdParam = setParam("clientId", String.valueOf(client.getId()));

        params.add(clientIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.disableClient, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static int createEmptyDebt(int clientId, int daysToDefault) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter clientIdParam = setParam("clientId", String.valueOf(clientId));
        Parameter daysToDefaultParam = setParam("daysToDefault", String.valueOf(daysToDefault));
        Parameter dateParam = setParam("date", MainController.getTodayDateToString());
        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));

        params.add(clientIdParam);
        params.add(daysToDefaultParam);
        params.add(dateParam);
        params.add(userIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.createEmptyDebt, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return -1;
        } else {
            return digestedJson.get("id").getAsInt();
        }
    }

    public static int createEmptyClient() throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter servicesUserIdParam = setParam("servicesUserId", String.valueOf(MainController.getUser().getServicesUserId()));
        Parameter dateParam = setParam("date", MainController.getTodayDateToString());
        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));

        params.add(servicesUserIdParam);
        params.add(dateParam);
        params.add(userIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.createEmptyClient, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
            return -1;
        } else {
            return digestedJson.get("id").getAsInt();
        }
    }
    
    public static void voidDeposit(int depositId) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter depositIdParam = setParam("depositId", String.valueOf(depositId));

        params.add(depositIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.voidDeposit, params);
        System.out.println(jsonResponse);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }

    public static void voidDebt(Debt voidableDebt) throws IOException {
        int debtId = voidableDebt.getId();
        
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter debtIdParam = setParam("debtId", String.valueOf(debtId));

        params.add(debtIdParam);

        addSessionData(params);
        
        String jsonResponse;
        jsonResponse = apply(Request.voidDebt, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }
    
    public static void requestPasswordReset(String email) throws IOException {
        List<Parameter> params;
        params = new ArrayList<>();

        Parameter emailParam = setParam("email", email);
        Parameter tokenParam = setParam("token", Reader.getRequestToken());

        params.add(emailParam);
        params.add(tokenParam);
        
        String jsonResponse;
        jsonResponse = apply(Request.requestPasswordReset, params);
        JsonObject digestedJson = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (digestedJson.get("error").getAsBoolean()) {
            showErrorMessage(digestedJson);
        }
    }
}
