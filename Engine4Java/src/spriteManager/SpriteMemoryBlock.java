package spriteManager;
import java.awt.*;
import java.util.*;
public class SpriteMemoryBlock {

    //Sprite List
    public static ArrayList<Sprite> sL = new ArrayList<>();

    //Button Sprite List
    public static ArrayList<ButtonSprite> bSL = new ArrayList<>();

    public static void addSprite(Sprite s) {
        sL.add(s);
    }
    public static void addButtonSprite(ButtonSprite bs) {bSL.add(bs);}

    public static void printSpriteList() {
        System.out.print("[ ");
        for(int i = 0; i < sL.size()-1; i++)
            System.out.print(sL.get(i).getName() + ", ");
        System.out.print(sL.get(sL.size()-1).getName());
        System.out.println(" ]");
    }

    public static void moveUp(Sprite s, int num) {
        int i = sL.indexOf(s);
        sL.remove(i);
        sL.add(i+num, s);
    }
    public static void moveUp(Sprite s) {
        int i = sL.indexOf(s);
        sL.remove(i);
        sL.add(s);
    }
    public static void moveDown(Sprite s, int num) {
        int i = sL.indexOf(s);
        sL.remove(i);
        sL.add(i-num, s);
    }
    public static void moveDown(Sprite s) {
        int i = sL.indexOf(s);
        sL.remove(i);
        sL.add(0, s);
    }

    public static void checkButtons(double x, double y) {
        for(ButtonSprite bs : bSL) {
            if(bs.getX() < x && bs.getX() + bs.getWidth() > x && bs.getY() < y && bs.getY() + bs.getHeight() > y) bs.buttonPressed();
        }
    }

    public static void checkHover(double x, double y) {
        for(ButtonSprite bs : bSL) {
            if(!bs.getHover() && bs.getX() < x && bs.getX() + bs.getWidth() > x && bs.getY() < y && bs.getY() + bs.getHeight() > y) bs.buttonOnHover();
            else if (bs.getHover() && !(bs.getX() < x && bs.getX() + bs.getWidth() > x && bs.getY() < y && bs.getY() + bs.getHeight() > y)) bs.buttonOffHover();
        }
    }

}
