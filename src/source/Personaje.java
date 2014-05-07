/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

/**
 * La clase <I>Personaje</I> describe a un personaje o un monito dentro del
 * juego. Un monito tiene: un <code>color</code> que define el dueño al que
 * pertenece. <code>velocidad en x y en y</code>, que define la dirección de su
 * movimiento. <code>animaciones</code>, del personaje. <code>sentado</code>,
 * con la orientación en la que se ha sentado. <code>mesa destino</code>, que
 * define la mesa a la que se dirige en caso de estar <I>ENMOVIMIENTO</I>
 * <code>estado</code>, que define el estado en el que está
 * <I>PARADO, SENTADO o ENMOVIMIENTO</I>
 *
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
public class Personaje extends Base implements Constantes {

    int velX;
    int velY;

    Mesa mesaDestino; //contiene la mesa a la que tiene que ir el personaje cuando está ENMOVIMIENTO
    Animacion aBack;
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
     tipo = 5 es alejandra
     tipo = 6 es estefy
     */
    int color; // Color del monito que va de acuerdo con el jugador que es dueño
    Color colorPadre;
    /*
     color = 0 es indefinido :P
     color = 1 es azul
     color = 2 es gris
     color = 3 es rojo
     color = 4 es verde
     */
    int valor; // Cuanto vale el monito dependiendo de su fuerza (upgrade)
    int velocidad; // Velocidad del monito (upgrade)
    int sentado; // -1 es indefinido 0 es izquierda, 1 es derecha, 2 es arriba, 3 es abajo, 4 arriba
    int estado = PARADO; // -1 es muerto, 0 es sentado, 1 es parado, y 2 es enmovimiento

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
        aBack = new Animacion();
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
        switch (col) {
            case BLUE:
                colorPadre = Color.BLUE;
                break;
            case RED:
                colorPadre = Color.RED;
                break;
            case GRAY:
                colorPadre = Color.GRAY;
                break;
            case GREEN:
                colorPadre = Color.GREEN;
                break;
        }
        valor = velocidad = 1;
        estado = 1;
        crearAnimaciones();
    }

    /**
     * Método que inicializa todas las animaciones del personaje.
     */
    public void crearAnimaciones() {
        anim = new Animacion();
        aRight = new Animacion();
        aLeft = new Animacion();
        aDown = new Animacion();
        aUp = new Animacion();
        aBack = new Animacion();
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
        } else if (tipo == 5) {
            aux += "alejandra";
        } else if (tipo == 6) {
            aux += "estefy";
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
            URL urlB1 = this.getClass().getResource("images/" + aux + "gamer_08.png");
            Image aB1 = Toolkit.getDefaultToolkit().getImage(urlB1);
            URL urlB2 = this.getClass().getResource("images/" + aux + "gamer_09.png");
            Image aB2 = Toolkit.getDefaultToolkit().getImage(urlB2);
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
            aBack.sumaCuadro(aB1, 400);
            aBack.sumaCuadro(aB2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 2) {
            URL urlB1 = this.getClass().getResource("images/" + aux + "divo_08.png");
            Image aB1 = Toolkit.getDefaultToolkit().getImage(urlB1);
            URL urlB2 = this.getClass().getResource("images/" + aux + "divo_09.png");
            Image aB2 = Toolkit.getDefaultToolkit().getImage(urlB2);
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
            aBack.sumaCuadro(aB1, 400);
            aBack.sumaCuadro(aB2, 400);
            aLeft.sumaCuadro(aL1, 400);
            aLeft.sumaCuadro(aL2, 400);
            aRight.sumaCuadro(aR1, 400);
            aRight.sumaCuadro(aR2, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 3) {
            URL urlB1 = this.getClass().getResource("images/" + aux + "mike_08.png");
            Image aB1 = Toolkit.getDefaultToolkit().getImage(urlB1);
            URL urlB2 = this.getClass().getResource("images/" + aux + "mike_09.png");
            Image aB2 = Toolkit.getDefaultToolkit().getImage(urlB2);
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
            aBack.sumaCuadro(aB1, 400);
            aBack.sumaCuadro(aB2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 4) {
            URL urlB1 = this.getClass().getResource("images/" + aux + "Travolta_08.png");
            Image aB1 = Toolkit.getDefaultToolkit().getImage(urlB1);
            URL urlB2 = this.getClass().getResource("images/" + aux + "Travolta_09.png");
            Image aB2 = Toolkit.getDefaultToolkit().getImage(urlB2);
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
            aBack.sumaCuadro(aB1, 400);
            aBack.sumaCuadro(aB2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 5) {
            URL url1 = this.getClass().getResource("images/" + aux + "ale_01.png");
            Image a1 = Toolkit.getDefaultToolkit().getImage(url1);
            URL url2 = this.getClass().getResource("images/" + aux + "ale_02.png");
            Image a2 = Toolkit.getDefaultToolkit().getImage(url2);
            URL urlL = this.getClass().getResource("images/" + aux + "ale_03.png");
            Image aL = Toolkit.getDefaultToolkit().getImage(urlL);
            URL urlR = this.getClass().getResource("images/" + aux + "ale_04.png");
            Image aR = Toolkit.getDefaultToolkit().getImage(urlR);
            URL urlU = this.getClass().getResource("images/" + aux + "ale_05.png");
            Image aU = Toolkit.getDefaultToolkit().getImage(urlU);
            URL urlD = this.getClass().getResource("images/" + aux + "ale03.png");
            Image aD = Toolkit.getDefaultToolkit().getImage(urlD);
            anim.sumaCuadro(a1, 400);
            anim.sumaCuadro(a2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        } else if (tipo == 6) {
            URL url1 = this.getClass().getResource("images/" + aux + "estefy_01.png");
            Image a1 = Toolkit.getDefaultToolkit().getImage(url1);
            URL url2 = this.getClass().getResource("images/" + aux + "estefy_02.png");
            Image a2 = Toolkit.getDefaultToolkit().getImage(url2);
            URL urlL = this.getClass().getResource("images/" + aux + "estefy_03.png");
            Image aL = Toolkit.getDefaultToolkit().getImage(urlL);
            URL urlR = this.getClass().getResource("images/" + aux + "estefy_04.png");
            Image aR = Toolkit.getDefaultToolkit().getImage(urlR);
            URL urlU = this.getClass().getResource("images/" + aux + "estefy_05.png");
            Image aU = Toolkit.getDefaultToolkit().getImage(urlU);
            URL urlD = this.getClass().getResource("images/" + aux + "estefy05.png");
            Image aD = Toolkit.getDefaultToolkit().getImage(urlD);
            anim.sumaCuadro(a1, 400);
            anim.sumaCuadro(a2, 400);
            aLeft.sumaCuadro(aL, 400);
            aRight.sumaCuadro(aR, 400);
            aUp.sumaCuadro(aU, 400);
            aDown.sumaCuadro(aD, 400);
        }
    }

    /**
     * Método que actualiza la animación del personaje que está actualmente en
     * uso.
     *
     * @param tiempoTranscurrido
     */
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
        if (aBack != null) {
            aBack.actualiza(tiempoTranscurrido);
        }
    }

    /**
     * Metodo actualizaPosicion que hace precisamente eso: Actualiza la posición
     * del personaje de acuerdo a su mesa destino y su estado.
     */
    public void actualizaPosicion() {
        if (getEstado() == ENMOVIMIENTO) {
            //velocidad en x
            if (mesaDestino.getPosX() > getPosX()) {
                setVelX(velocidad);
            } else {
                setVelX(-velocidad);
            }
            //velocidad en y
            if (mesaDestino.getPosY() > getPosY()) {
                setVelY(velocidad);
            } else {
                setVelY(-velocidad);
            }
            setPosX(getPosX() + getVelX());
            setPosY(getPosY() + getVelY());
        }
    }

    /**
     * Metodo modificador que cambia el color del padre.
     *
     * @param c
     */
    public void setColorPadre(Color c) {
        colorPadre = c;
    }

    /**
     * Metodo de acceso que obtiene el color del padre.
     *
     * @return
     */
    public Color getColorPadre() {
        return colorPadre;
    }

    /**
     * Metodo que revisa si el personaje ha llegado a su destino
     */
    public boolean isInMesaDestino() {
        return intersecta(getMesaDestino());
    }

    /**
     * Metodo de acceso que regresa la mesa a la que se dirige el personaje.
     *
     * @param m
     */
    public Mesa getMesaDestino() {
        return mesaDestino;
    }

    /**
     * Metodo modificador que define la mesa a cuyo destino tiene que ir el
     * personaje
     *
     * @param m
     */
    public void setMesaDestino(Mesa m) {
        mesaDestino = m;
        setEstado(ENMOVIMIENTO);
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
     *
     * @return variable de tipo <code>int</code> con el estado del personaje.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Método modificador que cambia el valor de la variable estado del peronaje
     * recibe un...
     *
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

    /**
     * Metodo de acceso que regresa la velocidad horizontal del objeto
     *
     * @return velX de tipo <code>int</code>
     */
    public int getVelX() {
        return velX;
    }

    /**
     * Método modificador que cambia la velocidad horizontal del objeto
     *
     * @param velX de tipo <code>int</code>
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * Metodo de acceso que regresa la velocidad vertical del objeto
     *
     * @return velY de tipo <code>int</code>
     */
    public int getVelY() {
        return velY;
    }

    /**
     * Método modificador que cambia la velocidad vertical del objeto
     *
     * @param velY de tipo <code>int</code>
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * Metodo de acceso que regresa si el objeto está en una intersección
     *
     * @return intersecta de tipo <code>boolean</code>
     */
    public boolean isIntersecta() {
        return intersecta;
    }

    /**
     * Método modificador que cambia el valor de la variable intersecta.
     *
     * @param intersecta de tipo <code>boolean</code>
     */
    public void setIntersecta(boolean intersecta) {
        this.intersecta = intersecta;
    }

    /**
     * Metodo de acceso que regresa el perímetro del objeto en un
     *
     * @return rectángulo de tipo <code>Rectangle</code>
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getPosX(), getPosY() + 40, getAncho(), getAlto() - 40);
    }

    /**
     * Método que pinta al personaje.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paint(Graphics g) {
        g.drawImage(anim.getImagen(), getPosX(), getPosY(), null);
    }

    /**
     * Método que pinta al personaje en una mesa de billar
     *
     * @param g de tipo <code> Graphics </code>
     * @param m de tipo <code> Mesa </code>
     * @param pos de tipo <cdoe> int </code>
     */
    public void paintBillar(Graphics g, Mesa m, int pos) {
        if (pos == 0) {
            g.drawImage(anim.getImagen(), m.getPosX() + 60, m.getPosY() - 25, null);
        } else if (pos == 1) {
            g.drawImage(anim.getImagen(), m.getPosX() + 90, m.getPosY() - 15, null);
        } else if (pos == 2) {
            g.drawImage(aBack.getImagen(), m.getPosX() + 15, m.getPosY() + 10, null);
        } else if (pos == 3) {
            g.drawImage(aBack.getImagen(), m.getPosX() + 45, m.getPosY() + 25, null);
        }
    }

    /**
     * Método que pinta al personaje sentado en el caso de que se encuentre
     * sentado en una mesa.
     *
     * @param g de tipo <code>Graphics</code>
     * @param s de tipo <code>Silla</code>
     * @param m de tipo <code>Mesa</code>
     */
    public void paintSentado(Graphics g, Silla s, Mesa m) {
        if (tipo == 1) {
            if (m.getTipo() == 1 || m.getTipo() == 7) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX(), s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() + 5, s.getPosY() + 2, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() + 5, s.getPosY() - 8, null);
                }
            } else if (m.getTipo() == 3) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 5, s.getPosY() - 20, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                }
            } else if (m.getTipo() == 4) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX(), s.getPosY() - 35, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 5, s.getPosY() - 35, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX(), s.getPosY() - 38, null);
                }
            } else if (m.getTipo() == 5 || m.getTipo() == 6) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 4, s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() + 4, s.getPosY(), null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() + 5, s.getPosY() - 5, null);
                }
            }

        } else if (tipo == 2) {
            if (m.getTipo() == 1 || m.getTipo() == 7) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 1, s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() + 5, s.getPosY() + 2, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() + 7, s.getPosY() - 8, null);
                }
            } else if (m.getTipo() == 3) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 5, s.getPosY() - 20, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                }
            } else if (m.getTipo() == 4) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX(), s.getPosY() - 35, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 5, s.getPosY() - 35, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX(), s.getPosY() - 38, null);
                }
            } else if (m.getTipo() == 5 || m.getTipo() == 6) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 4, s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() + 4, s.getPosY(), null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() + 5, s.getPosY() - 5, null);
                }
            }
            
        } else if (tipo == 3) {
            if (m.getTipo() == 1 || m.getTipo() == 7) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 6, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 9, s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() + 2, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() - 1, s.getPosY() - 6, null);
                }
            } else if (m.getTipo() == 3) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 20, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 15, s.getPosY() - 20, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() - 7, s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() - 7, s.getPosY() - 20, null);
                }
            } else if (m.getTipo() == 4) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX(), s.getPosY() - 40, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 20, s.getPosY() - 40, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() - 7, s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() - 7, s.getPosY() - 33, null);
                }
            } else if (m.getTipo() == 5 || m.getTipo() == 6) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 10, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 15, s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() - 8, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX(), s.getPosY() - 10, null);
                }
            }
            
        } else if (tipo == 4 || tipo == 5 || tipo == 6) {
            if (m.getTipo() == 1 || m.getTipo() == 7) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 3, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() + 1, s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() + 5, s.getPosY() + 2, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() + 7, s.getPosY() - 8, null);
                }
            } else if (m.getTipo() == 3) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 5, s.getPosY() - 20, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                }
            } else if (m.getTipo() == 4) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX(), s.getPosY() - 35, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 5, s.getPosY() - 35, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX(), s.getPosY() - 20, null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX(), s.getPosY() - 38, null);
                }
            } else if (m.getTipo() == 5 || m.getTipo() == 6) {
                if (sentado == 0) {
                    g.drawImage(aLeft.getImagen(), s.getPosX() + 5, s.getPosY() - 10, null);
                } else if (sentado == 1) {
                    g.drawImage(aRight.getImagen(), s.getPosX() - 4, s.getPosY() - 10, null);
                } else if (sentado == 2) {
                    g.drawImage(aUp.getImagen(), s.getPosX() + 4, s.getPosY(), null);
                } else if (sentado == 3) {
                    g.drawImage(aDown.getImagen(), s.getPosX() + 5, s.getPosY() - 5, null);
                }
            }
        }
    }

    /**
     * Metodo de acceso que regresa la orientación en la que se encuentra
     * sentado el personaje.
     *
     * @return sentado de tipo <code>int</code>
     */
    public int getSentado() {
        return sentado;
    }

    /**
     * Método modificador que cambia la orientación en la que se encuentra
     * sentado el personaje.
     *
     * @param sentado de tipo <code>int</code>
     */
    public void setSentado(int sentado) {
        this.sentado = sentado;
    }

}
