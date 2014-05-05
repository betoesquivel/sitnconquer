/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 * La clase base describe a nuestros objetos bases en el juego. 
 * Esta clase simplemente posiciona una <I>Animacion</I> o un 
 * <code>ImageIcon</code> dentro del <code>JFrame</code>.
 */
public class Base {

    private int posX;    //posicion en x.       
    private int posY;	//posicion en y.
    protected Animacion anim;   //animacion del objeto.
    protected ImageIcon icono;    //icono

    /**
     * Metodo constructor usado para crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto. anim es la
     * <code>Animacion</code> del objeto. icono es el <code>ImageIcon</code> del
     * objeto.
     */
    public Base(int posX, int posY) {
        anim = new Animacion();
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Metodo modificador usado para cambiar la posicion en x del objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Metodo de acceso que regresa la posicion en x del objeto
     *
     * @return posX es la <code>posicion en x</code> del objeto.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Metodo modificador usado para cambiar la posicion en y del objeto
     *
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Metodo de acceso que regresa la posicion en y del objeto
     *
     * @return posY es la <code>posicion en y</code> del objeto.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Metodo modificador usado para cambiar el icono del objeto
     *
     * @param icono es el <code>icono</code> del objeto.
     */
    public void setImageIcon(ImageIcon icono) {
        this.icono = icono;
    }

    /**
     * Metodo de acceso que regresa el icono del objeto
     *
     * @return icono es el <code>icono</code> del objeto.
     */
    public ImageIcon getImageIcon() {
        return icono;
    }

    /**
     * Todas las imagenes del juego van a ser animaciones, 
     * solo que las que solamente sean imágenes las trataremos como 
     * animaciones sin actualización.
     * 
     * Obtiene el ancho del objeto.
     * 
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        icono = new ImageIcon(anim.getImagen());
        return icono.getIconWidth();
    }

    /**
     * Todas las imagenes del juego van a ser animaciones, 
     * solo que las que solamente sean imágenes las trataremos como 
     * animaciones sin actualización.
     * 
     * Obtiene el alto del objeto.
     * 
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAlto() {
        icono = new ImageIcon(anim.getImagen());
        return icono.getIconHeight();
    }

    /**
     * Metodo de acceso que regresa la imagen del icono
     *
     * @return un objeto de la clase <code>Image</code> que es la imagen del
     * icono.
     */
    public Image getImagenI() {
        icono = new ImageIcon(anim.getImagen());
        return icono.getImage();
    }


    /**
     * Metodo de acceso que regresa un nuevo rectangulo
     *
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro
     * del rectangulo
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getPosX(), getPosY(), getAncho(), getAlto());
    }


    /**
     * Checa si el objeto <code>Base</code> intersecta a otro <code>Base</code>
     *
     * @return un valor boleano <code>true</code> si lo intersecta
     * <code>false</code> en caso contrario
     */
    public boolean intersecta(Base obj) {
        return getPerimetro().intersects(obj.getPerimetro());
    }
    
       public boolean intersecta2(Rectangle obj) {
        return getPerimetro().intersects(obj);
    }
   
 

    /**
     * Checa si el objeto <code>Base</code> ha sido clickeado <code>click</code>
     *
     * @return un valor boleano <code>false</code> si lo intersecta
     * <code>true</code> en caso contrario
     */
    public boolean clicked(MouseEvent e) {
        return getPerimetro().contains(e.getX(), e.getY());
    }

    /**
     * Metodo de acceso que regresa la animación del objeto
     *
     * @return anim es la <code>animación</code> del objeto.
     */
    public Animacion getAnim() {
        return anim;
    }

    /**
     * Metodo modificador usado para cambiar la animación del objeto
     *
     * @param anim es la <code>animación</code> del objeto.
     */
    public void setAnim(Animacion anim) {
        this.anim = anim;
    }

}
