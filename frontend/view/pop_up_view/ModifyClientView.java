package view.pop_up_view;

import control.MainController;
import control.ModifyClientController;
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
public class ModifyClientView extends javax.swing.JFrame {

    private boolean updated;
    private final ModifyClientController controller;
    private final String sessionKey;
    private boolean isOperable;

    /**
     * Creates new form modifyClientView
     * @param controller
     * @param sessionKey
     */
    public ModifyClientView(ModifyClientController controller, String sessionKey) {
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
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nickTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        modifyClientButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cpNumberField = new javax.swing.JTextField();
        commentaryField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        interestRateField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        interestRateOnDefaultField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Nombre:");

        nameTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Nick:");

        nickTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("Comentario:");

        modifyClientButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        modifyClientButton.setText("Modificar cliente");
        modifyClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Teléfono:");

        cpNumberField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        commentaryField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cancelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 153, 153));
        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("Tasa de interés:");

        interestRateField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("Tasa de interés en mora:");

        interestRateOnDefaultField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("% (≥ 0)");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("% (≥ 0)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(commentaryField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cpNumberField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nickTextField)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 306, Short.MAX_VALUE)
                        .addComponent(modifyClientButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(interestRateOnDefaultField, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(interestRateField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(commentaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(interestRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(interestRateOnDefaultField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyClientButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField commentaryField;
    private javax.swing.JTextField cpNumberField;
    private javax.swing.JTextField interestRateField;
    private javax.swing.JTextField interestRateOnDefaultField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton modifyClientButton;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField nickTextField;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            nameTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyClient();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            nickTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyClient();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cpNumberField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyClient();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            commentaryField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyClient();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            interestRateField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyClient();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
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
                            modifyClient();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            modifyClientButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        modifyClient();
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                        Logger.getLogger(ModifyClientView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            modifyClientButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyClient();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelModifyingAClient();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelModifyingAClient();
                    }
                }
            });

            updated = true;
        }
    }
    
    private void disableOperation() {
        nameTextField.setEnabled(false);
        nickTextField.setEnabled(false);
        cpNumberField.setEnabled(false);
        commentaryField.setEnabled(false);
        interestRateField.setEnabled(false);
        interestRateOnDefaultField.setEnabled(false);
        
        modifyClientButton.setEnabled(false);
        cancelButton.setEnabled(false);
        
        isOperable = false;
    }
    
    public void enableOperation() {
        nameTextField.setEnabled(true);
        nickTextField.setEnabled(true);
        cpNumberField.setEnabled(true);
        commentaryField.setEnabled(true);
        interestRateField.setEnabled(true);
        interestRateOnDefaultField.setEnabled(true);
        
        modifyClientButton.setEnabled(true);
        cancelButton.setEnabled(true);
        
        isOperable = true;
    }
    
    private void modifyClient() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException {
        if(isOperable) {
            disableOperation();
            controller.modifyClient();
        }
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        nameTextField.requestFocus();
    }

    private void setFocus(JTextField field) {
        MainController.authenticate(sessionKey);
        field.requestFocus();
        field.selectAll();
    }
    
    public String getNewName() {
        MainController.authenticate(sessionKey);
        return nameTextField.getText().
                trim();
    }

    public void setFocusOnName() {
        MainController.authenticate(sessionKey);
        setFocus(nameTextField);
    }

    public String getNewNick() {
        MainController.authenticate(sessionKey);
        return nickTextField.getText().
                trim();
    }

    public String getNewInterestRate() {
        MainController.authenticate(sessionKey);
        return interestRateField.getText().
                trim();
    }

    public String getNewInterestRateOnDefault() {
        MainController.authenticate(sessionKey);
        return interestRateOnDefaultField.getText().
                trim();
    }

    public void setFocusOnNick() {
        MainController.authenticate(sessionKey);
        setFocus(nickTextField);
    }

    public void setFocusOnInterestRate() {
        MainController.authenticate(sessionKey);
        setFocus(interestRateField);
    }

    public void setFocusOnInterestRateOnDefault() {
        MainController.authenticate(sessionKey);
        setFocus(interestRateOnDefaultField);
    }

    public String getNewCPNumber() {
        MainController.authenticate(sessionKey);
        return cpNumberField.getText().
                trim();
    }

    public void setFocusOnCPNumber() {
        MainController.authenticate(sessionKey);
        setFocus(cpNumberField);
    }

    public String getNewCommentary() {
        MainController.authenticate(sessionKey);
        return commentaryField.getText().
                trim();
    }

    public void setFocusOnCommentary() {
        MainController.authenticate(sessionKey);
        setFocus(commentaryField);
    }
    
    public void setClientName(String name) {
        MainController.authenticate(sessionKey);
        nameTextField.setText(name);
    }
    
    public void setClientNick(String nick) {
        MainController.authenticate(sessionKey);
        nickTextField.setText(nick);
    }
    
    public void setClientCPNumber(String cPNumber) {
        MainController.authenticate(sessionKey);
        cpNumberField.setText(cPNumber);
    }
    
    public void setClientCommentary(String area) {
        MainController.authenticate(sessionKey);
        commentaryField.setText(area);
    }
    
    public void setClientInterestRate(Float interestRate) {
        MainController.authenticate(sessionKey);
        interestRateField.setText(String.valueOf(interestRate));
    }
    
    public void setClientInterestRateOnDefault(Float interestRateOnDefault) {
        MainController.authenticate(sessionKey);
        interestRateOnDefaultField.setText(String.valueOf(interestRateOnDefault));
    }
}
