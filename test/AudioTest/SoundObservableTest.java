/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AudioTest;

import audio.SoundObservable;
import audio.SoundPlayerListener;
import audio.StateChangedEvent;
import javax.xml.bind.Marshaller.Listener;
import org.junit.*;
import org.junit.Assert;

/**
 *
 * @author User
 */
public class SoundObservableTest {
    private SoundObservable so;
    private SoundPlayerListener lis;
    @Before
    public void setUp() {
        so = new SoundObservable();
        lis = new SoundPlayerListener() {
            @Override
            public void stateChanged(StateChangedEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    @Test
    public void  testAddStates(){
        so.addStates(0);
        Assert.assertEquals(1, so.getList().size());
        
    }
    
    @Test 
    public void testAddListener(){
       so.addListener(lis);
       Assert.assertEquals(1, so.getListeners().size());
    }
    
    @Test
    public void testRemoveListener(){
        so.addListener(lis);
        so.removeListener(lis);
        Assert.assertEquals(0, so.getListeners().size());
    }
    
    @Test
    public void testRemoveState(){
        so.addStates(0);
        so.removeState(0);
        Assert.assertEquals(0, so.getList().size());
        
    }
   
}
