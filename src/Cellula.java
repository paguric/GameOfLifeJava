import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cellula extends JButton implements ActionListener {
    private boolean stato;
    private final Griglia griglia;
//    private Button bottone;
//    public Cellula() {
//        this.setFocusable(true);
//        bottone = new Button();
//        bottone.addActionListener(this);
//        bottone.setVisible(true);
//        this.add(bottone);
//    }

    public Cellula(Griglia griglia) {
        this.griglia = griglia;
        this.setBorder(null);

        this.setFocusable(true);
        addActionListener(this);
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
        super.paintComponent(g);
        if (stato) {
            this.setBackground(Color.BLACK);
        } else this.setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            String nomeConfigurazione = griglia.getMenuConfigurazioni().getSelectedOption();
            if (nomeConfigurazione != null) {
                Configurazione configurazione = Configurazione.valueOf(nomeConfigurazione.toUpperCase());
                griglia.generaConfigurazione(this, configurazione);
            }
            griglia.requestFocus();
        }
    }
}
