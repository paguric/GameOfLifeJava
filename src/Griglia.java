import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Griglia extends JFrame implements KeyListener {
    public static final int FRAME_WIDTH = 1200;
    public static final int MENU_WIDTH = 400;
    public static final int FRAME_HEIGHT = 800;
    public static final int RIGHE = 65;
    public static final int COLONNE = 65;
    private final Cellula[][] pannelloCellule = new Cellula[RIGHE][COLONNE];
    public boolean[][] statoCellule = new boolean[RIGHE][COLONNE];
    private final MenuConfigurazioni menuConfigurazioni;
    public Griglia(Configurazione configurazione) {
        super("The Game of Life");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(RIGHE, COLONNE));

        gridPanel.setPreferredSize(new Dimension(FRAME_WIDTH - MENU_WIDTH, FRAME_HEIGHT));

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                pannelloCellule[i][j] = new Cellula(this, i, j);
                gridPanel.add(pannelloCellule[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        menuConfigurazioni = new MenuConfigurazioni();
        add(menuConfigurazioni, BorderLayout.EAST);

        if (configurazione != null)
            this.generaConfigurazione(configurazione,RIGHE /2,COLONNE /2);

        setVisible(true);
    }

    public void generaConfigurazione(Configurazione configurazione, int riga, int colonna) {
        if (riga < 0 || riga >= RIGHE || colonna < 0 || colonna >= COLONNE) return;

        int i2 = 0;
        int j2 = 0;

        for (int i = 0; i < configurazione.getRighe(); i++) {

            int rigaCorrente =
                    riga +i >= RIGHE ? 0 : riga +i;

            for (int j = 0; j < configurazione.getColonne(); j++) {
                int colonnaCorrente =
                        colonna +j >= COLONNE ? 0 : colonna +j;

                statoCellule[rigaCorrente][colonnaCorrente] = configurazione.getElemento(i2, j2++);
            }
            i2++;
            j2 = 0;

        }

        repaint();
    }

    public void prossimaGenerazione() {
        boolean[][] prossimoStatoCellule = new boolean[RIGHE][COLONNE];

        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                prossimoStatoCellule[i][j] = calcolaProssimaGenerazioneCellula(i, j);
            }
        }

        statoCellule = prossimoStatoCellule;

        repaint();
    }
    public boolean calcolaProssimaGenerazioneCellula(final int riga, final int colonna) {
        int celluleVive = 0;

        for (int i = -1; i < 2; i++) {

            int rigaCorrente =
                    riga +i < 0 ? RIGHE -1 :
                            riga +i >= RIGHE ? 0 : riga +i;

            for (int j = -1; j < 2; j++) {   // colonne
                int colonnaCorrente =
                        colonna +j < 0 ? COLONNE -1 :
                                colonna +j >= COLONNE ? 0 : colonna +j;
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

    public MenuConfigurazioni getMenuConfigurazioni() {
        return this.menuConfigurazioni;
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
        //keyReleased = called whenever a button is released
    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//        // Ottieni le coordinate del clic del mouse
//        Point clickPoint = e.getPoint();
//
//        // Itera attraverso i pannelli per trovare il pannello cliccato
//        for (int i = 0; i < RIGHE; i++) {
//            for (int j = 0; j < COLONNE; j++) {
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


}
