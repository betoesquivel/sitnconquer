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
public class Jugador {

    private int id; // Variable entera con la cual se identificará que jugador es
    private int color; // Variable entera que representa un color en el juego de los 4 posibles
    private String nombre; // String que tiene el nombre que se puso el jugador

    /**
     * Método constructor de Jugador para crear al objeto
     *
     * @param i es el <code>id</code> del jugador.
     * @param c es el <code>color</code> del jugador.
     * @param n es el <code>nombre</code> del jugador.
     */
    public Jugador(int i, int c, String n) {
        id = i;
        color = c;
        nombre = n;
    }

    /**
     * Metodo de acceso que regresa el id del objeto
     *
     * @return ID del jugador.
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo modificador usado para cambiar el id del objeto
     *
     * @param id es la <code>ID</code> del jugador.
     */
    public void setId(int id) {
        this.id = id;
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
     * @param color es el <code>color</code> del jugador.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Metodo de acceso que regresa el nombre del objeto
     *
     * @return nombre que es un string con el nombre definido por el usuario.
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
