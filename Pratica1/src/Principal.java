import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Principal {
    public static void main(String[] args) throws IOException {
        // Criando arquivo para salvar os resultados
        FileWriter arq = new FileWriter("resultados.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        Random random = new Random();
        int numeroElementos = 1000;
        for (int i = 0; i < 9; i++) {
            ArvoreBinaria arvoreBinariaOrdenada = new ArvoreBinaria();
            for (int j = 0; j < numeroElementos; j++) {
                arvoreBinariaOrdenada.insere(new Item(j));
            }

            ArvoreBinaria arvoreBinariaAleatoria = new ArvoreBinaria();
            for (int j = 0; j < numeroElementos; j++) {
                // Gerando números aleatórios entre 0 e numeroElementos - 1
                int numeroAleatorio = random.nextInt(numeroElementos - 1);
                arvoreBinariaAleatoria.insere(new Item(numeroAleatorio));
            }

            // Tempo de pesquisa da árvore ordenada
            Instant starts1 = Instant.now();
            arvoreBinariaOrdenada.pesquisa(new Item(numeroElementos + 1));
            Instant ends1 = Instant.now();

            // Tempo de pesquisa da árvore aleatória
            Instant starts2 = Instant.now();
            arvoreBinariaAleatoria.pesquisa(new Item(numeroElementos + 1));
            Instant ends2 = Instant.now();

            System.out.println(Duration.between(starts2, ends2).toNanos());
            System.out.println("Número de comparações: " + arvoreBinariaOrdenada.getnComparacoes());

            numeroElementos += 1000;
        }
    }
}
