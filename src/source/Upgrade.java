/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 *
 * @author bernardot
 */
public class Upgrade {

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
        funcion = 0;
    }

    /**
     * Método constructor de Upgrade para crear un objeto y definir su función.
     */
    public Upgrade(int f) {
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

    public void paint(Graphics g, int x, int y) {
        if (funcion == 1) {
            g.drawImage(anim.getImagen(), x + 15, y, null);
        } else if (funcion == 2) {
            g.drawImage(anim.getImagen(), x + 7, y - 25, null);
        } else if (funcion == 3) {
            g.drawImage(anim.getImagen(), x + 22, y, null);
        }
    }
    
    public void upgradeBonus(Personaje p) {
        if (funcion == 1) {
            p.setVelocidad(2);
        } else if (funcion == 2) {
            p.setValor(2);
        }
    }

}
