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
public class Upgrade {

    private int funcion; // variable que indica que tipo de upgrade y función tiene

    /**
     * Método constructor de Upgrade para crear un objeto.
     */
    public Upgrade() {
        funcion = 0;
    }

    /**
     * Método constructor de Upgrade para crear un objeto y definir su función.
     */
    public Upgrade(int f) {
        funcion = f;
    }

    /**
     * Metodo de acceso que regresa la función del objeto
     *
     * @return funcion
     */
    public int getFuncion() {
        return funcion;
    }

    /**
     * Metodo modificador usado para cambiar la funcion del objeto
     *
     * @param funcion es la <code>funcion</code> del objeto.
     */
    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }

}
