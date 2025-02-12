package main;

import models.*;
import controllers.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NuevoPrestamo extends javax.swing.JDialog {

    private static Socio s;
    private static Prenda p;
    
    private List<Socio> listSocio = SocioController.getAll();
    private List<Prenda> listPrenda = PrendaController.getAll();
    
    private String currentTable;
    
    private void loadSocios(){     
        
        String[] cols = {"Nombre"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0){
            public boolean isCellEditable(int row, int col) {
            return false;
            }
        };
        
        for(Socio s:listSocio){
            String[] row = {s.getNombre(), String.valueOf(s.getId())};
            dtm.addRow(row);
        }
        
        tabla.setModel(dtm);
        currentTable="Socios";
        
    }
    
    private void loadPrendas(){     
        
        String[] cols = {"Tipo", "Color", "Talla"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0){
            public boolean isCellEditable(int row, int col) {
            return false;
            }
        };
        
        Iterator<Prenda> it = listPrenda.iterator();
        
        while(it.hasNext()){
           Prenda temp = it.next();
           if(temp.isDisponible()){
                String[] row = {temp.getTipo(), temp.getColor(), temp.getTalla()};
                dtm.addRow(row);
            }
           else{
               it.remove();
           }
        }
        
        for(Prenda pr:listPrenda){
                        
        }
        tabla.setModel(dtm);
        currentTable="Prendas";
    }
    
    /**
     * Creates new form NuevaPrenda
     */
    public NuevoPrestamo(java.awt.Frame parent, boolean modal, Socio s) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.s=s;
        this.p=null;
        datoLabel.setText("Socio");
        dato.setText(s.getNombre());
        listPrenda = PrendaController.getAll();
        loadPrendas();
    }
    
    public NuevoPrestamo(java.awt.Frame parent, boolean modal, Prenda p) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.s=null;
        this.p=p;
        datoLabel.setText("Prenda");
        dato.setText(p.getTipo()+" "+p.getColor()+" de talla "+p.getTalla());
        listSocio = SocioController.getAll();
        loadSocios();
        fechafin.getCalendarButton().setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datoLabel = new javax.swing.JLabel();
        dato = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        buscarTexto = new javax.swing.JTextField();
        buscarBoton = new javax.swing.JButton();
        prestar = new javax.swing.JButton();
        fechafin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        checkFin = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Préstamo");

        datoLabel.setText("dato");

        dato.setEnabled(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        buscarTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarTextoKeyPressed(evt);
            }
        });

        buscarBoton.setText("Buscar");
        buscarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBotonActionPerformed(evt);
            }
        });

        prestar.setText("Prestar");
        prestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prestarActionPerformed(evt);
            }
        });

        fechafin.setDate(new java.util.Date());
        fechafin.setEnabled(false);

        jLabel1.setText("Fecha de fin");

        checkFin.setSelected(true);
        checkFin.setText("Sin fecha de fin");
        checkFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(checkFin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(prestar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(datoLabel)
                        .addGap(18, 18, 18)
                        .addComponent(dato))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buscarTexto)
                        .addGap(18, 18, 18)
                        .addComponent(buscarBoton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datoLabel)
                    .addComponent(dato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkFin)
                        .addComponent(prestar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBoton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void prestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prestarActionPerformed
        
        Date fin;
        
        
        if(tabla.getSelectedRow()!=-1){
            try{
                if(!checkFin.isSelected()){
                    fin = new Date(fechafin.getDate().getTime());
                }

                else{
                    fin=null;
                }

                if(currentTable=="Socios"){
                    if(p.isDisponible()){
                        PrestamoController.createPrestamo(listSocio.get(tabla.getSelectedRow()).getId(), p.getId(), fin);                        
                        this.dispose();
                    }
                    
                    else{
                        JOptionPane.showMessageDialog(this, "La prenda no está disponible", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{                    
                    if(listPrenda.get(tabla.getSelectedRow()).isDisponible()){
                        PrestamoController.createPrestamo(s.getId(), listPrenda.get(tabla.getSelectedRow()).getId(), fin); 
                        JOptionPane.showMessageDialog(this, "Préstamo realizado con éxito", "Hecho", JOptionPane.OK_OPTION);
                        this.dispose();
                    }
                    
                    else{
                        JOptionPane.showMessageDialog(this, "La prenda no está disponible", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
                
            }
            catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al realizar el préstamo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        else{
            JOptionPane.showMessageDialog(this, "Selecciona un dato de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_prestarActionPerformed

    private void checkFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFinActionPerformed
        if(checkFin.isSelected()){
            fechafin.getCalendarButton().setEnabled(false);
        }
        else{
            fechafin.getCalendarButton().setEnabled(true);
        }
    }//GEN-LAST:event_checkFinActionPerformed

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
            java.util.logging.Logger.getLogger(NuevoPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevoPrestamo dialog;
                if(s==null){
                    dialog = new NuevoPrestamo(new javax.swing.JFrame(), true, p);
                }               
                else{
                    dialog = new NuevoPrestamo(new javax.swing.JFrame(), true, s);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBoton;
    private javax.swing.JTextField buscarTexto;
    private javax.swing.JCheckBox checkFin;
    private javax.swing.JTextField dato;
    private javax.swing.JLabel datoLabel;
    private com.toedter.calendar.JDateChooser fechafin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton prestar;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
