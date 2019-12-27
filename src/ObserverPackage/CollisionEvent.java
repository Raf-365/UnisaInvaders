/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObserverPackage;

import java.util.ArrayList;
import java.util.EventObject;

/**
 *
 * @author marcopreziosi
 */
public class CollisionEvent extends EventObject{
    
    private ArrayList<Integer> state; 
    
    public CollisionEvent(Object source,ArrayList<Integer> state) {
        super(source);
        this.state = state;
        this.source=source;
    }

   public ArrayList<Integer> getState(){
        return state;
    }      
    
}

    

