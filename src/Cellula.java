import javax.swing.*;
import java.awt.*;

public class Cellula extends JPanel {
    private final int riga, colonna;
    public Cellula(int riga, int colonna) {
        super();
        this.riga = riga;
        this.colonna = colonna;

        this.setPreferredSize(new Dimension(GameOfLife.FRAME_WIDTH / GrigliaGenerazioni.COLONNE, GameOfLife.FRAME_HEIGHT / GrigliaGenerazioni.RIGHE));
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (GameOfLife.getInstance().statoCellule[this.riga][this.colonna]) {
            g.fillRect(0,0,getWidth(),getHeight());
            return;
        }
        // g.drawRect(0,0,getWidth(),getHeight());
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }
}
