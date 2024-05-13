import java.util.Arrays;

public enum Configuration {
    CELLULA(1, 1, "o!",null),
    BLOCCO(2,2, "2o$2o!", Tipo.STILL_LIFE),
    BLINKER(1, 3, "o$o$o!", Tipo.STILL_LIFE),
    GLIDER(3, 3, "bob$2bo$3o!", Tipo.STILL_LIFE),
    PENTADECATHLON(3, 10, "bo$bo$obo$bo$bo$bo$bo$obo$bo$bo!", Tipo.STILL_LIFE);

    private final int x;

    private final int y;

    private final String runLengthEncoding;

    private final byte[][] configuration;

    private final Tipo tipo;

    Configuration(int x, int y, String runLengthEncoding, Tipo tipo) {
        this.x = x;
        this.y = y;
        this.runLengthEncoding = runLengthEncoding;
        configuration = computeConfiguration();
        this.tipo = tipo;

    }

    private byte[][] computeConfiguration() {
        if (runLengthEncoding == null) {
            return null;
        }

        byte[][] configuration = new byte[y][x];
        String decodedRLE = runLengthDecode();
        int configurationCounter = 0;

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ((decodedRLE.charAt(configurationCounter) == 'o')) {
                    configuration[i][j] += 0x01;
                }

                configurationCounter++;
            }
        }

        return configuration;
    }

    private String runLengthDecode() {
        StringBuilder sb = new StringBuilder();

        int currentLineLength = 0;

        for (int i = 0; i < runLengthEncoding.length(); i++) {
            char c = runLengthEncoding.charAt(i);

            if (c == 'b' || c == 'o') {
                sb.append(c);
                currentLineLength++;

            } else if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);

                while (Character.isDigit(runLengthEncoding.charAt(i + 1))) {
                    num = num * 10 + Character.getNumericValue(runLengthEncoding.charAt(i + 1));    // * 10 to shift the number to the left (e.g. 1 -> 10)
                    i++;
                }

                for (int j = 0; j < num; j++) {
                    sb.append(runLengthEncoding.charAt(i + 1));
                    currentLineLength++;
                }

                i++;
            } else {
                if (currentLineLength < x) {
//                    sb.append(String.valueOf(runLengthEncoding.charAt(i - 1)).repeat(Math.max(0, x - currentLineLength)));
                    sb.append(String.valueOf('b').repeat(Math.max(0, x - currentLineLength)));
                }
                currentLineLength = 0;
            }

        }

        return sb.toString();

    }

    public byte[][] getConfiguration() {
        return configuration;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        String name = name().toLowerCase().replace('_', ' ');
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String[] valuesToString(Configuration[] configurationSpeciale) {
        Configuration[] configurazioni = values();
        if (configurationSpeciale != null) configurazioni = configurationSpeciale;
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
