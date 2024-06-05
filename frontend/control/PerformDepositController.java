package control;

import model.Debt;
import model.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Deposit;
import model.IO.Writer;
import view.pop_up_view.PerformDepositView;

/**
 *
 * @author admin
 */
public class PerformDepositController {

    private final PerformDepositView view;
    private Client currentClient;
    private final String sessionKey;

    public PerformDepositController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new PerformDepositView(this, sessionKey);
        view.updateView();
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setReady(Client currentClient) throws IOException, ParseException {
        view.enableOperation();
        setViewData(currentClient);
    }

    private void setViewData(Client currentClient) throws IOException, ParseException {
        verifySession();
        this.currentClient = currentClient;

        view.clear();
        setToday();
        view.setClientIdentification();
        view.setClientNotPaidBalance();
        view.setWarningLabel();
    }

    public void performDeposit() throws IOException, ParseException, ClassNotFoundException, SQLException {
        MainController.authenticate(sessionKey);
        if (noFieldsEmpty()) {
            if (currentClient.getTotalNotPaidBalance() > 0) {
                Integer amount;
                try {
                    amount = Integer.parseInt(view.getNewDebtAmount());
                    if (amount <= 0) {
                        throw new NumberFormatException("debe ser mayor a cero");
                    }
                    if (amount > MainController.roundAmount(currentClient.getTotalNotPaidBalance())) {
                        throw new NumberFormatException("es mayor a la deuda redondeada del cliente");
                    }
                    if ((amount % 50) != 0) {
                        throw new NumberFormatException("revisar monto");
                    }
                    view.setVisible(false);
                    String printable;
                    printable = "Se cobrarán $" + MainController.formatAmount(amount) + "\n\n¿Continuar?";
                    int selection;
                    selection = MainController.confirm("Confirmar cobro", printable, "Realizar depósito", "Cancelar");
                    switch (selection) {
                        case 0:
                            pay(amount);
                            MainController.notify("Nuevo saldo", "Nuevo saldo para " + currentClient.getName() + ":\n\n" + "     $" + MainController.formatAmount(currentClient.getTotalNotPaidBalance()), "Aceptar");
                            MainController.changeToClientInfoMode(currentClient,
                                    sessionKey);
                            break;
                        case 1:
                            view.enableOperation();
                            break;
                        case 2:
                            MainController.alert("Cobro cancelado", "Se canceló el depósito", "Aceptar");
                            MainController.changeToClientInfoMode(currentClient,
                                    sessionKey);
                            break;
                    }
                } catch (NumberFormatException e) {
                    MainController.alert("Error de monto", "El monto ingresado no es válido", "Aceptar");
                    view.enableOperation();
                    view.setFocusOnAmount();
                }
            } else {
                MainController.notify("Cliente a paz y salvo", "No se encontraron deudas por pagar para el cliente actual", "Aceptar");
            }
        }
        else {
            view.enableOperation();
        }
    }

    private void pay(float amount) throws IOException, ClassNotFoundException, SQLException, ParseException {
        MainController.authenticate(sessionKey);
        String date;
        date = MainController.parseToModelDate(view.getNewDebtDate());
        currentClient.sortDebts();

        int currentDeposit;
        currentDeposit = Writer.recordDeposit(currentClient.getId(), (int) amount, date);
        if (currentDeposit >= 0) {
            currentClient.getDeposits().add(new Deposit(currentDeposit, amount, date, MainController.getUser().getName(), MainController.getUser().getId(), false));

            for (Debt debt : currentClient.getDebts()) {
                if (!debt.isVoid()) {
                    if (!debt.isPaid() && amount > 0) {
                        float paidAmount;
                        float debitAssessment;
                        debitAssessment = amount - debt.getTotalDebt();
                        if (debitAssessment > 0) {
                            paidAmount = debt.getTotalDebt();
                            debt.updateDebt(debt.getTotalDebt(), date);
                            amount = debitAssessment;

                        } else {
                            paidAmount = amount;
                            debt.updateDebt(amount, date);
                            amount = 0;
                        }
                        Writer.modifyDebt(debt, currentDeposit, paidAmount);
                    }
                }
            }
        } else {
            MainController.alert("Operación cancelada", "Operación de registro de depósito cancelada", "Aceptar");
        }
    }

    private boolean noFieldsEmpty() {
        if (view.getNewDebtAmount().
                length() <= 0) {
            MainController.alert("Error de monto", "El campo de monto no puede estar vacío", "Aceptar");
            view.setFocusOnAmount();
            return false;
        }
        if (view.getNewDebtDate().length() <= 0) {
            MainController.alert("Error de fecha", "El campo de fecha no puede estar vacío", "Aceptar");
            view.setFocusOnDate();
            return false;
        }
        return true;
    }

    private void setToday() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        view.setDate(dateFormat.format(date));
    }

    public PerformDepositView getView() {
        return view;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
