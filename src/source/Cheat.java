/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.event.KeyEvent;

/**
 * La clase <I>Cheat</I> permite introducir secuencias de caracteres en el
 * teclado para realizar cambios divertidos en el juego. Por ejemplo, si durante
 * el juego se teclea la siguiente secuencia <code>betoquierenovia</code>, todos
 * los personajes van a convertirse en mujeres.
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
public class Cheat {

    private int[] sequence;
    private int currentButton;
    private boolean encendido;
    private int contador;

    /**
     * Método constructor de <I>Cheat</I> para definir un objeto que escucha
     * cheats. Este es el constructor default y comienza apagado.
     */
    public Cheat() {
        encendido = false;
        contador = 0;
        currentButton = 0;
    }

    /**
     * Método constructor de <I>Cheat</I> para definir un objeto Cheat que
     * comienza encendido.
     *
     * @param b
     */
    public Cheat(boolean b) {
        encendido = b;
        contador = 0;
        currentButton = 0;
    }

    /**
     * Método de acceso que regresa si está activo escuchando nuevos cheats o
     * no.
     *
     * @return encendido de tipo <code>boolean</code>
     */
    public boolean isEncendido() {
        return encendido;
    }

    /**
     * Método modificador que cambia el estado del objeto cheat. 
     * Encendido = true y Apagado = false; Cuando está apagado, no escucha 
     * nuevos cheats. 
     * @param encendido de tipo <code>boolean</code>
     */
    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    /**
     * Método de acceso que regresa el contador con la cantidad de veces
     * que se ha introducido una cheat. 
     *
     * @return contador de tipo <code>int</code>
     */
    public int getContador() {
        return contador;
    }

    /**
     * Método modificador que cambia la cantidad de veces que se han
     * introducido cheats.
     * @param contador de tipo <code>int</code>
     */
    public void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * Método de acceso que regresa la secuencia de caracteres
     * que se ha introducido hasta ahora. 
     *
     * @return sequence de tipo <code>int[]</code>
     */
    public int[] getSequence() {
        return sequence;
    }

    /**
     * Método modificador que remplaza la secuencia de caracteres
     * que se ha introducido hasta ahora, por una nueva cadena de caracteres
     * 
     * @param sequence de tipo <code>int[]</code>
     */
    public void setSequence(int[] sequence) {
        this.sequence = sequence;
    }

    /**
     * Método que cambia la secuencia introducida a "betoquierenovia"
     * para poder causar el efecto del cheat que cambia todos los monitos por
     * mujeres. 
     */
    public void betoQuiereNovia() {
        setSequence(new int[]{KeyEvent.VK_B, KeyEvent.VK_E, KeyEvent.VK_T, KeyEvent.VK_O, KeyEvent.VK_Q, KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_E, KeyEvent.VK_R, KeyEvent.VK_E, KeyEvent.VK_N, KeyEvent.VK_O, KeyEvent.VK_V, KeyEvent.VK_I, KeyEvent.VK_A});
    }

    /**
     * Metodo que es llamado para revisar cada <code>KeyEvent</code> del juego
     * que permite revisar si se está inroduciendo una secuencia válida. 
     * @param e de tipo <code>KeyEvent</code>
     */
    public void check(KeyEvent e) {
        //Key sequence pressed is correct thus far
        if (e.getKeyCode() == sequence[currentButton]) {
            currentButton++;

            //return true when last button is pressed
            if (currentButton == sequence.length) {

                //Important! Next call to checkKonami()
                //would result in ArrayIndexOutOfBoundsException otherwise
                contador++;
                currentButton = 0;
                if (encendido) {
                    encendido = false;
                } else {
                    encendido = true;
                }
            }
        } else {
            //Reset currentButton
            currentButton = 0;
        }
    }
}
