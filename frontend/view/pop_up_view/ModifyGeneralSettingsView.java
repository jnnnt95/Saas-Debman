package view.pop_up_view;

import control.MainController;
import control.ModifyGeneralSettingsController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class ModifyGeneralSettingsView extends javax.swing.JFrame {

    private boolean updated;
    private final ModifyGeneralSettingsController controller;
    private final String sessionKey;
    private boolean isOperable;

    /**
     * Creates new form modifyUserView
     * @param controller
     * @param sessionKey
     */
    public ModifyGeneralSettingsView(ModifyGeneralSettingsController controller, String sessionKey) {
        updated = false;
        isOperable = true;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        interestRateField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        interestRateOnDefaultField = new javax.swing.JTextField();
        modifyUserButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        daysToDefaultField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuración general", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Tasa de interés:");

        interestRateField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Tasa de interés en mora:");

        interestRateOnDefaultField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        modifyUserButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        modifyUserButton.setText("Cambiar configuración");
        modifyUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Días para mora:");

        daysToDefaultField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cancelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 153, 153));
        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("% (≥ 0)");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("(> 0)");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("% (≥ 0)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(daysToDefaultField, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(interestRateOnDefaultField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(interestRateField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(modifyUserButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(interestRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(interestRateOnDefaultField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daysToDefaultField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifyUserButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField daysToDefaultField;
    private javax.swing.JTextField interestRateField;
    private javax.swing.JTextField interestRateOnDefaultField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton modifyUserButton;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            interestRateField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyGeneralConfiguration();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyGeneralSettingsView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            interestRateOnDefaultField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyGeneralConfiguration();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyGeneralSettingsView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            daysToDefaultField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyGeneralConfiguration();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyGeneralSettingsView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            modifyUserButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        modifyGeneralConfiguration();
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                        Logger.getLogger(ModifyGeneralSettingsView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            modifyUserButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyGeneralConfiguration();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyGeneralSettingsView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelModifyingUserInfo();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelModifyingUserInfo();
                    }
                }
            });

            updated = true;
        }
    }
    
    private void disableOperation() {
        interestRateField.setEnabled(false);
        interestRateOnDefaultField.setEnabled(false);
        daysToDefaultField.setEnabled(false);
        
        modifyUserButton.setEnabled(false);
        cancelButton.setEnabled(false);
        
        isOperable = false;
    }
    
    public void enableOperation() {
        interestRateField.setEnabled(true);
        interestRateOnDefaultField.setEnabled(true);
        daysToDefaultField.setEnabled(true);
        
        modifyUserButton.setEnabled(true);
        cancelButton.setEnabled(true);
        
        isOperable = true;
    }
    
    public void modifyGeneralConfiguration() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException {
        if(isOperable) {
            disableOperation();
            controller.modifyGeneralConfiguration();
        }
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        interestRateField.requestFocus();
    }

    private void setFocus(JTextField field) {
        MainController.authenticate(sessionKey);
        field.requestFocus();
        field.selectAll();
    }
    
    public String getNewInterestRate() {
        MainController.authenticate(sessionKey);
        return interestRateField.getText().
                trim();
    }

    public void setFocusOnInterestRate() {
        MainController.authenticate(sessionKey);
        setFocus(interestRateField);
    }

    public String getNewInterestRateOnDefault() {
        MainController.authenticate(sessionKey);
        return interestRateOnDefaultField.getText().
                trim();
    }

    public void setFocusOnInterestRateOnDefault() {
        MainController.authenticate(sessionKey);
        setFocus(interestRateOnDefaultField);
    }

    public String getNewDaysToDefault() {
        MainController.authenticate(sessionKey);
        return daysToDefaultField.getText().
                trim();
    }

    public void setFocusOnDaysToDefault() {
        MainController.authenticate(sessionKey);
        setFocus(daysToDefaultField);
    }
    
    public void setInterestRate(String name) {
        MainController.authenticate(sessionKey);
        interestRateField.setText(name);
    }
    
    public void setInterestRateOnDefault(String nick) {
        MainController.authenticate(sessionKey);
        interestRateOnDefaultField.setText(nick);
    }
    
    public void setDaysToDafault(String cPNumber) {
        MainController.authenticate(sessionKey);
        daysToDefaultField.setText(cPNumber);
    }
}
