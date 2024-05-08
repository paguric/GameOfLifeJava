import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

// Singleton
public class GenerationPanel extends JPanel implements MouseListener {
    private static GenerationPanel instance;
    
    public static final int WIDTH = GameFrame.WIDTH - GameFrame.MENU_WIDTH;
    
    public static final int HEIGHT = GameFrame.HEIGHT;
    
    public static final int ROWS = 200;
    
    public static final int COLS = 200;

    private static List<Integer> b = new ArrayList<>();   // born: a new cell is born if it has b neighbours

    private static List<Integer> s = new ArrayList<>();   // survive: a cell survives if it has s neighbours
    
    public boolean[][] cells = new boolean[ROWS][COLS];


    private GenerationPanel() {
        super();
        setLayout(new GridLayout(ROWS, COLS));

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        addMouseListener(this);
        this.setFocusable(true);

        setVisible(true);
    }

    public static GenerationPanel getInstance() {
        if (instance == null) {
            instance = new GenerationPanel();
        }
        return instance;
    }

    public boolean setRule(List<Integer> b, List<Integer> s) {
        if (b.isEmpty() || s.isEmpty()) {
            return false;
        }

        // TO DO


        return true;
    }

    // B3/S23
    public void setDefaultRule() {
        b.clear();
        s.clear();
        b.add(3);
        s.add(2);
        s.add(3);
    }

    public void spawnConfiguration(Configuration c, int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
            return;

        boolean[][] configurationMatrix = c.getConfigurationMatrix();

        int i2 = 0;
        int j2 = 0;

        for (int i = 0; i < configurationMatrix.length; i++) {

            int currentRow =
                    row +i >= ROWS ? 0 : row + i;

            for (int j = 0; j < configurationMatrix[0].length; j++) {
                int currentCol =
                        col +j >= COLS ? 0 : col + j;

                cells[currentRow][currentCol] = configurationMatrix[i2][j2++];
            }
            i2++;
            j2 = 0;

        }

    }

    public void computeNextGeneration() {
        boolean[][] nextCells = new boolean[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                nextCells[i][j] = computeCellsNextStatus(i, j);
            }
        }

        cells = nextCells;

    }

    public boolean computeCellsNextStatus(final int row, final int col) {
        int neighbours = 0;

        for (int i = -1; i < 2; i++) {

            int currentRow =
                    row +i < 0 ? ROWS -1 :
                            row +i >= COLS ? 0 : row +i;

            for (int j = -1; j < 2; j++) {

                if (i == 0 && j == 0)
                    continue; // skip the cell itself

                int currentCol =
                        col +j < 0 ? ROWS -1 :
                                col +j >= COLS ? 0 : col +j;

                if (cells[currentRow][currentCol])
                    neighbours++;

            }

        }

        boolean nextState = false;

        if (cells[row][col]) {

            if (s.contains(neighbours)) {
                nextState = true;

            }

        } else {

            if (b.contains(neighbours)) {
                nextState = true;

            }

        }

        return nextState;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = WIDTH / COLS;
        int cellHeight = HEIGHT / ROWS;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                if (cells[i][j]) {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                }

            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        // convert x and y to row and col
        int row = e.getY() / (HEIGHT / ROWS);
        int col = e.getX() / (WIDTH / COLS);

        String nomeConfigurazione = MenuConfigurazioni.getInstance().getSelectedOption();
        Configuration configuration = Configuration.CELLULA;
        if (nomeConfigurazione != null) {
            configuration = Configuration.valueOf(nomeConfigurazione.toUpperCase().replace(' ', '_'));
        }

        spawnConfiguration(configuration, row, col);

        repaint();

        GameFrame.getInstance().requestFocus();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
