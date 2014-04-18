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
import javax.swing.JFrame;

public class SitNConquer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game variable = new Game();//creo un objeto
        variable.setVisible(true); //Aparezca mi codigo en clase AppletExamen1
        variable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
