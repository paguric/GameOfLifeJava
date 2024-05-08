import javax.swing.*;
import java.awt.*;

// Singleton pattern
public class GamePanel extends JPanel {

    private static GamePanel instance = null;

    private GamePanel() {
        setLayout(new BorderLayout());

        add(GenerationPanel.getInstance(), BorderLayout.CENTER);

        add(MenuConfigurazioni.getInstance(), BorderLayout.EAST);

    }

    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }

}
