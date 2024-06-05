package view.full_size_view;

import control.MainController;
import control.QueryClientController;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import jxl.write.WriteException;
import model.Client;
import model.IO.Reader;
import model.IO.Writer;
import model.enums.UserType;

/**
 *
 * @author admin
 */
public class QueryClientView
        extends javax.swing.JFrame {

    private boolean updated;
    private boolean loginUpdated;
    private final QueryClientController controller;
    private final String sessionKey;

    /**
     * Creates new form ClientInfo
     *
     * @param controller
     * @param sessionKey
     */
    public QueryClientView(QueryClientController controller, String sessionKey) {
        updated = false;
        loginUpdated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        searchClientButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        exportToExcelButton = new javax.swing.JButton();
        disabledClientsButton = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Ingrese nombre, nick o comentario");

        searchTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        searchTextField.setFocusCycleRoot(true);
        searchTextField.setFocusTraversalPolicyProvider(true);

        resultTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nick", "Nombre", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(resultTable);
        if (resultTable.getColumnModel().getColumnCount() > 0) {
            resultTable.getColumnModel().getColumn(0).setResizable(false);
            resultTable.getColumnModel().getColumn(1).setResizable(false);
            resultTable.getColumnModel().getColumn(2).setResizable(false);
            resultTable.getColumnModel().getColumn(3).setResizable(false);
        }

        searchClientButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        searchClientButton.setForeground(new java.awt.Color(153, 204, 0));
        searchClientButton.setText("Buscar cliente");
        searchClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        clearButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        clearButton.setText("Limpiar");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        exportToExcelButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        exportToExcelButton.setForeground(new java.awt.Color(102, 204, 255));
        exportToExcelButton.setText("Exportar a Excel");
        exportToExcelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportToExcelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToExcelButtonActionPerformed(evt);
            }
        });

        disabledClientsButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        disabledClientsButton.setForeground(new java.awt.Color(204, 153, 0));
        disabledClientsButton.setText("Mostrar clientes deshabilitados");
        disabledClientsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchTextField)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchClientButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportToExcelButton))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(disabledClientsButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchClientButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportToExcelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disabledClientsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void exportToExcelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToExcelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportToExcelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton disabledClientsButton;
    private javax.swing.JButton exportToExcelButton;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JTable resultTable;
    private javax.swing.JButton searchClientButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            searchTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.setSearchTable();
                        } catch (IOException | ParseException ex) {
                            Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (event.getKeyCode()
                            == KeyEvent.VK_ESCAPE) {
                        setSearchFieldText("");
                        try {
                            controller.setSearchTable();
                        } catch (IOException | ParseException ex) {
                            Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            searchClientButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.setSearchTable();
                    } catch (IOException | ParseException ex) {
                        Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            clearButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setSearchFieldText("");
                    try {
                        controller.setSearchTable();
                    } catch (IOException | ParseException ex) {
                        Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            resultTable.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int activeClientId = (Integer) resultTable.getModel().getValueAt(resultTable.getSelectedRow(), 0);
                        try {
                            controller.updateActiveClient(activeClientId);
                        } catch (IOException ex) {
                            Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            disabledClientsButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.toggleShowDisabledClients();
                    } catch (IOException ex) {
                        Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            updated = true;
        }
    }
    
    public void updateDisabledClientsButton() {
        if(controller.getShowDisabledClients()) {
            disabledClientsButton.setText("Ocultar clientes deshabilitados");
        }
        else {
            disabledClientsButton.setText("Mostrar clientes deshabilitados");
        }
    }

    public void exportToExcel() throws IOException, WriteException, ParseException {
        MainController.authenticate(sessionKey);
        Object[][] clientsData;
        clientsData = controller.getClientsData();
        Writer.exportToExcel(clientsData);
    }

    private void openExportedData() throws IOException {
        MainController.authenticate(sessionKey);
        Reader.openExportedData();
    }

    public JPanel getMainContainer() {
        MainController.authenticate(sessionKey);
        return mainContainer;
    }

    public void setSearchFieldText(String text) {
        MainController.authenticate(sessionKey);
        searchTextField.setText(text);
    }

    public String getSearchFieldText() {
        MainController.authenticate(sessionKey);
        return searchTextField.getText().
                trim();
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        searchTextField.requestFocus();
    }

    public void setNewResultTableModel(List<Client> matches) {
        MainController.authenticate(sessionKey);
        resultTable.setModel(getNewResultTableModel(matches));
        resultTable.removeColumn(resultTable.getColumnModel().getColumn(0));
        setResultTableModelFormat(matches);
    }

    private void setResultTableModelFormat(List<Client> matches) {
        MainController.authenticate(sessionKey);
        if (resultTable.getColumnModel().getColumnCount() > 0) {
            resultTable.getColumnModel().getColumn(0).setResizable(false);
            resultTable.getColumnModel().getColumn(1).setResizable(false);
            resultTable.getColumnModel().getColumn(2).setResizable(false);
            resultTable.getColumnModel().getColumn(3).setResizable(false);
            resultTable.getColumnModel().getColumn(4).setResizable(false);
        }

        resultTable.getTableHeader().setResizingAllowed(false);
        resultTable.getTableHeader().setReorderingAllowed(false);
        resultTable.getColumnModel().getColumn(2).setPreferredWidth(15);

        TableColumnModel columns;
        columns = resultTable.getColumnModel();

        for (int i = 0; i < columns.getColumnCount(); i++) {
            resultTable.setDefaultRenderer(resultTable.getColumnClass(i), new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (matches.get(matches.size() - (row + 1)).isDisabled()) {
                        setForeground(new Color(204,153,0));
                    } else {
                        setForeground(Color.BLACK);
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            });
        }
    }

    private DefaultTableModel getNewResultTableModel(List<Client> matches) {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;

        objectMatrix = new Object[matches.size()][6];
        int matchesSize = matches.size();
        for (int i = 0; i < matchesSize; i++) {
            objectMatrix[i][0] = matches.get(matchesSize - (i + 1)).getId();
            objectMatrix[i][1] = matches.get(matchesSize - (i + 1)).getName();
            objectMatrix[i][2] = matches.get(matchesSize - (i + 1)).getNick();
            objectMatrix[i][3] = matches.get(matchesSize - (i + 1)).getPhone();
            objectMatrix[i][4] = matches.get(matchesSize - (i + 1)).getCommentary();
            objectMatrix[i][5] = matches.get(matchesSize - (i + 1)).getCreatedBy();
        }

        DefaultTableModel model;
        model = new DefaultTableModel(
                objectMatrix,
                new String[]{
                    "Id",
                    "Nombre",
                    "Nick",
                    "Teléfono",
                    "Comentario",
                    "Creado por"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            Class[] types = new Class[]{
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        return model;
    }

    public void loginUpdate() {
        if (!loginUpdated) {
            if (MainController.getUser().
                    getType() == UserType.servicesUser) {
                exportToExcelButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            exportToExcel();
                            openExportedData();
                        } catch (IOException | WriteException ex) {
                            Logger.getLogger(QueryClientController.class.
                                    getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(QueryClientView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } else {
                exportToExcelButton.setVisible(false);
            }
            loginUpdated = true;
        }
    }
}
