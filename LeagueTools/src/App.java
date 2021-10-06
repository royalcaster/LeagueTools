import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Layout layout = new Layout();
        layout.initGUI();
        layout.testWebScraper();    
        layout.updateNavbar(0, 0);
    }

}