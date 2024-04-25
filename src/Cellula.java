import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Cellula extends JPanel {
    private boolean stato;
    private final Griglia griglia;
    private final int riga, colonna;
    private ActionListener commonActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            griglia.generaConfigurazione(Configurazione.valueOf(griglia.getMenuConfigurazioni().getSelectedOption().toUpperCase().replace(' ','_')), riga, colonna);
            griglia.requestFocus();
        }
    };
    private JButton commonButton = new JButton();

    public Cellula(Griglia griglia, int riga, int colonna) {
        this.griglia = griglia;
        this.riga = riga;
        this.colonna = colonna;

        this.setPreferredSize(new Dimension(Griglia.FRAME_WIDTH - Griglia.MENU_WIDTH / Griglia.COLONNE,Griglia.FRAME_HEIGHT / Griglia.COLONNE));
        commonButton.setPreferredSize(this.getPreferredSize());
        commonButton.setBorder(null);
        commonButton.addActionListener(commonActionListener);
        commonButton.setVisible(true);
        this.add(commonButton, BorderLayout.CENTER);
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
//        if (this.griglia.statoCellule[this.riga][this.colonna]) g.fillRect(0,0,getWidth(),getHeight());
//        else g.drawRect(0,0,getWidth(),getHeight());
        if (this.griglia.statoCellule[this.riga][this.colonna]) commonButton.setBackground(Color.black);
        else commonButton.setBackground(Color.white);
    }
}
