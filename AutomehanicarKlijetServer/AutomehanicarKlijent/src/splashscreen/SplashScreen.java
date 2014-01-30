/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package splashscreen;

/**
 *
 * @author Rus
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SplashScreen extends JWindow {

    public int trajanje;

    public SplashScreen(int trajanje) {
        this.trajanje = trajanje;

        JPanel panel = (JPanel) getContentPane();
        int width = 500;
        int height = 411;
        Dimension rezolucija = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (rezolucija.width - width) / 2;
        int y = (rezolucija.height - height) / 2;
        setBounds(x, y, width, height);
        panel.add(new JLabel(new ImageIcon("slike/splash.jpg")), BorderLayout.CENTER);
        Color plava = new Color(010242);
        panel.setBorder(BorderFactory.createLineBorder(plava, 2));

        setVisible(true);
        try {
            Thread.sleep(trajanje);
        } catch (Exception e) {
        }
        setVisible(false);
    }
}