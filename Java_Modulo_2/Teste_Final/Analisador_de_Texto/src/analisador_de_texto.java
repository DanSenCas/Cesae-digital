import java.util.Scanner;

public class analisador_de_texto {
    static Scanner scanner = new Scanner(System.in);
    static String textoOriginal;
    static String textoFormatado;

    static void main(String[] args) {

        System.out.println("=== Analisador de Texto ===");
        System.out.print("Digite o texto para análise: ");
        textoOriginal = scanner.nextLine();

        if(textoOriginal.isEmpty()){
            System.out.println("Nao é valido frazes vazias");
            return;
        }

        System.out.println("\n\n=== Resultados da Análise ===");
        System.out.println("Texto original: " + "\"" + textoOriginal + "\"");
        textoFormatado = formatarTexto();
        System.out.println("Texto formatado: " + "\"" + textoFormatado + "\"" );
        analisePalavra();
        analiseCaracteres();
        pesquisaPalavra();
    }

    static String formatarTexto(){
        String textoModificado;

        textoModificado = textoOriginal.trim(); // tirar espaços do inicio e do fim

        while(textoModificado.contains("  ")){ // se o texto modificado conter ainda espaços duplos
            textoModificado = textoModificado.replace("  ", " "); //enquanto tiver 2 espaços troca por 1
        }
        return textoModificado;
    }

    static void analisePalavra(){
        String semEspaço = textoFormatado.replace(" ",""); //troca espaço por vazio
        int contador = 1; // primeira palavra
        System.out.println("\n1. Análise de Palavras:");

        for (int i = 0; i < textoFormatado.length(); i++) {
            if(textoFormatado.charAt(i) == ' '){
                contador++;  // 1 espaço = 1 palavra
            }
        }
        System.out.println("- Quantidade de palavras: " + contador);

        String[] palavras = textoFormatado.split(" ");

        String longa = palavras[0];

        for (int i = 1; i < contador; i++) {
            if (palavras[i].length()>longa.length()){
                longa = palavras[i];
            }
        }
        System.out.println("- Palavra mais longa: " + longa + " numero de palavras: " + longa.length() );

        String[] palavrasCurta = textoFormatado.split(" ");

        String Curta = palavrasCurta[0];

        for (int i = 1; i < contador; i++) {
            if (palavrasCurta[i].length()<Curta.length()){
                Curta = palavras[i];
            }
        }
        System.out.println("- Palavra mais curta: " + Curta + " numero de palavras: " + Curta.length() );
        System.out.println("- Média de comprimento: " + (semEspaço.length()/contador));
    }

    static void analiseCaracteres(){
        String semEspaço = textoFormatado.replace(" ","");

        System.out.println("\n2. Análise de Caracteres:");
        System.out.println("- Total de caracteres: " + textoFormatado.length());
        System.out.println("- Total de letras: " + semEspaço.length());
        int contadorEspaço = 0;

        for (int i = 0; i < textoFormatado.length(); i++) {
            if (textoFormatado.charAt(i) == ' '){
                contadorEspaço++;
            }
        }
        System.out.println("- Espaços: " + contadorEspaço);
        int maiusculas = 0;

        for (int i = 0; i < textoFormatado.length(); i++) {
            if (textoFormatado.charAt(i) >= 'A' && textoFormatado.charAt(i) <= 'Z' ){
                maiusculas++;
            }
        }
        System.out.println("- Letras maiúsculas: " + maiusculas);
        int minúsculas = 0;

        for (int i = 0; i < textoFormatado.length(); i++) {
            if (textoFormatado.charAt(i) >= 'a' && textoFormatado.charAt(i) <= 'z' ){
                minúsculas++;
            }
        }
        System.out.println("- Letras minúsculas: " + minúsculas);
    }

    static void pesquisaPalavra(){

        System.out.println("\n3. Função de Pesquisa:");
        System.out.print("Digite palavra para procurar: ");
        String pesquisa = scanner.nextLine();
        String pesquisaLower = pesquisa.toLowerCase();
        String textoFormatadoLower = textoFormatado.toLowerCase();

        boolean encontrado = false; // nao encontrado

        String[] palavras = textoFormatadoLower.split(" ");

        for (int i = 0; i < palavras.length; i++) {
            if(palavras[i].equals(pesquisaLower)){ // palavra equals ao que estiver dentro de parentises
                if(!encontrado){
                    System.out.println("Encontrado \"" + pesquisa + "\" (ignorando maiúsc./minúsc.):");
                    encontrado = true;
                }
                System.out.println("   - Palavra " + (i+1) + " de " + palavras.length + ": \"" + palavras[i] + "\""); // print se encontrar igual
            }
        }

        if (!encontrado){ // se mantiver false entao vai dizer que nao foi encontrado nada
            System.out.println("Nao foi encontrado nenhuma correspondencia para a palavra " + pesquisa);
        }
    }
}
