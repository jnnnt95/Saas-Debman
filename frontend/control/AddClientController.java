package control;

import model.Debt;
import model.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Deposit;
import model.IO.Writer;
import model.enums.OperationCode;
import view.pop_up_view.AddClientView;

/**
 *
 * @author admin
 */
public class AddClientController {

    private final String sessionKey;
    private final AddClientView view;

    public AddClientController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new AddClientView(this, sessionKey);
        view.updateView();
    }

    public void setReady()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        view.clearName();
        view.clearNick();
        view.clearCPNumber();
        view.clearArea();
        view.clearInterestRate();
        view.clearInterestRateOnDefault();
        view.clearInitialBalance();
        view.setMainElementFocus();
        view.enableOperation();
    }

    public void cancelAddingAClient() {
        try {
            MainController.executeOperation(OperationCode.cancelOperation,
                    sessionKey);
        } catch (IOException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }
    }

    private boolean isNewClientDataCorrect() {
        if (!isNewClientDataSet()) {
            return false;
        } else if (!isTextDataRight()) {
            return false;
        } else if (!isCPNumberRight()) {
            return false;
        } else if (!isAValidInitialBalance()) {
            return false;
        }
        return true;
    }

    private boolean isTextDataRight() {
        if (view.getNewClientName().
                length()
                > 30) {
            view.setFocusOnName();
            MainController.alert("Nombre inválido", "El nombre debe tener 30 caracteres o menos", "Aceptar");
            return false;
        }
        if (view.getNewClientNick().
                length()
                > 30) {
            view.setFocusOnNick();
            MainController.alert("Nick inválido", "El nick debe tener 30 caracteres o menos", "Aceptar");
            return false;
        }
        if (view.getNewClientCommentary().
                length()
                > 30) {
            view.setFocusOnCommentary();
            MainController.alert("Comentario inválido", "El comentario debe tener 30 caracteres o menos", "Aceptar");
            return false;
        }
        return true;
    }

    private boolean isAValidInitialBalance() {
        int initialBalance;
        initialBalance = 0;
        if (view.getNewClientInitialBalance().
                length() > 0) {
            try {
                initialBalance
                        = Integer.parseInt(view.getNewClientInitialBalance());
                if (initialBalance
                        <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                MainController.alert("Saldo inicial inválido", "Intente otro valor", "Aceptar");
                view.setFocusOnInitialBalance();
                return false;
            }
        }
        return true;
    }

    private boolean isNewClientDataSet() {
        if (view.getNewClientName().
                length() <= 0) {
            MainController.alert("Campo de nombre vacío", "Digite el nombre del nuevo cliente", "Aceptar");
            view.setFocusOnName();
            return false;
        }
        return true;
    }

    private boolean isCPNumberRight() {
        //check number for length
        String cpNumber;
        cpNumber = view.getNewClientCPNumber();
        
        if(cpNumber.length() <= 0) {
            return true;
        }

        if (cpNumber.length()
                > 10) {
            MainController.alert("Número telefónico inválido", "El número teléfonico debe tener máximo 10 dígitos", "Aceptar");
            view.setFocusOnCPNumber();
            return false;
        }

        //check number for suitability
        for (int i = 0;
                i
                < cpNumber.length();
                i++) {
            if (!MainController.isNumberADigit(String.
                    valueOf(cpNumber.charAt(i)))) {
                MainController.alert("Número telefónico inválido", "El número teléfonico debe contener solamente números", "Aceptar");
                view.setFocusOnCPNumber();
                return false;
            }
        }

        // return true if any of above return false first
        return true;
    }

    public void addClient()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        try {
            if (isNewClientDataCorrect()) {
                String name;
                name = view.getNewClientName();

                String nick;
                nick = view.getNewClientNick();

                String cpNumber;
                cpNumber = view.getNewClientCPNumber();

                String commentary;
                commentary = view.getNewClientCommentary();

                float interestRate;

                List<Debt> debts = new ArrayList<>();
                List<Deposit> deposits = new ArrayList<>();

                if (view.getNewClientInterestRate().
                        length() <= 0) {
                    interestRate = 0f;
                } else {
                    if (!MainController.validInterestRate(view.getNewClientInterestRate())) {
                        throw new NumberFormatException("interest" + ";" + "El valor de la tasa de interés es inválido, inténtelo de nuevo");
                    }
                    interestRate = Float.parseFloat(MainController.
                            eliminateLeadingZeros(view.
                                    getNewClientInterestRate()));
                }

                float interestRateOnDefault;

                if (view.getNewClientInterestRate().
                        length() <= 0) {
                    interestRateOnDefault = 0f;
                } else {
                    if (!MainController.validInterestRate(view.
                            getNewClientInterestRateOnDefault())) {
                        throw new NumberFormatException("interestOnDefault" + ";" + "El valor de la tasa de interés en mora es inválido, inténtelo de nuevo");
                    }
                    interestRateOnDefault = Float.parseFloat(MainController.
                            eliminateLeadingZeros(view.
                                    getNewClientInterestRateOnDefault()));
                }

                int amount;

                if (view.getNewClientInitialBalance().
                        length() <= 0) {
                    amount = 0;

                } else if (MainController.validInteger(view.getNewClientInitialBalance())) {
                    amount = Integer.parseInt(MainController.eliminateLeadingZeros(view.getNewClientInitialBalance()));
                    if (!MainController.validAmount(amount)) {
                        throw new NumberFormatException("amount" + ";" + "El valor del monto es inválido, inténtelo de nuevo");
                    }
                } else {
                    throw new NumberFormatException("amount" + ";" + "El valor del monto es inválido, inténtelo de nuevo");
                }
                Debt initialBalance;

                int newClientId;
                newClientId = Writer.createEmptyClient();

                if (newClientId < 0) {
                    throw new Exception("Error de adición de cliente.");
                }

                if (amount > 0) {
                    initialBalance = getInitialBalance(newClientId,
                            amount,
                            interestRate,
                            interestRateOnDefault);
                    debts.add(initialBalance);
                }

                Client client;
                client = new Client(
                        newClientId,
                        name,
                        nick,
                        cpNumber,
                        commentary,
                        interestRate,
                        interestRateOnDefault,
                        MainController.getUser().
                                getDaysToDefault(),
                        MainController.getUser().
                                getName(),
                        MainController.getUser().
                                getId(),
                        //disabled client
                        false
                );
                client.setDebts(debts);
                client.setDeposits(deposits);
                Writer.modifyClient(client);
                MainController.getClients().add(client);
                MainController.
                        executeOperation(OperationCode.completeOperation,
                                sessionKey);
            } else {
                view.enableOperation();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (NumberFormatException e) {
            String[] content;
            content = e.getMessage().
                    split(";");
            String error = content[0];
            String message = content[1];
            MainController.alert("Se encontró un error", message, "Aceptar");
            switch (error) {
                case "amount":
                    view.setFocusOnInitialBalance();
                    view.enableOperation();
                    break;
                case "interest":
                    view.setFocusOnInterestRate();
                    view.enableOperation();
                    break;
                case "interestOnDefault":
                    view.setFocusOnInterestRateOnDefault();
                    view.enableOperation();
                    break;
                    
            }
        } catch (Exception e) {
            MainController.alert("Error", "Operación terminada. " + e.getMessage(), "Aceptar");
        }
    }

    public AddClientView getView() {
        return view;
    }

    private Debt getInitialBalance(int newClientId,
            int amount, float interestRate, float interestRateOnDefault)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        Debt initialBalance;

        int newDebtId = Writer.createEmptyDebt(newClientId, MainController.getUser().getDaysToDefault());

        initialBalance = new Debt(
                newDebtId,
                newClientId,
                amount,
                0,
                MainController.calculateDebtInterest(amount, interestRate),
                interestRate,
                interestRateOnDefault,
                MainController.getUser().getDaysToDefault(),
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                null,
                MainController.getUser().
                        getName(),
                MainController.getUser().
                        getId(),
                false,
                false
        );

        Writer.modifyDebt(initialBalance);

        return initialBalance;
    }
}
