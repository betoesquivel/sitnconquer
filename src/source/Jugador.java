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
 * La clase <I>Jugador</I> describe a un jugador en el juego (hay dos). Un
 * Jugador controla un selector en el juego que siempre está posicionado sobre
 * una de las muchas mesas en el mapa.
 *
 * También tiene una lista de personajes de tipo
 * <code>LinkedList<Personaje></code>. Con todos los personajes que actualmente
 * tiene el jugador.
 *
 * Un jugador también es definido por un id: (1 o 2, dependiendo del número de
 * jugador), un color, y un nombre.
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
public class Jugador implements Constantes {

    private int id; // Variable entera con la cual se identificará que jugador es
    private Color color; // Variable entera que representa un color en el juego de los 4 posibles
    private String nombre; // String que tiene el nombre que se puso el jugador
    private LinkedList<Personaje> personajes;
    /*Seleccion*/
    // El mapa de mesas varía en cada escenario y empieza en (1,1).
    // X crece a la derecha de acuerdo al número de mesas y Y para abajo
    private int cordX; // Coordenada en X de acuerdo al mapa de mesas de cada escenario
    private int cordY;  // Coordenada en Y de acuerdo al mapa de mesas de cada escenario
    private int mesaSeleccionada;
    private int contadorDeTiempo;
    private double factorDeCreacion;
    private int conFDC;
    // El que sigue no es tan importante :)
    private int cambia;

    /**
     * Método constructor de Jugador para crear al objeto
     *
     * @param i es el <code>id</code> del jugador.
     * @param c es el <code>color</code> del jugador.
     * @param n es el <code>nombre</code> del jugador.
     */
    public Jugador(int i, Color c, String n, int inicial) {
        cambia = 0;
        id = i;
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
        conFDC = 0;

    }

    /**
     * Método que crea un personaje de tipo <code>Personaje</code> para el
     * juagdor del color del jugador y afuera de la pantalla del lado del
     * jugador.
     */
    public void crearPersonaje() {
        int tipo;
        if (cambia == 0) {
            tipo = (int) (Math.random() * 4) + 1;
        } else {
            tipo = cambia;
        }
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

        Personaje p;
        if (id != 1) {
            p = new Personaje((int) (Math.random() * -50) - 50, (int) (Math.random() * 500) + 50, tipo, col);
        } else {
            p = new Personaje((int) (Math.random() * 50) + 950, (int) (Math.random() * 500) + 50, tipo, col);
        }
        personajes.add(p);
    }

    /**
     * Método que crea a un personaje de tipo <code>Personaje</code> parado
     * junto a la mesa que se ha enviado como parámetro.
     *
     * @param m de tipo <code>Mesa</code>
     */
    public void crearPersonajeSentado(Mesa m) {
        int tipo;
        if (cambia == 0) {
            tipo = (int) (Math.random() * 4) + 1;
        } else {
            tipo = cambia;
        }
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

        Personaje p;
        if (id == 1) {
            p = new Personaje(m.getPosX() - 70, m.getPosY(), tipo, col);
        } else {
            p = new Personaje(m.getPosX() - 70, m.getPosY(), tipo, col);
        }
        personajes.add(p);
    }

    /**
     * Actualiza el estado de todos los personajes pertenecientes al jugador. Y
     * hace que todos los personajes en estado ENMOVIMIENTO actualicen su
     * posición hacia la mesa de destino.
     *
     * @param tiempoTranscurrido de tipo <code>long</code> con el tiempo que ha
     * transcurrido desde la última actualización.
     */
    public void actualiza(long tiempoTranscurrido) {
        contadorDeTiempo++;
        int division = (int) (5 / factorDeCreacion) * 50;
        if (contadorDeTiempo % division == 0) {
            crearPersonaje();
        }
        for (int x = 0; x < personajes.size(); x++) {
            Personaje aux = (Personaje) personajes.get(x);
            aux.actualizaAnimaciones(tiempoTranscurrido);
            aux.actualizaPosicion();
            if (aux.getEstado() == -1) {
                personajes.remove(x);
            }
            if (aux.getEstado() == 2 && aux.isInMesaDestino()) {
                aux.getMesaDestino().sentar(aux);
            }
        }
    }

