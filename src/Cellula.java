import javax.swing.*;
import java.awt.*;

public class Cellula extends JPanel {
    private boolean stato;
    private final Griglia griglia;
    private final int riga, colonna;

    public Cellula(Griglia griglia, int riga, int colonna) {
        this.griglia = griglia;
        this.riga = riga;
        this.colonna = colonna;
        this.setBorder(null);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public boolean getStato() {
        return this.stato;
    }

//    private void invertiStato() {
//        this.stato = !stato;
//    }

    public int getLato() {
        return getWidth() / Griglia.RIGHE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (this.griglia.statoCellule[this.riga][this.colonna]) g.fillRect(0,0,getWidth(),getHeight());
        //else g.drawRect(0,0,getWidth(),getHeight());
    }
}
