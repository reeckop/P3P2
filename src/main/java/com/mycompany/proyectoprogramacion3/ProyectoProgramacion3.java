/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoprogramacion3;

import Menu.MenuPrincipal;
import Persistencia.*;


/**
 *
 * @author Enrique Osuna
 */
public class ProyectoProgramacion3 {

    public static void main(String[] args) {
        IPersistenciaFachada persistencia = new PersistenciaFachada();
        MenuPrincipal menu = new MenuPrincipal(persistencia);
        menu.mostrar();
    }
}
