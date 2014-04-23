/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Rectangle;

/**
 *
 * @author bernardot
 */
public class Personaje extends Base {

    int velX;
    int velY;
    int moverX;
    int moverY;
    boolean intersecta;
    int tipo; // Se refiere al tipo de monito que es (gordo, flaco, chava, etc)
    int color; // Color del monito que va de acuerdo con el jugador que es dueño
    int valor; // Cuanto vale el monito dependiendo de su fuerza (upgrade)
    int velocidad; // Velocidad del monito (upgrade)

    /**
     * Método constructor de Personaje para definir solo la posición y crear el
     * objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public Personaje(int posX, int posY) {
        super(posX, posY);
        tipo = color = 0; // No se sabe que tipo ni el jugador
        valor = velocidad = 1; // Valor y velocidad default
    }

    /**
     * Método constructor de Personaje para definir la posición, tipo y color y
     * crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param t es el <code>tipo y forma</code> del monito.
     * @param col es el <code>color</code> del monito de acuerdo al jugador.
     */
    public Personaje(int posX, int posY, int t, int col) {
        super(posX, posY);
        tipo = t; // Se crea un personaje de algun tipo
        color = col; // Se sabe el color del jugador
        valor = velocidad = 1;
    }

    /**
     * Metodo de acceso que regresa el tipo del objeto
     *
     * @return tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Metodo modificador usado para cambiar el tipo del objeto
     *
     * @param tipo es el <code>tipo de monito</code> del objeto.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
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
     * @param color es el <code>color</code> del jugador al que le corresponde
     * el objeto.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Metodo de acceso que regresa el valor del objeto
     *
     * @return valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * Metodo modificador usado para cambiar el valor del objeto
     *
     * @param valor es el <code>valor</code> del objeto.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Metodo de acceso que regresa la velocidad del objeto
     *
     * @return
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Metodo modificador usado para cambiar la velocidad del objeto
     *
     * @param valocidad es la <code>velocidad</code> del objeto.
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getMoverX() {
        return moverX;
    }

    public void setMoverX(int moverX) {
        this.moverX = moverX;
    }

    public int getMoverY() {
        return moverY;
    }

    public void setMoverY(int moverY) {
        this.moverY = moverY;
    }

    public boolean isIntersecta() {
        return intersecta;
    }

    public void setIntersecta(boolean intersecta) {
        this.intersecta = intersecta;
    }

    public Rectangle getPerimetro() {
        return new Rectangle(getPosX(), getPosY() + 40, getAncho(), getAlto() - 40);
    }
}
