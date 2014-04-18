/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author Ferrufino
 */
import java.awt.Rectangle;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;
import java.awt.Point;
import javax.swing.JFrame;// 
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Ferrufino y Andres
 */
public class Game extends JFrame implements Constantes, Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;

    // Se declaran las variables para pintar 
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico

    //Objetos...
    private Mesa table;
    private Silla chair;
    private Personaje alesso;

    //Botones
    private Boton bPausa;

    //Objetos Imagen
    private Image fondo;

    //Objetos URL
    private URL fondoURL = this.getClass().getResource(iUrlFondo);

    //Lista de booleanas
    private boolean pausado;

    //Mesas y Sillas
    private LinkedList<Mesa> listaTables;
    private LinkedList<Silla> listaChairs;

    //Personajes
    private Animacion travolta;

    //Variables de control de tiempo de la animación
    private long tiempoActual;
    private long tiempoInicial;

    /**
     * Metodo <I>PlayGround()</I> de la clase <code>PlayGround</code>. Es el
     * constructor de la clase donde se definen las variables
     */
    public Game() {

        setSize(900, 670);
        pausado = false; 
        //Fondo
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL);
//        infoBar = Toolkit.getDefaultToolkit().getImage(barraInfoURL);
//        plateP = Toolkit.getDefaultToolkit().getImage(pausaURL);

        //Images 
//        imgCreditsBoton = Toolkit.getDefaultToolkit().getImage(creditsBotonURL);
        Image travolta1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/travolta1.png"));
        Image travolta2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/travolta2.png"));
        travolta = new Animacion();
        travolta.sumaCuadro(travolta1, 50);
        travolta.sumaCuadro(travolta2, 50);

//        bPausa = new Boton(850, 20, plateP);
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();

        //Controladores 
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();

    }

    public void crearMesasYSillas() {
//        table = new Mesa(75, 180, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);
//        table = new Mesa(75, 300, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);
//        table = new Mesa(75, 420, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);
//        table = new Mesa(370, 180, Toolkit.getDefaultToolkit().getImage(poolURL));
//        listaTables.add(table);
//        table = new Mesa(270, 300, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);
//        //table = new Mesa(270, 420, Toolkit.getDefaultToolkit().getImage(tableURL));
//        //listaTables.add(table);
//        //table = new Mesa(465, 180, Toolkit.getDefaultToolkit().getImage(tableURL));
//        //listaTables.add(table);
//        table = new Mesa(465, 300, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);
//        table = new Mesa(370, 420, Toolkit.getDefaultToolkit().getImage(poolURL));
//        listaTables.add(table);
//        table = new Mesa(660, 180, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);
//        table = new Mesa(660, 300, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);
//        table = new Mesa(660, 420, Toolkit.getDefaultToolkit().getImage(tableURL), 1);
//        listaTables.add(table);

        for (int x = 0; x < listaTables.size(); x++) {
            Mesa aux = (Mesa) listaTables.get(x);
            aux.crearSillas();
        }
    }

    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     *
     */
    public void run() {
        //Guarda el tiempo actual del sistema 
        tiempoActual = System.currentTimeMillis();

        while (true) {
//            if (!pausado) {
//                Actualiza();
//                ChecaColision();
//            }
            repaint();    // Se Actualiza el <code>Applet</code> repintando el POINTSenido.
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    /**
     * Metodo usado para actualizar la posicion de objetos del juego
     */
    public void Actualiza() {

        //Determina el tiempo que ha transcurrido desde que el Applet 
        //inicio su ejecución
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        travolta.actualiza(tiempoTranscurrido);
        //inicio su ejecución
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        travolta.actualiza(tiempoTranscurrido);
    }

    /**
     * Checa colisiones dentro del <code>JFrame</code>.
     */
    public void ChecaColision() {

    }

    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>, heredado
     * de la clase Container.<P>
     * En este metodo lo que hace es Actualizar el Paint
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Update la imagen de fondo.
        dbg.setColor(new Color(0x78, 0xae, 0xe3));
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Update el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen Actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Metodo <I>paint1</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion Actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @paramg es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {
        if (!pausado) {
            g.drawImage(fondo, 0, 0, this);
        } else {
            //juego pausado
//                g.drawImage(imgPantallaPausa, 0, 10, this);
        }
    }


    /*
     *Metodo keyPressed
     *Cuando una tecla esta apretada
     *recibe de param un evento, en este caso se busca que sea la p
     *para pausar el juego
     */
    public void keyPressed(KeyEvent e) {
        if (e.KEY_TYPED == e.VK_P){
            pausado = !pausado; 
        }
    }

    public void keyTyped(KeyEvent e) { //metodo cuando una tecla fue typeada
    }

    public void keyReleased(KeyEvent e) {   //metodo cuandos e suelta la tecla

    }

    /*
     *Metodo mouseClicked
     *Cuando el mouse es apretado
     *recibe de param un evento, que ayudara a definir donde fue picado
     *dentro del applet
     */
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) { //metodo cuando entra el mouse

    }

    public void mouseExited(MouseEvent e) { //metodo cuando sale el mouse

    }

    public void mousePressed(MouseEvent e) {    //metodo cuando el mouse es presionado

    }

    public void mouseReleased(MouseEvent e) {//metodo cuando el mouse es soltado

    }

    public void mouseMoved(MouseEvent e) {  //metodos de MouseMotionListener

    }

    public void mouseDragged(MouseEvent e) {   //metodos de MouseMotionListener

    }

    public void restart() {

    }

}