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
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;// 

/**
 * La clase <I>Game</I> contiene el thread principal del juego. Este es el
 * <code>JFrame</code> donde está el juego.
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
public class Game extends JFrame implements Constantes, Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;

    // Se declaran las variables para pintar 
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico

    //Cajas de Texto para introducir el nombre
    //private TextField jugador1;
    //private TextField jugador2;
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
    private Image imgBarra;
    private Image imgGanasteVerde;
    private Image imgGanasteAzul;
    private Image imgGanasteRojo;
    private Image imgGanasteGris;
    private Image imgMBar;
    private Image imgMCentrales;
    private Image imgMBahia;
    private Image imgMPlaya;
    private Image imgMCasino;
    private Image imgMapaT;
    private Image imgCreditos;

    //rectangle
    private Rectangle rec;
    // Jugadores
    Jugador j1;
    Jugador j2;

    // Posiciones auxiliares
    private int positionX;
    private int positionY;
    private int dX;
    private int dY;
    private int incX;
    private int incY;
    private int moverOla1;// Importante para el escenario 3
    private int moverOla2; // Importante para el escenario 3

    // Saber que escenario es
    private int escenario;

    // Music
    private String[] trackList = {songOne, songTwo, songThree, songFour, songFive, songIntro};
    private AudioInputStream audio;
    private Clip clip;

    // Cheats
    private Cheat BQT;

    //Contadores
    private int detenerGanaste;

    // Animacion Rum
    private Animacion RumIntro;
    private int RumIntroCont;
    private Image RumIntroGif;

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

    //Strings
    private String nameJ1;
    private String nameJ2;

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
    private Boton bBar;
    private Boton bCentrales;
    private Boton bBahia;
    private Boton bPlaya;
    private Boton bCasino;

    //Objetos Imagen
    private Image fondo;
    private Image ola1;
    private Image ola2;
    //private Image cerveza;

    //Objetos URL
    private URL fondoURL = this.getClass().getResource(iUrlFondo);
    private URL fondoURL2 = this.getClass().getResource(iUrlFondo2);
    private URL fondoURL3 = this.getClass().getResource(iUrlFondo3);
    private URL fondoURL4 = this.getClass().getResource(iUrlFondo4);
    private URL fondoURL5 = this.getClass().getResource(iUrlFondo5);
    private URL olaURL1 = this.getClass().getResource(iUrlola);
    private URL olaURL2 = this.getClass().getResource(iUrlola2);
    private URL tableURL = this.getClass().getResource(iUrlMesa);
    private URL poolURL = this.getClass().getResource(iUrlMesaBillar1);
    private URL mesaCentralesURL = this.getClass().getResource(iUrlMesaCentrales);
    private URL mesaBahiaURL = this.getClass().getResource(iUrlMesaBahia);
    private URL mesaPlaya1URL = this.getClass().getResource(iUrlMesaPlaya1);
    private URL mesaPlaya2URL = this.getClass().getResource(iUrlMesaPlaya2);
    private URL mesaCasinoURL = this.getClass().getResource(iUrlMesaCasino);
    //private URL cervezaURL = this.getClass().getResource(iUrlCerveza);
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
    private URL imgGanasteVerdeURL = this.getClass().getResource(iUrlGanasteVerde);
    private URL imgGanasteAzulURL = this.getClass().getResource(iUrlGanasteAzul);
    private URL imgGanasteRojoURL = this.getClass().getResource(iUrlGanasteRojo);
    private URL imgGanasteGrisURL = this.getClass().getResource(iUrlGanasteGris);
    private URL imgSelectColor1URL = this.getClass().getResource(iUrlSelectColor1);
    private URL imgSelectColor2URL = this.getClass().getResource(iUrlSelectColor2);
    private URL imgSelectName1URL = this.getClass().getResource(iUrlSelectName1);
    private URL imgSelectName2URL = this.getClass().getResource(iUrlSelectName2);
    private URL imgInstruccionesMenuColorURL = this.getClass().getResource(iUrlInstruccionesMenuColor);
    private URL imgInstruccionesMenuNombreURL = this.getClass().getResource(iUrlInstruccionesMenuNombre);
    private URL imgInstruccionesMovimientoURL = this.getClass().getResource(iUrlInstruccionesMovimiento);
    private URL imgLetreroPausadoURL = this.getClass().getResource(iUrlLetreroPausado);
    private URL imgBarraURL = this.getClass().getResource(iUrlBarra);
    private URL imgRI1URL = this.getClass().getResource(iUrlRI1);
    private URL imgRI2URL = this.getClass().getResource(iUrlRI2);
    private URL imgRI3URL = this.getClass().getResource(iUrlRI3);
    private URL imgRI4URL = this.getClass().getResource(iUrlRI4);
    private URL imgRI5URL = this.getClass().getResource(iUrlRI5);
    private URL imgRI6URL = this.getClass().getResource(iUrlRI6);
    private URL imgRI7URL = this.getClass().getResource(iUrlRI7);
    private URL imgRI8URL = this.getClass().getResource(iUrlRI8);
    private URL imgRI9URL = this.getClass().getResource(iUrlRI9);
    private URL imgRIGifURL = this.getClass().getResource(iUrlRIGIF);
    private URL imgMBarURL = this.getClass().getResource(iUrlMBar);
    private URL imgMCentralesURL = this.getClass().getResource(iUrlMCentrales);
    private URL imgMBahiaURL = this.getClass().getResource(iUrlMBahia);
    private URL imgMPlayaURL = this.getClass().getResource(iUrlMPlaya);
    private URL imgMCasinoURL = this.getClass().getResource(iUrlMCasino);
    private URL imgMapaTURL = this.getClass().getResource(iUrlMapaT);
    private URL imgCreditosURL = this.getClass().getResource(iUrlCreditos);

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
        HIGHSCORE,
        SELECT_MAP
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

    //Sonidos
    SoundClip sitClip;
    SoundClip standClip;
    SoundClip conquerClip;

    /**
     * Método constructor de la clase <I>Game</I> que inicializa el juego.
     */
    public Game() {

        setSize(GAME_WIDTH, GAME_HEIGHT);
        //Fondo
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL);
        // Rum Intro
        RumIntroGif = Toolkit.getDefaultToolkit().getImage(imgRIGifURL);
        RumIntroCont = 0;
        RumIntro = new Animacion();
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI1URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI2URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI3URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI4URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI5URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI6URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI7URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI8URL), 400);
        RumIntro.sumaCuadro(Toolkit.getDefaultToolkit().getImage(imgRI9URL), 400);

        // cheats
        BQT = new Cheat();
        BQT.betoQuiereNovia();
