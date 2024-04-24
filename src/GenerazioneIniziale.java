public enum GenerazioneIniziale {
    BLOCCO(new boolean[][] {{true,true},{true,true}}),
    BLINKER(new boolean[][] {{true},{true},{true}});
    private final boolean[][] generazione;
    private final int righe;
    private final int colonne;

    GenerazioneIniziale(boolean[][] generazione) {
        this.generazione = generazione;
        this.righe = generazione.length;
        this.colonne = generazione[0].length;
    }

    public int getRighe() {
        return this.generazione.length;
    }

    public int getColonne() {
        return this.generazione[0].length;
    }

    public boolean getElemento(int riga, int colonna) {
        if (riga < 0 || riga >= righe || colonna < 0 || colonna >= colonne) return false;
        return this.generazione[riga][colonna];
    }
}
