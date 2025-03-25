/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoprogramacion3;

import GUI.frmMenuPrincipal;
import Persistencia.*;


/**
 *
 * @author Enrique Osuna
 */
public class ProyectoProgramacion3 {

    public static void main(String[] args) {
        IPersistenciaFachada persistencia = new PersistenciaFachada();
        frmMenuPrincipal menu = new frmMenuPrincipal(persistencia);
        menu.setVisible(true);
    }
}
