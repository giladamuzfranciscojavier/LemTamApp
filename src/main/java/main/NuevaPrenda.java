package main;

import controllers.PrendaController;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import models.Prenda;

public class NuevaPrenda extends javax.swing.JDialog {

    private List<Prenda> list;    
    private byte[] foto;
    
    
    /**
     * Creates new form NuevaPrenda
     */
    public NuevaPrenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        list = PrendaController.getAll();
        List<String> select = new ArrayList();
        select.add("Selecciona tipo");
        for(Prenda p:list){
            select.add(p.getTipo());
        }
        
        tipo.setModel(new DefaultComboBoxModel(select.toArray()));
        
        select = new ArrayList();
        select.add("Selecciona color");
        for(Prenda p:list){
            select.add(p.getColor());
        }
        color.setModel(new DefaultComboBoxModel(select.toArray()));
        
        select = new ArrayList();
        select.add("Selecciona talla");
        for(Prenda p:list){
            select.add(p.getTalla());
        }
        talla.setModel(new DefaultComboBoxModel(select.toArray()));        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        color = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        talla = new javax.swing.JComboBox<>();
        addTipo = new javax.swing.JButton();
        addColor = new javax.swing.JButton();
        addTalla = new javax.swing.JButton();
        imageButton = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        imageDelete = new javax.swing.JButton();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cantidad = new com.toedter.components.JSpinField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Prenda");

        jLabel1.setText("Tipo");

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona tipo" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        jLabel2.setText("Color");

        color.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona color" }));
        color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorActionPerformed(evt);
            }
        });

        jLabel3.setText("Talla");

        talla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona talla" }));
        talla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tallaActionPerformed(evt);
            }
        });

        addTipo.setText("Añadir tipo");
        addTipo.setToolTipText("");
        addTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTipoActionPerformed(evt);
            }
        });

        addColor.setText("Añadir color");
        addColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColorActionPerformed(evt);
            }
        });

        addTalla.setText("Añadir talla");
        addTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTallaActionPerformed(evt);
            }
        });

        imageButton.setText("Seleccionar imagen");
        imageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageButtonActionPerformed(evt);
            }
        });

        imageLabel.setText("Sin imagen seleccionada");
        imageLabel.setMaximumSize(null);
        imageLabel.setMinimumSize(null);

        imageDelete.setText("Borrar Imagen");
        imageDelete.setEnabled(false);
        imageDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageDeleteActionPerformed(evt);
            }
        });

        save.setText("Añadir");
        save.setEnabled(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad");

        cantidad.setMinimum(1);
        cantidad.setValue(1);
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(talla, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addColor, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(addTalla, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(imageButton)
                                    .addComponent(imageDelete))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(108, 108, 108)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTipo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addColor))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(talla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTalla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageButton)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imageDelete)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTipoActionPerformed
        String s = JOptionPane.showInputDialog("Introduce el nuevo tipo");
        if(!s.isBlank()){
            tipo.addItem(s);
            tipo.setSelectedIndex(tipo.getItemCount()-1);
        }        
    }//GEN-LAST:event_addTipoActionPerformed

    private void addColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColorActionPerformed
        String s = JOptionPane.showInputDialog("Introduce el nuevo color");
        if(!s.isBlank()){
            color.addItem(s);
            color.setSelectedIndex(color.getItemCount()-1);
        } 
    }//GEN-LAST:event_addColorActionPerformed

    private void addTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTallaActionPerformed
        String s = JOptionPane.showInputDialog("Introduce la nueva talla");
        if(!s.isBlank()){
            talla.addItem(s);
            talla.setSelectedIndex(talla.getItemCount()-1);
        } 
    }//GEN-LAST:event_addTallaActionPerformed

    private void imageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageButtonActionPerformed
        JFileChooser choose = new JFileChooser();
        int i = choose.showOpenDialog(this);
        
        if(i==JFileChooser.APPROVE_OPTION){
            File f = choose.getSelectedFile();
            String ext = f.getName().substring(f.getName().length()-4);
            if(ext.equals(".png") || ext.equals(".jpg") || f.getName().substring(f.getName().length()-5).equals(".jpeg")){
                try {
                    BufferedImage img = ImageIO.read(f);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(img, "png", bos);
                    foto = bos.toByteArray();
                    imageLabel.setText(choose.getSelectedFile().getName());
                    imageDelete.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }            
        }
    }//GEN-LAST:event_imageButtonActionPerformed

    private void imageDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageDeleteActionPerformed
        foto=null;
        imageLabel.setText("Sin imagen seleccionada");
        imageDelete.setEnabled(false);
    }//GEN-LAST:event_imageDeleteActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        if(tipo.getSelectedIndex()>0 && color.getSelectedIndex()>0 && talla.getSelectedIndex()>0){
            save.setEnabled(true);
        }
        else{
            save.setEnabled(false);
        }
    }//GEN-LAST:event_tipoActionPerformed

    private void colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorActionPerformed
        if(tipo.getSelectedIndex()>0 && color.getSelectedIndex()>0 && talla.getSelectedIndex()>0){
            save.setEnabled(true);
        }
        else{
            save.setEnabled(false);
        }
    }//GEN-LAST:event_colorActionPerformed

    private void tallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tallaActionPerformed
        if(tipo.getSelectedIndex()>0 && color.getSelectedIndex()>0 && talla.getSelectedIndex()>0){
            save.setEnabled(true);
        }
        else{
            save.setEnabled(false);
        }
    }//GEN-LAST:event_tallaActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        try{
            boolean b = PrendaController.createPrenda(tipo.getItemAt(tipo.getSelectedIndex()), foto, color.getItemAt(color.getSelectedIndex()), talla.getItemAt(talla.getSelectedIndex()), cantidad.getValue());
            if(b){
                JOptionPane.showMessageDialog(this, "Prenda añadida con éxito", "Hecho", JOptionPane.OK_OPTION);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "Error al añadir la prenda", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al añadir la prenda", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadKeyTyped

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
            java.util.logging.Logger.getLogger(NuevaPrenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaPrenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaPrenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaPrenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevaPrenda dialog = new NuevaPrenda(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addColor;
    private javax.swing.JButton addTalla;
    private javax.swing.JButton addTipo;
    private javax.swing.JButton cancel;
    private com.toedter.components.JSpinField cantidad;
    private javax.swing.JComboBox<String> color;
    private javax.swing.JButton imageButton;
    private javax.swing.JButton imageDelete;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton save;
    private javax.swing.JComboBox<String> talla;
    private javax.swing.JComboBox<String> tipo;
    // End of variables declaration//GEN-END:variables
}
