package soundManager;
import javax.sound.sampled.*;
import java.io.*;


public class SoundManager {


    Clip c;
    Long currentFrame;
    String status;
    String path;
    AudioInputStream audioInputStream;
    public void backgroundMusic(String path) {
        this.path = path;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            try {
                c = AudioSystem.getClip();
                c.open(audioInputStream);
                c.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void playSound(String path) {
        File f = new File(path);
        AudioInputStream audioIn;
        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip;
            try {
                clip = AudioSystem.getClip();
                try {
                    clip.open(audioIn);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                clip.start();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
    public void pause() {
       this.currentFrame = this.c.getMicrosecondPosition();
       c.stop();
       status = "paused";
    }
    public void resume() {
        c.close();
        reset();
        c.setMicrosecondPosition(currentFrame);
        this.play();
    }
    public void play() {
        c.start();
        status = "play";
    }
    public void toggle() {
        if(status == null) play();
        else if(status.equals("play")) pause();
        else resume();
    }
    public void reset() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            try {
                c.open(audioInputStream);
                c.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void changeBackgroundSound(String path) {
        this.path = path;
        c.close();
        reset();
        status = null;
        play();
    }
}
