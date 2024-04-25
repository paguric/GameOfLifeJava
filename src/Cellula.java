import javax.swing.*;
import java.awt.*;

public class Cellula extends JPanel {
    private boolean stato;
    private final Griglia griglia;

    public Cellula(Griglia griglia) {
        this.griglia = griglia;
        this.setBorder(null);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void setStato(boolean nuovoStato) {
        this.stato = nuovoStato;
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
        if (stato) g.fillRect(0,0,getWidth(),getHeight());
        //else g.drawRect(0,0,getWidth(),getHeight());
    }
}
