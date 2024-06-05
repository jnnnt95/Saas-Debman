package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IO.Reader;
import model.IO.Writer;
import model.User;
import model.enums.OperationCode;
import view.pop_up_view.ModifyUserView;


/**
 *
 * @author admin
 */
public class ModifyUserController {
    private User user;
    private final ModifyUserView view;
    private final String sessionKey;
    
    public ModifyUserController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new ModifyUserView(this, sessionKey);
        view.updateView();
        view.setMainElementFocus();
        
        verifySession();
    }
    
    public ModifyUserView getView() {
        return view;
    }
    
    public void setUserData() {
        view.setUserNames(user.getNames());
        view.setUserSurnames(user.getSurnames());
        view.setUserPhone(user.getPhone());
    }
    
    public void loginUpdate() {
        this.user = MainController.getUser();
    }
    
    public void modifyUser() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException {
        if(isNewUserDataCorrect()) {
            String names;
            names = view.getNewNames();
            
            String surnames;
            surnames = view.getNewSurnames();
            
            String phone;
            phone = view.getNewCPNumber();

            String printable;
            printable = "<html><strong>Nueva información</strong><br><br>"
                    + "   <strong>Nombres</strong>: " + names + "<br>"
                    + "   <strong>Apellidos</strong>: " + surnames + "<br>"
                    + "   <strong>Teléfono</strong>: " + phone + "<br>";
            int selection;
            selection = MainController.confirm("Confirmar cambios", printable, "Modificar información", "Cancelar");
            switch(selection) {
                case 0:
                    user.setNames(names);
                    user.setSurnames(surnames);
                    user.setPhone(phone);

                    Writer.modifyUser(user);
                    MainController.updateClients();
                    MainController.executeOperation(OperationCode.completeOperation, sessionKey);
                    break;
                case 1:
                    break;
                case 2:
                    cancelModifyingAnUser();
                    break;
            }
            MainController.updateMainFrame();
        }
    }
    
    public void cancelModifyingAnUser() {
        try {
            MainController.executeOperation(OperationCode.cancelOperation,
                    sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(ModifyUserController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isNewUserDataCorrect() throws IOException {
        if(!isNewUserDataSet()) {
            return false;
        }
        else if(!isTextDataRight()) {
            return false;
        }
        else if(!isPhoneRight()) {
            return false;
        }
        return true;
    }
    
    private boolean isTextDataRight() {
        if(view.getNewNames().length() > 30) {
            MainController.alert("Error de nombres", "Los nombres deben tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnNames();
            return false;
        }
        if(view.getNewSurnames().length() > 30) {
            MainController.alert("Error de apellidos", "Los Apellidos deben tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnSurnames();
            return false;
        }
        return true;
    }
    
    private boolean isNewUserDataSet() {
        if(view.getNewNames().length() <= 0) {
            MainController.alert("Error de nombres", "El campo de nombres no puede estar vacío", "Aceptar");
            view.setFocusOnNames();
            return false;
        }
        else if(view.getNewSurnames().length() <= 0) {
            MainController.alert("Error de apellidos", "El campo de apellidos no puede estar vacío", "Aceptar");
            view.setFocusOnSurnames();
            return false;
        }
        return true;
    }
    
    private boolean isPhoneRight() throws IOException {
        //check number for length
        String cpNumber;
        cpNumber = view.getNewCPNumber();
        
        if(cpNumber.length() > 10) {
            MainController.alert("Error de número telefónico", "Número telefónico no válido, debe tener máximo 10 dígitos", "Aceptar");
            view.setFocusOnPhone();
            return false;
        }
        if(!(cpNumber.length() == 10 || cpNumber.length() == 7)) {
            MainController.alert("Error de número telefónico", "Número telefónico no válido, debe tener 7 o 10 dígitos", "Aceptar");
            view.setFocusOnPhone();
            return false;
        }
        if(Reader.newPhoneExists(cpNumber)) {
            MainController.alert("Error de número telefónico", "Número telefónico ya registrado, intente con uno diferente", "Aceptar");
            view.setFocusOnPhone();
            return false;
        }
        
        //check number for suitability
        for(int i = 0; i < cpNumber.length(); i++) {
            if(!MainController.isNumberADigit(String.valueOf(cpNumber.charAt(i)))) {
                MainController.alert("Error de número telefónico", "Número telefónico no válido, debe contener solamente números", "Aceptar");
                view.setFocusOnPhone();
                return false;
            }
        }
        
        // return true no false return prior this point
        return true;
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
