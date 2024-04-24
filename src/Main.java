public class Main {
    public static void main(String[] args) {
        GenerazioneIniziale generazioneIniziale = GenerazioneIniziale.BLINKER;
        Griglia grigliaTest = new Griglia(generazioneIniziale);
        System.out.println(grigliaTest);

        grigliaTest.prossimaGenerazione();
        System.out.println(grigliaTest);
        grigliaTest.prossimaGenerazione();
        System.out.println(grigliaTest);
        grigliaTest.prossimaGenerazione();
        System.out.println(grigliaTest);
        grigliaTest.prossimaGenerazione();
        System.out.println(grigliaTest);
    }
}
