package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IO.Writer;
import model.User;
import model.enums.OperationCode;
import view.pop_up_view.SetSubuserAbilityView;

/**
 *
 * @author admin
 */
public class SetSubuserAbilityController {

    private final SetSubuserAbilityView view;
    private final String sessionKey;
    private List<User> subusers;

    public SetSubuserAbilityController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new SetSubuserAbilityView(this, sessionKey);
        view.updateView();
        view.setMainElementFocus();

        verifySession();
    }

    public SetSubuserAbilityView getView() {
        return view;
    }
    
    public void setAbility() throws ClassNotFoundException, SQLException, IOException, ParseException, InterruptedException {
        int subuserId;
        subuserId = view.getSelectedSubuser();
        
        User subuser = null;
        for(int i = 0; i < subusers.size(); i++) {
            if(subuserId == subusers.get(i).getId()) {
                subuser = subusers.get(i);
                break;
            }
        }
        
        if(subuser.isDisabledSubuser()) {
            enableSubuser(subuser);
        }
        else {
            disableSubuser(subuser);
        }
    }

    public void enableSubuser(User subuserToDisable) throws ClassNotFoundException, SQLException, IOException, ParseException, InterruptedException {
        String printable;
        printable = "<html>Se habilitará al subusuario <i>" + subuserToDisable.getName() + 
                "</i><br><br><strong>¿Continuar?</strong><html>";
        int selection;
        selection = MainController.confirm("Aviso", printable, "Habilitar subusuario", "Cancelar");
        switch (selection) {
            case 0:
                Writer.enableSubuser(subuserToDisable);
                subuserToDisable.setDisabledSubuser(false);
                update();
                MainController.executeOperation(OperationCode.completeOperation, sessionKey);
                break;
            case 1:
                break;
            case 2:
                cancelSettingSubuserAbility();
                break;
        }
    }

    public void disableSubuser(User subuserToDisable) throws ClassNotFoundException, SQLException, IOException, ParseException, InterruptedException {
        String printable;
        printable = "<html>Se deshabilitará al subusuario <i>" + subuserToDisable.getName() + 
                "</i><br><br><strong>¿Continuar?</strong><html>";
        int selection;
        selection = MainController.confirm("Aviso", printable, "deshabilitar subusuario", "Cancelar");
        switch (selection) {
            case 0:
                Writer.disableSubuser(subuserToDisable);
                subuserToDisable.setDisabledSubuser(true);
                update();
                MainController.executeOperation(OperationCode.completeOperation, sessionKey);
                break;
            case 1:
                break;
            case 2:
                cancelSettingSubuserAbility();
                break;
        }
    }

    public void update() throws IOException, ParseException, ClassNotFoundException, SQLException {
        setSubusers();
        setSubuserTable();
    }
    
    public void setSubuserTable() {
        view.setNewSubuserTableModel(subusers);
    }
    
    private void setSubusers() throws IOException, ParseException, ClassNotFoundException, SQLException {
        this.subusers = MainController.getUser().getSubusers();
    }

    public void cancelSettingSubuserAbility() {
        try {
            MainController.executeOperation(OperationCode.cancelOperation,
                    sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(SetSubuserAbilityController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
