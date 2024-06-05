package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IO.Reader;
import model.IO.Writer;
import model.User;
import model.enums.OperationCode;
import view.pop_up_view.AddSubuserView;

/**
 *
 * @author admin
 */
public class AddSubuserController {

    private final AddSubuserView view;
    private final String sessionKey;
    private int subusersAllowed;
    private int subusersCreated;

    public AddSubuserController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new AddSubuserView(this, sessionKey);
        view.updateView();
        view.setMainElementFocus();

        verifySession();
    }

    public void loginUpdate() throws IOException, ClassNotFoundException, SQLException {
        subusersAllowed = Reader.subusersAllowed(MainController.getUser().getId());
        subusersCreated = Reader.subusersCreated(MainController.getUser().getId());
        if (subusersCreated != MainController.getUser().getSubusers().size()) {
            MainController.alert("Error", "Algo salió mal, el programa se cerrará", "Aceptar");
            System.exit(0);
        }
    }

    public void update() throws IOException, ClassNotFoundException, SQLException {
        loginUpdate();
    }

    public AddSubuserView getView() {
        return view;
    }

    public void addSubuser() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException, NoSuchAlgorithmException {
        if (isNewSubuserDataCorrect()) {
            String names;
            names = view.getNewNames();

            String surnames;
            surnames = view.getNewSurnames();

            String username;
            username = view.getNewUsername();

            char[] password;
            password = view.getNewPassword();

            String phone;
            phone = view.getNewCPNumber();

            String email;
            email = view.getNewEmail();

            String printable;
            printable
                    = "<html><strong>Datos del nuevo subusuario:</strong><br><br>"
                    + "   <strong>Nombres</strong>: " + names + "<br>"
                    + "   <strong>Apellidos</strong>: " + surnames + "<br>"
                    + "   <strong>Nombre de usuario</strong>: " + username + "<br>"
                    + "   <strong>Teléfono</strong>: " + phone + "<br>"
                    + "   <strong>Correo electrónico</strong>: " + email + "<br>";
            int selection;
            selection = MainController.confirm("Confirmar datos", printable, "Agregar subusuario", "Cancelar");
            switch (selection) {
                case 0:
                    int newSubuserId = Writer.addSubuser(
                            names,
                            surnames,
                            username,
                            password,
                            phone,
                            email
                    );
                    if (newSubuserId >= 0) {
                        User newSubuser;
                        newSubuser = new User(
                                newSubuserId,
                                MainController.getUser().getId(),
                                username,
                                phone,
                                "services_subuser",
                                email,
                                MainController.getTodayDateToString(),
                                names,
                                surnames,
                                MainController.getUser().getPreferredInterestRate(),
                                MainController.getUser().getPreferredInterestRateOnDefault(),
                                MainController.getUser().getDaysToDefault(),
                                null,
                                null
                        );
                        MainController.getUser().addSubuser(newSubuser);
                        flush(password);
                        clearData();
                        subusersCreated++;
                        Thread.sleep(5000);
                        MainController.executeOperation(OperationCode.completeOperation, sessionKey);
                    }
                    else {
                        MainController.alert("Se produjo un error", "Se canceló la operación de creación de subusario", "Aceptar");
                        flush(password);
                        clearData();
                        MainController.executeOperation(OperationCode.completeOperation, sessionKey);
                    }
                    break;
                case 1:
                    view.enableOperation();
                    break;
                case 2:
                    cancelAddingASubuser();
                    break;
            }
        }
        else {
            view.enableOperation();
        }
    }
    
    public void setReady() {
        view.clearData();
        view.enableOperation();
    }

    public void clearData() {
        view.clearData();
    }

    public void cancelAddingASubuser() {
        try {
            MainController.executeOperation(OperationCode.cancelOperation,
                    sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(AddSubuserController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private boolean isNewSubuserDataCorrect() throws SQLException, ClassNotFoundException, IOException {
        if (!isNewUserDataSet()) {
            return false;
        } else if (!isTextDataRight()) {
            return false;
        } else if (!isPasswordDataRight()) {
            return false;
        } else if (!isPhoneRight()) {
            return false;
        }
        return true;
    }

    private boolean isPasswordDataRight() {
        if (!MainController.validPassword(view.getNewPassword())) {
            MainController.alert("Nueva contraseña inválida", "La nueva contraseña debe tener al menos una letra y un número, y por lo menos 7 caracteres", "Aceptar");
            view.setFocusOnPassword();
            return false;
        }
        if (!passwordsAreEqual()) {
            MainController.alert("Error de contraseña", "Las contraseñas introducidas no coinciden", "Aceptar");
            view.setFocusOnPasswordRepetition();
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

    private void flush(char[] string) {
        for (int i = 0; i < string.length; i++) {
            string[i] = 0;
        }
    }

    private boolean isTextDataRight() throws SQLException, ClassNotFoundException, IOException {
        if (view.getNewNames().length() > 30) {
            MainController.alert("Error de nombres", "Los nombres deben tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnNames();
            return false;
        }
        if (view.getNewSurnames().length() > 30) {
            MainController.alert("Error de apellidos", "Los apellidos deben tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnSurnames();
            return false;
        }
        if (view.getNewUsername().length() > 30) {
            MainController.alert("Error de nombre de usuario", "El nombre de usuario debe tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnSurnames();
            return false;
        }
        if(!MainController.validUsername(view.getNewUsername())) {
            MainController.alert("Error de nombre de usuario", "El nombre de usuario no es válido, se permiten únicamente entre 8 y 30 caracteres alfanuméricos (sin tildes) y los símbolos guión bajo ('_') y punto ('.')", "Aceptar");
            view.setFocusOnUsername();
            return false;
        }
        if (Reader.newSubuserUsernameExists(view.getNewUsername())) {
            MainController.alert("Error de nombre de usuario", "El nombre de usuario introducido ya existe, inténte con uno diferente", "Aceptar");
            view.setFocusOnUsername();
            return false;
        }
        if (Reader.newPhoneExists(view.getNewCPNumber())) {
            MainController.alert("Error de número telefónico", "El número teléfonico ya existe, intente con uno diferente", "Aceptar");
            view.setFocusOnPhone();
            return false;
        }
        if (view.getNewEmail().length() > 60) {
            MainController.alert("Error de correo electrónico", "El correo electrónico debe tener 60 caracteres o menos", "Aceptar");
            view.setFocusOnEmail();
            return false;
        }
        if(!MainController.validEmail(view.getNewEmail())) {
            MainController.alert("Error de correo electrónico", "El correo electrónico no es válido", "Aceptar");
            view.setFocusOnEmail();
            return false;
        }
        if (Reader.newEmailExists(view.getNewEmail())) {
            MainController.alert("Error de correo electrónico", "El correo electrónico intriducido ya existe, intente con uno diferente", "Aceptar");
            view.setFocusOnEmail();
            return false;
        }
        return true;
    }

    private boolean isNewUserDataSet() {
        if (view.getNewNames().length() <= 0) {
            MainController.alert("Error de nombres", "El campo de nombres no puede estar vacío", "Aceptar");
            view.setFocusOnNames();
            return false;
        } else if (view.getNewSurnames().length() <= 0) {
            MainController.alert("Error de apellidos", "El campo de apellidos no puede estar vacío", "Aceptar");
            view.setFocusOnSurnames();
            return false;
        } else if (view.getNewUsername().length() <= 0) {
            MainController.alert("Error de nombre de usuario", "El campo de nombre de usuario no puede estar vacío", "Aceptar");
            view.setFocusOnUsername();
            return false;
        } else if (view.getNewPassword().length <= 0) {
            MainController.alert("Error de contraseña", "El campo de contraseña no puede estar vacío", "Aceptar");
            view.setFocusOnPassword();
            return false;
        } else if (view.getNewPasswordRepetition().length <= 0) {
            MainController.alert("Error de repetición de contraseña", "El campo de repetición de contraseña no puede estar vacío", "Aceptar");
            view.setFocusOnPasswordRepetition();
            return false;
        } else if (view.getNewCPNumber().length() <= 0) {
            MainController.alert("Error de teléfono", "El campo de teléfono no puede estar vacío", "Aceptar");
            view.setFocusOnPhone();
            return false;
        } else if (view.getNewEmail().length() <= 0) {
            MainController.alert("Error de correo electrónico", "El campo de correo electrónico no puede estar vacío", "Aceptar");
            view.setFocusOnEmail();
            return false;
        }
        return true;
    }

    private boolean isPhoneRight() {
        //check number for length
        String cpNumber;
        cpNumber = view.getNewCPNumber();

        if (cpNumber.length() > 10) {
            MainController.alert("Error de número telefónico", "Número telefónico no válido, debe tener máximo 10 dígitos", "Aceptar");
            view.setFocusOnPhone();
            return false;
        }
        if (!(cpNumber.length() == 7 || cpNumber.length() == 10)) {
            MainController.alert("Error de número telefónico", "El número teléfonico debe tener 7 o 10 dígitos", "Aceptar");
            view.setFocusOnPhone();
            return false;
        }

        //check number for suitability
        for (int i = 0; i < cpNumber.length(); i++) {
            if (!MainController.isNumberADigit(String.valueOf(cpNumber.charAt(i)))) {
                MainController.alert("Error de número telefónico", "Número telefónico no válido, debe contener solamente números", "Aceptar");
                view.setFocusOnPhone();
                return false;
            }
        }

        // return true if any of above return false first
        return true;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public boolean canCreateSubuser() {
        return subusersCreated < subusersAllowed;
    }

    public int getTotalLeftSubusersToCreate() {
        return subusersAllowed - subusersCreated;
    }
}
