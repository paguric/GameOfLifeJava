import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Singleton
public class GameOfLife extends JFrame implements KeyListener {
    private static GameOfLife istanza;
    private static boolean visibile = false;
    private static GrigliaGenerazioni grigliaGenerazioni;
    public static final int FRAME_WIDTH = 1200;
    public static final int MENU_WIDTH = 400;
    public static final int FRAME_HEIGHT = FRAME_WIDTH -MENU_WIDTH;
    public boolean[][] statoCellule = new boolean[GrigliaGenerazioni.RIGHE][GrigliaGenerazioni.COLONNE];
    private final MenuConfigurazioni menuConfigurazioni;
    private GameOfLife() {
        super("The Game of Life");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());

        grigliaGenerazioni = GrigliaGenerazioni.getInstance();
        add(grigliaGenerazioni, BorderLayout.CENTER);

        menuConfigurazioni = MenuConfigurazioni.getInstance();
        add(menuConfigurazioni, BorderLayout.EAST);

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        if (!visibile) {
            visibile = true;
            setVisible(visibile);
        }
    }

    public static GameOfLife getInstance() {
        if (istanza == null) {
            istanza = new GameOfLife();
        }
        return istanza;
    }

    public void generaConfigurazione(Configurazione configurazione, int riga, int colonna) {
        if (riga < 0 || riga >= GrigliaGenerazioni.RIGHE || colonna < 0 || colonna >= GrigliaGenerazioni.COLONNE) return;

        int i2 = 0;
        int j2 = 0;

        for (int i = 0; i < configurazione.getRighe(); i++) {

            int rigaCorrente =
                    riga +i >= GrigliaGenerazioni.RIGHE ? 0 : riga +i;

            for (int j = 0; j < configurazione.getRighe(); j++) {
                int colonnaCorrente =
                        colonna +j >= GrigliaGenerazioni.COLONNE ? 0 : colonna +j;

                statoCellule[rigaCorrente][colonnaCorrente] = configurazione.getElemento(i2, j2++);
            }
            i2++;
            j2 = 0;

        }
    }

    public void prossimaGenerazione() {
        boolean[][] prossimoStatoCellule = new boolean[GrigliaGenerazioni.RIGHE][GrigliaGenerazioni.COLONNE];

        for (int i = 0; i < GrigliaGenerazioni.RIGHE; i++) {
            for (int j = 0; j < GrigliaGenerazioni.COLONNE; j++) {
                prossimoStatoCellule[i][j] = calcolaProssimaGenerazioneCellula(i, j);
            }
        }

        statoCellule = prossimoStatoCellule;
        GrigliaGenerazioni.getInstance().repaint();
    }
    public boolean calcolaProssimaGenerazioneCellula(final int riga, final int colonna) {
        int celluleVive = 0;

        for (int i = -1; i < 2; i++) {

            int rigaCorrente =
                    riga +i < 0 ? GrigliaGenerazioni.RIGHE -1 :
                            riga +i >= GrigliaGenerazioni.RIGHE ? 0 : riga +i;

            for (int j = -1; j < 2; j++) {
                int colonnaCorrente =
                        colonna +j < 0 ? GrigliaGenerazioni.COLONNE -1 :
                                colonna +j >= GrigliaGenerazioni.COLONNE ? 0 : colonna +j;
                if (statoCellule[rigaCorrente][colonnaCorrente]) celluleVive++;
            }

        }

        // isolamento           - meno di due celle adiacenti vive, morte
        // sopravvivenza        - esattamente 2 o 3 celle adiacenti vive, sopravvivenza
        // sovrappopolazione    - piú di 3 celle vive adiacenti, morte
        // riproduzione         - esattamente 3 pannelloCellule vive adiacenti, nascita

        boolean prossimoStato = false;

        if (statoCellule[riga][colonna]) {
            celluleVive--; // tolgo dal conteggio la cellula stessa
            if (celluleVive < 2) prossimoStato = false;    // isolamento
            else if (celluleVive == 2 || celluleVive == 3) prossimoStato = true;       // sopravvivenza
            else prossimoStato = false;    // sovrappopolazione
        } else {
            if (celluleVive == 3) prossimoStato = true;    // riproduzione
        }

        return prossimoStato;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //keyTyped = Invoked when a key is typed. Uses KeyChar, char output
        switch(e.getKeyChar()) {
            case 'd': {      // next generation
                prossimaGenerazione();
            }
            break;

            case 'a': {      // former generation DA FARE
                prossimaGenerazione();
            }
            break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//        // Ottieni le coordinate del clic del mouse
//        Point clickPoint = e.getPoint();
//
//        // Itera attraverso i pannelli per trovare il pannello cliccato
//        for (int i = 0; i < GrigliaGenerazioni.RIGHE; i++) {
//            for (int j = 0; j < GrigliaGenerazioni.COLONNE; j++) {
//                Component component = pannelloCellule[i][j];
//                Rectangle bounds = component.getBounds();
//                if (bounds.contains(clickPoint)) {
//                    // System.out.println("Hai cliccato il pannello alla riga " + (i-1) + " e colonna " + j);  // TEST
//                    String nomeConfigurazione = this.getMenuConfigurazioni().getSelectedOption();
//                    if (nomeConfigurazione != null) {
//                        Configurazione configurazione = Configurazione.valueOf(nomeConfigurazione.toUpperCase().replace(' ', '_'));
//                        this.generaConfigurazione(configurazione, (i -1), j);
//                    }
//                    this.requestFocus();
//                    return;
//                }
//            }
//        }
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < GrigliaGenerazioni.RIGHE; i++) {
            for (int j = 0; j < GrigliaGenerazioni.COLONNE; j++) {
                if (statoCellule[i][j]) sb.append('1');
                else sb.append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }


}