    /**
     * Método que verifica y modifica el factor de creación de monitos, tanto
     * fuera del JFrame, como en cada mesa (de las que tienen el upgrade de
     * vidas y generan monitos)
     *
     * @param m de tipo <code>LinkedList<Mesa></code>
     */
    public void checaFactorDeCreacion(LinkedList<Mesa> m) {
        int cont = 0;
        for (int x = 0; x < m.size(); x++) {
            Mesa aux = (Mesa) m.get(x);
            if (aux.getColorPrincipal() == color) {
                cont++;
            }
            if (aux.getColorPrincipal() == color && aux.getUpgrade().getFuncion() == 3) {
                aux.doyVida(this);
            }
        }
        if (cont != conFDC) {
            conFDC = cont;
            factorDeCreacion = 1 + conFDC * .1;
        }
    }

    /**
     * Método que regresa si el jugador ha ganado o no el juego verificando que
     * todas las mesas ya le pertenezcan.
     *
     * @param m de tipo <code>LinkedList<Mesa></code>
     * @return tipo <code>boolean</code> siendo true si ha ganado.
     */
    public boolean checarGana(LinkedList<Mesa> m) {
        int cont = 0;
        for (int x = 0; x < m.size(); x++) {
            Mesa aux = (Mesa) m.get(x);
            if (aux.getColorPrincipal() == color) {
                cont++;
            }
            if (aux.getColorPrincipal() == color && aux.getUpgrade().getFuncion() == 3) {
                aux.doyVida(this);
            }
        }

        return (cont == m.size());

    }

    /**
     * Método que pinta cada uno de los personajes de tipo
     * <code>Personaje</code> del jugador en el juego.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paint(Graphics g) {
        for (int x = 0; x < personajes.size(); x++) {
            Personaje aux = (Personaje) personajes.get(x);
            if (aux.getEstado() > 0) {
                aux.paint(g);
            }
        }
    }

    /**
     * Método que cambia el estado de todos los personajes con estado
     * <code>PARADO</code> a <code>ENMOVIMIENTO</code> y les pone como destino
     * la mesa que ha recibido como parámetro.
     *
     * @param m de tipo <code>Mesa</code>
     */
    public void sentarAMesa(Mesa m) {
        for (int x = 0; x < personajes.size(); x++) {
            Personaje aux = (Personaje) personajes.get(x);
            if (aux.getEstado() == 1) {
                aux.setMesaDestino(m);
            }
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
     * @param color es el <code>Color</code> del jugador.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Metodo de acceso que regresa el facotr de creacion del jugador
     *
     * @return facotrDeCreacion de tipo <code>double</code>
     */
    public double getFactorDeCreacion() {
        return factorDeCreacion;
    }

    /**
     * Metodo modificador usado para cambiar el factor de creación del jugador
     *
     * @param factorDeCreacion de tipo <code>double</code> del jugador.
     */
    public void setFactorDeCreacion(double factorDeCreacion) {
        this.factorDeCreacion = factorDeCreacion;
    }

    /**
     * Metodo de acceso que regresa el nombre del objeto
     *
     * @return nombre que es un string con el nombre definido por el usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método modificador que cambia el nombre del jugador
     * @param nombre de tipo <code>String</code>
     */
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

    
    /**
     * Cambia el tipo de los personajes. 
     * @param t de tipo <code>int</code>
     */
    public void cambiaTipo(int t) {
        cambia = t;
        for (int x = 0; x < personajes.size(); x++) {
            Personaje aux = (Personaje) personajes.get(x);
            aux.setTipo(4);
            aux.crearAnimaciones();
        }
    }

    /**
     * Metodo que cambia el tipo de los personajes de manera aleatoria.
     */
    public void cambiaTipoRand() {
        cambia = 0;
        for (int x = 0; x < personajes.size(); x++) {
            Personaje aux = (Personaje) personajes.get(x);
            aux.setTipo((int) (Math.random() * 4) + 1);
            aux.crearAnimaciones();
        }
    }

    /**
     * Método que regresa la cantidad de personajes en el estado 
     * <code>SENTADO</code> que tiene el jugador
     * @return s de tipo <code>int</code>
     */
    public int getCantSentados() {

        int s = 0;
        for (Personaje p : personajes) {
            s += (p.getEstado() == SENTADO && p.getEstado() != 2) ? 1 : 0;
        }

        return s;

    }

    /**
     * Método que regresa la cantidad de personajes en el estado 
     * <code>PARADO</code> que tiene el jugador
     * @return parados de tipo <code>int</code>
     */
    public int getCantParados() {

        int parados = 0;
        for (Personaje p : personajes) {
            parados += (p.getEstado() == PARADO && p.getEstado() != 2) ? 1 : 0;
        }

        return parados;

    }
}
