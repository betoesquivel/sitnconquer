/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Graphics;

/**
 *
 * @author bernardot
 */
public class Silla extends Base {

    boolean ocupada;    // Variable booleana para saber si un personaje está sentado
    // false = no hay personaje sentado

    /**
     * Método constructor de Silla para definir solo la posición y crear el
     * objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public Silla(int posX, int posY) {
        super(posX, posY);
        ocupada = false;
    }

    public void paint(Graphics g) {
        g.drawImage(getImageIcon().getImage(), getPosX(), getPosY(), null);
    }
    
    /**
     * Metodo de acceso que regresa el valor booleano ocupado del objeto
     *
     * @return ocupada
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * Metodo modificador usado para cambiar el valor del booleano del objeto
     *
     * @param ocupada es la <code>variable booleana</code> del objeto.
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    

}
