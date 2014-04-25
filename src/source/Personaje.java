/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

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
    /*
    tipo = 0 es indefinido :P
    tipo = 1 es el gamer
    tipo = 2 es ilDivo
    tipo = 3 es mike
    tipo = 4 es travolta
    */
    int color; // Color del monito que va de acuerdo con el jugador que es dueño
    int valor; // Cuanto vale el monito dependiendo de su fuerza (upgrade)
    int velocidad; // Velocidad del monito (upgrade)
    int sentado; // 0 es izquierda, 1 es derecha, 2 es arriba, 3 es abajo, 4 arriba

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
    
    public void paint(Graphics g, Silla s) {
        URL sillaLeft = this.getClass().getResource("images/ilDivo/azul/divo_06.png");
                Image sillaL = Toolkit.getDefaultToolkit().getImage(sillaLeft);
                URL sillaRight = this.getClass().getResource("images/ilDivo/azul/divo_08.png");
                Image sillaR = Toolkit.getDefaultToolkit().getImage(sillaRight);
                URL sillaUp = this.getClass().getResource("images/ilDivo/azul/divo_05.png");
                Image sillaU = Toolkit.getDefaultToolkit().getImage(sillaUp);
                URL sillaDown = this.getClass().getResource("images/ilDivo/azul/divo_11.png");
                Image sillaD = Toolkit.getDefaultToolkit().getImage(sillaDown);
        if (sentado == 0) {
            g.drawImage(sillaL, s.getPosX() + 5, s.getPosY() - 10, null);
        } else if (sentado == 1) {
            g.drawImage(sillaR, s.getPosX() - 1, s.getPosY() - 10, null);
        } else if (sentado == 2) {
            g.drawImage(sillaU, s.getPosX() + 5, s.getPosY() + 2, null);
        } else if (sentado == 3) {
            g.drawImage(sillaD, s.getPosX() + 7, s.getPosY() - 8, null);
        }
        
    }

    public int getSentado() {
        return sentado;
    }

    public void setSentado(int sentado) {
        this.sentado = sentado;
    }
    
}
