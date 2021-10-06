import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.synth.SynthToolBarUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Layout {

    JPanel root_panel;

    ArrayList<MainNavButton> nav_buttons;
    int nav_index;

    ArrayList<JScrollPane> main_panels;

    public Layout() {
        //Array für navbuttons laden:
        nav_buttons = new ArrayList();
        nav_index = 0;

        //Array für 4 Hauptseiten:
        main_panels = new ArrayList();
    }
    
    

    //Fenster laden
    public void initGUI(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.red);
        
        root_panel = new JPanel(new BorderLayout());
        root_panel.setBackground(Color.white);

        JPanel navbar_panel = new JPanel();
        navbar_panel.setLayout(new BoxLayout(navbar_panel, BoxLayout.Y_AXIS));
        navbar_panel.setBackground(Color.decode("#252526"));
     
        JPanel content_panel = new JPanel();
        content_panel.setBackground(Color.decode("#1E1E1E"));
        
        MainNavButton button_start;
        MainNavButton button_champions;
        MainNavButton button_leistung;
        MainNavButton button_rolle;
        MainNavButton test;

        try {
            button_start = new MainNavButton("Start","src/img/home-button.png");
            nav_buttons.add(button_start);
            
            button_champions = new MainNavButton("Champions","src/img/superhero.png");
            nav_buttons.add(button_champions);
            
            button_leistung = new MainNavButton("Leistung","src/img/speedometer.png");
            nav_buttons.add(button_leistung);
            
            button_rolle = new MainNavButton("Deine Rolle","src/img/swords.png");
            nav_buttons.add(button_rolle);
            

            button_start.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    updateNavbar(nav_index,0);
                }
            });

            button_champions.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    updateNavbar(nav_index,1);
                }
            });

            button_leistung.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    updateNavbar(nav_index,2);
                }
            });

            button_rolle.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    updateNavbar(nav_index,3);
                }
            });

            navbar_panel.add(button_start);
            navbar_panel.add(button_champions);
            navbar_panel.add(button_leistung);
            navbar_panel.add(button_rolle);

            
            
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        root_panel.add(navbar_panel, BorderLayout.WEST);
        
        main_panels.add(getStartPage());
        main_panels.add(getChampionsPage());
        main_panels.add(getPerformancePage());
        main_panels.add(getRolePage());

        root_panel.add(getStartPage(), BorderLayout.CENTER);
        frame.getContentPane().add(root_panel);
        frame.setVisible(true);
    }

    //Umbenennen!
    public void testWebScraper() {
        String url = "https://www.leagueoflegends.com/de-de/news/tags/patch-notes/";
        String livepatch = getLivePatch(url);
        System.out.println(livepatch);
    }

    //Live Patch aus LoL-Homepage ziehen:
    public String getLivePatch(String url) {   
            Document doc = request(url);
            Elements test = doc.getElementsByClass("style__Title-sc-1h41bzo-8 fEywOQ");
            return getElementContent(test.first());
    }

    //Helfer-Methode um Seiten zu laden
    private static Document request(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if (con.response().statusCode() == 200 ) {
                System.out.println("Seite geladen: " + doc.title());
                return doc;
            }
            return null;
        }
        catch (IOException e) {
            return null;
        }
    }

    //Aus HTML-Elementen nur den Text bekommen:
    private String getElementContent(Element e) {
        String str = e.toString();
        return str.substring(str.indexOf(">"),str.indexOf("<",1));
    }

    public void updateNavbar(int old, int fresh){
        nav_buttons.get(old).demark();
        nav_buttons.get(fresh).mark();

        BorderLayout layout = (BorderLayout)root_panel.getLayout();
        root_panel.remove(layout.getLayoutComponent(root_panel, BorderLayout.CENTER));

        root_panel.add(main_panels.get(fresh));
        SwingUtilities.updateComponentTreeUI(root_panel);

        nav_index = fresh;          
    }

    public JScrollPane getStartPage(){
        JScrollPane sp = getScrollPane();
        JLabel title = new JLabel("Start");
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        title.setForeground(Color.white);

        sp.getViewport().add(title);
        return sp;
    }

    public JScrollPane getChampionsPage(){
        JScrollPane sp = getScrollPane();
        JLabel title = new JLabel("Champions");
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        title.setForeground(Color.white);

        sp.getViewport().add(title);
        return sp;
    }

    public JScrollPane getPerformancePage(){
        JScrollPane sp = getScrollPane();
        JLabel title = new JLabel("Leistung");
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        title.setForeground(Color.white);

        sp.getViewport().add(title);
        return sp;
    }

    public JScrollPane getRolePage(){
        JScrollPane sp = getScrollPane();
        JLabel title = new JLabel("Deine Rolle");
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        title.setForeground(Color.white);

        sp.getViewport().add(title);
        return sp;
    }

    public JScrollPane getScrollPane() {
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setBackground(Color.decode("#1E1E1E"));
        sp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        return sp;
    }

}

