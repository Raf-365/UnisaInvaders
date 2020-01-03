/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;


/**
 *
 * @author steh
 */
public class Professor extends FallingObject {


    public static final int OBSTACLE_SCALE = 50;
    
    
   
    
    public Professor(int x, int y, String path) {
        super(x, y, path);
        /*scaliamo l'immagine alla larghezza e altezza specificata dalla costante utlizzando l'algoritmo specificato da SCALE_SMOOTH*/
        image = image.getScaledInstance(OBSTACLE_SCALE, OBSTACLE_SCALE, Image.SCALE_SMOOTH);
        w = OBSTACLE_SCALE;
        h = OBSTACLE_SCALE;
    }



}
