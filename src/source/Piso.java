/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author bernardot
 */
public class Piso extends Base {

    private int color; // Color de acuerdo a que jugador está sentado en la mesa
    private Mesa miMesa; // Saber cual objeto mesa esta arriba del piso.

    /**
     * Método constructor de Piso para definir solo la posición y crear el
     * objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public Piso(int posX, int posY) {
        super(posX, posY);
        color = 0; // Valor default
    }

    /**
     * Metodo de acceso que regresa el color del objeto
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

    /**
     * Metodo modificador usado para cambiar el color del objeto
     *
     * @param color es el <code>color</code> del jugador correspondiente del
     * objeto.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Metodo de acceso que regresa la mesa del objeto
     *
     * @return miMesa
     */
    public Mesa getMiMesa() {
        return miMesa;
    }

    /**
     * Metodo modificador usado para cambiar la mesa del objeto
     *
     * @param miMesa es la <code>mesa</code> contenida del objeto.
     */
    public void setMiMesa(Mesa miMesa) {
        this.miMesa = miMesa;
    }

}