//        infoBar = Toolkit.getDefaultToolkit().getImage(barraInfoURL);
//        plateP = Toolkit.getDefaultToolkit().getImage(pausaURL);
        lockX = lockY = false;
        //Images 
//        cerveza = Toolkit.getDefaultToolkit().getImage(cervezaURL);
        Image travolta1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/ilDivo/azul/divo_01.png"));
        Image travolta2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/ilDivo/azul/divo_02.png"));

        // travolta debugging
        travolta = new Animacion();
        travolta.sumaCuadro(travolta1, 400);
        travolta.sumaCuadro(travolta2, 400);

        pTravolta1 = new Personaje(0, 50, 1, 1);
        pTravolta1.setAnim(travolta);
        pTravolta2 = new Personaje(10, 500, 2, 2);
        pTravolta2.setAnim(travolta);

        pTravolta3 = new Personaje(0, 0, 3, 3);
        pTravolta4 = new Personaje(0, 0, 4, 4);

        //jugadores init
        // LO COMENTE PARA QUE SE INICIALICEN CON LOS COLORES BUENOS
        //j1 = new Jugador(1, Color.red, nameJ1, listaTables.size() - 1);
        //j2 = new Jugador(2, Color.blue, nameJ2, 0);
        //Cajas de texto para nombres de jugadores
        /*jugador1 = new TextField();
         jugador2 = new TextField();
         jugador1.setBounds(new Rectangle(25, 250, 300, 50));
         jugador2.setBounds(new Rectangle(25, 250, 300, 50));*/
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
        imgBarra = Toolkit.getDefaultToolkit().getImage(imgBarraURL);
        imgGanasteVerde = Toolkit.getDefaultToolkit().getImage(imgGanasteVerdeURL);
        imgGanasteAzul = Toolkit.getDefaultToolkit().getImage(imgGanasteAzulURL);
        imgGanasteRojo = Toolkit.getDefaultToolkit().getImage(imgGanasteRojoURL);
        imgGanasteGris = Toolkit.getDefaultToolkit().getImage(imgGanasteGrisURL);
        imgMBar = Toolkit.getDefaultToolkit().getImage(imgMBarURL);
        imgMCentrales = Toolkit.getDefaultToolkit().getImage(imgMCentralesURL);
        imgMBahia = Toolkit.getDefaultToolkit().getImage(imgMBahiaURL);
        imgMPlaya = Toolkit.getDefaultToolkit().getImage(imgMPlayaURL);
        imgMCasino = Toolkit.getDefaultToolkit().getImage(imgMCasinoURL);
        imgMapaT = Toolkit.getDefaultToolkit().getImage(imgMapaTURL);
        imgCreditos = Toolkit.getDefaultToolkit().getImage(imgCreditosURL);

        //Sounds
        sitClip = new SoundClip(sSentar);
        standClip = new SoundClip(sParar);

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
        bBack = new Boton(30, 490, imgBackBoton);
        bBar = new Boton(190, 210, imgMBar);
        bCentrales = new Boton(490, 210, imgMCentrales);
        bBahia = new Boton(30, 410, imgMBahia);
        bPlaya = new Boton(330, 410, imgMPlaya);
        bCasino = new Boton(630, 410, imgMCasino);

        init();
        start();
    }

    public void init() {

        //        bPausa = new Boton(850, 20, plateP);
        //nombres de jugador
        nameJ1 = "alesso";
        nameJ2 = "lukas";

        detenerGanaste = 0;
        //Booleans
        movHorizontal = false;
        movVertical = false;
        rojo = true;
        gris = verde = azul = false;

        // Se inicializa con escenario = 1... y el metodo a continuacion te lleva a centrales.
        // Comentar la siguiente linea te regresa al bar.
        if (clip != null) {
            clip.close();
        }
        playMusic(trackList, 0, 6);
//        playMusic(trackList, 0, 3); //0 means that I want music, 1 means I dont want music; 1 means first song; 

    }

    public void start() {
        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();

    }

    public void hacerJugador2() {
        switch (colorJ2) {
            case 1:
                j1 = new Jugador(1, Color.red, nameJ2, listaTables.size() - 1);
                break;
            case 2:
                j1 = new Jugador(1, Color.gray, nameJ2, listaTables.size() - 1);
                break;
            case 3:
                j1 = new Jugador(1, Color.blue, nameJ2, listaTables.size() - 1);
                break;
            case 4:
                j1 = new Jugador(1, Color.green, nameJ2, listaTables.size() - 1);
                break;
        }
    }

    public void escenario() {
        escenario = 1;
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL);
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();
        clip.close();
        playMusic(trackList, 0, 1);
        hacerJugador2();
    }

    /**
     * Método que actualiza las variables que representan el escenario del juego
     * al escenario 2. Este es la <code>cafetería del tec</code>.
     */
    public void escenario2() {
        escenario = 2;
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL2);
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();
        clip.close();
        playMusic(trackList, 0, 2);
        hacerJugador2();
    }

    /**
     * Método que actualiza las variables que representan el escenario del juego
     * al escenario 3. Este es la <code>la bahía</code>.
     */
    public void escenario3() {
        escenario = 3;
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL3);
        ola1 = Toolkit.getDefaultToolkit().getImage(olaURL1);
        ola2 = Toolkit.getDefaultToolkit().getImage(olaURL2);
        moverOla1 = 0;
        moverOla2 = - 900;
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();
        clip.close();
        playMusic(trackList, 0, 3);
        hacerJugador2();
    }

    public void escenario4() {
        escenario = 4;
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL4);
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();
        clip.close();
        playMusic(trackList, 0, 4);
        hacerJugador2();
    }

    public void escenario5() {
        escenario = 5;
        fondo = Toolkit.getDefaultToolkit().getImage(fondoURL5);
        listaTables = new LinkedList<Mesa>();
        crearMesasYSillas();
        clip.close();
        playMusic(trackList, 0, 5);
        hacerJugador2();
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

        if (escenario == 2) {
            mapa = new int[][]{
                {125, 160, CENTRALES_ROUND},
                {75, 300, CENTRALES_ROUND},
                {125, 440, CENTRALES_ROUND},
                {330, 140, CENTRALES_ROUND},
                {520, 140, CENTRALES_ROUND},
                {310, 300, CENTRALES_ROUND},
                {540, 300, CENTRALES_ROUND},
                {330, 460, CENTRALES_ROUND},
                {520, 460, CENTRALES_ROUND},
                {700, 160, CENTRALES_ROUND},
                {750, 300, CENTRALES_ROUND},
                {700, 440, CENTRALES_ROUND}
            };
        }

        if (escenario == 3) {
            mapa = new int[][]{
                {60, 420, BAHIA_ROUND},
                {50, 120, BAHIA_ROUND},
                {150, 220, BAHIA_ROUND},
                {250, 320, BAHIA_ROUND},
                {350, 420, BAHIA_ROUND},
                {260, 120, BAHIA_ROUND},
                {360, 220, BAHIA_ROUND},
                {460, 320, BAHIA_ROUND},
                {560, 420, BAHIA_ROUND},
                {470, 120, BAHIA_ROUND},
                {570, 220, BAHIA_ROUND},
                {670, 320, BAHIA_ROUND},
                {770, 420, BAHIA_ROUND},
                {770, 120, BAHIA_ROUND}
            };
        }

        if (escenario == 4) {
            mapa = new int[][]{
                {60, 250, PLAYA_ROUND},
                {60, 370, PLAYA_ROUND},
                {220, 180, PLAYA_SQUARE},
                {225, 310, PLAYA_SQUARE},
                {225, 440, PLAYA_SQUARE},
                {415, 180, PLAYA_SQUARE},
                {410, 310, PLAYA_SQUARE},
                {410, 440, PLAYA_SQUARE},
                {595, 180, PLAYA_SQUARE},
                {595, 310, PLAYA_SQUARE},
                {595, 440, PLAYA_SQUARE},
                {770, 250, PLAYA_ROUND},
                {770, 370, PLAYA_ROUND}
            };
        }

        if (escenario == 5) {
            mapa = new int[][]{
                {100, 160, CASINO_ROUND},
                {100, 300, CASINO_ROUND},
                {100, 440, CASINO_ROUND},
                {300, 160, CASINO_ROUND},
                {300, 300, CASINO_ROUND},
                {300, 440, CASINO_ROUND},
                {500, 160, CASINO_ROUND},
                {500, 300, CASINO_ROUND},
                {500, 440, CASINO_ROUND},
                {700, 160, CASINO_ROUND},
                {700, 300, CASINO_ROUND},
                {700, 440, CASINO_ROUND}
            };
        }

        for (int r = 0;
                r < mapa.length;
                r++) {
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
                case CENTRALES_ROUND:
                    url = mesaCentralesURL;
                    tipo = 3;
                    break;
                case BAHIA_ROUND:
                    url = mesaBahiaURL;
                    tipo = 4;
                    break;
                case PLAYA_ROUND:
                    url = mesaPlaya1URL;
                    tipo = 5;
                    break;
                case PLAYA_SQUARE:
                    url = mesaPlaya2URL;
                    tipo = 6;
                    break;
                case CASINO_ROUND:
                    url = mesaCasinoURL;
                    tipo = 7;
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
            int upgrade = (int) (Math.random() * 11);
            if (upgrade < 2) {
                table.setUpgrade(new Upgrade(1));
            } else if (upgrade > 9) {
                table.setUpgrade(new Upgrade(2));
            } else if (upgrade == 5 || upgrade == 6) {
                table.setUpgrade(new Upgrade(3));
            }
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
            if (state == state.MENU_MAIN) {
                actualizaRumIntro();
            }
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
     * Método que actualiza la animación de introducción al juego.
     */
    public void actualizaRumIntro() {
        //Determina el tiempo que ha transcurrido desde que el Applet 
        //inicio su ejecución
        long tiempoTrans = System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTrans;
        //Actualiza la animación en base al tiempo transcurrido
        RumIntro.actualiza(tiempoTrans);
    }

    /**
     * Metodo usado para actualizar la posicion de objetos dentro del juego
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

        moverOla1++;
        moverOla2++;
        if (moverOla1 > 900) {
            moverOla1 = - 900;
        } else if (moverOla2 > 900) {
            moverOla2 = - 900;
        }

        //Acutalizo la posicion del pTravolta2
//        for (Personaje p : j1.listaPersonajes) {
        pTravolta2.actualizaPosicion();
//        }

        if (j1.checarGana(listaTables)) {
            state = state.MENU_POSTJUEGO;
            Ganaste = 1;
        } else if (j2.checarGana(listaTables)) {
            state = state.MENU_POSTJUEGO;
            Ganaste = 2;
        }
    }

    public void leerHighscore() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/source/score.txt"));
        String line = null;
        LinkedList<ScoreRow> puntajes = new LinkedList<ScoreRow>();
        while ((line = reader.readLine()) != null) {
            // ...
            String[] parts = line.split(",");
            ScoreRow fila = new ScoreRow(parts[0], Integer.parseInt(parts[1]));
            puntajes.add(fila);
            System.out.println(parts[0] + "," + parts[1]);
        }

    }

    public void guardarHighscore(String nombre) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/source/score.txt"));
        String line = null;
        LinkedList<ScoreRow> puntajes = new LinkedList<ScoreRow>();
        while ((line = reader.readLine()) != null) {
            // ...
            String[] parts = line.split(",");
            ScoreRow fila = new ScoreRow(parts[0], Integer.parseInt(parts[1]));
            puntajes.add(fila);
//            System.out.println(parts[0] + "," + parts[1]);
        }
        ScoreRow nuevo = new ScoreRow(nombre, 1);
        boolean found = false;
        //checo si está en la lista
        for (ScoreRow p : puntajes) {
            if (nuevo.getNombre() == p.getNombre()) {
                p.setScore(p.getScore() + nuevo.getScore());
                found = true;
            }
        }

        if (!found) {
            puntajes.add(nuevo);
        }

        //sort the LinkedList
        Collections.sort(puntajes, Collections.reverseOrder());

        System.out.println(puntajes);
    }

    /**
     * Checa colisiones dentro del <code>JFrame</code>. Colisiones con la
     * ventana y de los personajes con las mesas.
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
        if (RumIntroCont < DURACION_INTRO_RUM) {
            dbg.setColor(Color.WHITE);
        } else {
            dbg.setColor(new Color(0x78, 0xae, 0xe3));
            dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        }

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
            if (escenario == 3) {
                g.drawImage(ola1, moverOla1, 0, this);
                g.drawImage(ola2, moverOla2, 0, this);
            }
            g.drawImage(fondo, 0, 0, this);
            detenerGanaste = 0;

            //Info De Barra
            g.drawImage(imgBarra, 0, getHeight() - 70, this);
            g.setColor(Color.white);
            g.setFont(new Font("Monospaced", Font.BOLD, 30));
            g.drawString(nameJ2, getWidth() - 300, getHeight() - 35);
            g.drawString(nameJ1, 50, getHeight() - 35);
            g.setFont(new Font("Monospaced", Font.BOLD, 15));
            g.drawString("Sitting: " + j1.getCantSentados(), getWidth() - 150, getHeight() - 35);
            g.drawString("Standing: " + j1.getCantParados(), getWidth() - 150, getHeight() - 15);

            g.drawString("Sitting: " + j2.getCantSentados(), 220, getHeight() - 35);
            g.drawString("Standing: " + j2.getCantParados(), 220, getHeight() - 15);

            for (Mesa mesa : listaTables) {
                mesa.paintSillasArriba(g);
                mesa.paint(g);
                mesa.paintSillasAbajo(g);
                mesa.paintSelectors(g);
                Color colorPisoMesa = mesa.getColorPrincipal();
                if (colorPisoMesa != null) {
                    g.setColor(colorPisoMesa);
                    if (mesa.getTipo() == 1 || mesa.getTipo() == 2 || mesa.getTipo() == 4 || mesa.getTipo() == 5 || mesa.getTipo() == 6 || mesa.getTipo() == 7) {
                        g.drawRect(mesa.getPosX() - 25, mesa.getPosY() - 30, mesa.getAncho() + 50, mesa.getAlto() + 40);
                    } else {
                        g.drawRect(mesa.getPosX() - 30, mesa.getPosY() - 42, mesa.getAncho() + 60, mesa.getAlto() + 45);
                    }
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
            if (RumIntroCont < DURACION_INTRO_RUM) {
                g.drawImage(RumIntro.getImagen(), 0, 0, this);
                RumIntroCont++;

            } else {
                g.drawImage(imgLogoGrande, 200, 20, this);
                g.drawImage(bPlay.getImageIcon().getImage(), bPlay.getPosX(), bPlay.getPosY(), this);
                g.drawImage(bCredits.getImageIcon().getImage(), bCredits.getPosX(), bCredits.getPosY(), this);
                g.drawImage(bHighScore.getImageIcon().getImage(), bHighScore.getPosX(), bHighScore.getPosY(), this);
            }
        }

        if (state == state.SELECT_COLOR_1) {
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
            g.setColor(Color.WHITE);
            g.fillRect(25, 250, 300, 50);
            g.setColor(Color.BLACK);
            g.drawString(nameJ1, 35, 280);
        }

        if (state == state.SELECT_COLOR_2) {
            Color c = new Color(255, 255, 0, 100);
            g.setColor(c);
            if (azul && j2.getColor() != Color.blue) {
                g.fillOval(bColorAzul.getPosX() - 10, bColorAzul.getPosY() - 10, bColorAzul.getAncho() + 20, bColorAzul.getAlto() + 20);
            } else if (rojo && j2.getColor() != Color.red) {
                g.fillOval(bColorRojo.getPosX() - 10, bColorRojo.getPosY() - 10, bColorRojo.getAncho() + 20, bColorRojo.getAlto() + 20);
            } else if (verde && j2.getColor() != Color.green) {
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
            g.setColor(Color.WHITE);
            g.fillRect(25, 250, 300, 50);
            g.setColor(Color.BLACK);
            g.drawString(nameJ2, 35, 280);
        }

        if (state == state.SELECT_MAP) {
            g.drawImage(imgMapaT, 30, 30, this);
            g.drawImage(bBar.getImageIcon().getImage(), bBar.getPosX(), bBar.getPosY(), this);
            g.drawImage(bCentrales.getImageIcon().getImage(), bCentrales.getPosX(), bCentrales.getPosY(), this);
            g.drawImage(bBahia.getImageIcon().getImage(), bBahia.getPosX(), bBahia.getPosY(), this);
            g.drawImage(bPlaya.getImageIcon().getImage(), bPlaya.getPosX(), bPlaya.getPosY(), this);
            g.drawImage(bCasino.getImageIcon().getImage(), bCasino.getPosX(), bCasino.getPosY(), this);
        }

        if (state == state.CREDITS) {
            /*g.drawImage(imgLogoGrande, 200, 20, this);
            Font helvetica = new Font("Helvetica", Font.BOLD, 20);
            g.setFont(helvetica);
            g.setColor(Color.WHITE);
            g.drawString("DESARROLLADO POR RUM", 350, 420);
            helvetica = new Font("Helvetica", Font.BOLD, 20);
            g.setFont(helvetica);
            g.drawString("Hugo León ", 300, 440);
            g.drawString("Bernardo Treviño", 300, 460);
            g.drawString("José Alberto Esquivel", 300, 480);
            g.drawString("Gustavo Ferrufino", 300, 500);*/
            g.drawImage(imgCreditos, 0, 0, this);
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

        if (state == state.MENU_POSTJUEGO) {
            if (detenerGanaste <= 100) {

                switch (Ganaste) {
                    case 1:
                        if (Color.red == j1.getColor()) {
                            g.drawImage(imgGanasteRojo, 0, 0, this);
                        } else if (Color.blue == j1.getColor()) {
                            g.drawImage(imgGanasteAzul, 0, 0, this);
                        } else if (Color.gray == j1.getColor()) {
                            g.drawImage(imgGanasteGris, 0, 0, this);
                        } else {
                            g.drawImage(imgGanasteVerde, 0, 0, this);
                        }
                        break;
                    case 2:
                        if (Color.red == j2.getColor()) {
                            g.drawImage(imgGanasteRojo, 0, 0, this);
                        } else if (Color.blue == j2.getColor()) {
                            g.drawImage(imgGanasteAzul, 0, 0, this);
                        } else if (Color.gray == j2.getColor()) {
                            g.drawImage(imgGanasteGris, 0, 0, this);
                        } else {
                            g.drawImage(imgGanasteVerde, 0, 0, this);
                        }

                        break;
                }

                detenerGanaste++;
            } else {
                restart();
            }
        }

    }

    /**
     * Toca la música del juego. Extraido de
     * https://github.com/tylucaskelley/BrickBreaker
     *
     * @param songs de tipo <code>String[]</code> con la ubicación de las
     * canciones.
     * @param yesNo de tipo <code>int</code> siendo 0 = con musica y 1 lo
     * contrario.
     * @param level de tipo <code>int</code> con el número de la canción a
     * reproducir de entre la lista de canciones <code>songs</code>
     */
    public void playMusic(String[] songs, int yesNo, int level) {
        if (yesNo == 1) {
            return;
        } else if (yesNo == -1) {
            System.exit(0);
        }
        if (level == 10) {
            level = 1;
        }
        try {
            audio = AudioSystem.getAudioInputStream(new File(songs[level - 1]).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Current song: " + audio);
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * Metodo keyPressed Cuando una tecla esta apretada recibe de param un
     * evento de tipo <code>KeyEvent</code>, en este caso se busca que sea la p
     * para pausar el juego
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
                    sitClip.play();
                    break;
                case KeyEvent.VK_UP:
                    listaTables.get(j1.getMesaSeleccionada()).parar(j1);
                    standClip.play();
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
                    sitClip.play();
                    break;
                case KeyEvent.VK_W:
                    listaTables.get(j2.getMesaSeleccionada()).parar(j2);
                    standClip.play();
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
        } else if (state == state.PLAYER_NAME_1) {
            nameJ1 = modificaNombreJ(e, nameJ1);
        } else if (state == state.PLAYER_NAME_2) {
            nameJ2 = modificaNombreJ(e, nameJ2);
        }
    }

    /**
     * Método que modifica el nombre del Jugador conforme se va tecleando en la
     * pantallas de selección de nombre al inicio del juego.
     *
     * @param e de tipo <code>KeyEvent</code>
     * @param n de tipo <cdoe>String</code> con el nombre acumulado.
     * @return el string n modificado hasta ahora.
     */
    public String modificaNombreJ(KeyEvent e, String n) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                n = n.substring(0, n.length() - 1);
                break;
            case KeyEvent.VK_0:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_1:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_2:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_3:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_4:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_5:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_6:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_7:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_8:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_9:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_A:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_B:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_C:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_D:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_E:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_F:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_G:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_H:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_I:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_J:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_K:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_L:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_M:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_N:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_O:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_P:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_Q:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_R:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_S:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_T:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_U:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_V:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_W:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_X:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_Y:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_Z:
                n += e.getKeyChar();
                break;
            case KeyEvent.VK_SPACE:
                n += e.getKeyChar();
                break;
        }
        return n;
    }

    public void keyTyped(KeyEvent e) { //metodo cuando una tecla fue typeada
    }

    public void keyReleased(KeyEvent e) {   //metodo cuandos e suelta la tecla

    }

    /**
     * Metodo mouseClicked Cuando el mouse es apretado recibe de param un
     * evento, que ayudara a definir donde fue picado dentro del applet
     *
     * @param e de tipo <code>MouseEvent</code> con el evento capturado.
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
                try {
                    leerHighscore();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                //this.add(jugador1, null);
            }
            if (bColorAzul.clicked(e)) {
                azul = true;
                rojo = gris = verde = false;
            } else if (bColorGris.clicked(e)) {
                azul = verde = rojo = false;
                gris = true;
            } else if (bColorVerde.clicked(e)) {
                verde = true;
                azul = rojo = gris = false;
            } else {
                rojo = true;
                azul = gris = verde = false;
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
                //nameJ1 = jugador1.getText();
                //ATENCION SE INICIALIZA INVIERTO PARA QUE PUEDAN ESTAR EN EL LADO DE LA PANTALLA QUE LES CORRESPONDE
                switch (colorJ1) {
                    case 1:
                        j2 = new Jugador(2, Color.red, nameJ1, 0);
                        rojo = false;
                        gris = true;
                        break;
                    case 2:
                        j2 = new Jugador(2, Color.gray, nameJ1, 0);
                        gris = false;
                        rojo = true;
                        break;
                    case 3:
                        j2 = new Jugador(2, Color.blue, nameJ1, 0);
                        azul = false;
                        rojo = true;
                        break;
                    case 4:
                        j2 = new Jugador(2, Color.green, nameJ1, 0);
                        verde = false;
                        rojo = true;
                        break;
                }
                //this.remove(jugador1);
            }

            if (back) {
                state = state.SELECT_COLOR_1;
                back = false;
                //this.remove(jugador1);
            }
        }

        if (state == state.SELECT_COLOR_2) {

            if (next) {
                if (rojo) {
                    colorJ2 = 1;
                } else if (gris) {
                    colorJ2 = 2;
                } else if (azul) {
                    colorJ2 = 3;
                } else if (verde) {
                    colorJ2 = 4;
                }
            }
            if (bColorAzul.clicked(e)) {
                azul = true;
                rojo = gris = verde = false;
            } else if (bColorGris.clicked(e)) {
                azul = verde = rojo = false;
                gris = true;
            } else if (bColorVerde.clicked(e)) {
                verde = true;
                azul = rojo = gris = false;
            } else {
                rojo = true;
                azul = gris = verde = false;
            }

            if (next && colorJ1 != colorJ2) {
                System.out.println("colorj1= " + colorJ1 + "colorj2= " + colorJ2);
                state = state.PLAYER_NAME_2;
                next = false;

                //this.add(jugador2, null);
            }

            if (back) {
//                state = state.PLAYER_NAME_1;
                back = false;
            }
        }

        if (state == state.PLAYER_NAME_2) {
            if (next) {
                next = false;
                //nameJ2 = jugador2.getText();
                state = state.SELECT_MAP;
                //this.remove(jugador2);
            }

            if (back) {
                state = state.SELECT_COLOR_2;
                back = false;
                //this.remove(jugador2);
            }
        } else if (state == state.SELECT_MAP) {
            if (bBar.clicked(e)) {
                escenario();
                state = state.GAME;
            } else if (bCentrales.clicked(e)) {
                escenario2();
                state = state.GAME;
            } else if (bBahia.clicked(e)) {
                escenario3();
                state = state.GAME;
            } else if (bPlaya.clicked(e)) {
                escenario4();
                state = state.GAME;
            } else if (bCasino.clicked(e)) {
                escenario5();
                state = state.GAME;
            }

        }

    }

    public void mouseEntered(MouseEvent e) { //metodo cuando entra el mouse

    }

    public void mouseExited(MouseEvent e) { //metodo cuando sale el mouse

    }

    public void mousePressed(MouseEvent e) {    //metodo cuando el mouse es presionado

    }

    /**
     * Metodo que funciona para habilitar el movimiento de los monitos con
     * mouseDrag...
     *
     * @param e de tipo <code>MouseEvent</code>
     */
    public void mouseReleased(MouseEvent e) {//metodo cuando el mouse es soltado
        // La bandera se apaga cuando se deja de picar en el mouse.
        mouseDrag = false;
    }

    public void mouseMoved(MouseEvent e) {  //metodos de MouseMotionListener

    }

    /**
     * Otro método para escuchar el mouse drag y permitir el movimiento del
     * personaje hacia esta posición
     *
     * @param e de tipo <code>MouseEvent</code>
     */
    public void mouseDragged(MouseEvent e) {   //metodos de MouseMotionListener
        // Si la bandera esta prendida, salvar los valores del drag.
        if (mouseDrag) {
            positionX = e.getX();
            positionY = e.getY();
        }
    }

    /**
     * Método que reinicia el juego cambiando el estado del mismo.
     */
    public void restart() {
        init();
        state = state.MENU_MAIN;
    }

}
