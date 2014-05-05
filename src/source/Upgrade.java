/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 *
 * @author bernardot
 */

/**
 * La clase <I>Upgrade</I> maneja los objetos que permiten a un jugador
 * conseguir extra puntos o una ventaja durante el juego.
 * 
 */
public class Upgrade {

    private boolean pintar;
    private int contPintar;
    private Animacion anim;
    private int funcion; // variable que indica que tipo de upgrade y función tiene
    /*
     funcion = 0: nada
     funcion = 1: velocidad
     funcion = 2: fuerza
     funcion = 3: vida
     */

    /**
     * Método constructor de Upgrade para crear un objeto.
     */
    public Upgrade() {
        pintar = false;
        funcion = 0;
    }

    /**
     * Método constructor de Upgrade para crear un objeto y definir su función.
     */
    public Upgrade(int f) {
        pintar = false;
        funcion = f;
        crearAnimacion();
    }

    /**
     * Metodo de acceso que regresa la función del objeto
     *
     * @return funcion
     */
    public int getFuncion() {
        return funcion;
    }

    /**
     * Metodo modificador usado para cambiar la funcion del objeto
     *
     * @param funcion es la <code>funcion</code> del objeto.
     */
    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }

    public boolean isPintar() {
        return pintar;
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    public Animacion getAnim() {
        return anim;
    }

    public void setAnim(Animacion anim) {
        this.anim = anim;
    }

    public int getContPintar() {
        return contPintar;
    }

    public void setContPintar(int contPintar) {
        this.contPintar = contPintar;
    }

    public void crearAnimacion() {
        anim = new Animacion();
        if (funcion == 1) {
            URL urlB = this.getClass().getResource("images/beers.png");
            Image aB = Toolkit.getDefaultToolkit().getImage(urlB);
            anim.sumaCuadro(aB, 400);
        } else if (funcion == 2) {
            URL urlB = this.getClass().getResource("images/comidaBar.png");
            Image aB = Toolkit.getDefaultToolkit().getImage(urlB);
            anim.sumaCuadro(aB, 400);
        } else if (funcion == 3) {
            URL urlB = this.getClass().getResource("images/vida.png");
            Image aB = Toolkit.getDefaultToolkit().getImage(urlB);
            anim.sumaCuadro(aB, 400);
        }
    }

    public void paint(Graphics g, int x, int y, Mesa m) {
        if (m.getTipo() == 1) {
            g.setColor(Color.WHITE);
            if (funcion == 1) {
                g.drawImage(anim.getImagen(), x + 15, y, null);
                if (pintar) {
                    g.drawString("+ Rápido", x, y + 5);
                    contPintar++;
                    if (contPintar > 25) {
                        pintar = false;
                    }
                }
            } else if (funcion == 2) {
                g.drawImage(anim.getImagen(), x + 7, y - 25, null);
                if (pintar) {
                    g.drawString("+ Fuerte", x, y + 5);
                    contPintar++;
                    if (contPintar > 25) {
                        pintar = false;
                    }
                }
            } else if (funcion == 3) {
                g.drawImage(anim.getImagen(), x + 22, y, null);
                if (pintar) {
                    g.drawString("Hola amigo", x, y + 5);
                    contPintar++;
                    if (contPintar > 25) {
                        pintar = false;
                    }
                }
            }
        } else if (m.getTipo() == 3) {
            g.setColor(Color.YELLOW);
            if (funcion == 1) {
                g.drawImage(anim.getImagen(), x + 12, y - 15, null);
                if (pintar) {
                    g.drawString("+ Rápido", x, y);
                    contPintar++;
                    if (contPintar > 25) {
                        pintar = false;
                    }
                }
            } else if (funcion == 2) {
                g.drawImage(anim.getImagen(), x + 7, y - 45, null);
                if (pintar) {
                    g.drawString("+ Fuerte", x, y);
                    contPintar++;
                    if (contPintar > 25) {
                        pintar = false;
                    }
                }
            } else if (funcion == 3) {
                g.drawImage(anim.getImagen(), x + 20, y - 20, null);
                if (pintar) {
                    g.drawString("Hola amigo", x, y);
                    contPintar++;
                    if (contPintar > 25) {
                        pintar = false;
                    }
                }
            }
        }
    }

    public void upgradeBonus(Personaje p) {
        if (funcion == 1) {
            p.setVelocidad(2);
            contPintar = 0;
            pintar = true;
        } else if (funcion == 2) {
            p.setValor(2);
            pintar = true;
            contPintar = 0;
        }
    }

}
