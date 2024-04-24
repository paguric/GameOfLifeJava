public class Griglia {
    private static final int RIGHE = 21;
    private static final int COLONNE = 21;
    // isolamento           - meno di due celle adiacenti, morte
    // sopravvivenza        - esattamente 2 o 3 celle adiacenti, sopravvive
    // sovrappopolazione    -
    // riproduzione
    private static final int ISOLAMENTO = 2; // cellula con meno di due cellule adiacenti muore
    private boolean[][] cellule;
    public Griglia() {

    }

    public void prossimaGenerazione() {


        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; i++) {
                prossimoStato(i, j);
            }
        }


    }

    public void prossimoStato(int riga, int colonna) {
        // salva coordinate "in alto a sinistra"
        int rigaInizializzazione = riga -1 < 0 ? RIGHE -1 : riga -1;
        int colonnaInizializzazione = colonna -1 < 0 ? COLONNE -1 : colonna -1;

        for (int i = 0; i < 3; i++) {
            int rigaVicino = riga -1 +i < 0 ? RIGHE -1 : riga -1 +i;
            for (int j = 0; j < 3; j++) {   // colonne

            }
        }


    }

}
