import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Principal {
    public static void main(String[] args) throws IOException {
        // Criando arquivo para salvar os resultados
        FileWriter arq = new FileWriter("resultados.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        List<Integer> numeroComparacoes = new ArrayList<>();
        List<Long> tempoGasto = new ArrayList<>();

        int numeroElementos = 10000;
        for (int i = 0; i < 10; i++) {
            Random random = new Random();

            ArvoreSBB arvoreBinariaOrdenada = new ArvoreSBB();
            for (int j = 0; j < numeroElementos; j++) {
                arvoreBinariaOrdenada.insere(new Item(j));
            }

            ArvoreSBB arvoreBinariaAleatoria = new ArvoreSBB();
            for (int j = 0; j < numeroElementos; j++) {
                // Gerando números aleatórios entre 0 e numeroElementos - 1
                int numeroAleatorio = random.nextInt(numeroElementos - 1);
                arvoreBinariaAleatoria.insere(new Item(numeroAleatorio));
            }

            // Tempo de pesquisa da árvore ordenada
            Instant starts1 = Instant.now();
            arvoreBinariaOrdenada.pesquisa(new Item(numeroElementos + 50));
            Instant ends1 = Instant.now();

            // Tempo de pesquisa da árvore aleatória
            Instant starts2 = Instant.now();
            arvoreBinariaAleatoria.pesquisa(new Item(numeroElementos + 50));
            Instant ends2 = Instant.now();

            System.out.println("Arvore ordenada");
            System.out.printf("Comparacoes: %d\tTempo Gasto: %d\n", arvoreBinariaOrdenada.getnComparacoes(),
                    Duration.between(starts1, ends1).toNanos());

            System.out.println("Arvore aleatoria");
            System.out.printf("Comparacoes: %d\tTempo Gasto: %d\n", arvoreBinariaAleatoria.getnComparacoes(),
                    Duration.between(starts2, ends2).toNanos());
            System.out.println("\n");

            numeroComparacoes.add(arvoreBinariaOrdenada.getnComparacoes());
            numeroComparacoes.add(arvoreBinariaAleatoria.getnComparacoes());

            tempoGasto.add(Duration.between(starts1, ends1).toMillis());
            tempoGasto.add(Duration.between(starts2, ends2).toMillis());

            numeroElementos += 10000;
        }

        gravarArq.printf("Numero de comparacoes\n");
        for (Integer numeroComparacoe : numeroComparacoes) {
            gravarArq.printf("%d ", numeroComparacoe);
        }
        gravarArq.printf("\n");

        gravarArq.printf("\nTempo gasto\n");
        for (Long aLong : tempoGasto) {
            gravarArq.printf("%d ", aLong);
        }

        arq.close();
    }
}
