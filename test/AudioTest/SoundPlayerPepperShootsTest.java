/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AudioTest;

import Controller.PepperController;
import audio.SoundPlayerPepperShoots;
import org.junit.*;

/**
 *
 * @author User
 */
public class SoundPlayerPepperShootsTest {
    private SoundPlayerPepperShoots sp;
    private PepperController pc;
    @Before
    public void setUp() {
    sp = new SoundPlayerPepperShoots("");
    pc = new PepperController();
    }
    
    @Test
    public void testStateChanged(){
        
    }
    
    
}
