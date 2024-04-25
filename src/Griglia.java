import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Griglia extends JFrame implements KeyListener, MouseListener {
    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 800;
    public static final int RIGHE = 77;
    public static final int COLONNE = 77;
    private Cellula[][] cellule = new Cellula[RIGHE][COLONNE];
    private final MenuConfigurazioni menuConfigurazioni;
    public Griglia(Configurazione configurazione) {
        super("The Game of Life");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Imposta un layout a BorderLayout per la finestra principale
        setLayout(new BorderLayout());

        // Crea la griglia e aggiungila al centro della finestra
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(RIGHE, COLONNE));

        // Aggiungi i componenti della griglia al pannello della griglia
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                cellule[i][j] = new Cellula(this);
                cellule[i][j].setPreferredSize(new Dimension(FRAME_WIDTH /COLONNE,FRAME_HEIGHT /RIGHE));
                gridPanel.add(cellule[i][j]);
                // pack();
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        menuConfigurazioni = new MenuConfigurazioni();
        add(menuConfigurazioni, BorderLayout.EAST);

        if (configurazione != null)
            this.generaConfigurazione(configurazione,RIGHE /2,COLONNE /2);

        this.addKeyListener(this);
        addMouseListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow(true);


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

                cellule[rigaCorrente][colonnaCorrente].setStato(configurazione.getElemento(i2, j2++));
            }
            i2++;
            j2 = 0;

        }


        repaint();
    }

//    public void generaConfigurazione(JPanel cellula, Configurazione configurazione) {
//        if (cellula == null) return;
//        int riga = 0;
//        int colonna = 0;
//
//        // individua cellula
//        for (int i = 0; i < RIGHE; i++) {
//            for (int j = 0; j < COLONNE; j++) {
//                if (cellule[i][j] == cellula) {
//                    riga = i;
//                    colonna = j;
//                    break;
//                }
//            }
//        }
//        generaConfigurazione(configurazione, riga, colonna);
//    }

    public void prossimaGenerazione() {
        Cellula[][] nuoveCellule = new Cellula[RIGHE][COLONNE];

        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                nuoveCellule[i][j] = new Cellula(this);
            }
        }

        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                nuoveCellule[i][j].setStato(calcolaProssimaGenerazioneCellula(i, j));
            }
        }

        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                cellule[i][j].setStato(nuoveCellule[i][j].getStato());
            }
        }
        // cellule = nuoveCellule; non è possibile per via di swing

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
                if (cellule[rigaCorrente][colonnaCorrente].getStato()) celluleVive++;
            }

        }

        // isolamento           - meno di due celle adiacenti vive, morte
        // sopravvivenza        - esattamente 2 o 3 celle adiacenti vive, sopravvivenza
        // sovrappopolazione    - piú di 3 celle vive adiacenti, morte
        // riproduzione         - esattamente 3 cellule vive adiacenti, nascita

        boolean prossimoStato = false;
        if (cellule[riga][colonna].getStato()) {
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
    public String toString() {
        String s = "";
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                s += cellule[i][j].getStato() ? 1 : " ";
            }
            s += "\n";
        }
        return s;
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

    @Override
    public void mouseClicked(MouseEvent e) {

        // Ottieni le coordinate del clic del mouse
        Point clickPoint = e.getPoint();

        // Itera attraverso i pannelli per trovare il pannello cliccato
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                Component component = cellule[i][j];
                Rectangle bounds = component.getBounds();
                if (bounds.contains(clickPoint)) {
                    // System.out.println("Hai cliccato il pannello alla riga " + (i-1) + " e colonna " + j);  // TEST
                    String nomeConfigurazione = this.getMenuConfigurazioni().getSelectedOption();
                    if (nomeConfigurazione != null) {
                        Configurazione configurazione = Configurazione.valueOf(nomeConfigurazione.toUpperCase().replace(' ', '_'));
                        this.generaConfigurazione(configurazione, (i -1), j);
                    }
                    this.requestFocus();
                    return;
                }
            }
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
