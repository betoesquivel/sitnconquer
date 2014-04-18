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
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

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
     * Metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        return icono.getIconWidth();
    }

    /**
     * Metodo de acceso que regresa el alto del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAlto() {
        return icono.getIconHeight();
    }

    /**
     * Metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAnchoAnim() {
        icono = new ImageIcon(anim.getImagen());
        return icono.getIconWidth();
    }

    /**
     * Metodo de acceso que regresa el alto del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAltoAnim() {
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
        //icono = new ImageIcon(anim.getImagen());
        return icono.getImage();
    }

    public Image getImagenIAnim() {
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
     * Metodo de acceso que regresa un nuevo rectangulo
     *
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro
     * del rectangulo
     */
    public Rectangle getPerimetroBoton() {
        return new Rectangle(getPosX(), getPosY(), 248, 160);
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

    /**
     * Checa si el objeto <code>Base</code> intersecta al <code>click</code>
     *
     * @return un valor boleano <code>false</code> si lo intersecta
     * <code>true</code> en caso contrario
     */
    public boolean clicked(MouseEvent e) {
        return (getPerimetro().intersects(new Rectangle(e.getX(), e.getY(), 1, 1)));
    }
    
    /**
     * Checa si el objeto <code>Base</code> intersecta al <code>click</code>
     * Lo uso para cuando tengo botones sin imagenes. Testing... Solamente
     * @return un valor boleano <code>false</code> si lo intersecta
     * <code>true</code> en caso contrario
     */
    public boolean clickedBoton(MouseEvent e) {
        return (getPerimetroBoton().intersects(new Rectangle(e.getX(), e.getY(), 1, 1)));
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
