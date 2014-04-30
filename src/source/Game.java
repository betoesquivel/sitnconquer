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
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextField;
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

    //Cajas de Texto para introducir el nombre
    private TextField jugador1;
    private TextField jugador2;

    //Objetos Imagen
    private Image imgCreditsBoton;
    private Image imgHighScoreBoton;
    private Image imgPlayBoton;
    private Image imgLogoGrande;
    private Image imgLogo;
    private Image imgSelecColor1;
    private Image imgSelecColor2;
    private Image imgSelecNombre1;
    private Image imgSelecNombre2;
    private Image imgBarraNombre;
    private Image imgBotonBack;
    private Image imgPantallaPausa;
    private Image imgGanaste;
    private Image imgColorAzul;
    private Image imgColorVerde;
    private Image imgColorRojo;
    private Image imgColorGris;
    private Image imgBackBoton;
    private Image imgNextBoton;
    private Image imgInstruccionesMenu;
    private Image imgInstruccionesMenuColor;
    private Image imgInstruccionesMenuNombre;
    private Image imgSelectColor1;
    private Image imgSelectColor2;
    private Image imgSelectName1;
    private Image imgSelectName2;
    private Image imgInstruccionesMovimiento;
    private Image imgLetreroPausado;

    //rectangle
    private Rectangle rec;
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

    // Cheats
    private Cheat BQT;
    
    // Booleanos
    private boolean mouseDrag;
    private boolean movHorizontal;
    private boolean movVertical;
    private boolean azul, rojo, verde, gris; // booleanas para indicar que boton de color de jugador se debe prender
    private int Ganaste = -1; //Numero entero que actua como booleana para indicar si ya gano alguien -1=no ha ganado nadie, id de jugador para indicar que ya gano
    private int colorJ1 = -1; //booleanas para indicar color seleccionado por jugadores
    private int colorJ2 = -1; // 1 = rojo, 2 = gris, 3 = azul, 4 = verde
    //Objetos...
    private Mesa table;
    private Silla chair;
    private Personaje alesso;

    //Botones
    private Boton bPausa;
    private Boton bPlay;
    private Boton bCredits;
    private Boton bHighScore;
    private Boton bNext;
    private Boton bBack;
    private Boton bColorAzul;
    private Boton bColorVerde;
    private Boton bColorRojo;
    private Boton bColorGris;
    private Boton bContinue;

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
    private URL imgSelectColor1URL = this.getClass().getResource(iUrlSelectColor1);
    private URL imgSelectColor2URL = this.getClass().getResource(iUrlSelectColor2);
    private URL imgSelectName1URL = this.getClass().getResource(iUrlSelectName1);
    private URL imgSelectName2URL = this.getClass().getResource(iUrlSelectName2);
    private URL imgInstruccionesMenuColorURL = this.getClass().getResource(iUrlInstruccionesMenuColor);
    private URL imgInstruccionesMenuNombreURL = this.getClass().getResource(iUrlInstruccionesMenuNombre);
    private URL imgInstruccionesMovimientoURL = this.getClass().getResource(iUrlInstruccionesMovimiento);
    private URL imgLetreroPausadoURL = this.getClass().getResource(iUrlLetreroPausado);

    //Estados del juego (Para saber cuando estoy jugando on menus)
    private enum STATE {

        MENU_MAIN,
        SELECT_COLOR_1,
        SELECT_COLOR_2,
        PLAYER_NAME_1,
        PLAYER_NAME_2,
        GAME,
        PAUSED,
        INSTRUCCIONES,
        GANADOR,
        MENU_POSTJUEGO,
        CREDITS,
        HIGHSCORE
    };

    private STATE state = STATE.MENU_MAIN;

    //Lista de booleanas
    //Mesas y Sillas
    private LinkedList<Mesa> listaTables;
    private LinkedList<Silla> listaChairs;

    //Personajes
    private Animacion travolta;
    Personaje pTravolta1;
    Personaje pTravolta2;
    Personaje pTravolta3;
    Personaje pTravolta4;
    boolean lockX;
    boolean lockY;

    //Variables para habilitar cheats
    

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
        BQT = new Cheat();
        BQT.betoQuiereNovia();
