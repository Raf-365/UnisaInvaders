/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObserverPackage;
import java.util.*;
/**
 *
 * @author marcopreziosi
 */
public interface  ControllerObserver extends EventListener{
    
    public void eventCollisionChanged(CollisionEvent collisionEvent);
    
}
