/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;
import PepperMoving.PepperJPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.*;
/**
 *
 * @author stefa
 */
public class ObstacleJPanel extends JPanel{
    
    private Obstacle obstacle;
    
    public ObstacleJPanel(Obstacle obstacle){
        this.obstacle=obstacle;
        initBoard();
    }
    
    public Obstacle getObstacle(){return this.obstacle;}
    
    private void initBoard() {
        this.setSize(obstacle.getWidth()+500, obstacle.getHeigth()+500);
        setBackground(Color.blue);
	//setOpaque(true);
    }    
    
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);   
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), this);
        Toolkit.getDefaultToolkit().sync();        
    }       
}
