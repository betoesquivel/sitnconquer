/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * La clase <I>Boton</I> describe a un objeto, definido por un rectángulo 
 * y una imagen que cambia el estado del juego (es decir, mueve al usuario a otra
 * pantalla, o cambia características de los jugadores)
 */
public class Boton extends Base {

    private int tipo;   // Variable que ayudará a saber para que sirve el boton
    private Animacion a;

    /**
     * Método constructor de Boton para definir solo la posición y crear el
     * objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public Boton(int posX, int posY) {
        super(posX, posY);
        tipo = 0;   // Valor default
    }
    
    /**
     * Método constructor de Boton para definir posición e imagen 
     * del objeto.
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param image es la <code>imagen</code> del objeto. 
     */
    public Boton(int posX, int posY, Image image) {
        super(posX, posY);
        anim = new Animacion();
        anim.sumaCuadro(image, 100);
        ImageIcon icono = new ImageIcon(image);
        this.setImageIcon(icono);
        tipo = 0;   // Valor default
    }

    /**
     * Método constructor de Boton para definir posición y función y crear el
     * objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param t es la <code>función</code> que desempeñará el objeto.
     */
    public Boton(int posX, int posY, int t) {
        super(posX, posY);
        tipo = t;
    }

    /**
     * Metodo de acceso que regresa el tipo del objeto
     *
     * @return tipo que es la funcionalidad del botón.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Metodo modificador usado para cambiar el tipo/función del objeto
     *
     * @param tipo es la <code>función</code> que desempeñará el objeto.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
