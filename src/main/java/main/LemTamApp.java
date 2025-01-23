
package main;

import controllers.*;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import models.*;
import javax.swing.table.DefaultTableModel;

public class LemTamApp extends javax.swing.JFrame {

    private DefaultTableModel dtm;
    private String currentTable;
    private List<Socio> listSocio;
    private List<Prenda> listPrenda;
    private List<Prestamo> listPrestamo;
    private Frame t = this;
    private Socio socio = null;
    
    private VerSocio socioDialog;
    
    private boolean scanMode = false;
    private String scan = "";
    
    
    void QREvents(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
              
              //Ignora eventos que no sean de pulsación de tecla
              if(KeyEvent.KEY_PRESSED!=e.getID()){
                  return false;
              }
              
              //Acciones del modo de escaneo
              if(scanMode){
                  //Si se introduce el código de ENTER se termina el escaneo y se procesa el resultado
                  if(e.getKeyCode()==10){
                      scanMode=false;
                      
                      //Ignora entradas sin el formato adecuado
                      String[] res = scan.split(":");
                      if(res.length!=2 && !res[0].equals("lemtam")){
                          return true;
                      }
                      
                      int id = Integer.parseInt(res[1]);
                      
                      //Si no existe ninguna prenda con la id introducida se ignora la entrada
                      if(PrendaController.getPrenda(id)==null){
                          return true;
                      }
                      
                      //Si la prenda está disponible se considerará para préstamo
                      if(PrendaController.getPrenda(id).isDisponible()){
                        //Si hay un diálogo de socio abierto se crea el préstamo automáticamente
                        if(socioDialog!=null){
                            PrestamoController.createPrestamo(socio.getId(), id, null);
                            socioDialog.loadPrestamos();
                        }
                        
                        //En caso contrario abre el diálogo de creación de préstamos
                        else{
                            new NuevoPrestamo(t, rootPaneCheckingEnabled, PrendaController.getPrenda(id)).setVisible(true);
                        }
                        
                        //Actualiza los datos de las tablas
                        loadDB();
                        if(currentTable.equals("Préstamos")){
                            loadPrestamos();
                        }
                        else if(currentTable.equals("Prendas")){
                            loadPrendas();
                        }
                      }
                      
                      //Si la prenda no está disponible (está prestada) se borra el préstamo
                      else{
                            PrestamoController.borrarPrestamo(PrestamoController.getPrestamosPrenda(id).get(0).getId());
                            loadDB();
                            if(currentTable.equals("Préstamos")){
                                loadPrestamos();
                            }
                            else if(currentTable.equals("Prendas")){
                                loadPrendas();
                            }
                            if(socioDialog!=null){
                                socioDialog.loadPrestamos();
                            }
                      }                      
                  }
                  else{
                    scan+=e.getKeyChar();
                  }
                  
                  return true;
              }
              
              else{
                  //Si se introduce el caracter clave se inicia el modo de escaneo
                  if(e.getKeyChar()=='~'){  
                  scanMode=true;
                  scan="";
                  return true;
                }
              }            
              
              
              return false;
            }
      });
    }
    
    
    /**
     * Creates new form NewJFrame
     */
    public LemTamApp() {
        initComponents();
        this.setLocationRelativeTo(null);
        QREvents();
        
        loadDB();
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadPrestamos();
    }

    
    void loadDB(){
        listSocio = SocioController.getAll();
        listPrenda = PrendaController.getAll();
        listPrestamo = PrestamoController.getAll();
    }
    
    void loadSocios(){
        String[] cols = {"Nombre"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0){
            public boolean isCellEditable(int row, int col) {
            return false;
            }
        };
        
        for(Socio s:listSocio){
            String[] row = {s.getNombre()};
            dtm.addRow(row);
        }
        
        jTable1.setModel(dtm);
        currentTable="Socios";
        nomTabla.setText(currentTable);
        socios.setEnabled(false);
        prestamos.setEnabled(true);
        prendas.setEnabled(true);
        buscarBoton.setEnabled(true);
        buscarTexto.setEnabled(true);
        nuevo.setEnabled(true);
    }
    
    
    void loadPrendas(){
        String[] cols = {"Tipo", "Color", "Talla", "Disponible"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0){
            public boolean isCellEditable(int row, int col) {
            return false;
            }
        };
        
        for(Prenda s:listPrenda){
            String[] row = {s.getTipo(), s.getColor(), s.getTalla(), s.isDisponible()?"✓":"X"};
            dtm.addRow(row);
        }
        
        jTable1.setModel(dtm);
        currentTable="Prendas"; 
        nomTabla.setText(currentTable);
        prendas.setEnabled(false);
        socios.setEnabled(true);
        prestamos.setEnabled(true);
        buscarBoton.setEnabled(true);
        buscarTexto.setEnabled(true);
        nuevo.setEnabled(true);
    }
    
    void loadPrestamos(){
        String[] cols = {"Socio", "Prenda", "Fecha inicio", "Fecha fin"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0){
            public boolean isCellEditable(int row, int col) {
            return false;
            }
        };
        
        for(Prestamo s:listPrestamo){
            Prenda p = PrendaController.getPrenda(s.getPrenda());
            String prenda=p.getTipo()+" "+p.getColor();
            if(p.getTalla()!=null){
                prenda+=" de talla "+p.getTalla();
            }
            
            String[] row = {SocioController.getSocio(s.getSocio()).getNombre(), prenda, String.valueOf(s.getFechaInicio()), String.valueOf(s.getFechaFin())};
            dtm.addRow(row);
        }
        
        jTable1.setModel(dtm);
        currentTable="Préstamos";
        nomTabla.setText(currentTable);
        prestamos.setEnabled(false);
        socios.setEnabled(true);
        prendas.setEnabled(true);
        buscarBoton.setEnabled(false);
        buscarTexto.setEnabled(false);
        nuevo.setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        socios = new javax.swing.JButton();
        prestamos = new javax.swing.JButton();
        prendas = new javax.swing.JButton();
        nomTabla = new javax.swing.JLabel();
        nuevo = new javax.swing.JButton();
        buscarTexto = new javax.swing.JTextField();
        buscarBoton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        buscaMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lembranzas do Tambre Vestuario");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        socios.setText("Socios");
        socios.setName(""); // NOI18N
        socios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sociosActionPerformed(evt);
            }
        });

        prestamos.setText("Prestamos");
        prestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prestamosActionPerformed(evt);
            }
        });

        prendas.setText("Prendas");
        prendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prendasActionPerformed(evt);
            }
        });

        nomTabla.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        nomTabla.setText("Cargando...");

        nuevo.setText("Añadir");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });

        buscarTexto.setEnabled(false);
        buscarTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarTextoKeyPressed(evt);
            }
        });

        buscarBoton.setText("Buscar");
        buscarBoton.setEnabled(false);
        buscarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBotonActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jMenu1.add(salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Buscar");

        buscaMenu.setText("Prendas");
        buscaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaMenuActionPerformed(evt);
            }
        });
        jMenu2.add(buscaMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(socios, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomTabla))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buscarTexto)
                                .addGap(18, 18, 18)
                                .addComponent(buscarBoton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(prendas, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(prestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(socios)
                    .addComponent(prestamos)
                    .addComponent(prendas))
                .addGap(29, 29, 29)
                .addComponent(nuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomTabla)
                    .addComponent(buscarTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBoton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prendasActionPerformed
        loadPrendas();        
    }//GEN-LAST:event_prendasActionPerformed

    private void sociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sociosActionPerformed
        loadSocios();        
    }//GEN-LAST:event_sociosActionPerformed

    private void prestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prestamosActionPerformed
        loadPrestamos();        
    }//GEN-LAST:event_prestamosActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(evt.getClickCount()==2){
            JTable table = (JTable)evt.getSource();
            System.out.println();
            String[] row = new String[table.getColumnCount()];
            for(int i=0;i<table.getColumnCount();i++){
                row[i] = String.valueOf(table.getValueAt(table.getSelectedRow(), i));
            }
            
            switch(currentTable){
                
                case "Socios":
                    socio = listSocio.get(table.getSelectedRow());
                    socioDialog = new VerSocio(this, rootPaneCheckingEnabled, socio);
                    socioDialog.setVisible(true);
                    socio=null;
                    socioDialog=null;
                    loadDB();
                    loadSocios();
                    break;
                    
                    
                case "Prendas":
                    new VerPrenda(this, rootPaneCheckingEnabled, listPrenda.get(table.getSelectedRow())).setVisible(true);
                    loadDB();
                    loadPrendas();
                    break;
                    
                    
                case "Préstamos":
                    new VerPrestamo(this, rootPaneCheckingEnabled, listPrestamo.get(table.getSelectedRow())).setVisible(true);
                    loadDB();
                    loadPrestamos();
                    break;
                    
                default:
                    break;
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        switch(currentTable){
            
            case "Socios":
                    String s = JOptionPane.showInputDialog("Introduce el nombre del nuevo socio");
                    if(s!=null && !s.isEmpty()){
                        SocioController.createSocio(s.strip());
                        loadDB();
                        loadSocios();
                    }
                    break;
                    
                case "Prendas":
                    new NuevaPrenda(this, rootPaneCheckingEnabled).setVisible(true);
                    loadDB();
                    loadPrendas();
                    break;
                    
                default:
                    break;
        }
    }//GEN-LAST:event_nuevoActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void buscaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaMenuActionPerformed
        new BuscarPrenda(this, rootPaneCheckingEnabled).setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_buscaMenuActionPerformed

    private void buscarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBotonActionPerformed
        switch(currentTable){
            
            case "Socios":
                listSocio = SocioController.getSociosByName(buscarTexto.getText());
                loadSocios();
                //Restablece la lista completa después de mostrar la filtrada
                listSocio = SocioController.getAll();           
                break;
                
            case "Prendas":
                listPrenda = PrendaController.getPrendaSearch(buscarTexto.getText());
                loadPrendas();
                listPrenda = PrendaController.getAll();
                break;
            
            default:
                break;
        }
    }//GEN-LAST:event_buscarBotonActionPerformed

    private void buscarTextoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarTextoKeyPressed
        if(evt.getKeyCode()==10){
            buscarBotonActionPerformed(null);
        }
    }//GEN-LAST:event_buscarTextoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LemTamApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LemTamApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LemTamApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LemTamApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LemTamApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem buscaMenu;
    private javax.swing.JButton buscarBoton;
    private javax.swing.JTextField buscarTexto;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel nomTabla;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton prendas;
    private javax.swing.JButton prestamos;
    private javax.swing.JMenuItem salir;
    private javax.swing.JButton socios;
    // End of variables declaration//GEN-END:variables
}
