import java.util.Arrays;
import java.util.Collections;

public enum Configurazione {
    CELLULA(1, 1, "o!",null),
    BLOCCO(2,2, "2o$2o!", Tipo.STILL_LIFE),
    BLINKER(1, 3, "o$o$o!", Tipo.STILL_LIFE),
    SCHICK_ENGINE(9,15,"3o3b3o$o2bobo2bo$o7bo$o7bo$bobobobo2$4bo$3b3o$2b2ob2o$3b3o$3b3o$3b3o$3bobo$3bobo$4bo!", Tipo.SPACESHIP),
    CLOVERLEAF_INTERCHANGE(13,13,"4bo3bo$3bobobobo$3bobobobo$b2o2bobo2b2o$o4bobo4bo$b4o3b4o2$b4o3b4o$o4bobo4bo$b2o2bobo2b2o$3bobobobo$3bobobobo$4bo3bo!", Tipo.STILL_LIFE),
    LINEAR_GROWTH_TEST(16,16,"boboobbobobboooo$boobbboboooooooo$oobobobbbboboobb$booobobobboboobo$oboobobobboobbbb$oobooobbbboboboo$obobobooobbbobbo$oboobbooooobbobb$ooooooobobbobobb$booboooooooooobo$ooooobbbobboobbo$ooboooooobobobob$boboboobbbobbbbo$oobboooboobobobb$bbbobbooobboobob$bbooobooobbooboo!", null),
    GOSPER_GLIDER_GUN(47,14,  "16bo30b$16bobo16bo11b$16b2o17bobo9b$obo10bo21b2o10b$b2o11b2o31b$bo11b2o32b3$10b2o20b2o13b$11b2o19bobo9b3o$10bo21bo11bo2b$27bo17bob$27b2o18b$26bobo!", Tipo.GUN);

    private final int x;

    private final int y;

    private final String runLengthEncoding;

    private final boolean[][] configurationMatrix;

    private final Tipo tipo;

    Configurazione(int x, int y, String runLengthEncoding, Tipo tipo) {
        this.x = x;
        this.y = y;
        this.runLengthEncoding = runLengthEncoding;
        configurationMatrix = computeConfigurationMatrix();
        this.tipo = tipo;

    }

    private boolean[][] computeConfigurationMatrix() {
        if (runLengthEncoding == null) {
            return null;
        }

        boolean[][] configurationMatrix = new boolean[y][x];
        String decodedRLE = runLengthDecode();

        int row = 0;
        int col = 0;

        System.out.println(decodedRLE);
        for (int i = 0; i < decodedRLE.length() - 1; i++) {
            char c = decodedRLE.charAt(i);

            if (c == '$') {
                row++;
                col = 0;
                continue;

            }

            configurationMatrix[row][col++] = c == 'o';

        }

        return configurationMatrix;

    }

    private String runLengthDecode() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < runLengthEncoding.length(); i++) {
            char c = runLengthEncoding.charAt(i);

            if (c == 'b' || c == 'o') {
                sb.append(c);

            } else if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);

                while (Character.isDigit(runLengthEncoding.charAt(i + 1))) {
                    num = num * 10 + Character.getNumericValue(runLengthEncoding.charAt(i + 1));    // * 10 to shift the number to the left (e.g. 1 -> 10)
                    i++;
                }

                for (int j = 0; j < num; j++) {
                    sb.append(runLengthEncoding.charAt(i + 1));
                }

                i++;
            } else {
                sb.append(c);
            }

        }

        return sb.toString();

    }

    public boolean[][] getConfigurationMatrix() {
        return configurationMatrix;
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
