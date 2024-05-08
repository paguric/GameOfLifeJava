import javax.swing.*;
import java.awt.event.*;

// Singleton
public class GameFrame extends JFrame implements KeyListener {

    private static GameFrame istanza;

    public static final String TITLE = "The Game of Life";

    public static final int WIDTH = 1200;

    public static final int MENU_WIDTH = 400;

    public static final int HEIGHT = WIDTH -MENU_WIDTH;

    private static GenerationPanel generationPanel = GenerationPanel.getInstance();

    private GameFrame() {
        super(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(MainMenu.getInstance());

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        setVisible(true);

    }

    public static GameFrame getInstance() {
        if (istanza == null) {
            istanza = new GameFrame();
        }
        return istanza;
    }

    public void showGamePanel() {
        getContentPane().removeAll();
        add(GamePanel.getInstance());
        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //keyTyped = Invoked when a key is typed. Uses KeyChar, char output
//        switch(e.getKeyChar()) {
//            case 'd': {      // next generation
//                generationPanel.computeNextGeneration();
//            }
//            break;
//
//            case 'a': {      // former generation DA FARE
//                generationPanel.computeNextGeneration();
//            }
//            break;
//        }





    }

    @Override
    public void keyPressed(KeyEvent e) {
        //keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'a'){
            for (int i = 0; i < 1; i++){
                generationPanel.computeNextGeneration();
                revalidate();
                repaint();

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
