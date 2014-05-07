/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

/**
 * La clase <I>ScoreRow</I> describe a una fila dentro
 * del marcador que es presentado al final del juego. 
 * Cuenta con el nombre del jugador y la cantidad de partidas
 * jugadas. 
 *
 * @author ferrufino, hugolg, betoesquivel, bernardot
 */
public class ScoreRow implements Comparable<ScoreRow> {
    String nombre; 
    int score; 

    /**
     * Método constructor de ScoreRow sin definir valores
     */
    public ScoreRow() {
        nombre = "Sin ocupar";
        score = 15; 
    }

    /**
     * Método contructor de <I>ScoreRow</I> recibiendo valores iniciales
     * @param nombre con el nombre del usuario
     * @param score con el número de juegos ganados
     */
    public ScoreRow(String nombre, int score) {
        this.nombre = nombre;
        this.score = score;
    }

    /**
     * Método de acceso que regres el nombre del jugador.
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método modificador que cambia el nombre del jugador.
     * @param nombre nuevo del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método de acceso que regresa el número de juegos ganados del 
     * jugador.
     * @return número de juegos ganados del jugador.
     */
    public int getScore() {
        return score;
    }

    /**
     * Método modificador que cambia el valor del marcador.
     * @param score con el número de juegos ganados del jugador.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Función que sobrecarga la comparación para poder comparar
     * esta clase. Es utilizada por el método sort de LinkedList
     * para ordenar los marcadores.
     * 
     * @param o objeto contra el que se compara.
     * @return 1 si objeto es mayor o igual que el parámetro ó -1 si no es cierto
     */
    @Override
    public int compareTo(ScoreRow o) {
        if (score >= o.score) {
            return 1; 
        }else{
            return -1; 
        }
    }
    
    /**
     * Método para poder imprimir el objeto directamente con System.out.println;
     * @return 
     */
    public String toString(){
        return "Nombre: " + getNombre() + " " + "Score: " + getScore(); 
    }    
    
}


