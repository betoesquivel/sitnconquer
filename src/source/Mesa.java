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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * La clase <I>Mesa</I> describe a una mesa dentro del juego. Las mesas pueden
 * estar siendo seleccionadas por máximo 2 jugadores y pertenecerle a solamente
 * 1 jugador.
 *
 * Las mesas tienen una lista de personajes de tipo
 * <code>LinkedList<Personaje></code> con los personajes que se encuentran
 * sentados en ella.
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
public class Mesa extends Base implements Constantes {

    private int sentados;   // Variable entera pasa saber cuantos están sentados
    private int valor;
    private Color color1;    // Color del jugador seleccionando la mesa
    private Color color2;   // Color del jugador seleccionando la mesa
    private Color colorPrincipal; //color del jugador dueño de la mesa.
    private int color;
    //    private int jugador;    // ID del jugador al que pertenece la mesa
    private LinkedList<Silla> sillas;   // Lista de sillas
    private LinkedList<Personaje> monitosSentados;   // Lista de personajes
    private int cantSillas; // Variable entera con la cantidad de sillas
    private Upgrade upgrade;    // Objeto upgrade que pudiera tener la mesa
    private int contTiempo; // contador para el upgrade de vida
    private int tipo;
    private SoundClip conquerClip;
    /* tipo = 0: mesa indefinida
     *  tipo = 1: mesa redonda con 4 sillas
     *  tipo = 2: mesa de billar
     *  tipo = 3: mesa de centrales
     *  tipo = 4: mesa de bahia
     *  tipo = 5: mesa de playa redonda
     *  tipo = 6: mesa de playa cuadrada
     */

    /**
     * Método constructor de Mesa para definir solo la posición y crear el
     * objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public Mesa(int posX, int posY) {
        super(posX, posY);
        sentados = valor = contTiempo = 0;
        color = 0;
        cantSillas = tipo = 0; // Valor default
        sillas = new LinkedList();
        monitosSentados = new LinkedList();
        upgrade = new Upgrade();
        conquerClip = new SoundClip(sMesaCapturada);
    }

    /**
     * Método constructor de Mesa para definir la posición y la imágen de la
     * mesa.
     *
     * @param posX es la <cdoe>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param image es la Imagen de tipo <code>Image</code> del objeto.
     */
    public Mesa(int posX, int posY, Image image) {
        super(posX, posY);
        ImageIcon icono = new ImageIcon(image);
        this.setImageIcon(icono);
        sillas = new LinkedList();
        monitosSentados = new LinkedList();
        sentados = valor = contTiempo = 0;
        color = 0;
        cantSillas = tipo = 0; // Valor default
        upgrade = new Upgrade();
        colorPrincipal = null;
        conquerClip = new SoundClip(sMesaCapturada);
    }

    /**
     * Método constructor de Mesa para definir la posición y la imágen de la
     * mesa. Así como también para definir el tipo de la misma.
     *
     * @param posX es la <cdoe>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param image es la Imagen de tipo <code>Image</code> del objeto.
     * @param t es el tipo de tipo <code>int</code> del objeto.
     */
    public Mesa(int posX, int posY, Image image, int t) {
        super(posX, posY);
        ImageIcon icono = new ImageIcon(image);
        this.setImageIcon(icono);
        sillas = new LinkedList();
        monitosSentados = new LinkedList();
        sentados = valor = contTiempo = 0;
        color = 0;
        cantSillas = 0; // Valor default
        tipo = t;
        if (tipo == 1 || tipo == 3 || tipo == 4 || tipo == 5) {
            cantSillas = 4;
        } else if (tipo == 6) {
            cantSillas = 2;
        }
        upgrade = new Upgrade();
        colorPrincipal = null;
        conquerClip = new SoundClip(sMesaCapturada);
    }

