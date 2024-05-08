import javax.swing.*;
import java.awt.*;

// Singleton pattern
public class MainMenu extends JPanel {
    private static MainMenu instance = null;

    private MainMenu() {
        setPreferredSize(new Dimension(GameFrame.MENU_WIDTH, GameFrame.FRAME_HEIGHT));
        setLayout(new GridBagLayout());

        add(new JLabel("The Game of Life"));



        setVisible(true);
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

}