//        infoBar = Toolkit.getDefaultToolkit().getImage(barraInfoURL);
//        plateP = Toolkit.getDefaultToolkit().getImage(pausaURL);
        lockX = lockY = false;
        //Images 
        cerveza = Toolkit.getDefaultToolkit().getImage(cervezaURL);
        Image travolta1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/ilDivo/azul/divo_01.png"));
        Image travolta2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/ilDivo/azul/divo_02.png"));
        travolta = new Animacion();
        travolta.sumaCuadro(travolta1, 400);
        travolta.sumaCuadro(travolta2, 400);

        pTravolta1 = new Personaje(0, 50, 1, 1);
        pTravolta1.setAnim(travolta);
        pTravolta2 = new Personaje(10, 500, 2, 2);
        pTravolta2.setAnim(travolta);

        pTravolta3 = new Personaje(0, 0, 3, 3);
        pTravolta4 = new Personaje(0, 0, 4, 4);

//        bPausa = new Boton(850, 20, plateP);
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();

        //jugadores init
        j1 = new Jugador(1, Color.red, "Beto", 0);
        j2 = new Jugador(2, Color.blue, "Hugo", 1);

        //Cajas de texto para nombres de jugadores
        jugador1 = new TextField();
        jugador2 = new TextField();
        jugador1.setBounds(new Rectangle(25, 250, 300, 50));
        jugador2.setBounds(new Rectangle(25, 250, 300, 50));

        //Images 
        imgCreditsBoton = Toolkit.getDefaultToolkit().getImage(imgCreditsBotonURL);
        imgPlayBoton = Toolkit.getDefaultToolkit().getImage(imgPlayBotonURL);
        imgLogoGrande = Toolkit.getDefaultToolkit().getImage(imgLogoGrandeURL);
        imgHighScoreBoton = Toolkit.getDefaultToolkit().getImage(imgHighScoreBotonURL);
        imgInstruccionesMenu = Toolkit.getDefaultToolkit().getImage(imgMenuInstruccionesURL);
        imgColorAzul = Toolkit.getDefaultToolkit().getImage(imgColorAzulURL);
        imgColorRojo = Toolkit.getDefaultToolkit().getImage(imgColorRojoURL);
        imgColorVerde = Toolkit.getDefaultToolkit().getImage(imgColorVerdeURL);
        imgColorGris = Toolkit.getDefaultToolkit().getImage(imgColorGrisURL);
        imgNextBoton = Toolkit.getDefaultToolkit().getImage(imgBotonNextURL);
        imgBackBoton = Toolkit.getDefaultToolkit().getImage(imgBotonBackURL);
        imgSelectColor1 = Toolkit.getDefaultToolkit().getImage(imgSelectColor1URL);
        imgSelectColor2 = Toolkit.getDefaultToolkit().getImage(imgSelectColor2URL);
        imgSelectName1 = Toolkit.getDefaultToolkit().getImage(imgSelectName1URL);
        imgSelectName2 = Toolkit.getDefaultToolkit().getImage(imgSelectName2URL);
        imgInstruccionesMenuColor = Toolkit.getDefaultToolkit().getImage(imgInstruccionesMenuColorURL);
        imgInstruccionesMenuNombre = Toolkit.getDefaultToolkit().getImage(imgInstruccionesMenuNombreURL);
        imgInstruccionesMovimiento = Toolkit.getDefaultToolkit().getImage(imgInstruccionesMovimientoURL);
        imgLetreroPausado = Toolkit.getDefaultToolkit().getImage(imgLetreroPausadoURL);

        //Booleans
        movHorizontal = false;
        movVertical = false;

        //Controladores 
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        //listaTables.get(0).sentar(pTravolta1);
        //listaTables.get(0).sentar(pTravolta2);
        //listaTables.get(0).sentar(pTravolta3);
        //listaTables.get(0).sentar(pTravolta4);
        //Botones
        bPlay = new Boton(300, 400, imgPlayBoton);
        bCredits = new Boton(20, 400, imgCreditsBoton);
        bHighScore = new Boton(630, 400, imgHighScoreBoton);
        bColorAzul = new Boton(15, 335, imgColorAzul);
        bColorVerde = new Boton(180, 335, imgColorVerde);
        bColorGris = new Boton(180, 225, imgColorGris);
        bColorRojo = new Boton(15, 225, imgColorRojo);
        //bContinue = new Boton(20, 20);
        //bPausa = new Boton(850, 20, plateP);
        bNext = new Boton(750, 490, imgNextBoton);
        bBack = new Boton(20, 490, imgBackBoton);

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
                    tipo = 2;
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

        if (BQT.isEncendido()) {
            if (BQT.getContador() % 2 == 1) {
            j1.cambiaTipo(4);
            j2.cambiaTipo(4);
            } else {
                j1.cambiaTipoRand();
                j2.cambiaTipoRand();
            }
            BQT.setEncendido(false);
        }
        listaTables.get(j1.getMesaSeleccionada()).setColor1(j1.getColor());
        listaTables.get(j2.getMesaSeleccionada()).setColor2(j2.getColor());

        //Determina el tiempo que ha transcurrido desde que el Applet 
        //inicio su ejecución
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        pTravolta1.actualizaAnimaciones(tiempoTranscurrido);
        pTravolta2.actualizaAnimaciones(tiempoTranscurrido);
        pTravolta3.actualizaAnimaciones(tiempoTranscurrido);
        pTravolta4.actualizaAnimaciones(tiempoTranscurrido);

        // Actualizar el tiempo para la generacion de monitos y las animaciones de los monitos que tienen
        j1.actualiza(tiempoTranscurrido);
        j2.actualiza(tiempoTranscurrido);
        j1.checaFactorDeCreacion(listaTables);
        j2.checaFactorDeCreacion(listaTables);

        //Acutalizo la posicion del pTravolta2
