package control;

import model.Debt;
import model.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import model.IO.Writer;
import view.pop_up_view.AddDebtView;

/**
 *
 * @author admin
 */
public class AddDebtController {

    private final AddDebtView view;
    private Client currentClient;
    private double mean;
    private double standardDeviation;
    private final String sessionKey;
    private final Calendar calendar;

    public AddDebtController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new AddDebtView(this, sessionKey);
        view.updateView();
        view.clear();
        calendar = Calendar.getInstance();
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    private void setViewData(Client currentClient) throws IOException, ParseException {
        verifySession();
        this.currentClient = currentClient;
        mean = this.currentClient.getMean();
        standardDeviation = this.currentClient.getStandardDeviation();

        view.clear();
        setToday();
        view.setClientIdentification();
        view.setClientNotPaidBalance();
        view.setWarningLabel();
    }

    public void setReady(Client currentClient) throws IOException, ParseException {
        setViewData(currentClient);
        view.enableOperation();
    }

    public void addOneDayToDate() throws ParseException {
        calendar.setTime(MainController.parseStringToDate(MainController.parseToModelDate(view.getDate())));
        calendar.add(Calendar.DATE, 1);
        view.setDate(MainController.parseToViewDate(MainController.parseDateToString(calendar.getTime())));
    }

    public void removeOneDayToDate() throws ParseException {
        calendar.setTime(MainController.parseStringToDate(MainController.parseToModelDate(view.getDate())));
        calendar.add(Calendar.DATE, -1);
        view.setDate(MainController.parseToViewDate(MainController.parseDateToString(calendar.getTime())));
    }

    public void addDebt() throws IOException, ParseException, ClassNotFoundException, SQLException {
        if (noFieldsEmpty()) {
            Date date;
            try {
                date = MainController.parseStringToDate(MainController.parseToModelDate(view.getDate()));
                try {
                    int newDebtAmount;
                    Debt newDebt;
                    newDebtAmount = Integer.parseInt(view.getNewDebtAmount());
                    String dateString = MainController.parseDateToString(date);
                    if (newDebtAmount <= 0) {
                        throw new NumberFormatException("el monto debe ser mayor que cero");
                    }
                    if (!MainController.validAmount(newDebtAmount)) {
                        throw new NumberFormatException("revisar monto");
                    }

                    int selection;
                    selection = MainController.confirm("Confirmar", "¿Agregar deuda?\n\n     $ " + MainController.formatAmount(newDebtAmount), "Agregar deuda", "Cancelar");

                    switch (selection) {
                        case 0:

                            if ((newDebtAmount + getThisMonthBalance()) > (mean + standardDeviation)) {
                                MainController.alert("Cliente sobrepasó límite recomendado", "El saldo del mes para este cliente sobrepasó el límite recomendado", "Entiendo el riesgo");
                            }

                            int newDebtId = Writer.createEmptyDebt(currentClient.getId(), MainController.getUser().getDaysToDefault());

                            if (newDebtId >= 0) {
                                newDebt = new Debt(
                                        newDebtId,
                                        currentClient.getId(),
                                        newDebtAmount,
                                        0,
                                        MainController.calculateDebtInterest(newDebtAmount, currentClient.getInterestRate()),
                                        currentClient.getInterestRate(),
                                        currentClient.getInterestRateOnDefault(),
                                        MainController.getUser().getDaysToDefault(),
                                        dateString,
                                        null,
                                        MainController.getUser().
                                                getName(),
                                        MainController.getUser().
                                                getId(),
                                        false,
                                        false
                                );

                                Writer.modifyDebt(newDebt);

                                currentClient.addDebt(newDebt);
                                currentClient.sortDebts();
                                currentClient.updateDefaultAmount();
                                MainController.notify("Deuda agregada exitosamente", "Nuevo saldo para " + currentClient.
                                        getName() + ", " + currentClient.getNick() + ":\n\n     $" + MainController.
                                        formatAmount(currentClient.
                                                getTotalNotPaidBalance()), "Aceptar");
                                MainController.changeToClientInfoMode(currentClient,
                                        sessionKey);
                            } else {
                                MainController.alert("Error", "Operación de adición de deuda cancelada.", "Aceptar");
                            }
                            break;
                        case 1:
                            view.enableOperation();
                            break;
                        case 2:
                            MainController.changeToClientInfoMode(currentClient,
                                    sessionKey);
                            break;
                    }
                } catch (NumberFormatException e) {
                    MainController.alert("Error de monto", "Monto de deuda no válido", "Aceptar");
                    view.enableOperation();
                    view.setFocusOnAmount();
                } catch (ParseException e) {
                    MainController.alert("Error de fecha", "Fecha no válida", "Aceptar");
                    view.enableOperation();
                    view.setFocusOnDate();
                }
            } catch (ParseException ex) {
                MainController.alert("Error de fecha", "Fecha no válida", "Aceptar");
                view.enableOperation();
                view.setFocusOnDate();
            }
        } else {
            view.enableOperation();
        }
    }

    private boolean noFieldsEmpty() {
        if (view.getNewDebtAmount().
                length() <= 0) {
            MainController.alert("Error de monto", "El campo de monto no puede estar vacío", "Aceptar");
            view.setFocusOnAmount();
            return false;
        }
        if (view.getNewDebtDate().
                length() <= 0) {
            MainController.alert("Error de fecha", "El campo de fecha no puede estar vacío", "Aceptar");
            view.setFocusOnDate();
            return false;
        }
        return true;
    }

    private void setToday() {
        view.setDate(MainController.parseToViewDate(MainController.getTodayDateToString()));
    }

    public AddDebtView getView() {
        return view;
    }

    private int getThisMonthBalance() throws IOException, ParseException {
        int thisMonthBalance;
        thisMonthBalance = 0;

        SimpleDateFormat formater;
        formater = new SimpleDateFormat("MM");

        int thisMonth;
        thisMonth = Integer.parseInt(formater.format(new Date()));

        for (Debt debt : currentClient.getDebts()) {
            if ((Integer.parseInt(debt.getCreationDate().
                    substring(5, 7)) == thisMonth) && (!debt.isPaid())) {
                thisMonthBalance += debt.getAmount();
            }
        }

        return thisMonthBalance;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
