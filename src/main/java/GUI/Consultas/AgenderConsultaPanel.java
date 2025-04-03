package GUI.Consultas;

import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Persistencia.IPersistenciaFachada;
import Persistencia.PersistenciaFachada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo & angelsn
 */
public class AgenderConsultaPanel extends javax.swing.JPanel {

    private IPersistenciaFachada persistencia;
    /**
     * Creates new form AgenderConsultaPanel
     */
    public AgenderConsultaPanel() {
        initComponents();
        persistencia = new PersistenciaFachada();
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
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        diaField = new javax.swing.JTextField();
        mesField = new javax.swing.JTextField();
        anioField = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agendar Consulta");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 488, -1));

        jLabel2.setText("ID del paciente");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 71, 125, -1));
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 66, 327, -1));

        jLabel3.setText("ID del Medico");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 103, 125, -1));
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 98, 327, -1));

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 130, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 195, 488, 100));

        jLabel4.setText("Fecha (DD/MM/AA)");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 135, 143, -1));
        add(diaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 130, -1, -1));
        add(mesField, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 130, -1, -1));

        anioField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anioFieldActionPerformed(evt);
            }
        });
        add(anioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 130, -1, -1));
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 163, 327, -1));

        jLabel5.setText("ID");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 168, 143, -1));
    }// </editor-fold>//GEN-END:initComponents

    // método para agendar consultas
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
        // Validar campos vacíos
        if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || 
            jTextField3.getText().isEmpty() || diaField.getText().isEmpty() || 
            mesField.getText().isEmpty() || anioField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que los IDs sean números válidos
        int idPaciente = Integer.parseInt(jTextField1.getText());
        int idMedico = Integer.parseInt(jTextField2.getText());
        int id = Integer.parseInt(jTextField3.getText());

        // Validar y formatear fecha
        String fechaTexto = anioField.getText() + "-" + mesField.getText() + "-" + diaField.getText();
        LocalDate fecha;
        
        try {
            fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("yyyy-M-d"));
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Fecha inválida. Usa formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener objetos de base de datos
        Paciente paciente = persistencia.obtenerPacientePorId(idPaciente);
        Medico medico = persistencia.obtenerMedicoPorId(idMedico);

        if (paciente == null || medico == null) {
            JOptionPane.showMessageDialog(null, "Paciente o Médico no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Agendar consulta
        persistencia.programarConsulta(new Consulta(id, paciente, medico, fecha.toString()));

        // Confirmación de éxito
        JOptionPane.showMessageDialog(null, "Consulta programada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los IDs deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void anioFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anioFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anioFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anioField;
    private javax.swing.JTextField diaField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField mesField;
    // End of variables declaration//GEN-END:variables
}