//        for (Personaje p : j1.listaPersonajes) {
        pTravolta2.actualizaPosicion();
//        }

        if (Ganaste != -1) {
            state = state.MENU_POSTJUEGO;
        }
    }

    /**
     * Checa colisiones dentro del <code>JFrame</code>.
     */
    public void ChecaColision() {
        //checa colision con el applet
        /*
         Falta un for general para cada personaje
         */
        //////
        //Lateral Izquiero
        if (pTravolta2.getPosX() < 0) {
            pTravolta2.setPosX(0);
        }
        //Arriba       
        if (pTravolta2.getPosY() < pTravolta2.getAlto()) {
            pTravolta2.setPosY(pTravolta2.getAlto());
        }
        //Lateral Derecho
        if (pTravolta2.getPosX() > getWidth() - pTravolta2.getAncho()) {
            pTravolta2.setPosX(getWidth() - pTravolta2.getAncho());
        }
        //Abajo
        if (pTravolta2.getPosY() > getHeight() - pTravolta2.getAlto()) {
            pTravolta2.setPosY(getHeight() - pTravolta2.getAlto());
        }

        if (pTravolta2.getEstado() == ENMOVIMIENTO && pTravolta2.isInMesaDestino()) {
            pTravolta2.getMesaDestino().sentar(pTravolta2);
            pTravolta2.setEstado(SENTADO);
        } else {
            //checo otras colisiones...
        }
// POR AHORA NO LO VAMOS A HACER...
//        int temp = 0;
//        for (int i = 0; i < listaTables.size(); i++) {
//
//            Mesa ayuda = (Mesa) listaTables.get(i);
//            // g.drawRect(mesa.getPosX() -25, mesa.getPosY() - 30, mesa.getAncho() + 50, mesa.getAlto() + 40);
//            rec = new Rectangle(ayuda.getPosX() - 25, ayuda.getPosY() - 30, ayuda.getAncho() + 50, ayuda.getAlto() + 40);
//
//            //Lado Izq
//            if (pTravolta2.intersecta2(rec)) {
//                //lado Izquierdo
//                if (ayuda.getPosX() - 50 == pTravolta2.getPosX()) {
//                    pTravolta2.setPosX(pTravolta2.getPosX() - 1);
//
//                    if (pTravolta2.getPosY() > ayuda.getPosY() - (ayuda.getAlto() / 2)) {
//                        pTravolta2.setPosY(pTravolta2.getPosY() + pTravolta2.getVelY());
//                    }
//
//                    if (pTravolta2.getPosY() < ayuda.getPosY() - (ayuda.getAlto() / 2)) {
//                        pTravolta2.setPosY(pTravolta2.getPosY() - pTravolta2.getVelY());
//                    }
//
//                    movVertical = true;
//                    movHorizontal = false;
//
//                }
//                //Lado derecho
//                if (ayuda.getPosX() + 100 == pTravolta2.getPosX()) {
//                    pTravolta2.setPosX(pTravolta2.getPosX() + 1);
//                    movVertical = true;
//                    movHorizontal = false;
//
//                    if (pTravolta2.getPosY() > (ayuda.getPosY() - (ayuda.getAlto() / 2))) {
//                        pTravolta2.setPosY(pTravolta2.getPosY() + pTravolta2.getVelY());
//                    }
//
//                    if (pTravolta2.getPosY() < (ayuda.getPosY() - (ayuda.getAlto() / 2))) {
//                        pTravolta2.setPosY(pTravolta2.getPosY() - pTravolta2.getVelY());
//                    }
//                }
//                //Arriba
//                if (ayuda.getPosY() - 95 == pTravolta2.getPosY()) {
//
//                    pTravolta2.setPosY(pTravolta2.getPosY() - 1);
//                    movHorizontal = true;
//                    movVertical = false;
//                }
//                //Abajo
//                if (ayuda.getPosY() + 30 == pTravolta2.getPosY()) {
//
//                    pTravolta2.setPosY(pTravolta2.getPosY() + 1);
//                    movHorizontal = true;
//                    movVertical = false;
//                }
//                //Como un jugador solamente puede chocar con una mesa a la vez (entonces solamente tengo que encontrar esa mesa)
//                break;
//            } else {
//                movHorizontal = true;
//                movVertical = true;
//            }
//
//        }
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
                    g.drawImage(cerveza, mesa.getPosX() + 20, mesa.getPosY() + 5, this);
                }
                mesa.paintSelectors(g);
                Color colorPisoMesa = mesa.getColorPrincipal();
                if (colorPisoMesa != null) {
                    g.setColor(colorPisoMesa);
                    g.drawRect(mesa.getPosX() - 25, mesa.getPosY() - 30, mesa.getAncho() + 50, mesa.getAlto() + 40);
                    g.setColor(Color.BLACK);
                }
            }

