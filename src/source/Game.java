/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author 
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.JFrame;// 

/**
 *
 * @author Ferrufino y Andres
 */
public class Game extends JFrame implements Constantes, Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;

    // Se declaran las variables para pintar 
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    
    //Objetos Imagen
    private Image imgCreditsBoton;
    private Image imgHighScoreBoton;
    private Image imgPlayBoton;
    private Image imgLogoGrande;
    private Image imgLogo;        
    private Image imgBotonNext;
    private Image imgSelecColor1;
    private Image imgSelecColor2;
    private Image imgSelecNombre1;
    private Image imgSelecNombre2;
    private Image imgBarraNombre;
    private Image imgBotonBack;
    private Image imgPantallaPausa;
    private Image imgGanaste;

    // Jugadores
    Jugador j1;
    Jugador j2;

    // Posiciones ayudailiares
    private int positionX;
    private int positionY;
    private int dX;
    private int dY;
    private int incX;
    private int incY;

    // Booleanos
    private boolean mouseDrag;

    //Objetos...
    private Mesa table;
    private Silla chair;
    private Personaje alesso;

    //Botones
    private Boton bPausa;
    private Boton bPlay;
    private Boton bCredits;
    private Boton bHighScore;

    //Objetos Imagen
    private Image fondo;
    private Image cerveza;

    //Objetos URL
    private URL fondoURL = this.getClass().getResource(iUrlFondo);
    private URL tableURL = this.getClass().getResource(iUrlMesa);
    private URL poolURL = this.getClass().getResource(iUrlMesaBillar1);
    private URL cervezaURL = this.getClass().getResource(iUrlCerveza);
    private URL imgLogoGrandeURL = this.getClass().getResource(iUrlLogoGrande);
    private URL imgPlayBotonURL = this.getClass().getResource(iUrlBotonPlay);
    private URL imgCreditsBotonURL = this.getClass().getResource(iUrlBotonCredits);
    private URL imgHighScoreBotonURL = this.getClass().getResource(iUrlBotonPuntajesAltos);
    private URL imgBotonAtrasURL = this.getClass().getResource(iUrlBotonAtras);
    private URL imgColorAzulURL = this.getClass().getResource(iUrlColorAzul);
    private URL imgColorGrisURL = this.getClass().getResource(iUrlColorGris);
    private URL imgColorRojoURL = this.getClass().getResource(iUrlColorRojo);
    private URL imgColorVerdeURL = this.getClass().getResource(iUrlColorVerde);
    private URL imgMenuInstruccionesURL = this.getClass().getResource(iUrlMenuInstrucciones);
    private URL imgLogoURL = this.getClass().getResource(iUrlLogo);
    private URL imgBotonNextURL = this.getClass().getResource(iUrlBotonNext);
    private URL imgSelecColor1URL = this.getClass().getResource(iUrlSelecColor1);
    private URL imgSelecColor2URL = this.getClass().getResource(iUrlSelecColor2);
    private URL imgSelecNombre1URL = this.getClass().getResource(iUrlSelecNombre1);
    private URL imgSelecNombre2URL = this.getClass().getResource(iUrlSelecNombre2);
    private URL imgBarraNombreURL = this.getClass().getResource(iUrlBarraNombre);
    private URL imgBotonBackURL = this.getClass().getResource(iUrlBotonBack);
    private URL imgPantallaPausaURL = this.getClass().getResource(iUrlPantallaPausa);
    private URL imgGanasteURL = this.getClass().getResource(iUrlGanaste);
    

    //Estados del juego (Para saber cuando estoy jugando on menus)
    private enum STATE {

        MENU,
        GAME,
        PAUSED
    };

    private STATE state = STATE.MENU;

    //Lista de booleanas
    //Mesas y Sillas
    private LinkedList<Mesa> listaTables;
    private LinkedList<Silla> listaChairs;

    //Personajes
    private Animacion travolta;
    Personaje pTravolta1;
    Personaje pTravolta2;

    //Variables de control de tiempo de la animación
    private long tiempoActual;
    private long tiempoInicial;

    /**
     * Metodo <I>PlayGround()</I> de la clase <code>PlayGround</code>. Es el
     * constructor de la clase donde se definen las variables
     */
    public Game() {

        setSize(GAME_WIDTH, GAME_HEIGHT);
        //Fondo
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL);
//        infoBar = Toolkit.getDefaultToolkit().getImage(barraInfoURL);
//        plateP = Toolkit.getDefaultToolkit().getImage(pausaURL);

        //Images 
        cerveza = Toolkit.getDefaultToolkit().getImage(cervezaURL);
