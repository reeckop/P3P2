package GUI;

import GUI.Paciente.AgregarPacientePanel;
import Persistencia.IPersistenciaFachada;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;

/**
 *
 * @author Ricardo
 */
public class frmMenuPrincipal extends javax.swing.JFrame {

    private IPersistenciaFachada persistencia;

    public frmMenuPrincipal(IPersistenciaFachada persistencia) {
        initComponents();
        aplicarTemaOscuro();
        this.persistencia = persistencia;
        personalizarComponentes();
        displayPanel.setLayout(new BorderLayout());
    }
    
    private void aplicarTemaOscuro() {
        try {
            // Intenta usar FlatLaf Dark (si tienes la biblioteca)
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Si no se encuentra FlatLaf, usa un tema oscuro personalizado básico
            personalizarUIManagerTemaOscuro();
        }
    }
    
    private void personalizarUIManagerTemaOscuro() {
        // Colores base para tema oscuro
        Color bgColor = new Color(33, 33, 33);        // Fondo principal
        Color fgColor = new Color(220, 220, 220);     // Texto
        Color selectionBg = new Color(38, 79, 120);   // Selección fondo
        Color selectionFg = Color.WHITE;              // Selección texto
        Color menuBg = new Color(45, 45, 45);         // Fondo de menús
        
        // Aplicar colores a componentes principales
        UIManager.put("Panel.background", bgColor);
        UIManager.put("Panel.foreground", fgColor);
        UIManager.put("Menu.background", menuBg);
        UIManager.put("Menu.foreground", fgColor);
        UIManager.put("MenuBar.background", menuBg);
        UIManager.put("MenuBar.foreground", fgColor);
        UIManager.put("MenuItem.background", menuBg);
        UIManager.put("MenuItem.foreground", fgColor);
        UIManager.put("MenuItem.selectionBackground", selectionBg);
        UIManager.put("MenuItem.selectionForeground", selectionFg);
        UIManager.put("Menu.selectionBackground", selectionBg);
        UIManager.put("Menu.selectionForeground", selectionFg);
        UIManager.put("Button.background", new Color(60, 60, 60));
        UIManager.put("Button.foreground", fgColor);
        UIManager.put("TextField.background", new Color(50, 50, 50));
        UIManager.put("TextField.foreground", fgColor);
        UIManager.put("ComboBox.background", new Color(50, 50, 50));
        UIManager.put("ComboBox.foreground", fgColor);
        UIManager.put("Label.foreground", fgColor);
        UIManager.put("TabbedPane.background", bgColor);
        UIManager.put("TabbedPane.foreground", fgColor);
        UIManager.put("TabbedPane.selected", new Color(60, 60, 60));
        UIManager.put("TabbedPane.contentBorderInsets", new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0));
    }
    
    private void personalizarComponentes() {
        // Color de fondo para el displayPanel
        displayPanel.setBackground(new Color(40, 40, 40));
        
        // Borde minimalista para el panel principal
        getRootPane().setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Personalizar menú para hacerlo más minimalista
        jMenuBar1.setBorderPainted(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        agregarPaciente = new javax.swing.JMenuItem();
        buscarPaciente = new javax.swing.JMenuItem();
        actualizarPaciente = new javax.swing.JMenuItem();
        eliminarPaciente = new javax.swing.JMenuItem();
        listaPacientes = new javax.swing.JMenuItem();
        agregarDoctor = new javax.swing.JMenu();
        AgregarDoctor = new javax.swing.JMenuItem();
        buscarDoctor = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        iventarear = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        desinventarear = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        consultaID = new javax.swing.JMenuItem();
        consultaMedico = new javax.swing.JMenuItem();
        agendarConsulta = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        cancelarConsulta = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu1.setText("Pacientes");

        agregarPaciente.setText("Agregar Paciente");
        agregarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPacienteActionPerformed(evt);
            }
        });
        jMenu1.add(agregarPaciente);

        buscarPaciente.setText("Buscar Paciente");
        buscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPacienteActionPerformed(evt);
            }
        });
        jMenu1.add(buscarPaciente);

        actualizarPaciente.setText("Actualizar Paciente");
        actualizarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarPacienteActionPerformed(evt);
            }
        });
        jMenu1.add(actualizarPaciente);

        eliminarPaciente.setText("Eliminar Paciente");
        eliminarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPacienteActionPerformed(evt);
            }
        });
        jMenu1.add(eliminarPaciente);

        listaPacientes.setText("Lista de Pacientes");
        listaPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaPacientesActionPerformed(evt);
            }
        });
        jMenu1.add(listaPacientes);

        jMenuBar1.add(jMenu1);

        agregarDoctor.setText("Medico");

        AgregarDoctor.setText("Agregar Medico");
        AgregarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarDoctorActionPerformed(evt);
            }
        });
        agregarDoctor.add(AgregarDoctor);

        buscarDoctor.setText("Buscar Medico");
        buscarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarDoctorActionPerformed(evt);
            }
        });
        agregarDoctor.add(buscarDoctor);

        jMenuBar1.add(agregarDoctor);

        jMenu3.setText("Equipo Medico");

        iventarear.setText("Consultar Inventario");
        iventarear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iventarearActionPerformed(evt);
            }
        });
        jMenu3.add(iventarear);

        jMenuItem8.setText("Inventarear");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        desinventarear.setText("Desinventarear");
        desinventarear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desinventarearActionPerformed(evt);
            }
        });
        jMenu3.add(desinventarear);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Consultas");

        consultaID.setText("Consulta por ID");
        consultaID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaIDActionPerformed(evt);
            }
        });
        jMenu4.add(consultaID);

        consultaMedico.setText("Consultas por Medico");
        consultaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaMedicoActionPerformed(evt);
            }
        });
        jMenu4.add(consultaMedico);

        agendarConsulta.setText("Cosultas por periodo");
        agendarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agendarConsultaActionPerformed(evt);
            }
        });
        jMenu4.add(agendarConsulta);

        jMenuItem13.setText("Agendar Consulta");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        cancelarConsulta.setText("Cancelar Consulta");
        cancelarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarConsultaActionPerformed(evt);
            }
        });
        jMenu4.add(cancelarConsulta);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cambiarMenu(JPanel displayPanel, JPanel menu){
        displayPanel.add(menu, BorderLayout.CENTER);
        displayPanel.revalidate();
        displayPanel.repaint();
    }
    
    // Opciones del Menu Pacientes
    private void agregarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPacienteActionPerformed
        displayPanel.removeAll();
        AgregarPacientePanel menu = new AgregarPacientePanel();
        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_agregarPacienteActionPerformed

    private void buscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPacienteActionPerformed
