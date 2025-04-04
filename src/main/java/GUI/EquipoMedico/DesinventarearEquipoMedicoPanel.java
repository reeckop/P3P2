package GUI.EquipoMedico;

import Entidades.EquipoMedico;
import Persistencia.IPersistenciaFachada;
import Persistencia.PersistenciaFachada;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DesinventarearEquipoMedicoPanel extends javax.swing.JPanel {

    private IPersistenciaFachada persistencia;
    
    public DesinventarearEquipoMedicoPanel() {
        initComponents();
        this.persistencia = new PersistenciaFachada();
        try {
            cargarInventario(persistencia.listarEquiposMedicos(), modeloTabla);
        } catch (Exception ex) {
            Logger.getLogger(DesinventarearEquipoMedicoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarInventario(List<EquipoMedico> equipos, JTable tabla){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"ID","Nombre","Edad","Dirección"});
        
        for (EquipoMedico e : equipos) {
            model.addRow(new Object[]{e.getId(), e.getNombre(), e.getCantidad()});
            System.out.println(e.getId());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        modeloTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Desinventarear Equipo Médico");

        modeloTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(modeloTabla);

        jButton1.setText("Desinventarear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // Get equipment ID
            int id = Integer.parseInt(jTextField1.getText());

            // Check if equipment exists
            EquipoMedico equipo = persistencia.buscar(id);
            if (equipo == null) {
                JOptionPane.showMessageDialog(
                    null, 
                    "El equipo no existe en el inventario", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int ogCantidad = equipo.getCantidad();

            String input = JOptionPane.showInputDialog(
                null, 
                "¿Cuánta cantidad va a desinventarear?\n(Cantidad actual: " + ogCantidad + ")", 
                "", 
                JOptionPane.QUESTION_MESSAGE
            );

            if (input != null) { 
                int cantidad = Integer.parseInt(input);

                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "La cantidad debe ser mayor que cero", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                } else if (cantidad > ogCantidad) {                        
                    JOptionPane.showMessageDialog(
                        null, 
                        "La cantidad ingresada es mayor a la que hay en inventario", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    persistencia.actualizarCantidadEquipo(id, ogCantidad - cantidad);
                    JOptionPane.showMessageDialog(
                        null, 
                        "Inventario actualizado correctamente\nNueva cantidad: " + (ogCantidad - cantidad), 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Por favor ingrese un número válido", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable modeloTabla;
    // End of variables declaration//GEN-END:variables
}