    /**
     * Método constructor de Mesa para definir la posición, el upgrade y crear
     * el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param tipoUpgrade es el <code>upgrade</code> con el que cuenta la mesa.
     */
    public Mesa(int posX, int posY, int tipoUpgrade) {
        super(posX, posY);
        sillas = new LinkedList();
        monitosSentados = new LinkedList();
        sentados = valor = contTiempo = 0;
        color = 0;
        cantSillas = tipo = 0; // Valor default
        upgrade = new Upgrade(tipoUpgrade);
        colorPrincipal = null;
        conquerClip = new SoundClip(sMesaCapturada);
    }

    /**
     * Método constructor de Mesa para definir la posición, el upgrade, cantidad
     * de sillas y crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param tipoUpgrade es el <code>upgrade</code> con el que cuenta la mesa.
     * @param numeroSillas es la <code>cantidad de sillas</code> con las que
     * cuenta la mesa.
     */
    public Mesa(int posX, int posY, int tipoUpgrade, int numeroSillas) {
        super(posX, posY);
        sentados = valor = contTiempo = 0;
        color = 0;
        tipo = 0; // Valor default
        cantSillas = numeroSillas;
        monitosSentados = new LinkedList();
        sillas = new LinkedList();
        upgrade = new Upgrade(tipoUpgrade);
        colorPrincipal = null;
        conquerClip = new SoundClip(sMesaCapturada);
    }

