package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import model.IO.Reader;
import model.IO.Writer;
import model.User;
import model.enums.OperationCode;
import view.pop_up_view.LogInView;

/**
 *
 * @author admin
 */
public class LogInController {

    private final LogInView view;
    private final String sessionKey;
    private User user;

    public LogInController(String sessionKey)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        this.sessionKey = sessionKey;
        view = new LogInView(this, sessionKey);
        view.updateView();
    }

    public LogInView getView() {
        return view;
    }

    public void logIn()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException,
            InterruptedException,
            NoSuchAlgorithmException {

        String username = view.getUsername();
        char[] password = view.getPassword();
        user = Reader.authenticate(username, password);
        if(user != null) {
            MainController.executeOperation(OperationCode.launchApplication,
                    sessionKey);
            view.clear();
        }
        else {
            view.setFocusOnUsername();
        }
    }

    public User getUser() {
        return user;
    }
    
    public void requestPasswordReset(String email) throws IOException {
        if(MainController.validEmail(email)) {
            Writer.requestPasswordReset(email);
            MainController.alert("Registro exitoso", "La solicitud realizada se registró adecuadamente", "Aceptar");
            System.exit(0);
        }
        else {
            MainController.alert("Error", "No ingresó un correo válido.", "Aceptar");
            view.requestPasswordReset();
        }
    }
}
