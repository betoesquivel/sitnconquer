/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.event.KeyEvent;

/**
 *
 * @author bernardot
 */
public class Cheat {

    private int[] sequence;
    private int currentButton;
    private boolean encendido;
    private int contador;

    public Cheat() {
        encendido = false;
        contador = 0;
        currentButton = 0;
    }

    public Cheat(boolean b) {
        encendido = b;
        contador = 0;
        currentButton = 0;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int[] getSequence() {
        return sequence;
    }

    public void setSequence(int[] sequence) {
        this.sequence = sequence;
    }

    public void betoQuiereNovia() {
        setSequence(new int[]{KeyEvent.VK_B, KeyEvent.VK_E, KeyEvent.VK_T, KeyEvent.VK_O, KeyEvent.VK_Q, KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_E, KeyEvent.VK_R, KeyEvent.VK_E, KeyEvent.VK_N, KeyEvent.VK_O, KeyEvent.VK_V, KeyEvent.VK_I, KeyEvent.VK_A});
    }

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
                if(encendido) {
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
