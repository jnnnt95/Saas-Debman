package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import model.IO.Writer;
import model.enums.OperationCode;
import view.pop_up_view.ModifyClientView;


/**
 *
 * @author admin
 */
public class ModifyClientController {
    private Client currentClient;
    private final ModifyClientView view;
    private final String sessionKey;
    
    public ModifyClientController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new ModifyClientView(this, sessionKey);
        view.updateView();
        view.setMainElementFocus();
        
        verifySession();
    }
    
    public ModifyClientView getView() {
        return view;
    }
    
    public void setReady(Client client) {
        setCurrentClient(client);
        setClientData();
        view.enableOperation();
    }
    
    private void setCurrentClient(Client client) {
        this.currentClient = client;
    }
    
    public void setClientData() {
        view.
                setClientName(currentClient.getName());
        view.
                setClientNick(currentClient.getNick());
        view.
                setClientCPNumber(currentClient.getPhone());
        view.
                setClientCommentary(currentClient.getCommentary());
        view.
                setClientInterestRate(currentClient.getInterestRate());
        view.
                setClientInterestRateOnDefault(currentClient.getInterestRateOnDefault());
                
    }
    
    public void modifyClient() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException {
        if(isNewClientDataCorrect()) {
            String name;
            name = view.getNewName();
            
            String nick;
            nick = view.getNewNick();
            
            String cpNumber;
            cpNumber = view.getNewCPNumber();
            
            String commentary;
            commentary = view.getNewCommentary();
            
            String interestRate;
            interestRate = view.getNewInterestRate();
            
            String interestRateOnDefault;
            interestRateOnDefault = view.getNewInterestRateOnDefault();

            String printable;
            printable = "<html><strong>Nuevos datos de cliente</strong><br><br>"
                    + "   <strong>Nombre</strong>: " + name + "<br>"
                    + "   <strong>Nick</strong>: " + nick + "<br>"
                    + "   <strong>Teléfono</strong>: " + cpNumber + "<br>"
                    + "   <strong>Comentario</strong>: " + commentary + "<br>"
                    + "   <strong>Tasa de interés</strong>: " + interestRate + " %<br>"
                    + "   <strong>Tasa de interés en mora</strong>: " + interestRateOnDefault + " %</html>";
            int selection;
            selection = MainController.confirm("Confirmar cambios", printable, "Modificar usuario", "Cancelar");
            switch(selection) {
                case 0:
                    currentClient.setName(name);
                    currentClient.setNick(nick);
                    currentClient.setPhone(cpNumber);
                    currentClient.setCommentary(commentary);
                    currentClient.setInterestRate(Float.parseFloat(interestRate));
                    currentClient.setInterestRateOnDefault(Float.parseFloat(interestRateOnDefault));

                    Writer.modifyClient(currentClient);
                    MainController.changeToClientInfoMode(currentClient,
                            sessionKey);
                    break;
                case 1:
                    view.enableOperation();
                    break;
                case 2:
                    cancelModifyingAClient();
                    break;
            }
        }
        else {
            view.enableOperation();
        }
    }
    
    public void cancelModifyingAClient() {
        try {
            MainController.executeOperation(OperationCode.cancelOperation,
                    sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(ModifyClientController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isNewClientDataCorrect() {
        if(!isNewClientDataSet()) {
            return false;
        }
        else if(!isTextDataRight()) {
            return false;
        }
        else if(!isCPNumberRight()) {
            return false;
        }
        else if (!isInterestRateRight()) {
            return false;
        }
        else if(!isInterestRateOnDefaultRight()) {
            return false;
        }
        return true;
    }
    
    private boolean isInterestRateRight() {
        if(MainController.validInterestRate(view.getNewInterestRate())) {
           return true; 
        }
        else {
            MainController.alert("Error de tasa de interés", "El valor de interés no es válido, inténtelo de nuevo", "Aceptar");
            view.setFocusOnInterestRate();
            return false;
        }
    }
    
    private boolean isInterestRateOnDefaultRight() {
        if(MainController.validInterestRate(view.getNewInterestRateOnDefault())) {
           return true; 
        }
        else {
            MainController.alert("Error de tasa de interés en mora", "El valor de interés en mora no es válido, inténtelo de nuevo", "Aceptar");
            view.setFocusOnInterestRateOnDefault();
            return false;
        }
    }
    
    private boolean isTextDataRight() {
        if(view.getNewName().length() > 30) {
            MainController.alert("Error de nombre", "El nombre debe tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnName();
            return false;
        }
        if(view.getNewNick().length() > 30) {
            MainController.alert("Error de nick", "El nick debe tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnNick();
            return false;
        }
        if(view.getNewCommentary().length() > 30) {
            MainController.alert("Error de comentario", "El comentario debe tener 30 caracteres o menos", "Aceptar");
            view.setFocusOnCommentary();
            return false;
        }
        return true;
    }
    
    private boolean isNewClientDataSet() {
        if(view.getNewName().length() <= 0) {
            MainController.alert("Error de nombre", "El campo del nombre no puede estar vacío", "Aceptar");
            view.setFocusOnName();
            return false;
        }
        return true;
    }
    
    private boolean isCPNumberRight() {
        //check number for length
        String cpNumber;
        cpNumber = view.getNewCPNumber();
        
        if(cpNumber.length() > 10) {
            MainController.alert("Error de número telefónico", "Número telefónico debe tener máximo 10 dígitos", "Aceptar");
            view.setFocusOnCPNumber();
            return false;
        }
        
        //check number for suitability
        for(int i = 0; i < cpNumber.length(); i++) {
            if(!MainController.isNumberADigit(String.valueOf(cpNumber.charAt(i)))) {
                MainController.alert("Error de número telefónico", "Número telefónico debe contener solamente números", "Aceptar");
                view.setFocusOnCPNumber();
                return false;
            }
        }
        
        // return true if any of above return false first
        return true;
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