    /**
     * Método paint de la clase mesa que pinta la mesa.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paint(Graphics g) {
        Font helvetica = new Font("Helvetica", Font.BOLD, 18);
        g.setFont(helvetica);
        g.setColor(Color.WHITE);
        //draws the number of sitted people on the center of a table...
        //moves the text 10 units up to avoid being covered by the sitted fellas
        if (tipo == 1 || tipo == 2 || tipo == 4 || tipo == 5 || tipo == 6) {
            g.drawString(getValor() + "", getPosX() + getAncho(), getPosY() - 10);
        } else {
            g.setColor(Color.YELLOW);
            g.drawString(getValor() + "", getPosX() + getAncho(), getPosY() - 25);
        }
        g.setColor(Color.BLACK);
        g.drawImage(getImageIcon().getImage(), getPosX(), getPosY(), null);
        if (upgrade.getFuncion() != 0) {
            upgrade.paint(g, getPosX(), getPosY(), Mesa.this);
        }
    }

    /**
     * Método que pinta las sillas en la parte superior de la mesa.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paintSillasArriba(Graphics g) {
        //Color c = new Color(6, 100, 6, 100);
        //g.setColor(c);
        //g.fillRect(getPosX() - 25, getPosY() - 30, getAncho() + 50, getAlto() + 40);
        if (tipo == 2) {
            paintMonitoArriba(g);
        } else if (tipo == 1 || tipo == 3 || tipo == 4 || tipo == 5) {
            for (int x = 0; x < sillas.size() - 1; x++) {
                Silla aux = (Silla) sillas.get(x);
                aux.paint(g);
            }
            paintMonitoIzqDer(g);
            paintMonitoArriba(g);
        } else if (tipo == 6) {
            for (int x = 0; x < sillas.size(); x++) {
                Silla aux = (Silla) sillas.get(x);
                aux.paint(g);
            }
            paintMonitoIzqDer(g);
        }
    }

    /**
     * Método que pinta las sillas en la parte inferior de la mesa.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paintSillasAbajo(Graphics g) {
        if (tipo == 1 || tipo == 3 || tipo == 5) {
            paintMonitoAbajo(g);
            for (int x = sillas.size() - 1; x < sillas.size(); x++) {
                Silla aux = (Silla) sillas.get(x);
                aux.paint(g);
            }
        } else if (tipo == 2 && sentados > 2) {
            monitosSentados.get(2).paintBillar(g, this, 2);
            if (sentados > 3) {
                monitosSentados.get(2).paintBillar(g, this, 3);
            }
        } else if (tipo == 4) {
            for (int x = sillas.size() - 1; x < sillas.size(); x++) {
                Silla aux = (Silla) sillas.get(x);
                aux.paint(g);
            }
            paintMonitoAbajo(g);
        }
    }

    /**
     * Método que pinta las sillas en la parte izquierda de la mesa.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paintMonitoIzqDer(Graphics g) {
        if (tipo == 1 || tipo == 3 || tipo == 4 || tipo == 5 && monitosSentados.size() > 0) {
            for (int x = 0; x < sillas.size() - 2; x++) {
                Silla aux = (Silla) sillas.get(x);
                if (aux.isOcupada()) {
                    monitosSentados.get(x).paintSentado(g, aux, this);
                }
            }
        } else if (tipo == 6) {
            for (int x = 0; x < sillas.size(); x++) {
                Silla aux = (Silla) sillas.get(x);
                if (aux.isOcupada()) {
                    monitosSentados.get(x).paintSentado(g, aux, this);
                }
            }
        }
    }

    /**
     * Método que pinta un monito sentado en la parte superior de la mesa.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paintMonitoArriba(Graphics g) {
        if (tipo == 2 && sentados > 0) {
            monitosSentados.get(0).paintBillar(g, this, 0);
            if (sentados > 1) {
                monitosSentados.get(1).paintBillar(g, this, 1);
            }
        } else if (tipo == 1 || tipo == 3 || tipo == 4 || tipo == 5 && monitosSentados.size() > 2) {
            Silla aux = (Silla) sillas.get(2);
            if (aux.isOcupada()) {
                monitosSentados.get(2).paintSentado(g, aux, this);
            }
        }
    }

    /**
     * Método que pinta un monito sentado en la parte inferior de la mesa.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paintMonitoAbajo(Graphics g) {
        if (tipo == 1 || tipo == 3 || tipo == 4 || tipo == 5 && monitosSentados.size() > 3) {
            Silla aux = (Silla) sillas.get(3);
            if (aux.isOcupada()) {
                monitosSentados.get(3).paintSentado(g, aux, this);
            }
        }
    }

    /**
     * Método que pinta los selectores que estén posicionados dentro de la mesa.
     * El selector del jugador 1 y el del jugador 2.
     *
     * @param g de tipo <code>Graphics</code>
     */
    public void paintSelectors(Graphics g) {
        Color playerColor;
        int displacement = 5; //table selector size
        if (color1 != null) {
            playerColor = new Color(color1.getRed(), color1.getGreen(), color1.getBlue(), 150);
            g.setColor(playerColor);
            g.fillRect(getPosX() - displacement, getPosY() - displacement, icono.getIconWidth() + (displacement * 2), icono.getIconHeight() + (displacement * 2));
        }
        if (color2 != null) {
            playerColor = new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), 150);
            g.setColor(playerColor);
            g.fillOval(getPosX() - displacement, getPosY() - displacement, icono.getIconWidth() + (displacement * 2), icono.getIconHeight() + (displacement * 2));
        }
    }

    /**
     * Metodo de acceso que regresa el valor de la mesa
     *
     * @return valor de tipo <code>int</code>
     */
    public int getValor() {
        return valor;
    }

    /**
     * Método modificador que cambia el valor de la mesa.
     *
     * @param valor de tipo <code>int</code>
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Metodo de acceso que regresa la cantidad de personas sentadas del objeto
     *
     * @return sentados
     */
    public int getSentados() {
        return sentados;
    }

    /**
     * Metodo modificador usado para cambiar la cantidad de sentados del objeto
     *
     * @param sentados es la <code>cantidad de personas sentadas</code> del
     * objeto.
     */
    public void setSentados(int sentados) {
        this.sentados = sentados;
    }

    /**
     * Metodo de acceso usado para obtener el color1 del jugador dueño de la
     * mesa
     *
     * @param sentados es la <code>cantidad de personas sentadas</code> del
     * objeto.
     */
    public Color getColor1() {
        return color1;
    }

    /**
     * Método modificador que cambia el color del primer selector que está sobre
     * la mesa.
     *
     * @param color1 de tipo <code>Color</code>
     */
    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    /**
     * Metodo de acceso usado para obtener el color2 del jugador2 seleccionando
     * la mesa.
     *
     * @param sentados es la <code>cantidad de personas sentadas</code> del
     * objeto.
     */
    public Color getColor2() {
        return color2;
    }

    /**
     * Método modificador que cambia el color del segundo selector que está
     * sobre la mesa.
     *
     * @param color1 de tipo <code>Color</code>
     */
    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    /**
     * Metodo de acceso que regresa la lista con las sillas del objeto
     *
     * @return sillas
     */
    public LinkedList<Silla> getSillas() {
        return sillas;
    }

    /**
     * Metodo modificador usado para cambiar la lista de sillas del objeto
     *
     * @param sillas es la <code>lista de sillas</code> del objeto.
     */
    public void setSillas(LinkedList<Silla> sillas) {
        this.sillas = sillas;
    }

    /**
     * Metodo de acceso que regresa la cantidad de sillas del objeto
     *
     * @return cantSillas
     */
    public int getCantSillas() {
        return cantSillas;
    }

    /**
     * Metodo modificador usado para cambiar la cantidad de sillas del objeto
     *
     * @param cantSillas es la <code>cantidad de sillas</code> del objeto.
     */
    public void setCantSillas(int cantSillas) {
        this.cantSillas = cantSillas;
    }

    /**
     * Metodo de acceso que regresa el upgrade del objeto
     *
     * @return upgrade
     */
    public Upgrade getUpgrade() {
        return upgrade;
    }

    /**
     * Metodo modificador usado para cambiar el upgrade del objeto
     *
     * @param upgrade es el <code>upgrade</code> del objeto.
     */
    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
    }

    /**
     * Metodo de acceso que regresa el tipo de mesa del objeto
     *
     * @return tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Metodo modificador usado para cambiar el tipo del objeto
     *
     * @param tipo es el <code>tipo</code> de mesa que es.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
        if (tipo == 1 || tipo == 3 || tipo == 4 || tipo == 5) {
            cantSillas = 4;
        } else if (tipo == 6) {
            cantSillas = 2;
        }
    }

    /**
     * Metodo utilizado para crear las sillas correspondientes al tipo de mesa
     * que es de manera automatica.
     *
     */
    public void crearSillas() {
        if (tipo != 0) {
            sillas = new LinkedList();
            if (tipo == 1) {
                URL sillaLeft = this.getClass().getResource("images/chair2.png");
                Image sillaL = Toolkit.getDefaultToolkit().getImage(sillaLeft);
                URL sillaRight = this.getClass().getResource("images/chair1.png");
                Image sillaR = Toolkit.getDefaultToolkit().getImage(sillaRight);
                URL sillaUp = this.getClass().getResource("images/chair4.png");
                Image sillaU = Toolkit.getDefaultToolkit().getImage(sillaUp);
                URL sillaDown = this.getClass().getResource("images/chair3.png");
                Image sillaD = Toolkit.getDefaultToolkit().getImage(sillaDown);
                ImageIcon siLeft = new ImageIcon(sillaL);
                ImageIcon siRight = new ImageIcon(sillaR);
                ImageIcon siUp = new ImageIcon(sillaU);
                ImageIcon siDown = new ImageIcon(sillaD);
                Silla auxLeft = new Silla(this.getPosX() - 28, this.getPosY());
                Silla auxRight = new Silla(this.getPosX() + icono.getIconWidth() - 12, this.getPosY());
                Silla auxUp = new Silla(this.getPosX() + icono.getIconWidth() / 4, this.getPosY() - icono.getIconHeight() / 2);
                Silla auxDown = new Silla(this.getPosX() + icono.getIconWidth() / 4, this.getPosY() + icono.getIconHeight() / 2);
                auxLeft.setImageIcon(siLeft);
                auxRight.setImageIcon(siRight);
                auxUp.setImageIcon(siUp);
                auxDown.setImageIcon(siDown);
                /*auxLeft.setPosX(auxLeft.getPosX() - 30);
                 auxRight.setPosX(auxRight.getPosX() + icono.getIconWidth() - 10);
                 auxUp.setPosY(auxUp.getPosY() - icono.getIconHeight()/2);
                 auxUp.setPosX(auxUp.getPosX() + icono.getIconWidth()/4);
                 auxDown.setPosX(auxDown.getPosX() + icono.getIconWidth()/4);
                 auxDown.setPosY(auxDown.getPosY() + icono.getIconHeight()/2);*/
                sillas.add(auxLeft);
                sillas.add(auxRight);
                sillas.add(auxUp);
                sillas.add(auxDown);
            } else if (tipo == 3) {
                URL sillaLeft = this.getClass().getResource("images/silla2.png");
                Image sillaL = Toolkit.getDefaultToolkit().getImage(sillaLeft);
                URL sillaRight = this.getClass().getResource("images/silla1.png");
                Image sillaR = Toolkit.getDefaultToolkit().getImage(sillaRight);
                URL sillaUp = this.getClass().getResource("images/silla4.png");
                Image sillaU = Toolkit.getDefaultToolkit().getImage(sillaUp);
                URL sillaDown = this.getClass().getResource("images/silla3.png");
                Image sillaD = Toolkit.getDefaultToolkit().getImage(sillaDown);
                ImageIcon siLeft = new ImageIcon(sillaL);
                ImageIcon siRight = new ImageIcon(sillaR);
                ImageIcon siUp = new ImageIcon(sillaU);
                ImageIcon siDown = new ImageIcon(sillaD);
                Silla auxLeft = new Silla(this.getPosX() - 28, this.getPosY());
                Silla auxRight = new Silla(this.getPosX() + icono.getIconWidth(), this.getPosY());
                Silla auxUp = new Silla(this.getPosX() + icono.getIconWidth() / 4 + 5, this.getPosY() - icono.getIconHeight() / 2 + 10);
                Silla auxDown = new Silla(this.getPosX() + icono.getIconWidth() / 4 + 5, this.getPosY() + icono.getIconHeight() / 2 - 10);
                auxLeft.setImageIcon(siLeft);
                auxRight.setImageIcon(siRight);
                auxUp.setImageIcon(siUp);
                auxDown.setImageIcon(siDown);

                sillas.add(auxLeft);
                sillas.add(auxRight);
                sillas.add(auxUp);
                sillas.add(auxDown);

            } else if (tipo == 4) {
                URL sillaLeft = this.getClass().getResource("images/sillaBahia.png");
                Image sillaL = Toolkit.getDefaultToolkit().getImage(sillaLeft);
                URL sillaRight = this.getClass().getResource("images/sillaBahia.png");
                Image sillaR = Toolkit.getDefaultToolkit().getImage(sillaRight);
                URL sillaUp = this.getClass().getResource("images/sillaBahia.png");
                Image sillaU = Toolkit.getDefaultToolkit().getImage(sillaUp);
                URL sillaDown = this.getClass().getResource("images/sillaBahia.png");
                Image sillaD = Toolkit.getDefaultToolkit().getImage(sillaDown);
                ImageIcon siLeft = new ImageIcon(sillaL);
                ImageIcon siRight = new ImageIcon(sillaR);
                ImageIcon siUp = new ImageIcon(sillaU);
                ImageIcon siDown = new ImageIcon(sillaD);
                Silla auxLeft = new Silla(this.getPosX() - 28, this.getPosY() + 18);
                Silla auxRight = new Silla(this.getPosX() + icono.getIconWidth(), this.getPosY() + 18);
                Silla auxUp = new Silla(this.getPosX() + icono.getIconWidth() / 4 + 5, this.getPosY() - icono.getIconHeight() / 2 + 20);
                Silla auxDown = new Silla(this.getPosX() + icono.getIconWidth() / 4 + 5, this.getPosY() + icono.getIconHeight() / 2 + 10);
                auxLeft.setImageIcon(siLeft);
                auxRight.setImageIcon(siRight);
                auxUp.setImageIcon(siUp);
                auxDown.setImageIcon(siDown);

                sillas.add(auxLeft);
                sillas.add(auxRight);
                sillas.add(auxUp);
                sillas.add(auxDown);
            } else if (tipo == 5) {
                URL sillaLeft = this.getClass().getResource("images/sillaPlaya1.png");
                Image sillaL = Toolkit.getDefaultToolkit().getImage(sillaLeft);
                URL sillaRight = this.getClass().getResource("images/sillaPlaya2.png");
                Image sillaR = Toolkit.getDefaultToolkit().getImage(sillaRight);
                URL sillaUp = this.getClass().getResource("images/sillaPlaya4.png");
                Image sillaU = Toolkit.getDefaultToolkit().getImage(sillaUp);
                URL sillaDown = this.getClass().getResource("images/sillaPlaya3.png");
                Image sillaD = Toolkit.getDefaultToolkit().getImage(sillaDown);
                ImageIcon siLeft = new ImageIcon(sillaL);
                ImageIcon siRight = new ImageIcon(sillaR);
                ImageIcon siUp = new ImageIcon(sillaU);
                ImageIcon siDown = new ImageIcon(sillaD);
                Silla auxLeft = new Silla(this.getPosX() - 28, this.getPosY() + 10);
                Silla auxRight = new Silla(this.getPosX() + icono.getIconWidth() - 10, this.getPosY() + 10);
                Silla auxUp = new Silla(this.getPosX() + icono.getIconWidth() / 4 + 5, this.getPosY() - icono.getIconHeight() / 2 + 20);
                Silla auxDown = new Silla(this.getPosX() + icono.getIconWidth() / 4 + 5, this.getPosY() + icono.getIconHeight() / 2 - 10);
                auxLeft.setImageIcon(siLeft);
                auxRight.setImageIcon(siRight);
                auxUp.setImageIcon(siUp);
                auxDown.setImageIcon(siDown);

                sillas.add(auxLeft);
                sillas.add(auxRight);
                sillas.add(auxUp);
                sillas.add(auxDown);
            } else if (tipo == 6) {
                URL sillaLeft = this.getClass().getResource("images/sillaPlaya1.png");
                Image sillaL = Toolkit.getDefaultToolkit().getImage(sillaLeft);
                URL sillaRight = this.getClass().getResource("images/sillaPlaya2.png");
                Image sillaR = Toolkit.getDefaultToolkit().getImage(sillaRight);
                ImageIcon siLeft = new ImageIcon(sillaL);
                ImageIcon siRight = new ImageIcon(sillaR);
                Silla auxLeft = new Silla(this.getPosX() - 33, this.getPosY());
                Silla auxRight = new Silla(this.getPosX() + icono.getIconWidth() - 10, this.getPosY());
                auxLeft.setImageIcon(siLeft);
                auxRight.setImageIcon(siRight);

                sillas.add(auxLeft);
                sillas.add(auxRight);
            }
        }
    }

    /**
     * Metodo de acceso que regresa la lista de personajes sentados del objeto
     *
     * @return monitosSentados
     */
    public LinkedList<Personaje> getMonitosSentados() {
        return monitosSentados;
    }

    /**
     * Metodo modificador usado para cambiar la lista de personajes del objeto
     *
     * @param monitosSentados es la <code>lista</code> de personajes sentados en
     * la mesa.
     */
    public void setMonitosSentados(LinkedList<Personaje> monitosSentados) {
        this.monitosSentados = monitosSentados;
    }

    /**
     * Método de acceso que regresa el
     *
     * @return perimetro de tipo<code>Rectangle</code> del objeto.
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getPosX() - 25, getPosY() - 30, getAncho() + 40, getAlto() + 50);
    }

    /**
     * Método de acceso que regresa el color del jugador que es dueño de la
     * mesa.
     *
     * @return colorPrincipal de tipo <code>Color</code>
     */
    public Color getColorPrincipal() {
        return colorPrincipal;
    }

    /**
     * Metodo que recibe el jugador que quiere parar monitos y para a un monito
     * de los que estan en la mesa En ese momento, su valor ya no cuenta en la
     * mesa, porque está preparado para irse
     *
     * @param j
     */
    public synchronized void parar(Jugador j) {
        if (colorPrincipal == j.getColor()) {
            //está autorizado para parar monitos.
            if (sentados > 0) {
                //solamente me aseguro de que haya más monitos
                while (sentados > 0) {
                    Personaje monito = (Personaje) monitosSentados.getLast();
                    if (monito.getEstado() == SENTADO) {
                        monito.setEstado(PARADO);
                        valor -= monito.getValor();
                        sentados -= 1;
                        monito.setSentado(-1);
                        if (sentados < sillas.size()) {
                            sillas.get(sentados).setOcupada(false);
                        }
                        monitosSentados.remove(monito);
                        break;
                    }
                }
                if (sentados == 0) {
                    colorPrincipal = null;
                    color = 0;
                }
            }
        } else {
            System.out.println("No esta autorizado para parar monitos el jugador: " + j.getColor());
            System.out.println("Su color principal es:  " + getColorPrincipal());
        }
    }

    /**
     * Método que sienta a un personaje dentro de la mesa. Modificando el valor
     * de la mesa y pintándolo si es que todavía no se han llenado las sillas.
     *
     * @param p de tipo <code>Personaje</code>
     */
    public synchronized void sentar(Personaje p) {
        if (color == 0 || color == p.getColor()) {
            if (color == 0) {
                //play sound
                conquerClip.play();
            }
            color = p.getColor();
            colorPrincipal = p.getColorPadre();
            if (upgrade.getFuncion() != 0) {
                upgrade.upgradeBonus(p);
            }
            valor += p.getValor();
            p.setSentado(sentados);
            p.setEstado(SENTADO);
            monitosSentados.add(p);
            if (sentados < sillas.size()) {
                sillas.get(sentados).setOcupada(true);
            }
            sentados++;
        } else {
            if (sentados > 0) {
                while (p.getValor() != 0 && valor != 0 && sentados != 0) {
                    Personaje defensa = (Personaje) monitosSentados.getLast();
                    int ganadorBatalla = p.getValor() - defensa.getValor();
                    if (ganadorBatalla == 0) {
                        sentados--;
                        valor--;
                        if (sentados < sillas.size()) {
                            sillas.get(sentados).setOcupada(false);
                        }
                        defensa.setSentado(-1);
                        monitosSentados.removeLast();
                        p.setValor(ganadorBatalla);
                        defensa.setValor(ganadorBatalla);
                        p.setEstado(-1);
                        defensa.setEstado(-1);
                    } else if (ganadorBatalla > 0) {
                        valor--;
                        sentados--;
                        monitosSentados.removeLast();
                        defensa.setEstado(-1);
                        p.setValor(ganadorBatalla);
                    } else if (ganadorBatalla < 0) {
                        valor--;
                        p.setEstado(-1);
                        defensa.setValor(-ganadorBatalla);
                    }
                }
            }

            if (sentados == 0) {
                color = 0;
                colorPrincipal = null;
            }
        }
    }

    /**
     * Método que crea un jugador parado y adjunto a la mesa.
     *
     * @param j de tipo <code>Jugador</code>
     */
    public void doyVida(Jugador j) {
        contTiempo++;
        if (contTiempo % 250 == 0) {
            j.crearPersonajeSentado(this);
            upgrade.setPintar(true);
            upgrade.setContPintar(0);
        }
    }
}
