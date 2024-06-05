package control;

import java.io.IOException;
import model.Client;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Debt;
import model.Deposit;
import model.IO.Reader;
import model.IO.Writer;
import view.full_size_view.ClientInfoView;

/**
 *
 * @author admin
 */
public class ClientInfoController {

    private final ClientInfoView view;
    private Client currentClient;
    private final String sessionKey;

    public ClientInfoController(String sessionKey)
            throws ParseException {
        this.sessionKey = sessionKey;
        view = new ClientInfoView(this, sessionKey);
        view.updateView();
        verifySession();
    }

    public void setViewData(Client client) throws ParseException, IOException {
        verifySession();
        this.currentClient = client;
        view.setInfoData();
    }

    public void modifyClient()
            throws ParseException {
    }

    public ClientInfoView getView() {
        return view;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void loginUpdate() {
        view.loginUpdate();
    }

    public void voidDebt(int debtId) throws IOException, ParseException {
        Debt voidableDebt = null;
        try {
            for (Debt debt : currentClient.getDebts()) {
                if (debt.getId() == debtId) {
                    voidableDebt = debt;
                    break;
                }
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(ClientInfoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (voidableDebt != null) {
            if (!voidableDebt.isVoid()) {
                String message;
                message = "¿Anular deuda de " + currentClient.getName() + "?\n"
                        + "\n"
                        + "Monto: $ " + MainController.formatAmount(voidableDebt.getAmount()) + "\n"
                        + "Monto con intereses: $ " + MainController.formatAmount(voidableDebt.getAmountPlusInterest());
                int option = MainController.confirm("Confirmar anulación de deuda", message, "Anular deuda", "Cancelar");
                switch (option) {
                    case 0:
                        int linkedDeposits;
                        linkedDeposits = Reader.getDebtLinkedDeposits(voidableDebt);
                        if (linkedDeposits > 0) {
                            message = "Se encontraron " + linkedDeposits + " depósitos asociados a la deuda que desea anular. Si decide continuar, se anularán cada uno de los depósitos asociados, esto puede impactar el balance histórico del negocio."
                                    + "\n\n   ¿Continuar? Esta acción no se puede deshacer.";
                            option = MainController.confirm("Confirmar anulación de deuda: depósitos", message, "Anular igualmente", "Cancelar");
                        } else if (linkedDeposits < 0) {
                            option = linkedDeposits;
                            MainController.alert("Error de anulación", "Se produjo un error y la deuda no pudo ser anulada", "Aceptar");
                        }
                        switch (option) {
                            case 0:
                                Writer.voidDebt(voidableDebt);
                                currentClient = MainController.updateClient(currentClient);
                                view.setInfoData();
                                view.setMainElementFocus();
                                MainController.notify("Anulación exitosa", "Deuda anulada exitosamente", "Aceptar");
                                break;
                            default:
                                break;
                        }
                    default:
                        break;
                }
            } else {
                MainController.alert("Error de Anulación", "La deuda ya se encuentra anulada", "Aceptar");
            }
        } else {
            MainController.alert("Error de Anulación", "No fue posible anular deuda", "Aceptar");
        }
    }

    public void voidDeposit(int depositId) throws IOException, ParseException {
        Deposit voidableDeposit = null;
        try {
            for (Deposit deposit : currentClient.getDeposits()) {
                if (deposit.getId() == depositId) {
                    voidableDeposit = deposit;
                    break;
                }
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(ClientInfoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (voidableDeposit != null) {
            if (!voidableDeposit.isVoid()) {
                String message;
                message = "¿Anular depósito de " + currentClient.getName() + "? Esta acción no se puede deshacer.\n"
                        + "\n"
                        + "Monto: $ " + MainController.formatAmount(voidableDeposit.getAmount());
                int option = MainController.confirm("Confirmar anulación de depósito", message, "Anular depósito", "Cancelar");
                switch (option) {
                    case 0:
                        Writer.voidDeposit(depositId);
                        currentClient = MainController.updateClient(currentClient);
                        view.setInfoData();
                        view.setMainElementFocus();
                        MainController.notify("Anulación exitosa", "Depósito anulado exitosamente", "Aceptar");
                        break;
                    default:
                        break;
                }
            }
            else {
                MainController.notify("Error de anulación", "El depósito ya se encuentra anulado", "Aceptar");
            }
        } else {
            MainController.notify("Error de anulación", "No fue posible anular depósito", "Aceptar");
        }
    }
    
    public void disableClient() {
        currentClient.setDisabled(true);
    }
}
