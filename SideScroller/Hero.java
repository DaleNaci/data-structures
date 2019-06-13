import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Hero extends Character {

    private BufferedImage[] runImages;
    private BufferedImage[] jumpImages;
    private BufferedImage[] standImages;

    private int     imageIndex;
    private int     framecount;
    private int     velocity;
    private String  currentAction; // stand, run, jump
    private String  direction;
    private boolean jumping;

    public Hero(int x, int y) {
        super(x, y);

        runImages   =   new BufferedImage[5];
        jumpImages  =   new BufferedImage[2];
        standImages =   new BufferedImage[2];

        currentAction   =   "stand";
        imageIndex      =   0;
        framecount      =   0;
        direction       =   "right";
        jumping         =   false;
        velocity        =   0;

        try {
            for (int i = 0; i < 5; i++) {
                runImages[i]    =   ImageIO.read(new File("sprite/run/frame-" + Integer.toString(i + 1) + ".png"));
            }
            for (int i = 0; i < 2; i++) {
                jumpImages[i]   =   ImageIO.read(new File("sprite/jump/frame-" + Integer.toString(i + 1) + ".png"));
            }
            for (int i = 0; i < 2; i++) {
                standImages[i]  =   ImageIO.read(new File("sprite/standing/frame-" + Integer.toString(i + 1) + ".png"));
            }
        } catch (IOException e) { }
        currentImage    =   standImages[0];
        width           =   standImages[0].getWidth();
        height          =   standImages[0].getHeight();
    }

    public BufferedImage getImage(int i) {
        if (currentAction.equals("stand"))
            return standImages[i];
        if (currentAction.equals("run"))
            return runImages[i];
        if (currentAction.equals("jump"))
            return jumpImages[i];
        return standImages[0];
    }

    public void setImage(int i) {
        if (currentAction.equals("stand"))
            currentImage = standImages[i];
        if (currentAction.equals("run"))
            currentImage = runImages[i];
        if (currentAction.equals("jump"))
            currentImage = jumpImages[i];
    }

    public String getCurrentAction() { return currentAction; }
    public int getImageIndex() { return imageIndex; }
    public int getFramecount() { return framecount; }
    public String getDirection() { return direction; }
    public boolean isJumping() { return jumping; }
    public int getVelocity() { return velocity; }

    public void setCurrentAction(String currentAction) { this.currentAction = currentAction; }
    public void setImageIndex(int imageIndex) { this.imageIndex = imageIndex; }
    public void setFramecount(int framecount) { this.framecount = framecount; }
    public void setDirection(String direction) { this.direction = direction; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }
    public void setVelocity(int velocity) { this.velocity = velocity; }
}
