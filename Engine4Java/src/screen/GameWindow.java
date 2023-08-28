package screen;

import soundManager.SoundManager;

import javax.swing.*;


public class GameWindow extends JFrame {

    private final Panel panel;
    private final SoundManager sm;

    public GameWindow(int width, int height, String title) {
        setVisible(true);

        panel = new Panel(width, height);
        add(panel);
        setResizable(false);
        pack();

        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sm = new SoundManager();

    }

    public Panel getPanel() { return panel; }
    public SoundManager getSoundManager() {return sm;}

}
