package View;

import View.MainView;
import javax.swing.*;
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
