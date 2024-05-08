import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Singleton pattern
public class MainMenu extends JPanel implements ActionListener {

    public static final int BUTTON_WIDTH = 200;

    public static final int BUTTON_HEIGHT = 50;

    private static MainMenu instance = null;

    private static JLabel gameTitle = new JLabel(GameFrame.TITLE);

    private static JButton startButton = new JButton("Play");

    private static JButton customModeButton = new JButton("Custom Mode");

    private static JButton optionsButton = new JButton("Options");

    private static JButton infoButton = new JButton("Info");

    private static JButton quitButton = new JButton("Quit");

    private MainMenu() {
        setPreferredSize(new Dimension(GameFrame.MENU_WIDTH, GameFrame.FRAME_HEIGHT));
        setLayout(new GridBagLayout());

        initializeComponents();

        placeComponents();

        setVisible(true);
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private void initializeComponents() {

        gameTitle.setFont(new Font("Arial", Font.BOLD, 30));

        JButton[] buttons = {startButton, customModeButton, optionsButton, infoButton, quitButton};
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            button.addActionListener(this);
        }

    }

    private void placeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(gameTitle, gbc);

        gbc.gridy++;
        add(startButton, gbc);

        gbc.gridy++;
        add(customModeButton, gbc);

        gbc.gridy++;
        add(optionsButton, gbc);

        gbc.gridy++;
        add(infoButton, gbc);

        gbc.gridy++;
        add(quitButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {
            GameFrame.getInstance().showGamePanel();
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }

    }

}
