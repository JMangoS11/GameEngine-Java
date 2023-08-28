package screen;

import spriteManager.Sprite;
import spriteManager.SpriteMemoryBlock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Panel extends JPanel implements Runnable {

    private final int width;
    private final int height;
    private final int DELAY = 25;
    private Thread animator;

    private final ArrayList<UpdateMethod> um;

    //mouse booleans
    private boolean mInFrame;

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;
        um = new ArrayList<>();
        initFrame();
    }

    private void initFrame() {

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));

    }
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        for(Sprite s : SpriteMemoryBlock.sL)
            if(s.getVisible()) g.drawImage(s.getImage(), s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
    }

    public void cycle() {
        for(UpdateMethod u : um) u.update();
        if(mInFrame) {
            Point m = MouseInfo.getPointerInfo().getLocation();
            SpriteMemoryBlock.checkHover(m.getX() - getLocationOnScreen().getX(), m.getY() - getLocationOnScreen().getY());
        }
    }
    public void addUpdateMethod(UpdateMethod um) {
        this.um.add(um);
    }
    public void printSpriteList() {
        SpriteMemoryBlock.printSpriteList();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        setFocusable(true);
        mInFrame = true;
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(mInFrame) {
                    Point m = MouseInfo.getPointerInfo().getLocation();
                    SpriteMemoryBlock.checkButtons(m.getX() - getLocationOnScreen().getX(), m.getY() - getLocationOnScreen().getY());
                }
            }
            @Override public void mouseEntered(MouseEvent e) {mInFrame = true;}
            @Override public void mouseExited(MouseEvent e) {mInFrame = false;}

            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}

        });

        //loop
        while(true) {

            cycle();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                animator.sleep(sleep);
            } catch (InterruptedException e) {

                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }


}
