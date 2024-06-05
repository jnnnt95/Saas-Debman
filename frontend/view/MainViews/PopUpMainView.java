package view.MainViews;

import control.MainController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import model.enums.OperationCode;
import view.pop_up_view.AddClientView;
import view.pop_up_view.AddDebtView;
import view.pop_up_view.AddSubuserView;
import view.pop_up_view.SetSubuserAbilityView;
import view.pop_up_view.LogInView;
import view.pop_up_view.ModifyClientView;
import view.pop_up_view.ModifyGeneralSettingsView;
import view.pop_up_view.ModifyPasswordView;
import view.pop_up_view.ModifyUserView;
import view.pop_up_view.OmachiView;
import view.pop_up_view.PerformDepositView;

public final class PopUpMainView extends javax.swing.JFrame {

    private FullSizeMainView fullSizeViewport;
    private final String sessionKey;
    private boolean updated;

    public PopUpMainView(String sessionKey) {
        this.sessionKey = sessionKey;
        updated = false;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Debtor Manager <Beta>");

        ImageIcon img = new ImageIcon("Images/icon.png");
        setIconImage(img.getImage());
    }

    public void updateView() {
        if (!updated) {
            setDefaultCloseOperation(
                    WindowConstants.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        MainController.executeOperation(OperationCode.cancelOperation, sessionKey);
                    } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                        Logger.getLogger(PopUpMainView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            updated = true;
        }
    }

    public void setInstantiationUserType(String userType, String user) {
        MainController.authenticate(sessionKey);
        setTitle("Debtor Manager | " + userType + ": " + user);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Default content placeholder");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(183, 183, 183)
                    .addComponent(jLabel1)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(jLabel1)
                    .addContainerGap(118, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel container;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    public void setFullSizeViewport(FullSizeMainView fullSizeViewport) {
        MainController.authenticate(sessionKey);
        this.fullSizeViewport = fullSizeViewport;
    }

    private void backToPopUpSize() {
        MainController.authenticate(sessionKey);
        setLocationRelativeTo(null);
        fullSizeViewport.setEnabled(false);
        setVisible(true);
        requestFocus();
    }

    private void setContainerContent(JPanel content) {
        MainController.authenticate(sessionKey);
        if (!content.getSize().
                equals(container.getSize())) {
            content.setSize(MainController.getPopUpSizeDimension());
        }
        container.getComponent(0).
                setVisible(false);
        container.removeAll();
        container.add(content);
        container.getComponent(0).
                setVisible(true);

    }

    // -------------------- Change-to-methods

    public void changeToAddSubuserMode(AddSubuserView addSubuserView) {
        MainController.authenticate(sessionKey);
        setContainerContent(addSubuserView.mainContainer);
        backToPopUpSize();
        addSubuserView.setMainElementFocus();
    }

    public void changeToModifyPersonalInfoMode(ModifyUserView modifyUserView) {
        MainController.authenticate(sessionKey);
        setContainerContent(modifyUserView.mainContainer);
        backToPopUpSize();
        modifyUserView.setMainElementFocus();
    }
    
    public void changeToModifyGeneralSettingsMode(ModifyGeneralSettingsView modifyGeneralSettingsView) {
        MainController.authenticate(sessionKey);
        setContainerContent(modifyGeneralSettingsView.mainContainer);
        backToPopUpSize();
        modifyGeneralSettingsView.setMainElementFocus();
    }
    
    public void changeToModifyPasswordMode(ModifyPasswordView modifyPasswordView) {
        MainController.authenticate(sessionKey);
        setContainerContent(modifyPasswordView.mainContainer);
        backToPopUpSize();
        modifyPasswordView.setMainElementFocus();
    }
    
    public void changeToCreateClientMode(AddClientView addClientView) {
        MainController.authenticate(sessionKey);
        setContainerContent(addClientView.mainContainer);
        backToPopUpSize();
        addClientView.setMainElementFocus();
    }

    public void changeToModifyClientMode(ModifyClientView modifyClientView) {
        MainController.authenticate(sessionKey);
        setContainerContent(modifyClientView.mainContainer);
        backToPopUpSize();
        modifyClientView.setMainElementFocus();
    }

    public void changeToDisableSubuserMode(SetSubuserAbilityView disableSubuserView) {
        MainController.authenticate(sessionKey);
        setContainerContent(disableSubuserView.mainContainer);
        backToPopUpSize();
        disableSubuserView.setMainElementFocus();
    }

    public void changeToPerformDepositMode(PerformDepositView performPaymentView) {
        MainController.authenticate(sessionKey);
        setContainerContent(performPaymentView.mainContainer);
        backToPopUpSize();
        performPaymentView.setMainElementFocus();
    }

    public void changeToAddDebtMode(AddDebtView addDebtView) {
        MainController.authenticate(sessionKey);
        setContainerContent(addDebtView.mainContainer);
        backToPopUpSize();
        addDebtView.setMainElementFocus();
    }

    public void startLoading(OmachiView loadingView) {
        MainController.authenticate(sessionKey);
        container.getComponent(0).
                setVisible(false);
        container.removeAll();
        container.add(loadingView.mainContainer);
        container.getComponent(0).
                setVisible(true);
        backToPopUpSize();
    }

    public void login(LogInView logInView) {
        MainController.authenticate(sessionKey);
        setContainerContent(logInView.mainContainer);
        backToPopUpSize();
        logInView.setMainElementFocus();
    }
}
