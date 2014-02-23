/**
 *     Copyright (C) 2009-2011  Jack A. Rider All rights reserved.
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 */

package org.xmlportletfactory.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jack A. Rider
 * @author Fran Garcia
 */
public class MetodosComunes {
// IMPLEMENTATION OF SINGLETON'S PATTERN.
    private static MetodosComunes instancia;

    private MetodosComunes(){
    }

    public static MetodosComunes getInstancia(){
        if(instancia == null) instancia = new MetodosComunes();
        return instancia;
    }
    // GETTING SCREEN SIZE.
    static Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
    // METHOD TO CENTER IN THE SCREEN.
    public void centrarEnPantalla(JFrame frame) {
        frame.setLocation((pantallaTamano.width/2)-(frame.getWidth()/2), (pantallaTamano.height/2)-(frame.getHeight()/2));
    }

    // SHOWING AND EXCEPTION MESSAGE.
    public void ventanaError(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "¡CAUTION!", JOptionPane.ERROR_MESSAGE);
    }

    // SHOWING MESSAGE TO INFORM THAT THE PROCESS IS FINISHED.
    public void ventanaTerminar() {
        JOptionPane.showMessageDialog(null, "PROCESS FINISHED!");
        System.exit(0);
    }
}
