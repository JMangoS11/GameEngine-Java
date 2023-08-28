package spriteManager;

import javax.swing.*;
import java.awt.*;

public class Sprite {

    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private boolean visible;
    private String name;


    public Sprite(int x, int y, String path, String name) {
        this.x = x;
        this.y = y;
        ImageIcon i = new ImageIcon(path);
        image = i.getImage();
        visible = true;

        width = image.getWidth(null);
        height = image.getHeight(null);

        this.name = name;
        SpriteMemoryBlock.addSprite(this);
    }

    public Sprite(int x, int y, int width, int height, String path, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        ImageIcon i = new ImageIcon(path);
        image = i.getImage();
        visible = true;

        this.name = name;
        SpriteMemoryBlock.addSprite(this);
    }


    //moves sprite up in render list by a chosen spot
    public void moveUp(int num) {
        SpriteMemoryBlock.moveUp(this,num);
    }
    public void moveDown(int num) {
        SpriteMemoryBlock.moveDown(this,num);
    }
    public void moveUp() {
        SpriteMemoryBlock.moveUp(this);
    }
    public void moveDown() {
        SpriteMemoryBlock.moveDown(this);
    }

    public void changeX(int inc) {x+=inc;}
    public void changeY(int inc) {y+=inc;}

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Image getImage() { return image; }
    public boolean getVisible() {return visible;};
    public String getName() {
        return name;
    }

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setVisible(boolean visible) {this.visible = visible;}
    public void setImage(String path) {
        image = new ImageIcon(path).getImage();
    }
    public void setName(String name) {this.name = name;}

}
