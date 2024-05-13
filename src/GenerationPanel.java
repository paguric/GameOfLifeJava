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
    
    public static final int ROWS = 33;
    
    public static final int COLS = 33;

    private static List<Integer> b = new ArrayList<>();   // born: a new cell is born if it has b neighbours

    private static List<Integer> s = new ArrayList<>();   // survive: a cell survives if it has s neighbours
    
    public byte[][] cells = new byte[ROWS][COLS];


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

    private byte[][] getPadding() {
        byte[][] cellsWithPadding = new byte[ROWS + 2][COLS + 2];

        // upper left corner
        cellsWithPadding[0][0] = cells[ROWS - 1][COLS - 1];

        // upper right corner
        cellsWithPadding[0][COLS + 1] = cells[ROWS - 1][0];

        // lower left corner
        cellsWithPadding[ROWS + 1][0] = cells[0][COLS - 1];

        // lower right corner
        cellsWithPadding[ROWS + 1][COLS + 1] = cells[0][0];

        // upper row
        for (int i = 0; i < COLS - 1; i++) {
            cellsWithPadding[0][i] = cells[ROWS - 1][i];
        }

        // lower row
        for (int i = 0; i < COLS - 1; i++) {
            cellsWithPadding[ROWS + 1][i] = cells[0][i];
        }

        // left col
        for (int i = 1; i < ROWS - 1; i++) {
            cellsWithPadding[i][0] = cells[i][COLS - 1];
        }

        // right col
        for (int i = 1; i < ROWS - 1; i++) {
            cellsWithPadding[i][COLS + 1] = cells[i][0];
        }

        // center
        for (int i = 1; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                cellsWithPadding[i][j] = cells[i - 1][j - 1];
            }
        }

        return cellsWithPadding;
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
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return;
        }
        byte[][] configuration = c.getConfiguration();

        for (int i = 0; i < c.getY(); i++) {
            for (int j = 0; j < c.getX(); j++) {
                int nextRow = (row + i) % ROWS;
                int nextCol = (col + j) % COLS;
                cells[nextRow][nextCol] = configuration[i][j];
            }
        }

    }

    public void computeNextGeneration() {
        byte[][] nextGeneration = new byte[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                nextGeneration[i][j] = computeCellsNextState(i, j);
            }
        }
        cells = nextGeneration;
    }

    public byte computeCellsNextState(final int row, final int col) {
        byte[][] padding = getPadding();
        int neighbours = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                if (i == 0 && j == 0)
                    continue; // skip the cell itself

                if ((padding[i + row + 1][j + col + 1] & 0x01) == 1)
                    neighbours++;

            }

        }

        if ((cells[row][col] & 0x01) == 1) {

            if (!s.contains(neighbours)) {
                return 0x00;

            }

        } else {

            if (b.contains(neighbours)) {
                return 0x01;

            }

        }
        return cells[row][col];

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = WIDTH / COLS;
        int cellHeight = HEIGHT / ROWS;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if ((cells[i][j] & 0x01) == 1) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);

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
