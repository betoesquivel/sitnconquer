/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

/**
 *
 * @author hlg
 */
public class ScoreRow implements Comparable<ScoreRow> {
    String nombre; 
    int score; 

    public ScoreRow() {
        nombre = "Sin ocupar";
        score = 15; 
    }

    public ScoreRow(String nombre, int score) {
        this.nombre = nombre;
        this.score = score;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(ScoreRow o) {
        if (score >= o.score) {
            return 1; 
        }else{
            return -1; 
        }
    }
    
    public String toString(){
        return "Nombre: " + getNombre() + " " + "Score: " + getScore(); 
    }    
    
}