//        imgCreditsBoton = Toolkit.getDefaultToolkit().getImage(creditsBotonURL);
        Image travolta1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/ilDivo/azul/divo_01.png"));
        Image travolta2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/ilDivo/azul/divo_02.png"));
        travolta = new Animacion();
        travolta.sumaCuadro(travolta1, 400);
        travolta.sumaCuadro(travolta2, 400);

        pTravolta1 = new Personaje(0, 50);
        pTravolta1.setAnim(travolta);
        pTravolta2 = new Personaje(10, 500);
        pTravolta2.setAnim(travolta);

//        bPausa = new Boton(850, 20, plateP);
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();

        //jugadores init
        j1 = new Jugador(Color.red, "Beto", 0);
        j2 = new Jugador(Color.blue, "Hugo", 1);
        
        //Images 
        imgCreditsBoton = Toolkit.getDefaultToolkit().getImage(imgCreditsBotonURL);
        imgPlayBoton = Toolkit.getDefaultToolkit().getImage(imgPlayBotonURL);
        imgLogoGrande = Toolkit.getDefaultToolkit().getImage(imgLogoGrandeURL);
        imgHighScoreBoton = Toolkit.getDefaultToolkit().getImage(imgHighScoreBotonURL);

        //Controladores 
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //Botones
        bPlay = new Boton(300, 400, imgPlayBoton);
        bCredits = new Boton(20, 400, imgCreditsBoton);
        bHighScore = new Boton(630, 400, imgHighScoreBoton);

        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();

    }

    /**
     * Metodo crearMesasYSillas que crea las mesas y sillas en base a un mapa
     * Para crear un nuevo mapa, solamente hay que crear una matriz con 3
     * columnas posX | posY | tipoDeMesa El tipo de mesa es un entero constante
     * que pusimos en nuestra clase Constantes.java También hay que modificar la
     * URL en el switch para cada una de estas constantes y listo. Creado por
     * beto y hugo.
     */
    public void crearMesasYSillas() {

        int mapa[][] = {
            {75, 160, BAR_ROUND},
            {75, 300, BAR_ROUND},
            {75, 440, BAR_ROUND},
            {370, 140, BAR_POOL},
            {270, 300, BAR_ROUND},
            {465, 300, BAR_ROUND},
            {370, 440, BAR_POOL},
            {660, 160, BAR_ROUND},
            {660, 300, BAR_ROUND},
            {660, 440, BAR_ROUND}
        };
        for (int r = 0; r < mapa.length; r++) {
            URL url;
            int tipo = 0;
            switch (mapa[r][2]) {
                case BAR_POOL:
                    url = poolURL;
                    tipo = 0;
                    break;
                case BAR_ROUND:
                    url = tableURL;
                    tipo = 1;
                    break;
                default:
                    url = tableURL;
                    tipo = 0;
                    break;
            }
            Animacion a = new Animacion();
            a.sumaCuadro(Toolkit.getDefaultToolkit().getImage(url), 10);
            table = new Mesa(mapa[r][0], mapa[r][1], Toolkit.getDefaultToolkit().getImage(url), tipo);
            table.setAnim(a);
            listaTables.add(table);
        }
        for (Mesa mesa : listaTables) {
            mesa.crearSillas();
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
            if (state == state.GAME) {
                Actualiza();
                ChecaColision();
            }
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

        listaTables.get(j1.getMesaSeleccionada()).setColor1(j1.getColor());
        listaTables.get(j2.getMesaSeleccionada()).setColor2(j2.getColor());

        //Determina el tiempo que ha transcurrido desde que el Applet 
        //inicio su ejecución
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        pTravolta1.anim.actualiza(tiempoTranscurrido);
        pTravolta2.anim.actualiza(tiempoTranscurrido);

        // Si hay drag, actualizar la posicion de pTravolta1
        if (mouseDrag) {
            pTravolta1.setPosX(positionX - dX);
            pTravolta1.setPosY(positionY - dY);
        }

        //Acutalizo la posicion del pTravolta2
        if (pTravolta2.isIntersecta()) {
            if (pTravolta2.getMoverX() > 0) {
                pTravolta2.setPosX(pTravolta2.getPosX() + 1);
                pTravolta2.setMoverX(pTravolta2.getMoverX() - 1);
            } else {
                pTravolta2.setPosX(pTravolta2.getPosX() - 1);
                pTravolta2.setMoverX(pTravolta2.getMoverX() + 1);
            }
            
            if (pTravolta2.getMoverX() == 0 && pTravolta2.getMoverY() == 0) {
                pTravolta2.setIntersecta(false);
            }

            if (pTravolta2.getMoverY() > 0) {
                pTravolta2.setPosY(pTravolta2.getPosY() + 1);
                pTravolta2.setMoverY(pTravolta2.getMoverY() - 1);
            } else {
                pTravolta2.setPosY(pTravolta2.getPosY() - 1);
                pTravolta2.setMoverY(pTravolta2.getMoverY() + 1);
            }

            if (pTravolta2.getMoverX() == 0 && pTravolta2.getMoverY() == 0) {
                pTravolta2.setIntersecta(false);
            }

        } else {
            if (pTravolta1.getPosX() > pTravolta2.getPosX()) {
                pTravolta2.setVelX(1);
            } else {
                pTravolta2.setVelX(-1);
            }

            if (pTravolta1.getPosY() > pTravolta2.getPosY()) {
                pTravolta2.setVelY(1);
            } else {
                pTravolta2.setVelY(-1);
            }
        }

        pTravolta2.setPosX(pTravolta2.getPosX() + pTravolta2.getVelX());
        pTravolta2.setPosY(pTravolta2.getPosY() + pTravolta2.getVelY());
    }

    /**
     * Checa colisiones dentro del <code>JFrame</code>.
     */
    public void ChecaColision() {
        //checa colision con el applet
        if (pTravolta2.getPosX() + pTravolta2.getAncho() > getWidth()) {
            pTravolta2.setPosX(pTravolta2.getPosX() - incX);
        }
        if (pTravolta2.getPosX() < 0) {
            pTravolta2.setPosX(pTravolta2.getPosX() - incX);
        }
        if (pTravolta2.getPosY() + pTravolta2.getAlto() > getHeight()) {
            pTravolta2.setPosY(pTravolta2.getPosY() - incY);
        }
        if (pTravolta2.getPosY() < 0) {
            pTravolta2.setPosY(pTravolta2.getPosY() - incY);
        }

        for (int x = 0; x < listaTables.size(); x++) {
            Mesa ayuda = (Mesa) listaTables.get(x);
            if (pTravolta2.intersecta(ayuda) && !pTravolta2.isIntersecta()) {
                pTravolta2.setIntersecta(true);
                if (pTravolta2.getVelX() > 0 && pTravolta2.getPosX() + pTravolta2.getAncho() >= ayuda.getPosX() ) {
                    //pTravolta2.setMoverX(ayuda.getAncho() + 2);
                    if (pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() < ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY()) {
                        pTravolta2.setMoverY(-(pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() + 3));
                    } else {
                        pTravolta2.setMoverY(ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY() + 3);
                    }
                } else if (pTravolta2.getVelX() < 0 && pTravolta2.getPosX() <= ayuda.getPosX() + ayuda.getAncho() ) {
                    //pTravolta2.setMoverX(-(ayuda.getAncho() + 2));
                    if (pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() < ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY()) {
                        pTravolta2.setMoverY(-(pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() + 3));
                    } else {
                        pTravolta2.setMoverY(ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY() + 3);
                    }
                } else if (pTravolta2.getVelY() > 0 && pTravolta2.getPosY() + pTravolta2.getAlto() > ayuda.getPosY() ) {
                    //pTravolta2.setMoverY(ayuda.getAlto() + 2);
                    if ((pTravolta2.getPosX() + pTravolta2.getAncho() / 2) - ayuda.getPosX() <= ayuda.getPosX() + ayuda.getAlto() - (pTravolta2.getPosX() + pTravolta2.getAncho() / 2)) {
                        pTravolta2.setMoverX(-(pTravolta2.getPosX() + pTravolta2.getAncho() - ayuda.getPosX() + 3));
                    } else {
                        pTravolta2.setMoverX(ayuda.getPosX() + ayuda.getAncho() - pTravolta2.getPosX() + 3);
                    }
                    pTravolta2.setPosY(ayuda.getPosY() - pTravolta2.getAlto() - 1);
                } else if (pTravolta2.getVelY() < 0 && pTravolta2.getPosY() <= ayuda.getPosY() + ayuda.getAlto() ) {
                    //pTravolta2.setMoverY(-(ayuda.getAlto() + 2));
                    if ((pTravolta2.getPosX() + pTravolta2.getAncho() / 2) - ayuda.getPosX() < ayuda.getPosX() + ayuda.getAlto() - (pTravolta2.getPosX() + pTravolta2.getAncho() / 2)) {
                        pTravolta2.setMoverX(-(pTravolta2.getPosX() + pTravolta2.getAncho() - ayuda.getPosX() + 3));
                    } else {
                        pTravolta2.setMoverX(ayuda.getPosX() + ayuda.getAncho() - pTravolta2.getPosX() + 3);
                    }
                    pTravolta2.setPosY(ayuda.getPosY() - pTravolta2.getAlto() - 1);
                }
            
            /*if (pTravolta2.intersecta(ayuda)) {
             if (pTravolta2.getVelX() > 0 && pTravolta2.getPosX() + pTravolta2.getAncho() >= ayuda.getPosX() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) >= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
             pTravolta2.setPosX(ayuda.getPosX() - pTravolta2.getAncho() - 1);
             } else if (pTravolta2.getVelX() < 0 && pTravolta2.getPosX() <= ayuda.getPosX() + ayuda.getAncho() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) >= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
             pTravolta2.setPosX(ayuda.getPosX() + ayuda.getAncho() + 1);
             } else if (pTravolta2.getVelY() > 0 && pTravolta2.getPosY() + pTravolta2.getAlto() >= ayuda.getPosY() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) <= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
             pTravolta2.setPosY(ayuda.getPosY() - pTravolta2.getAlto() - 1);
             } else if (pTravolta2.getVelY() < 0 && pTravolta2.getPosY() <= ayuda.getPosY() + ayuda.getAlto() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) <= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
             pTravolta2.setPosY(ayuda.getPosY() + ayuda.getAlto() + 1);
             }
             }*/

            /*if (pTravolta2.intersecta(ayuda) && !pTravolta2.isIntersecta()) {
                pTravolta2.setIntersecta(true);
                if (pTravolta2.getVelX() > 0 && pTravolta2.getPosX() + pTravolta2.getAncho() >= ayuda.getPosX() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) >= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
                    //pTravolta2.setMoverX(ayuda.getAncho() + 2);
                    if (pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() < ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY()) {
                        pTravolta2.setMoverY(-(pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() + 3));
                    } else {
                        pTravolta2.setMoverY(ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY() + 3);
                    }
                } else if (pTravolta2.getVelX() < 0 && pTravolta2.getPosX() <= ayuda.getPosX() + ayuda.getAncho() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) >= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
                    //pTravolta2.setMoverX(-(ayuda.getAncho() + 2));
                    if (pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() < ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY()) {
                        pTravolta2.setMoverY(-(pTravolta2.getPosY() + pTravolta2.getAlto() - ayuda.getPosY() + 3));
                    } else {
                        pTravolta2.setMoverY(ayuda.getPosY() + ayuda.getAlto() - pTravolta2.getPosY() + 3);
                    }
                } else if (pTravolta2.getVelY() > 0 && pTravolta2.getPosY() + pTravolta2.getAlto() > ayuda.getPosY() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) <= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
                    //pTravolta2.setMoverY(ayuda.getAlto() + 2);
                    if ((pTravolta2.getPosX() + pTravolta2.getAncho() / 2) - ayuda.getPosX() <= ayuda.getPosX() + ayuda.getAlto() - (pTravolta2.getPosX() + pTravolta2.getAncho() / 2)) {
                        pTravolta2.setMoverX(-(pTravolta2.getPosX() + pTravolta2.getAncho() - ayuda.getPosX() + 3));
                    } else {
                        pTravolta2.setMoverX(ayuda.getPosX() + ayuda.getAncho() - pTravolta2.getPosX() + 3);
                    }
                    pTravolta2.setPosY(ayuda.getPosY() - pTravolta2.getAlto() - 1);
                } else if (pTravolta2.getVelY() < 0 && pTravolta2.getPosY() <= ayuda.getPosY() + ayuda.getAlto() && Math.abs(pTravolta2.getPosX() + pTravolta2.getAncho() / 2 - (ayuda.getPosX() + ayuda.getAncho() / 2)) <= Math.abs(pTravolta2.getPosY() + pTravolta2.getAlto() / 2 - (ayuda.getPosY() + ayuda.getAlto() / 2))) {
                    //pTravolta2.setMoverY(-(ayuda.getAlto() + 2));
                    if ((pTravolta2.getPosX() + pTravolta2.getAncho() / 2) - ayuda.getPosX() < ayuda.getPosX() + ayuda.getAlto() - (pTravolta2.getPosX() + pTravolta2.getAncho() / 2)) {
                        pTravolta2.setMoverX(-(pTravolta2.getPosX() + pTravolta2.getAncho() - ayuda.getPosX() + 3));
                    } else {
                        pTravolta2.setMoverX(ayuda.getPosX() + ayuda.getAncho() - pTravolta2.getPosX() + 3);
                    }
                    pTravolta2.setPosY(ayuda.getPosY() - pTravolta2.getAlto() - 1);
                }*/
            }
        }
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
        if (state == STATE.GAME) {
            g.drawImage(fondo, 0, 0, this);
            for (Mesa mesa : listaTables) {
                mesa.paintSillasArriba(g);
                mesa.paint(g);
                mesa.paintSillasAbajo(g);
                if (mesa.getTipo() != 0) {
                    g.drawImage(cerveza, mesa.getPosX() + 27, mesa.getPosY() + 5, this);
                }
                mesa.paintSelectors(g);
                g.drawRect(mesa.getPosX() -25, mesa.getPosY() - 30, mesa.getAncho() + 50, mesa.getAlto() + 40);
            }
            
            g.drawImage(pTravolta1.getAnim().getImagen(), pTravolta1.getPosX(), pTravolta1.getPosY(), this);
            g.drawImage(pTravolta2.getAnim().getImagen(), pTravolta2.getPosX(), pTravolta2.getPosY(), this);
            g.drawRect(pTravolta2.getPosX(), pTravolta2.getPosY() +40, pTravolta2.getAncho(), pTravolta2.getAlto() - 40);
        }

        if (state == STATE.PAUSED) {
            //está pausado
        }
       
        if(state == STATE.MENU) {            
            g.drawImage(imgLogoGrande, 200, 20, this);
            g.drawImage(bPlay.getImageIcon().getImage(), bPlay.getPosX(), bPlay.getPosY(), this);
            g.drawImage(bCredits.getImageIcon().getImage(), bCredits.getPosX(), bCredits.getPosY(), this);
            g.drawImage(bHighScore.getImageIcon().getImage(), bHighScore.getPosX(), bHighScore.getPosY(), this);
        }
    }


    /*
     *Metodo keyPressed
     *Cuando una tecla esta apretada
     *recibe de param un evento, en este caso se busca que sea la p
     *para pausar el juego
     */
    public void keyPressed(KeyEvent e) {
        if (state == state.GAME) {
            int ant, sig;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    state = state.PAUSED;
                    break;

                //Controles para el jugador 1
                case KeyEvent.VK_RIGHT:
                    sig = (j1.getMesaSeleccionada() + 1) % listaTables.size();
                    listaTables.get(j1.getMesaSeleccionada()).setColor1(null);
                    j1.setMesaSeleccionada(sig);
                    break;
                case KeyEvent.VK_LEFT:
                    ant = (j1.getMesaSeleccionada() - 1);
                    ant = (ant < 0) ? listaTables.size() - 1 : ant;
                    listaTables.get(j1.getMesaSeleccionada()).setColor1(null);
                    j1.setMesaSeleccionada(ant);
                    break;

                //Controles para el jugador 2
                case KeyEvent.VK_D:
                    sig = (j2.getMesaSeleccionada() + 1) % listaTables.size();
                    listaTables.get(j2.getMesaSeleccionada()).setColor2(null);
                    j2.setMesaSeleccionada(sig);
                    break;

                case KeyEvent.VK_A:
                    ant = (j2.getMesaSeleccionada() - 1);
                    ant = (ant < 0) ? listaTables.size() - 1 : ant;
                    listaTables.get(j2.getMesaSeleccionada()).setColor2(null);
                    j2.setMesaSeleccionada(ant);
                    break;
            }
        }

        if (state == state.PAUSED) {
            System.out.println("I am paused...");
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    System.out.println("I dont wanna be paused anymo...");
                    state = state.GAME;
                    break;
            }
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
        // Si hubo click dentro del objeto pTravolta1 se guarda posicion y diferencia.
        if (pTravolta1.clicked(e)) {
            mouseDrag = true;
            positionX = e.getX();
            positionY = e.getY();
            dX = positionX - pTravolta1.getPosX();
            dY = positionY - pTravolta1.getPosY();
        }
    }

    public void mouseEntered(MouseEvent e) { //metodo cuando entra el mouse

    }

    public void mouseExited(MouseEvent e) { //metodo cuando sale el mouse

    }

    public void mousePressed(MouseEvent e) {    //metodo cuando el mouse es presionado

    }

    public void mouseReleased(MouseEvent e) {//metodo cuando el mouse es soltado
        // La bandera se apaga cuando se deja de picar en el mouse.
        mouseDrag = false;
    }

    public void mouseMoved(MouseEvent e) {  //metodos de MouseMotionListener

    }

    public void mouseDragged(MouseEvent e) {   //metodos de MouseMotionListener
        // Si la bandera esta prendida, salvar los valores del drag.
        if (mouseDrag) {
            positionX = e.getX();
            positionY = e.getY();
        }
    }

    public void restart() {

    }

}
