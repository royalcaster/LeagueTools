import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GraphicsEnvironment;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.awt.event.*;
import java.awt.*;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Image.*;

public class MainNavButton extends JPanel{

Font Roboto_Regular;

    public MainNavButton(String title, String url) throws FontFormatException {
    
        setBackground(Color.decode("#252526"));
        setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#252526")));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,0,10));
        panel.setBorder(BorderFactory.createEmptyBorder(0,13,0,13));
        panel.setBackground(Color.decode("#252526"));

        //ICON -> Google icons einbinden oder als Bilddatei...

      

        JLabel img_label = new JLabel();
        img_label.setIcon(getNavIcon(url));
        img_label.setHorizontalAlignment(JLabel.CENTER);

        JLabel label = new JLabel(title);
        label.setForeground(Color.decode("#8F8F94"));
        label.setOpaque(true);
        label.setBackground(Color.decode("#252526"));
        label.setHorizontalAlignment(JLabel.CENTER);

        try {
            //create the font to use. Specify the size!
            InputStream myStream = new BufferedInputStream(new FileInputStream("src/fonts/Roboto-Bold.ttf"));
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Roboto_Regular = customFont.deriveFont(13f);
            //register the font
            ge.registerFont(Roboto_Regular);
            label.setFont(Roboto_Regular);
            } catch (IOException e) {
                e.printStackTrace();
            } catch(FontFormatException e) {
                e.printStackTrace();
            }
            
        panel.add(getFiller());
        panel.add(img_label);
        panel.add(label);
        add(panel);

        addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                setBackground(Color.decode("#313133"));
                label.setBackground(Color.decode("#313133"));
                panel.setBackground(Color.decode("#313133"));

                Cursor pointer = new Cursor(java.awt.Cursor.HAND_CURSOR);
                setCursor(pointer);
            }

            public void mouseExited(MouseEvent e) {
                setBackground(Color.decode("#252526"));
                label.setBackground(Color.decode("#252526"));
                panel.setBackground(Color.decode("#252526"));
            }
        });

    }

    public ImageIcon getNavIcon(String url) {
        ImageIcon icon_old = new ImageIcon(url);
        Image image = icon_old.getImage();

        Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public JLabel getFiller() {
        JLabel filler = new JLabel("");
        filler.setFont(new Font("Arial", Font.PLAIN, 5));
        return filler;
    }

    public void mark(){
        setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#007ACC")));
    }

    public void demark(){
        setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#252526")));
    }
}
