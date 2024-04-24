import java.util.Arrays;

public class Griglia {
    private static final int RIGHE = 7;
    private static final int COLONNE = 7;
    private boolean[][] cellule;
    public Griglia(GenerazioneIniziale generazioneIniziale) {
        cellule = new boolean[RIGHE][COLONNE];
        this.generazioneIniziale(generazioneIniziale,RIGHE /2,COLONNE /2);
    }

    private void generazioneIniziale(GenerazioneIniziale generazioneIniziale, int riga, int colonna) {
        if (riga < 0 || riga >= RIGHE || colonna < 0 || colonna >= COLONNE) return;

        int i2 = 0;
        int j2 = 0;
        for (int i = riga; i < riga +generazioneIniziale.getRighe(); i++) {
            for (int j = colonna; j < colonna +generazioneIniziale.getColonne(); j++) {
                cellule[i][j] = generazioneIniziale.getElemento(i2, j2++);
            }
            i2++;
            j2 = 0;
        }
    }

    public void prossimaGenerazione() {
        boolean[][] nuoveCellule = new boolean[RIGHE][COLONNE];
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {

            }
        }

        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                nuoveCellule[i][j] = calcolaProssimaGenerazioneCellula(i, j);
            }
        }
        cellule = nuoveCellule;
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
                if (cellule[rigaCorrente][colonnaCorrente]) celluleVive++;
            }

        }

        // isolamento           - meno di due celle adiacenti vive, morte
        // sopravvivenza        - esattamente 2 o 3 celle adiacenti vive, sopravvivenza
        // sovrappopolazione    - piú di 3 celle vive adiacenti, morte
        // riproduzione         - esattamente 3 cellule vive adiacenti, nascita

        if (cellule[riga][colonna]) {
            celluleVive--; // tolgo dal conteggio la cellula stessa
            if (celluleVive < 2) return false;    // isolamento
            if (celluleVive == 2 || celluleVive == 3) return true;       // sopravvivenza
            return false;    // sovrappopolazione
        } else {
            if (celluleVive == 3) return true;    // riproduzione
        }
        return false;
    }


    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                s += cellule[i][j] ? 1 : " ";
            }
            s += "\n";
        }
        return s;
    }

}
