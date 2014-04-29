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
    Animacion aLeft;
    Animacion aRight;
    Animacion aUp;
    Animacion aDown;
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
    /*
     color = 0 es indefinido :P
     color = 1 es azul
     color = 2 es gris
     color = 3 es rojo
     color = 4 es verde
     */
    int valor; // Cuanto vale el monito dependiendo de su fuerza (upgrade)
    int velocidad; // Velocidad del monito (upgrade)
    int sentado; // 0 es izquierda, 1 es derecha, 2 es arriba, 3 es abajo, 4 arriba
    int estado; // 0 es sentado, 1 es parado, y 2 es enmovimiento
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
        aRight = new Animacion();
        aLeft = new Animacion();
        aDown = new Animacion();
        aUp = new Animacion();
        estado = 1;
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
        estado = 1;
        crearAnimaciones();
    }

    public void crearAnimaciones() {
        anim = new Animacion();
        aRight = new Animacion();
        aLeft = new Animacion();
        aDown = new Animacion();
        aUp = new Animacion();
        String aux = new String();
        aux = "";
        if (tipo == 1) {
            aux += "gamer";
        } else if (tipo == 2) {
            aux += "ilDivo";
        } else if (tipo == 3) {
            aux += "mike";
        } else if (tipo == 4) {
            aux += "travolta";
        }

        if (color == 1) {
            aux += "/azul/";
        } else if (color == 2) {
            aux += "/gris/";
        } else if (color == 3) {
            aux += "/rojo/";
        } else if (color == 4) {
            aux += "/verde/";
        }

        if (tipo == 1) {
            URL url1 = this.getClass().getResource("images/" + aux + "gamer_01.png");
            Image a1 = Toolkit.getDefaultToolkit().getImage(url1);
            URL url2 = this.getClass().getResource("images/" + aux + "gamer_02.png");
            Image a2 = Toolkit.getDefaultToolkit().getImage(url2);
            URL urlL = this.getClass().getResource("images/" + aux + "gamer_06.png");
            Image aL = Toolkit.getDefaultToolkit().getImage(urlL);
            URL urlR = this.getClass().getResource("images/" + aux + "gamer_07.png");
            Image aR = Toolkit.getDefaultToolkit().getImage(urlR);
            URL urlU = this.getClass().getResource("images/" + aux + "gamer_05.png");
            Image aU = Toolkit.getDefaultToolkit().getImage(urlU);
            URL urlD = this.getClass().getResource("images/" + aux + "gamer_10.png");
            Image aD = Toolkit.getDefaultToolkit().getImage(urlD);
            anim.sumaCuadro(a1, 400);
            anim.sumaCuadro(a2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 2) {
            URL url1 = this.getClass().getResource("images/" + aux + "divo_01.png");
            Image a1 = Toolkit.getDefaultToolkit().getImage(url1);
            URL url2 = this.getClass().getResource("images/" + aux + "divo_02.png");
            Image a2 = Toolkit.getDefaultToolkit().getImage(url2);
            URL urlL1 = this.getClass().getResource("images/" + aux + "divo_06.png");
            Image aL1 = Toolkit.getDefaultToolkit().getImage(urlL1);
            URL urlL2 = this.getClass().getResource("images/" + aux + "divo_06Extra.png");
            Image aL2 = Toolkit.getDefaultToolkit().getImage(urlL2);
            URL urlR1 = this.getClass().getResource("images/" + aux + "divo_07.png");
            Image aR1 = Toolkit.getDefaultToolkit().getImage(urlR1);
            URL urlR2 = this.getClass().getResource("images/" + aux + "divo_07Extra.png");
            Image aR2 = Toolkit.getDefaultToolkit().getImage(urlR2);
            URL urlU = this.getClass().getResource("images/" + aux + "divo_05.png");
            Image aU = Toolkit.getDefaultToolkit().getImage(urlU);
            URL urlD = this.getClass().getResource("images/" + aux + "divo_10.png");
            Image aD = Toolkit.getDefaultToolkit().getImage(urlD);
            anim.sumaCuadro(a1, 400);
            anim.sumaCuadro(a2, 400);
            aLeft.sumaCuadro(aL1, 400);
            aLeft.sumaCuadro(aL2, 400);
            aRight.sumaCuadro(aR1, 400);
            aRight.sumaCuadro(aR2, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 3) {
            URL url1 = this.getClass().getResource("images/" + aux + "mike_01.png");
            Image a1 = Toolkit.getDefaultToolkit().getImage(url1);
            URL url2 = this.getClass().getResource("images/" + aux + "mike_02.png");
            Image a2 = Toolkit.getDefaultToolkit().getImage(url2);
            URL urlL = this.getClass().getResource("images/" + aux + "mike_06.png");
            Image aL = Toolkit.getDefaultToolkit().getImage(urlL);
            URL urlR = this.getClass().getResource("images/" + aux + "mike_07.png");
            Image aR = Toolkit.getDefaultToolkit().getImage(urlR);
            URL urlU = this.getClass().getResource("images/" + aux + "mike_05.png");
            Image aU = Toolkit.getDefaultToolkit().getImage(urlU);
            URL urlD = this.getClass().getResource("images/" + aux + "mike_10.png");
            Image aD = Toolkit.getDefaultToolkit().getImage(urlD);
            anim.sumaCuadro(a1, 400);
            anim.sumaCuadro(a2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 4) {
            URL url1 = this.getClass().getResource("images/" + aux + "Travolta_01.png");
            Image a1 = Toolkit.getDefaultToolkit().getImage(url1);
            URL url2 = this.getClass().getResource("images/" + aux + "Travolta_02.png");
            Image a2 = Toolkit.getDefaultToolkit().getImage(url2);
            URL urlL = this.getClass().getResource("images/" + aux + "Travolta_06.png");
            Image aL = Toolkit.getDefaultToolkit().getImage(urlL);
            URL urlR = this.getClass().getResource("images/" + aux + "Travolta_07.png");
            Image aR = Toolkit.getDefaultToolkit().getImage(urlR);
            URL urlU = this.getClass().getResource("images/" + aux + "Travolta_05.png");
            Image aU = Toolkit.getDefaultToolkit().getImage(urlU);
            URL urlD = this.getClass().getResource("images/" + aux + "Travolta_10.png");
            Image aD = Toolkit.getDefaultToolkit().getImage(urlD);
            anim.sumaCuadro(a1, 400);
            anim.sumaCuadro(a2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        }
    }

    public void actualizaAnimaciones(long tiempoTranscurrido) {
        if (anim != null) {
            anim.actualiza(tiempoTranscurrido);
        }
        if (aLeft != null) {
            aLeft.actualiza(tiempoTranscurrido);
        }
        if (aRight != null) {
            aRight.actualiza(tiempoTranscurrido);
        }
        if (aUp != null) {
            aUp.actualiza(tiempoTranscurrido);
        }
        if (aDown != null) {
            aDown.actualiza(tiempoTranscurrido);
        }
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
     * Metodo de acceso que regresa el estado del personaje.
     * @return variable de tipo <code>int</code> con el estado del personaje.
     */
    public int getEstado() {
        return estado;
    }
    
    /**
     * Método modificador que cambia el valor de la variable estado del peronaje
     * recibe un...
     * @param e <code>int</code> con el estado del personaje. 
     */
    public void setEstado(int e) {
        estado = e; 
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
    
    public void paint(Graphics g) {
        g.drawImage(anim.getImagen(), getPosX(), getPosY(), null);
    }

    public void paintSentado(Graphics g, Silla s) {
        if (tipo == 1) {
            if (sentado == 0) {
                g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 10, null);
            } else if (sentado == 1) {
                g.drawImage(aRight.getImagen(), s.getPosX(), s.getPosY() - 10, null);
            } else if (sentado == 2) {
                g.drawImage(aUp.getImagen(), s.getPosX() + 5, s.getPosY() + 2, null);
            } else if (sentado == 3) {
                g.drawImage(aDown.getImagen(), s.getPosX() + 5, s.getPosY() - 8, null);
            }
        } else if (tipo == 2) {
            if (sentado == 0) {
                g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 10, null);
            } else if (sentado == 1) {
                g.drawImage(aRight.getImagen(), s.getPosX() - 1, s.getPosY() - 10, null);
            } else if (sentado == 2) {
                g.drawImage(aUp.getImagen(), s.getPosX() + 5, s.getPosY() + 2, null);
            } else if (sentado == 3) {
                g.drawImage(aDown.getImagen(), s.getPosX() + 7, s.getPosY() - 8, null);
            }
        } else if (tipo == 3) {
            if (sentado == 0) {
                g.drawImage(aLeft.getImagen(), s.getPosX() + 6, s.getPosY() - 10, null);
            } else if (sentado == 1) {
                g.drawImage(aRight.getImagen(), s.getPosX() - 9, s.getPosY() - 10, null);
            } else if (sentado == 2) {
                g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() + 2, null);
            } else if (sentado == 3) {
                g.drawImage(aDown.getImagen(), s.getPosX() - 1, s.getPosY() - 6, null);
            }
        } else if (tipo == 4) {
            if (sentado == 0) {
                g.drawImage(aLeft.getImagen(), s.getPosX() + 3, s.getPosY() - 10, null);
            } else if (sentado == 1) {
                g.drawImage(aRight.getImagen(), s.getPosX() + 1, s.getPosY() - 10, null);
            } else if (sentado == 2) {
                g.drawImage(aUp.getImagen(), s.getPosX() + 5, s.getPosY() + 2, null);
            } else if (sentado == 3) {
                g.drawImage(aDown.getImagen(), s.getPosX() + 7, s.getPosY() - 8, null);
            }
        }
    }

    public int getSentado() {
        return sentado;
    }

    public void setSentado(int sentado) {
        this.sentado = sentado;
    }

}
