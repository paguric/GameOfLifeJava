public enum Configurazione {
    CELLULA(new boolean[][] {{true}}, null),
    BLOCCO(new boolean[][] {{true,true},{true,true}}, Tipo.STILL_LIFE),
    BLINKER(new boolean[][] {{true},{true},{true}}, Tipo.STILL_LIFE),
    GOSPER_GLIDER_GUN(null, Tipo.GUN);
    private final boolean[][] generazione;
    private final int righe;
    private final int colonne;
    private final Tipo tipo;

    Configurazione(boolean[][] generazione, Tipo tipo) {
        if (generazione == null) {
            String nome = this.toString();
            switch (nome) {
                case "Gosper glider gun" -> {

                    // Dimensioni della griglia per la Gosper glider gun
                    int righe = 36;
                    int colonne = 39;

                    // Gosper glider gun
                    int[][] gosperGliderGun = {
                            {24, 0}, {22, 1}, {24, 1}, {12, 2}, {13, 2}, {20, 2}, {21, 2}, {34, 2}, {35, 2},
                            {11, 3}, {15, 3}, {20, 3}, {21, 3}, {34, 3}, {35, 3}, {0, 4}, {1, 4}, {10, 4},
                            {16, 4}, {20, 4}, {21, 4}, {0, 5}, {1, 5}, {10, 5}, {14, 5}, {16, 5}, {17, 5},
                            {22, 5}, {24, 5}, {10, 6}, {16, 6}, {24, 6}, {11, 7}, {15, 7}, {12, 8}, {13, 8}
                    };

                    // Inizializzazione della matrice
                    generazione = new boolean[righe][colonne];

                    // Impostazione della Gosper glider gun nella matrice
                    for (int[] cella : gosperGliderGun) {
                        generazione[cella[1]][cella[0]] = true;
                    }
                }
            }
        }

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
