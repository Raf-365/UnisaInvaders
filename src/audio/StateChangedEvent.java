/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.util.*;

/**
 *
 * @author antno
 */
public class StateChangedEvent extends EventObject{
    private List<Integer> state; 
    
    public StateChangedEvent(Object source,List<Integer> state) {
        super(source);
        this.state = state;
        this.source=source;
    }

   public List<Integer> getState(){
        return state;
    }      
    
}
