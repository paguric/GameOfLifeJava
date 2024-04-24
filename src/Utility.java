public class Utility {
    public static void spostaElementoInTesta(int indice, Object[] array) {
        if (indice >= 0 && indice < array.length) {
            Object elemento = array[indice]; // Salva l'elemento da spostare
            // Sposta gli elementi verso destra fino all'indice specificato
            for (int i = indice; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = elemento; // Sposta l'elemento salvato in testa
        } else {
            System.out.println("Indice non valido.");
        }
    }
}