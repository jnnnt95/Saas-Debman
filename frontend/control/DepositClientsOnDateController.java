package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Client;
import model.IO.Reader;
import view.full_size_view.DepositClientsOnDateView;

/**
 *
 * @author admin
 */
public class DepositClientsOnDateController {

    private final DepositClientsOnDateView view;
    private final String sessionKey;
    private final Calendar calendar;
    private final Date today;
    private List<Client> clients;

    public DepositClientsOnDateController(String sessionKey)
            throws ParseException {
        this.sessionKey = sessionKey;
        view = new DepositClientsOnDateView(this, sessionKey);
        view.updateView();
        calendar = Calendar.getInstance();
        today = new Date();
        view.setDate(MainController.parseToViewDate(MainController.getTodayDateToString()));
        verifySession();
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setViewData()
            throws ParseException, ClassNotFoundException, SQLException {
        verifySession();
        view.setInfoData(view.getDate());
    }

    public void modifyClient()
            throws ParseException {
    }

    public DepositClientsOnDateView getView() {
        return view;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public void setToday() {
        view.setDate(MainController.parseToViewDate(MainController.getTodayDateToString()));
    }

    public void addOneDayToDate() throws ParseException, ClassNotFoundException, SQLException, IOException {
        verifyDate(view.getDate());
        calendar.setTime(MainController.parseStringToDate(MainController.parseToModelDate(view.getDate())));
        calendar.add(Calendar.DATE, 1);
        view.setDate(MainController.parseToViewDate(MainController.parseDateToString(calendar.getTime())));
        view.setCashInRegister(MainController.formatAmount(getCashInRegister(MainController.parseToModelDate(view.getDate()))));
        view.setFocusOnDate();
    }

    public void removeOneDayToDate() throws ParseException, ClassNotFoundException, SQLException, IOException {
        verifyDate(view.getDate());
        calendar.setTime(MainController.parseStringToDate(MainController.parseToModelDate(view.getDate())));
        calendar.add(Calendar.DATE, -1);
        view.setDate(MainController.parseToViewDate(MainController.parseDateToString(calendar.getTime())));
        view.setCashInRegister(MainController.formatAmount(getCashInRegister(MainController.parseToModelDate(view.getDate()))));
        view.setFocusOnDate();
    }
    
    public int getCashInRegister(String stringDate) throws ParseException, IOException {
        Date date = MainController.parseStringToDate(stringDate);
        int cashInRegister = Reader.cashInRegister(MainController.parseDateToString(date));
        if (cashInRegister < 0) {
            cashInRegister = 0;
        }
        return cashInRegister;
    }

    public ArrayList<String[]> getDepositClients(String date) throws ClassNotFoundException, SQLException, IOException, ParseException {
        ArrayList<String[]> data;
        data = Reader.getDepositClients(MainController.parseToModelDate(date));

        ArrayList<String[]> returnableData;
        returnableData = new ArrayList<>();

        String[] tempDepositClient;

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < clients.size(); j++) {
                if (clients.get(j).getId() == Integer.parseInt(data.get(i)[0])) {
                    float totalDebt;
                    tempDepositClient = new String[4];
                    totalDebt = clients.get(j).getTotalNotPaidBalance();
                    tempDepositClient[0] = data.get(i)[1];
                    tempDepositClient[1] = data.get(i)[2];
                    tempDepositClient[2] = data.get(i)[3];
                    tempDepositClient[3] = String.valueOf(totalDebt);

                    returnableData.add(tempDepositClient);
                    
                    break;
                }
            }
        }

        return returnableData;
    }

    public void verifyDate(String date) {
        try {
            MainController.parseStringToDate(MainController.parseToModelDate(date));
        } catch (ParseException ex) {
            MainController.alert("Error de fecha", "Fecha no válida, la operación se realizará sobre el día de hoy", "Aceptar");
            setToday();
            view.setFocusOnDate();
        }
    }

    public void setCashInRegister(String amount) {
        view.setCashInRegister(amount);
    }
}
