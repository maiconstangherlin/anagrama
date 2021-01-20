public class App {

    public static void main(String[] args) {
        String texto = "mom";
        Anagrama anagrama = new Anagrama(texto);

        System.out.printf("Qtd de anagramas: %s \n", anagrama.quantidadeAnagramas());
        System.out.printf("Anagramas: %s", anagrama.getAnagramas());

    }

}
