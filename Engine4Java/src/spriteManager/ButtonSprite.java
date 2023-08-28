package spriteManager;

public class ButtonSprite extends Sprite {

    ButtonListener bl;
    private boolean hover;

    public ButtonSprite(int x, int y, String path, String name, ButtonListener bl) {
        super(x, y, path, name);
        SpriteMemoryBlock.addButtonSprite(this);
        this.bl = bl;
    }

    public ButtonSprite(int x, int y, int width, int height, String path, String name, ButtonListener bl) {
        super(x, y, width, height, path, name);
        SpriteMemoryBlock.addButtonSprite(this);
        this.bl = bl;
    }

    public void setButtonListener(ButtonListener bl) {this.bl = bl;}

    public boolean getHover() {return hover;}

    public void buttonPressed() {bl.onClick();}
    public void buttonOnHover() {
        hover = true;
        bl.onHover();
    }
    public void buttonOffHover() {
        hover = false;
        bl.offHover();
    }
}
