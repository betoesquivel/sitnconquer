/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 * La interfaz <I>Constantes</I> es un método para centralizar constantes dentro
 * del juego.
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
public interface Constantes {

    //URLs Imagenes
    public static final String iUrlFondo = "images/background1.png";
    public static final String iUrlFondo2 = "images/cafeterias.png";
    public static final String iUrlFondo3 = "images/bahia.png";
    public static final String iUrlFondo4 = "images/playa.png";
    public static final String iUrlFondo5 = "images/casino_royal_red (1).jpg";
    public static final String iUrlola = "images/waves1.png";
    public static final String iUrlola2 = "images/waves2.png";
    public static final String iUrlCerveza = "images/beers.png";
    public static final String iUrlBotellas = "images/bottles.png";
    public static final String iUrlSilla1 = "images/chair1.png";
    public static final String iUrlSilla2 = "images/chair2.png";
    public static final String iUrlSilla3 = "images/chair3.png";
    public static final String iUrlSilla4 = "images/chair4.png";
    public static final String iUrlBarraInfo = "images/infoBar2.png";
    public static final String iUrlPausa = "images/pause.png";
    public static final String iUrlPlato1 = "images/plate1.png";
    //
    public static final String iUrlMesaBillar1 = "images/pooltable1.png";
    public static final String iUrlMesa = "images/table1.1.png";
    public static final String iUrlMesaCentrales = "images/mesa.png";
    public static final String iUrlMesaBahia = "images/mesaBahia.png";
    public static final String iUrlMesaPlaya1 = "images/mesaPlaya1.png";
    public static final String iUrlMesaPlaya2 = "images/mesaPlaya2.png";
    public static final String iUrlMesaCasino = "images/casinoTable.png";
    public static final String iUrlBotonAtras = "images/backBoton.png";
    public static final String iUrlColorAzul = "images/colorAzul.png";
    public static final String iUrlColorGris = "images/colorGris.png";
    public static final String iUrlColorRojo = "images/colorRojo.png";
    public static final String iUrlColorVerde = "images/colorVerde.png";
    public static final String iUrlBotonCredits = "images/creditsBoton.png";
    public static final String iUrlBotonPuntajesAltos = "images/highscoreBoton.png";
    public static final String iUrlMenuInstrucciones = "images/instruccionesMenu.png";
    public static final String iUrlLogo = "images/logo.png";
    public static final String iUrlBotonNext = "images/nextBoton.png";
    public static final String iUrlBotonPlay = "images/playBoton.png";
    public static final String iUrlSelecColor1 = "images/selectColorForPlayer1.png";
    public static final String iUrlSelecColor2 = "images/selectColorForPlayer2.png";
    public static final String iUrlSelecNombre1 = "images/selectNameForPlayer1.png";
    public static final String iUrlSelecNombre2 = "images/selectNameForPlayer2.png";
    public static final String iUrlBarraNombre = "images/barraNombre.png";
    public static final String iUrlBotonBack = "images/backBoton.png";
    public static final String iUrlLogoGrande = "images/sit&conquer_biglogo.png";
    public static final String iUrlPantallaPausa = "images/pantallaPausaSinTexto.png";
    public static final String iUrlGanasteVerde = "images/ganasteVerde.png";
    public static final String iUrlGanasteAzul = "images/ganasteAzul.png";
    public static final String iUrlGanasteRojo = "images/ganasteRojo.png";
    public static final String iUrlGanasteGris = "images/ganasteGris.png";
    public static final String iUrlSelectColor1 = "images/selectColorForPlayer1.png";
    public static final String iUrlSelectColor2 = "images/selectColorForPlayer2.png";
    public static final String iUrlSelectName1 = "images/selectNameForPlayer1.png";
    public static final String iUrlSelectName2 = "images/selectNameForPlayer2.png";
    public static final String iUrlInstruccionesMenuColor = "images/instruccionesMenuColor.png";
    public static final String iUrlInstruccionesMenuNombre = "images/instruccionesMenuNombre.png";
    public static final String iUrlInstruccionesMovimiento = "images/instruccionesMovimiento.png";
    public static final String iUrlLetreroPausado = "images/letreroPausado.png";
    public static final String iUrlBarra = "images/infoBar2.png";
    public static final String iUrlRI1 = "images/Rum_intro/1.png";
    public static final String iUrlRI2 = "images/Rum_intro/2.png";
    public static final String iUrlRI3 = "images/Rum_intro/3.png";
    public static final String iUrlRI4 = "images/Rum_intro/4.png";
    public static final String iUrlRI5 = "images/Rum_intro/5.png";
    public static final String iUrlRI6 = "images/Rum_intro/6.png";
    public static final String iUrlRI7 = "images/Rum_intro/7.png";
    public static final String iUrlRI8 = "images/Rum_intro/8.png";
    public static final String iUrlRI9 = "images/Rum_intro/9.png";
    public static final String iUrlRIGIF = "images/Rum_intro/rum.gif";

    //Colores
    public static final int BLUE = 1;
    public static final int GRAY = 2;
    public static final int RED = 3;
    public static final int GREEN = 4;

    //Table types int values
    public static final int BAR_ROUND = 0;
    public static final int BAR_POOL = 1;
    public static final int CENTRALES_ROUND = 2;
    public static final int BAHIA_ROUND = 3;
    public static final int PLAYA_ROUND = 4;
    public static final int PLAYA_SQUARE = 5;
    public static final int CASINO_ROUND = 6;
    public static final int DURACION_INTRO_RUM = 190;

    //Int values de personaje
    public static final int SENTADO = 0;
    public static final int PARADO = 1;
    public static final int ENMOVIMIENTO = 2;

    //Dimensiones Applet
    public static final int GAME_WIDTH = 900;
    public static final int GAME_HEIGHT = 600;

    //Musica
    String songThree = "src/source/music/heisapirate.wav";
    String songTwo = "src/source/music/goldontheceiling.wav";
    String songOne = "src/source/music/rickrolled.wav";

    //Sounds
    String sParar = "sounds/pararse.wav";
    String sSentar = "sounds/flap.wav";
    String sMesaCapturada = "sounds/mesacapturada.wav";

}