//            g.drawImage(pTravolta1.getAnim().getImagen(), pTravolta1.getPosX(), pTravolta1.getPosY(), this);
//            g.drawImage(pTravolta2.getAnim().getImagen(), pTravolta2.getPosX(), pTravolta2.getPosY(), this);
//            g.drawRect(pTravolta2.getPosX(), pTravolta2.getPosY() + 40, pTravolta2.getAncho(), pTravolta2.getAlto() - 40);
            j1.paint(g);
            j2.paint(g);
            Font helvetica = new Font("Helvetica", Font.BOLD, 18);
            g.setFont(helvetica);
            g.setColor(Color.WHITE);
            g.drawString(j1.getNombre(), 15, 45);
            g.drawString(j2.getNombre(), getWidth() - 90, 45);
            g.setColor(Color.BLACK);
        }

        if (state == STATE.PAUSED) {
            //está pausado
            g.drawImage(imgLogoGrande, 200, 20, this);
            g.drawImage(imgLetreroPausado, 300, 420, this);
            g.drawString("Para quitar la pausa volver a presionar la letra 'p' ", 200, 520);
        }

        if (state == STATE.INSTRUCCIONES) {
            //está pausado
            g.drawImage(imgInstruccionesMovimiento, 100, 100, this);
            g.drawString("Para quitar las instrucciones volver a presionar la letra 'i' ", 200, 520);
        }

        if (state == STATE.MENU_MAIN) {
            g.drawImage(imgLogoGrande, 200, 20, this);
            g.drawImage(bPlay.getImageIcon().getImage(), bPlay.getPosX(), bPlay.getPosY(), this);
            g.drawImage(bCredits.getImageIcon().getImage(), bCredits.getPosX(), bCredits.getPosY(), this);
            g.drawImage(bHighScore.getImageIcon().getImage(), bHighScore.getPosX(), bHighScore.getPosY(), this);
        }

        if (state == state.SELECT_COLOR_1) {
            Color c = new Color(255, 255, 0, 100);
            g.setColor(c);
            if (azul) {
                g.fillOval(bColorAzul.getPosX() - 10, bColorAzul.getPosY() - 10, bColorAzul.getAncho() + 20, bColorAzul.getAlto() + 20);
            } else if (gris) {
                g.fillOval(bColorRojo.getPosX() - 10, bColorRojo.getPosY() - 10, bColorRojo.getAncho() + 20, bColorRojo.getAlto() + 20);
            } else if (verde) {
                g.fillOval(bColorVerde.getPosX() - 10, bColorVerde.getPosY() - 10, bColorVerde.getAncho() + 20, bColorVerde.getAlto() + 20);
            } else {
                g.fillOval(bColorGris.getPosX() - 10, bColorGris.getPosY() - 10, bColorGris.getAncho() + 20, bColorGris.getAlto() + 20);
            }

            g.drawImage(imgSelectColor1, 20, 20, this);
            g.drawImage(bColorRojo.getImageIcon().getImage(), bColorRojo.getPosX(), bColorRojo.getPosY(), this);
            g.drawImage(bColorAzul.getImageIcon().getImage(), bColorAzul.getPosX(), bColorAzul.getPosY(), this);
            g.drawImage(bColorGris.getImageIcon().getImage(), bColorGris.getPosX(), bColorGris.getPosY(), this);
            g.drawImage(bColorVerde.getImageIcon().getImage(), bColorVerde.getPosX(), bColorVerde.getPosY(), this);
            g.drawImage(imgInstruccionesMenuColor, 350, 50, this);
            g.drawImage(bBack.getImageIcon().getImage(), bBack.getPosX(), bBack.getPosY(), this);
            g.drawImage(bNext.getImageIcon().getImage(), bNext.getPosX(), bNext.getPosY(), this);

        }

        if (state == state.PLAYER_NAME_1) {
            g.drawImage(imgSelectName1, 20, 20, this);
            g.drawImage(imgInstruccionesMenuNombre, 350, 50, this);
            g.drawImage(bBack.getImageIcon().getImage(), bBack.getPosX(), bBack.getPosY(), this);
            g.drawImage(bNext.getImageIcon().getImage(), bNext.getPosX(), bNext.getPosY(), this);
        }

        if (state == state.SELECT_COLOR_2) {
            Color c = new Color(255, 255, 0, 100);
            g.setColor(c);
            if (azul) {
                g.fillOval(bColorAzul.getPosX() - 10, bColorAzul.getPosY() - 10, bColorAzul.getAncho() + 20, bColorAzul.getAlto() + 20);
            } else if (rojo) {
                g.fillOval(bColorRojo.getPosX() - 10, bColorRojo.getPosY() - 10, bColorRojo.getAncho() + 20, bColorRojo.getAlto() + 20);
            } else if (verde) {
                g.fillOval(bColorVerde.getPosX() - 10, bColorVerde.getPosY() - 10, bColorVerde.getAncho() + 20, bColorVerde.getAlto() + 20);
            } else {
                g.fillOval(bColorGris.getPosX() - 10, bColorGris.getPosY() - 10, bColorGris.getAncho() + 20, bColorGris.getAlto() + 20);
            }

            g.drawImage(imgSelectColor2, 20, 20, this);
            g.drawImage(bColorRojo.getImageIcon().getImage(), bColorRojo.getPosX(), bColorRojo.getPosY(), this);
            g.drawImage(bColorAzul.getImageIcon().getImage(), bColorAzul.getPosX(), bColorAzul.getPosY(), this);
            g.drawImage(bColorGris.getImageIcon().getImage(), bColorGris.getPosX(), bColorGris.getPosY(), this);
            g.drawImage(bColorVerde.getImageIcon().getImage(), bColorVerde.getPosX(), bColorVerde.getPosY(), this);
            g.drawImage(imgInstruccionesMenuColor, 350, 50, this);
//            g.drawImage(bBack.getImageIcon().getImage(), bBack.getPosX(), bBack.getPosY(), this);
            g.drawImage(bNext.getImageIcon().getImage(), bNext.getPosX(), bNext.getPosY(), this);
        }

        if (state == state.PLAYER_NAME_2) {
            g.drawImage(imgSelectName2, 20, 20, this);
            g.drawImage(imgInstruccionesMenuNombre, 350, 50, this);
            g.drawImage(bBack.getImageIcon().getImage(), bBack.getPosX(), bBack.getPosY(), this);
            g.drawImage(bNext.getImageIcon().getImage(), bNext.getPosX(), bNext.getPosY(), this);
        }

        if (state == state.CREDITS) {
            g.drawImage(imgLogoGrande, 200, 20, this);
            g.drawString("DESARROLLADO POR RUM", 350, 420);
            g.drawString("Hugo León ", 300, 440);
            g.drawString("Bernardo Treviño", 300, 460);
            g.drawString("José Alberto Esquivel", 300, 480);
            g.drawString("Gustavo Ferrufino", 300, 500);
            g.drawImage(bBack.getImageIcon().getImage(), bBack.getPosX(), bBack.getPosY(), this);
        }

        if (state == state.HIGHSCORE) {
            g.drawImage(imgLogoGrande, 200, 20, this);
            g.drawString("HIGH SCORE: ", 350, 420);
            g.drawString("1.- ", 300, 435);
            g.drawString("2.- ", 300, 450);
            g.drawString("3.- ", 300, 465);
            g.drawImage(bBack.getImageIcon().getImage(), bBack.getPosX(), bBack.getPosY(), this);

        }

    }

    /**
     * Metodo que actualiza el destino de todos los personajes que estan parados
     */
    public void actualizaDestinoParados(Jugador j) {
        if (pTravolta2.getEstado() == PARADO) {
            pTravolta2.setMesaDestino(listaTables.get(j.getMesaSeleccionada()));
            pTravolta2.setEstado(ENMOVIMIENTO);
        }
    }

    /**
     * Metodo que actualiza el destino de todos los personajes que estan en
     * movimiento
     */
    public void actualizaDestinoEnMovimiento(Jugador j) {
        if (pTravolta2.getEstado() == ENMOVIMIENTO) {
            pTravolta2.setMesaDestino(listaTables.get(j.getMesaSeleccionada()));
            pTravolta2.setEstado(ENMOVIMIENTO);
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
            int ant, sig, posX, posY; //calculates the position in x and y of the players selector
            BQT.check(e);
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    state = state.PAUSED;
                    break;

                //Controles para el jugador 1
                case KeyEvent.VK_RIGHT:
                    sig = (j1.getMesaSeleccionada() + 1) % listaTables.size();
                    listaTables.get(j1.getMesaSeleccionada()).setColor1(null);
                    j1.setMesaSeleccionada(sig);
                    posX = listaTables.get(j1.getMesaSeleccionada()).getPosX();
                    posY = listaTables.get(j1.getMesaSeleccionada()).getPosY();
                    j1.setCordX(posX);
                    j1.setCordY(posY);
                    break;
                case KeyEvent.VK_LEFT:
                    ant = (j1.getMesaSeleccionada() - 1);
                    ant = (ant < 0) ? listaTables.size() - 1 : ant;
                    listaTables.get(j1.getMesaSeleccionada()).setColor1(null);
                    j1.setMesaSeleccionada(ant);
                    posX = listaTables.get(j1.getMesaSeleccionada()).getPosX();
                    posY = listaTables.get(j1.getMesaSeleccionada()).getPosY();
                    j1.setCordX(posX);
                    j1.setCordY(posY);
                    break;
                case KeyEvent.VK_DOWN:
                    j1.sentarAMesa(listaTables.get(j1.getMesaSeleccionada()));
                    break;
                case KeyEvent.VK_UP:
                    listaTables.get(j1.getMesaSeleccionada()).parar(j1);
                    break;

                //Controles para el jugador 2
                case KeyEvent.VK_D:
                    sig = (j2.getMesaSeleccionada() + 1) % listaTables.size();
                    listaTables.get(j2.getMesaSeleccionada()).setColor2(null);
                    j2.setMesaSeleccionada(sig);
                    posX = listaTables.get(j2.getMesaSeleccionada()).getPosX();
                    posY = listaTables.get(j2.getMesaSeleccionada()).getPosY();
                    j2.setCordX(posX);
                    j2.setCordY(posY);
                    break;

                case KeyEvent.VK_A:
                    ant = (j2.getMesaSeleccionada() - 1);
                    ant = (ant < 0) ? listaTables.size() - 1 : ant;
                    listaTables.get(j2.getMesaSeleccionada()).setColor2(null);
                    j2.setMesaSeleccionada(ant);
                    posX = listaTables.get(j2.getMesaSeleccionada()).getPosX();
                    posY = listaTables.get(j2.getMesaSeleccionada()).getPosY();
                    j2.setCordX(posX);
                    j2.setCordY(posY);
                    break;
                case KeyEvent.VK_S:
                    j2.sentarAMesa(listaTables.get(j2.getMesaSeleccionada()));
                    break;
                case KeyEvent.VK_W:
                    listaTables.get(j2.getMesaSeleccionada()).parar(j2);
                    break;
                case KeyEvent.VK_0:
                    state = state.INSTRUCCIONES;
                    break;

                /*case KeyEvent.VK_DOWN: 
                 actualizaDestinoParados(j1);
                 break;
                    
                 case KeyEvent.VK_S: 
                 actualizaDestinoParados(j2);
                 break;*/
            }
        } else if (state == state.PAUSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    System.out.println("I dont wanna be paused anymo...");
                    state = state.GAME;
                    break;
            }
        } else if (state == state.INSTRUCCIONES) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_0:
                    System.out.println("No more instructions... I want to play!");
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
        boolean next = bNext.clicked(e);
        boolean back = bBack.clicked(e);

        if (pTravolta1.clicked(e)) {
            mouseDrag = true;
            positionX = e.getX();
            positionY = e.getY();
            dX = positionX - pTravolta1.getPosX();
            dY = positionY - pTravolta1.getPosY();
        }

        if (state == state.MENU_MAIN) {

            if (bPlay.clicked(e)) {
                state = state.SELECT_COLOR_1;
            }

            if (bCredits.clicked(e)) {
                state = state.CREDITS;
            }

            if (bHighScore.clicked(e)) {
                state = state.HIGHSCORE;
            }

        }

        if (state == state.CREDITS) {
            if (back) {
                state = state.MENU_MAIN;
                back = false;
            }
        }

        if (state == state.HIGHSCORE) {
            if (back) {
                state = state.MENU_MAIN;
                back = false;
            }
        }

        if (state == state.SELECT_COLOR_1) {
            rojo = true;
            azul = verde = gris = false;
            if (next) {
                state = state.PLAYER_NAME_1;
                next = false;
                if (rojo) {
                    colorJ1 = 1;
                } else if (gris) {
                    colorJ1 = 2;
                } else if (azul) {
                    colorJ1 = 3;
                } else if (verde) {
                    colorJ1 = 4;
                }
                this.add(jugador1, null);
            }
            if (bColorAzul.clicked(e)) {
                azul = true;
            } else if (bColorGris.clicked(e)) {
                azul = false;
                rojo = true;
            } else if (bColorVerde.clicked(e)) {
                verde = true;
                azul = rojo = false;
            } else {
                gris = true;
                azul = rojo = verde = false;
            }

            if (back) {
                state = state.MENU_MAIN;
                back = false;
            }
        }

        if (state == state.PLAYER_NAME_1) {
            if (next) {
                state = state.SELECT_COLOR_2;
                next = false;
                String nameJ1 = jugador1.getText();
                /*
                 switch(colorJ1) {
                 case 1:
                 j1 = new Jugador(Color.red, nameJ1, 1);
                 break;
                 case 2:
                 j1 = new Jugador(Color.gray, nameJ1, 1);
                 break;
                 case 3:
                 j1 = new Jugador(Color.blue, nameJ1, 1);
                 break;
                 case 4:
                 j1 = new Jugador(Color.green, nameJ1, 1);
                 break;
                 }
                 */
                this.remove(jugador1);
            }

            if (back) {
                state = state.SELECT_COLOR_1;
                back = false;
                this.remove(jugador1);
            }
        }

        if (state == state.SELECT_COLOR_2) {
            gris = true;
            azul = verde = rojo = false;

            if (bColorAzul.clicked(e)) {

                azul = true;
            } else if (bColorRojo.clicked(e)) {
                azul = false;
                rojo = true;
            } else if (bColorVerde.clicked(e)) {
                verde = true;
                azul = rojo = false;
            } else {
                gris = true;
                azul = rojo = verde = false;
            }

            if (rojo) {
                colorJ2 = 1;
            } else if (gris) {
                colorJ2 = 2;
            } else if (azul) {
                colorJ2 = 3;
            } else if (verde) {
                colorJ2 = 4;
            }

            if (next && colorJ1 != colorJ2) {
                System.out.println("colorj1= " + colorJ1 + "colorj2= " + colorJ2);
                state = state.PLAYER_NAME_2;
                next = false;

                this.add(jugador2, null);
            }

            if (back) {
//                state = state.PLAYER_NAME_1;
                back = false;
            }
        }

        if (state == state.PLAYER_NAME_2) {
            if (next) {
                state = state.GAME;
                next = false;
                String nameJ2 = jugador2.getText();
                /*
                 switch(colorJ2) {
                 case 1:
                 j2 = new Jugador(Color.red, nameJ2, 1);
                 break;
                 case 2:
                 j2 = new Jugador(Color.gray, nameJ2, 1);
                 break;
                 case 3:
                 j2 = new Jugador(Color.blue, nameJ2, 1);
                 break;
                 case 4:
                 j2 = new Jugador(Color.green, nameJ2, 1);
                 break;
                 }
                 */
                this.remove(jugador2);
            }

            if (back) {
                state = state.SELECT_COLOR_2;
                back = false;
                this.remove(jugador2);
            }
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
