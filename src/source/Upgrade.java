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
 * La clase <I>Upgrade</I> describe los tipos de mejoras que pueden contener las
 * mesas que son aplicadas a los personajes que se sienten en ellas. Cuando
 * un personaje que ya tiene una mejora se sienta en una mesa que le dé una 
 * mejora, se pierde la mejora anterior. 
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
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

    /**
     * Método de acceso que regresa si se debe pintar un upgrade en la mesa.
     * @return pintar de tipo <code>boolean</code>
     */
    public boolean isPintar() {
        return pintar;
    }

    /**
     * Método modificador que actualiza la variabe pintar. 
     * @param pintar de tipo <code>boolean</code>
     */
    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    /**
     * Método de acceso que regresa la animación del upgrade
     * @return anim de tipo <code>Animacion</code>
     */
    public Animacion getAnim() {
        return anim;
    }

    /**
     * Método modificador que cambia la animación del objeto. 
     * @param anim de tipo <code>Animación</code>
     */
    public void setAnim(Animacion anim) {
        this.anim = anim;
    }

    /**
     * Método de acceso que regresa el contador de pintar.
     * @return contPintar de tipo <code>int</code>
     */
    public int getContPintar() {
        return contPintar;
    }

    /**
     * Método modificador que actualiza el contador de pintar. 
     * @param contPintar de tipo <code>int</code>
     */
    public void setContPintar(int contPintar) {
        this.contPintar = contPintar;
    }

    /**
     * Método crearAnimación que crea la animación del upgrade dentro de la
     * mesa. Por ahora los upgrades son animaciones de solamente un frame, pero 
     * esto brinda oportunidad de crecimiento. 
     */
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

    /**
     * Método que pinta el upgrade en una posición específica de una Mesa. 
     * @param g de tipo <code>Graphics</code>
     * @param x de tipo <code>int</code>
     * @param y de tipo <code>int</code>
     * @param m de tipo <code>Mesa</code>
     */
    public void paint(Graphics g, int x, int y, Mesa m) {
        if (m.getTipo() == 1 || m.getTipo() == 4) {
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

    /**
     * Método que actualiza las características de un personaje que este
     * sentado en una mesa con este upgrade. 
     * @param p de tipo <code>Personaje</code>
     */
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
