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
public class Posicion {

    // El mapa de mesas varía en cada escenario y empieza en (1,1).
    // X crece a la derecha de acuerdo al número de mesas y Y para abajo
    private int cordX; // Coordenada en X de acuerdo al mapa de mesas de cada escenario
    private int cordY;  // Coordenada en Y de acuerdo al mapa de mesas de cada escenario
    private Mesa mesaSeleccionada;  // Objeto mesa el cual está actualemnte seleccionado
    private int jugador;    // ID del Jugador que controla en que mesa posicionarse

    /**
     * Método constructor de Posicion para crear el objeto y asignar el id del
     * jugador
     *
     * @param jugad contiene el <code>ID</code> del jugador.
     */
    public Posicion(int jugad) {
        jugador = jugad;
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
    public Mesa getMesaSeleccionada() {
        return mesaSeleccionada;
    }

    /**
     * Metodo modificador usado para cambiar la mesa seleccionada del objeto
     *
     * @param mesaSeleccionada es la <code>mesa</code>seleccionada por el
     * jugador.
     */
    public void setMesaSeleccionada(Mesa mesaSeleccionada) {
        this.mesaSeleccionada = mesaSeleccionada;
    }

    /**
     * Metodo de acceso que regresa el ID del jugador del objeto
     *
     * @return jugador
     */
    public int getJugador() {
        return jugador;
    }

    /**
     * Metodo modificador usado para cambiar el ID del objeto
     *
     * @param jugador es el <code>ID</code> del jugador del objeto.
     */
    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

}
