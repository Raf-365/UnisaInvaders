/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.MainView;
import javax.swing.*;

/**
 *
 * @author stefa
 */
public class GameFrame extends JFrame {

    public static final int MAX_X = 1280, MAX_Y = 720;
    public static final int INF_BORDER = 40;

    public GameFrame() {
        initUi();
    }

    private void initUi() {
        setSize(MAX_X, MAX_Y);
        this.setResizable(false);
        setTitle("UnisaInvader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
