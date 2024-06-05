package control;

import model.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.full_size_view.QueryClientView;

public class QueryClientController {

    private List<Client> clients;
    private QueryClientView view;
    private ClientInfoController son;
    private Client activeClient;
    private String sessionKey;
    private List<Client> enabledClients;
    private boolean showDisabledClients;

    public QueryClientController(String sessionKey) throws IOException, ParseException, ClassNotFoundException, SQLException {
        this.sessionKey = sessionKey;
        view = new QueryClientView(this, sessionKey);
        view.updateView();
        activeClient = null;
        son = new ClientInfoController(sessionKey);
        showDisabledClients = false;
    }

    public QueryClientView getView() {
        verifySession();
        return view;
    }

    public void loginUpdate() {
        view.loginUpdate();
    }

    public void setViewData() throws IOException, ParseException, ClassNotFoundException, SQLException {
        verifySession();
        update();
    }

    public void setSearchTable() throws IOException, ParseException {
        if (this.clients == null) {
            this.clients = MainController.getClients();
        }
        List<Client> matches;
        matches = new ArrayList<>();

        for (Client client : clients) {
            if (removeDiacriticalMarks(client.getName()).toUpperCase().contains(removeDiacriticalMarks(view.getSearchFieldText().toUpperCase()))
                    || removeDiacriticalMarks(client.getNick().toUpperCase()).contains(removeDiacriticalMarks(view.getSearchFieldText().toUpperCase()))
                    || removeDiacriticalMarks(client.getCommentary().toUpperCase()).contains(removeDiacriticalMarks(view.getSearchFieldText().toUpperCase()))) {
                if (!client.isDisabled()) {
                    matches.add(client);
                } else if (showDisabledClients) {
                    matches.add(client);
                }
            }
        }

        setClientsAsResult(matches);
    }

    public void showDefaulters() {
        List<Client> matches;
        matches = new ArrayList<>();

        for (Client client : clients) {
            if (client.isDefaulter() && !client.isDisabled()) {
                matches.add(client);
            }
        }

        setClientsAsResult(matches);
    }

    private void setClientsAsResult(List<Client> matches) {
        view.setNewResultTableModel(matches);
    }

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Form.NFD).
                replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public void update() throws IOException, ParseException, ClassNotFoundException, SQLException {
        this.clients = MainController.getClients();
        setSearchTable();
    }

    public Object[][] getClientsData() throws IOException, ParseException {
        Object[][] clientsData;
        clientsData = new String[clients.size()][6];

        for (int i = 0; i
                < clientsData.length; i++) {
            clientsData[i][0] = clients.get(i).
                    getNick();
            clientsData[i][1] = clients.get(i).
                    getName();
            clientsData[i][2] = clients.get(i).getPhone();
            clientsData[i][3] = clients.get(i).getCommentary();
            clientsData[i][4] = String.valueOf(clients.get(i).
                    getTotalNotPaidBalance());
            if (clients.get(i).isDefaulter()) {
                clientsData[i][5] = "*";
            } else {
                clientsData[i][5] = "";
            }
        }

        return clientsData;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public void updateActiveClient(int clientId) throws IOException {
        for (int i = 0; i < clients.size(); i++) {
            if (clientId == clients.get(i).getId()) {
                activeClient = clients.get(i);
                break;
            }
        }

        try {
            MainController.changeToClientInfoMode(activeClient, sessionKey);
        } catch (ParseException ex) {
            Logger.getLogger(QueryClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setEnabledClients() {
        if (enabledClients == null) {
            enabledClients = new ArrayList<>();
            for (Client client : clients) {
                if (!client.isDisabled()) {
                    enabledClients.add(client);
                }
            }
        }
    }

    public List<Client> getEnabledClients() {
        if (enabledClients == null) {
            setEnabledClients();
        }
        return enabledClients;
    }

    public void toggleShowDisabledClients() throws IOException, ParseException {
        showDisabledClients = !showDisabledClients;
        setSearchTable();
        view.updateDisabledClientsButton();
    }
    
    public boolean getShowDisabledClients() {
        return showDisabledClients;
    }
}
