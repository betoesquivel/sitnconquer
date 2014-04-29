/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
/**
 *
 * @author bernardot
 */
public class Jugador {

//    private int id; // Variable entera con la cual se identificará que jugador es
    private Color color; // Variable entera que representa un color en el juego de los 4 posibles
    private String nombre; // String que tiene el nombre que se puso el jugador
    private LinkedList personajes;
    /*Seleccion*/
    // El mapa de mesas varía en cada escenario y empieza en (1,1).
    // X crece a la derecha de acuerdo al número de mesas y Y para abajo
    private int cordX; // Coordenada en X de acuerdo al mapa de mesas de cada escenario
    private int cordY;  // Coordenada en Y de acuerdo al mapa de mesas de cada escenario
    private int mesaSeleccionada; 
    private int contadorDeTiempo;
    private double factorDeCreacion;

    /**
     * Método constructor de Jugador para crear al objeto
     *
     * @param i es el <code>id</code> del jugador.
     * @param c es el <code>color</code> del jugador.
     * @param n es el <code>nombre</code> del jugador.
     */
    public Jugador(Color c, String n, int inicial) {
//        id = i;
        this.color = c;
        nombre = n;
        mesaSeleccionada = inicial;
        personajes = new LinkedList();
        crearPersonaje();
        crearPersonaje();
        crearPersonaje();
        crearPersonaje();
        crearPersonaje();
        contadorDeTiempo = 0;
        factorDeCreacion = 1;
    }
    
    public void crearPersonaje() {
        int tipo = (int) (Math.random() * 4) + 1;
        int col = 0;
        if (color == Color.blue) {
            col = 1;
        } else if (color == Color.gray) {
            col = 2;
        } else if (color == Color.red) {
            col = 3;
        } else if (color == Color.green) {
            col = 4;
        }
        Personaje p = new Personaje((int) (Math.random() * 900), (int) (Math.random() * 600), tipo, col);
        personajes.add(p);
    }
    
    public void actualiza(long tiempoTranscurrido) {
        contadorDeTiempo++;
        int division = (int) (5 / factorDeCreacion) * 50;
        if (contadorDeTiempo % division == 0) {
            crearPersonaje();
        }
        for (int x = 0; x < personajes.size(); x++) {
            Personaje aux = (Personaje) personajes.get(x);
            aux.actualizaAnimaciones(tiempoTranscurrido);
        }
    }
        
    public void paint(Graphics g) {
        for (int x = 0; x < personajes.size(); x++) {
            Personaje aux = (Personaje) personajes.get(x);
            aux.paint(g);
        }
    }

    /**
     * Metodo de acceso que regresa el color del objeto
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Metodo modificador usado para cambiar el color del objeto
     *
     * @param color es el <code>color</code> del jugador.
     */
    public void setColor(Color color) {
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

    /**
     * Metodo de acceso que regresa la coordenada en x del objeto
     *
     * @return cordX
     */
    public int getCordX() {
        return cordX;
    }

    /**
     * Metodo modificador usado para cambiar la coordenada en x del objeto
     *
     * @param cordX es la <code>coordenada en x</code> del objeto.
     */
    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    /**
     * Metodo de acceso que regresa la coordenada en y del objeto
     *
     * @return cordY
     */
    public int getCordY() {
        return cordY;
    }

    /**
     * Metodo modificador usado para cambiar la coordenada en y del objeto
     *
     * @param cordY es la <code>coordenada en y</code> del objeto.
     */
    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    /**
     * Metodo de acceso que regresa la mesa del objeto
     *
     * @return mesaSeleccionada
     */
    public int getMesaSeleccionada() {
        return mesaSeleccionada;
    }

    /**
     * Metodo modificador usado para cambiar la mesa seleccionada del objeto
     *
     * @param mesaSeleccionada es la <code>mesa</code>seleccionada por el
     * jugador.
     */
    public void setMesaSeleccionada(int mesaSeleccionada) {
        this.mesaSeleccionada = mesaSeleccionada;
    }

}
