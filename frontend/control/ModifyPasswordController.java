package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IO.Reader;
import model.IO.Writer;
import model.enums.OperationCode;
import view.pop_up_view.ModifyPasswordView;

/**
 *
 * @author admin
 */
public class ModifyPasswordController {

    private final ModifyPasswordView view;
    private final String sessionKey;

    public ModifyPasswordController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new ModifyPasswordView(this, sessionKey);
        view.updateView();
        view.setMainElementFocus();

        verifySession();
    }

    public ModifyPasswordView getView() {
        return view;
    }
    
    public void setReady() {
        clearData();
        view.enableOperation();
    }

    private void clearData() {
        view.clearData();
    }

    public void modifyPassword() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException, NoSuchAlgorithmException {
        if (isPasswordDataCorrect()) {
            char[] currentPassword;
            currentPassword = view.getCurrentPassword();

            char[] newPassword;
            newPassword = view.getNewPassword();

            String printable;
            printable = "La contraseña cambiará, ¿continuar?";
            int selection;
            selection = MainController.confirm("Confirmar", "La contraseña cambiará, ¿desea continuar?", "Cambiar contraseña", "Cancelar");
            switch (selection) {
                case 0:
                    Writer.changePassword(newPassword);
                    MainController.notify("Operación exitosa", "La contraseña se modificó exitosamente", "Aceptar");
                    MainController.executeOperation(OperationCode.completeOperation, sessionKey);

                    flush(currentPassword);
                    flush(newPassword);
                    clearData();
                    
                    break;
                case 1:
                    view.enableOperation();
                    break;
                case 2:
                    cancelModifyingPassword();
                    break;
            }

            MainController.updateMainFrame();
        }
        else {
            view.enableOperation();
        }
    }

    public void cancelModifyingPassword() {
        try {
            MainController.executeOperation(OperationCode.cancelOperation,
                    sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(ModifyPasswordController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private boolean isPasswordDataCorrect() throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException {
        if (!isNewPasswordDataSet()) {
            return false;
        } else if (!isPasswordDataRight()) {
            return false;
        }
        return true;
    }

    private boolean isPasswordDataRight() throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException {
        if (!Reader.passwordCorrect(view.getCurrentPassword())) {
            MainController.alert("Error de contraseña", "La contraseña actual introducida no es correcta", "Aceptar");
            view.setFocusOnCurrenPassword();
            return false;
        }
        if (!MainController.validPassword(view.getNewPassword())) {
            MainController.alert("Error de contraseña", "Contraseña nueva no válida", "Aceptar");
            view.setFocusOnNewPassword();
            return false;
        }
        if (!passwordsAreEqual()) {
            MainController.alert("Error de contraseña", "Las contraseñas no coinciden", "Aceptar");
            view.setFocusOnNewPasswordRepetition();
            return false;
        }
        return true;
    }

    private boolean passwordsAreEqual() {
        char[] password;
        password = view.getNewPassword();

        char[] passwordRepetition;
        passwordRepetition = view.getNewPasswordRepetition();

        if (password.length != passwordRepetition.length) {
            flush(password);
            flush(passwordRepetition);
            return false;
        }

        for (int i = 0; i < password.length; i++) {
            if (password[i] != passwordRepetition[i]) {
                flush(password);
                flush(passwordRepetition);
                return false;
            }
        }
        flush(password);
        flush(passwordRepetition);
        return true;
    }

    private boolean isNewPasswordDataSet() {
        if (view.getCurrentPassword().length <= 0) {
            MainController.alert("Error de contraseña", "Ingrese su contraseña actual", "Aceptar");
            view.setFocusOnCurrenPassword();
            return false;
        } else if (view.getNewPassword().length <= 0) {
            MainController.alert("Error de contraseña", "Ingrese una contraseña nueva", "Aceptar");
            view.setFocusOnNewPassword();
            return false;
        } else if (view.getNewPasswordRepetition().length <= 0) {
            MainController.alert("Error de contraseña", "Ingrese nuevamente la contraseña nueva", "Aceptar");
            view.setFocusOnNewPasswordRepetition();
            return false;
        }
        return true;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    private void flush(char[] string) {
        for (int i = 0; i < string.length; i++) {
            string[i] = 0;
        }
    }
}
