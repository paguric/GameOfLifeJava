public enum Configurazione {
    CELLULA(new boolean[][] {{true}}, null),
    BLOCCO(new boolean[][] {{true,true},{true,true}}, Tipo.STILL_LIFE),
    BLINKER(new boolean[][] {{true},{true},{true}}, Tipo.STILL_LIFE);
    private final boolean[][] generazione;
    private final int righe;
    private final int colonne;
    private final Tipo tipo;

    Configurazione(boolean[][] generazione, Tipo tipo) {
        this.generazione = generazione;
        this.righe = generazione.length;
        this.colonne = generazione[0].length;
        this.tipo = tipo;
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

    @Override
    public String toString() {
        String name = name().toLowerCase().replace('_', ' ');
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String[] valuesToString(Configurazione[] configurazioneSpeciale) {
        Configurazione[] configurazioni = values();
        if (configurazioneSpeciale != null) configurazioni = configurazioneSpeciale;
        String[] valuesToString = new String[configurazioni.length];
        for (int i = 0; i < configurazioni.length; i++) {
            valuesToString[i] = configurazioni[i].toString();
        }
        return valuesToString;
    }

//    public static String[] valuesToStringOrdinatiSecondoTipo() {
//        Configurazione[] configurazioni = values();
//        for (int j = Tipo.values().length -1; j >= 0; j--) {
//            for (int i = 0; i < configurazioni.length; i++) {
//                if (configurazioni[i].getTipo() == Tipo.values()[j]) {
//                    Utility.spostaElementoInTesta(i, configurazioni);
//                }
//            }
//        }
//        return valuesToString(configurazioni);
//    }

    public Tipo getTipo() {
        return tipo;
    }
}
