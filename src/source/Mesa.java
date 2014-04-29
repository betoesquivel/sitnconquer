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

public class Mesa extends Base {

    private int sentados;   // Variable entera pasa saber cuantos están sentados
    private Color color1;    // Color del jugador dueño de la mesa
    private Color color2;    // Color del jugador dueño de la mesa
    //    private int jugador;    // ID del jugador al que pertenece la mesa
    private LinkedList<Silla> sillas;   // Lista de sillas
    private LinkedList<Personaje> monitosSentados;   // Lista de personajes
    private int cantSillas; // Variable entera con la cantidad de sillas
    private Upgrade upgrade;    // Objeto upgrade que pudiera tener la mesa
    private int tipo;
    /* tipo = 0: mesa indefinida
     *  tipo = 1: mesa redonda con 4 sillas
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
        sentados = 0;
        cantSillas = tipo = 0; // Valor default
        sillas = new LinkedList();
        monitosSentados = new LinkedList();
        upgrade = new Upgrade();
    }

    public Mesa(int posX, int posY, Image image) {
        super(posX, posY);
        ImageIcon icono = new ImageIcon(image);
        this.setImageIcon(icono);
        sillas = new LinkedList();
        monitosSentados = new LinkedList();
        sentados = 0;
        cantSillas = tipo = 0; // Valor default
        upgrade = new Upgrade();
    }

    public Mesa(int posX, int posY, Image image, int t) {
        super(posX, posY);
        ImageIcon icono = new ImageIcon(image);
        this.setImageIcon(icono);
        sillas = new LinkedList();
        monitosSentados = new LinkedList();
        sentados = 0;
        cantSillas = 0; // Valor default
        tipo = t;
        if (tipo == 1) {
            cantSillas = 4;
        }
        upgrade = new Upgrade();
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
        sentados = 0;
        cantSillas = tipo = 0; // Valor default
        upgrade = new Upgrade(tipoUpgrade);
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
        sentados = 0;
        tipo = 0; // Valor default
        cantSillas = numeroSillas;
        monitosSentados = new LinkedList();
        sillas = new LinkedList();
        upgrade = new Upgrade(tipoUpgrade);
    }

    public void paint(Graphics g) {
        Font helvetica = new Font("Helvetica", Font.BOLD, 18);
        g.setFont(helvetica);
        g.setColor(Color.WHITE);
        //draws the number of sitted people on the center of a table...
        //moves the text 10 units up to avoid being covered by the sitted fellas
        g.drawString(getSentados() + "", getPosX() + getAncho(), getPosY() - 10);
        g.setColor(Color.BLACK);
        g.drawImage(getImageIcon().getImage(), getPosX(), getPosY(), null);
    }

    public void paintSillasArriba(Graphics g) {
        Color c = new Color(6, 100, 6, 100);
        g.setColor(c);
        g.fillRect(getPosX() - 25, getPosY() - 30, getAncho() + 50, getAlto() + 40);
        if (tipo != 0) {
            for (int x = 0; x < sillas.size() - 1; x++) {
                Silla aux = (Silla) sillas.get(x);
                aux.paint(g);
            }
            paintMonitoIzqDer(g);
            paintMonitoArriba(g);
        }
    }

    public void paintSillasAbajo(Graphics g) {
        paintMonitoAbajo(g);
        if (tipo != 0) {
            for (int x = sillas.size() - 1; x < sillas.size(); x++) {
                Silla aux = (Silla) sillas.get(x);
                aux.paint(g);
            }
        }
    }

    public void paintMonitoIzqDer(Graphics g) {
        if (tipo != 0) {
            for (int x = 0; x < sillas.size() - 2; x++) {
                Silla aux = (Silla) sillas.get(x);
                if (aux.isOcupada()) {
                    monitosSentados.get(x).paintSentado(g, aux);
                }
            }
        }
    }

    public void paintMonitoArriba(Graphics g) {
        if (tipo != 0) {
            Silla aux = (Silla) sillas.get(2);
            if (aux.isOcupada()) {
                monitosSentados.get(2).paintSentado(g, aux);
            }
        }
    }

    public void paintMonitoAbajo(Graphics g) {
        if (tipo != 0) {
            Silla aux = (Silla) sillas.get(3);
            if (aux.isOcupada()) {
                monitosSentados.get(3).paintSentado(g, aux);
            }
        }
    }

    public void paintSelectors(Graphics g) {
        Color playerColor;
        int displacement = 5; //table selector size
        if (color1 != null) {
            playerColor = new Color(color1.getRed(), color1.getGreen(), color1.getBlue(), 100);
            g.setColor(playerColor);
            g.fillRect(getPosX() - displacement, getPosY() - displacement, icono.getIconWidth() + (displacement * 2), icono.getIconHeight() + (displacement * 2));
        }
        if (color2 != null) {
            playerColor = new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), 100);
            g.setColor(playerColor);
            g.fillOval(getPosX() - displacement, getPosY() - displacement, icono.getIconWidth() + (displacement * 2), icono.getIconHeight() + (displacement * 2));
        }
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

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

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
        if (tipo == 1) {
            cantSillas = 4;
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

    public Rectangle getPerimetro() {
        return new Rectangle(getPosX() - 25, getPosY() - 30, getAncho() + 40, getAlto() + 50);
    }

    public void sentar(Personaje p) {
        p.setSentado(sentados);
        p.setEstado(0);
        monitosSentados.add(p);
        if (sentados < sillas.size()) {
            sillas.get(sentados).setOcupada(true);
        }
        sentados++;
    }

}