//        displayPanel.removeAll();
//        BuscarPacientePanel menu = new BuscarPacientePanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_buscarPacienteActionPerformed

    private void actualizarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarPacienteActionPerformed
//        displayPanel.removeAll();
//        ActualizarPaciente menu = new ActualizarPaciente();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_actualizarPacienteActionPerformed

    private void eliminarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPacienteActionPerformed
//        displayPanel.removeAll();
//        EliminarPacientePanel menu = new EliminarPacientePanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_eliminarPacienteActionPerformed

    private void listaPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaPacientesActionPerformed
//        displayPanel.removeAll();
//        ListaPacientesPanel menu = new ListaPacientesPanel();
//        cambiarMenu(displayPanel, menu);
        
    }//GEN-LAST:event_listaPacientesActionPerformed
    
    // Opciones del Menu de Doctores
    private void AgregarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarDoctorActionPerformed
//        displayPanel.removeAll();
//        AgregarMedicoPanel menu = new AgregarMedicoPanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_AgregarDoctorActionPerformed

    private void buscarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarDoctorActionPerformed
//        displayPanel.removeAll();
//        BuscarMedico menu = new BuscarMedico();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_buscarDoctorActionPerformed

    private void iventarearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iventarearActionPerformed
//        displayPanel.removeAll();
//        InventarearEquipoMedico menu = new InventarearEquipoMedico();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_iventarearActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void desinventarearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desinventarearActionPerformed
//        displayPanel.removeAll();
//        DesinventarearEquipoMedicoPanel menu = new DesinventarearEquipoMedicoPanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_desinventarearActionPerformed

    private void consultaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaIDActionPerformed
//        displayPanel.removeAll();
//        ConsultaPacientePanel menu = new ConsultaPacientePanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_consultaIDActionPerformed

    private void consultaMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaMedicoActionPerformed
//        displayPanel.removeAll();
//        ConsultasMedicoPanel menu = new ConsultasMedicoPanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_consultaMedicoActionPerformed

    private void agendarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agendarConsultaActionPerformed
//        displayPanel.removeAll();
//        AgenderConsultaPanel menu = new AgenderConsultaPanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_agendarConsultaActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
//        displayPanel.removeAll();
//        InventarearEquipoMedico menu = new InventarearEquipoMedico();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void cancelarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarConsultaActionPerformed
//        displayPanel.removeAll();
//        CancelarConsultaPanel menu = new CancelarConsultaPanel();
//        cambiarMenu(displayPanel, menu);
    }//GEN-LAST:event_cancelarConsultaActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AgregarDoctor;
    private javax.swing.JMenuItem actualizarPaciente;
    private javax.swing.JMenuItem agendarConsulta;
    private javax.swing.JMenu agregarDoctor;
    private javax.swing.JMenuItem agregarPaciente;
    private javax.swing.JMenuItem buscarDoctor;
    private javax.swing.JMenuItem buscarPaciente;
    private javax.swing.JMenuItem cancelarConsulta;
    private javax.swing.JMenuItem consultaID;
    private javax.swing.JMenuItem consultaMedico;
    private javax.swing.JMenuItem desinventarear;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JMenuItem eliminarPaciente;
    private javax.swing.JMenuItem iventarear;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem listaPacientes;
    // End of variables declaration//GEN-END:variables
}
