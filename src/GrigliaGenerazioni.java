import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Singleton
public class GrigliaGenerazioni extends JPanel implements MouseListener {
    private static GrigliaGenerazioni istanza;
    public static final int RIGHE = 77;
    public static final int COLONNE = 77;
    private static Cellula[][] cellule = new Cellula[RIGHE][COLONNE];
    private GrigliaGenerazioni() {
        super();
        setLayout(new GridLayout(RIGHE, COLONNE));

        setPreferredSize(new Dimension(GameFrame.FRAME_WIDTH - GameFrame.MENU_WIDTH, GameFrame.FRAME_HEIGHT));

        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                cellule[i][j] = new Cellula(i, j);
                add(cellule[i][j]);
            }
        }

        addMouseListener(this);
        this.setFocusable(true);

        setVisible(true);
    }

    public static GrigliaGenerazioni getInstance() {
        if (istanza == null) {
            istanza = new GrigliaGenerazioni();
        }
        return istanza;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point punto = e.getPoint();
        Component pannello = getComponentAt(punto);
        if (pannello instanceof Cellula) {
            Cellula cellula = (Cellula) pannello;

            String nomeConfigurazione = MenuConfigurazioni.getInstance().getSelectedOption();
            Configurazione configurazione = Configurazione.CELLULA;
            if (nomeConfigurazione != null) {
                configurazione = Configurazione.valueOf(nomeConfigurazione.toUpperCase().replace(' ', '_'));
            }

            GameFrame.getInstance().generaConfigurazione(configurazione, cellula.getRiga(), cellula.getColonna());
            GameFrame.getInstance().requestFocus();
            repaint();
        }
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
