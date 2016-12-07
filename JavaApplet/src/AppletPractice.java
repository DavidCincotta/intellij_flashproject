import java.applet.Applet;
import java.awt.Graphics;

public class AppletPractice extends Applet {
    public void paint(Graphics g) {

        System.out.println("Where does this go");
        g.drawString("Hello world!", 50, 25);
    }
}

