package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IO.Writer;
import model.User;
import model.enums.OperationCode;
import view.pop_up_view.ModifyGeneralSettingsView;

/**
 *
 * @author admin
 */
public class ModifyGeneralSettingsController {

    private User user;
    private final ModifyGeneralSettingsView view;
    private final String sessionKey;

    public ModifyGeneralSettingsController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new ModifyGeneralSettingsView(this, sessionKey);
        view.updateView();
        view.setMainElementFocus();

        verifySession();
    }

    public ModifyGeneralSettingsView getView() {
        return view;
    }
    
    public void setReady() {
        view.enableOperation();
        setInfoData();
    }

    private void setInfoData() {
        view.setInterestRate(String.valueOf(user.getPreferredInterestRate()));
        view.setInterestRateOnDefault(String.valueOf(user.getPreferredInterestRateOnDefault()));
        view.setDaysToDafault(String.valueOf(user.getDaysToDefault()));
    }

    public void loginUpdate() {
        this.user = MainController.getUser();
    }

    public void modifyGeneralConfiguration() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException {
        if (isNewInfoDataCorrect()) {
            String interestRate;
            interestRate = view.getNewInterestRate();

            String interestRateOnDefault;
            interestRateOnDefault = view.getNewInterestRateOnDefault();

            String daysToDefault;
            daysToDefault = view.getNewDaysToDefault();

            String printable;
            printable = "<html><strong>Nueva configuración general</strong><br><br>"
                    + "   <strong>Tasa de interés</strong>: " + interestRate + " % <br>"
                    + "   <strong>Tasa de interés en mora</strong>: " + interestRateOnDefault + "% <br>"
                    + "   <strong>Días para mora</strong>: " + daysToDefault + " días<br>";
            int selection;
            selection = MainController.confirm("Confirmar cambios", printable, "Cambiar configuración", "Cancelar");
            switch (selection) {
                case 0:
                    user.setPreferredInterestRate(Float.parseFloat(interestRate));
                    user.setPreferredInterestRateOnDefault(Float.parseFloat(interestRateOnDefault));
                    user.setDaysToDefault(Integer.parseInt(daysToDefault));

                    Writer.modifyGeneralInfo(user);
                    MainController.executeOperation(OperationCode.completeOperation, sessionKey);
                    break;
                case 1:
                    view.enableOperation();
                    break;
                case 2:
                    cancelModifyingUserInfo();
                    break;
            }

            MainController.updateMainFrame();
        }
        else {
            view.enableOperation();
        }
    }

    public void cancelModifyingUserInfo() {
        try {
            MainController.executeOperation(OperationCode.cancelOperation,
                    sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(ModifyGeneralSettingsController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private boolean isNewInfoDataCorrect() {
        if (!isNewConfigSet()) {
            return false;
        } else if (!isDataRight()) {
            return false;
        }
        return true;
    }

    private boolean isDataRight() {
        if (!MainController.validInterestRate(view.getNewInterestRate())) {
            MainController.alert("Error de tasa de interés", "El valor de la tasa de interés no es válido, inténtelo de nuevo", "Aceptar");
            view.setFocusOnInterestRate();
            return false;
        }
        if (!MainController.validInterestRate(view.getNewInterestRateOnDefault())) {
            MainController.alert("Error de tasa de interés en mora", "El valor de la tasa de interés en mora no es válido, inténtelo de nuevo", "Aceptar");
            view.setFocusOnInterestRateOnDefault();
            return false;
        }
        if (MainController.validInteger(view.getNewDaysToDefault())) {
            int tempDays;
            tempDays = Integer.parseInt(view.getNewDaysToDefault());
            if (tempDays <= 0) {
                MainController.alert("Error de días para mora", "El valor de días para mora no es válido, inténtelo de nuevo", "Aceptar");
                view.setFocusOnDaysToDefault();
                return false;
            }
        }
        return true;
    }

    private boolean isNewConfigSet() {
        if (view.getNewInterestRate().length() <= 0) {
            MainController.alert("Error tasa de interés", "El campo de tasa de interés no puede estar vacío", "Aceptar");
            view.setFocusOnInterestRate();
            return false;
        } else if (view.getNewInterestRateOnDefault().length() <= 0) {
            MainController.alert("Error tasa de interés en mora", "El campo de tasa de interés en mora no puede estar vacío", "Aceptar");
            view.setFocusOnInterestRateOnDefault();
            return false;
        } else if (view.getNewDaysToDefault().length() <= 0) {
            MainController.alert("Error días para mora", "El campo de días para mora no puede estar vacío", "Aceptar");
            view.setFocusOnDaysToDefault();
            return false;
        }
        return true;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
