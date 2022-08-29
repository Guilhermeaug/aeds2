import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Principal {
    public static void main(String[] args) throws IOException {
        // Criando arquivo para salvar os resultados
        FileWriter arq = new FileWriter("resultados.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        List<Integer> numeroComparacoesOrdenada = new ArrayList<>(10);
        List<Long> tempoGastoOrdenada = new ArrayList<>(10);

        List<Integer> numeroComparacoesAleatoria = new ArrayList<>(10);
        List<Long> tempoGastoAleatoria = new ArrayList<>(10);

        int LIMITE_GERADOR = 1000000;
        for (int i = 1000; i <= 9000; i += 1000) {
            Random random = new Random();

            ArvoreBinaria arvoreBinariaOrdenada = new ArvoreBinaria();
            for (int j = 0; j < i; j++) {
                arvoreBinariaOrdenada.insere(new Item(j));
            }

            ArvoreBinaria arvoreBinariaAleatoria = new ArvoreBinaria();
            for (int j = 0; j < i; j++) {
                // Gerando números aleatórios entre 0 e numeroElementos - 1
                int numeroAleatorio = random.nextInt(LIMITE_GERADOR - 1);
                arvoreBinariaAleatoria.insere(new Item(numeroAleatorio));
            }

            // Tempo de pesquisa da árvore ordenada
            long starts1 = System.nanoTime();
            Item item1 = arvoreBinariaOrdenada.pesquisa(new Item(LIMITE_GERADOR + 50));
            if (item1 == null) {
                System.out.println("Item não encontrado");
            }
            long ends1 = System.nanoTime();

            // Tempo de pesquisa da árvore aleatória
            long starts2 = System.nanoTime();
            Item item2 = arvoreBinariaAleatoria.pesquisa(new Item(LIMITE_GERADOR + 50));
            if (item2 == null) {
                System.out.println("Item não encontrado");
            }
            long ends2 = System.nanoTime();

            numeroComparacoesOrdenada.add(arvoreBinariaOrdenada.getnComparacoes());
            numeroComparacoesAleatoria.add(arvoreBinariaAleatoria.getnComparacoes());

            tempoGastoOrdenada.add(ends1 - starts1);
            tempoGastoAleatoria.add(ends2 - starts2);
        }

        gravarArq.printf("Número de comparações da árvore ordenada:\n");
        numeroComparacoesOrdenada.forEach((n) -> {
            gravarArq.printf("%d ", n);
        });

        gravarArq.printf("\n\nNúmero de comparações da árvore aleatória:\n");
        numeroComparacoesAleatoria.forEach((n) -> {
            gravarArq.printf("%d ", n);
        });

        gravarArq.printf("\n\nTempo gasto da árvore ordenada:\n");
        tempoGastoOrdenada.forEach((n) -> {
            gravarArq.printf("%d ", n);
        });

        gravarArq.printf("\n\nTempo gasto da árvore aleatória:\n");
        tempoGastoAleatoria.forEach((n) -> {
            gravarArq.printf("%d ", n);
        });

        arq.close();
    }
}
