public class ArvoreBinaria {
    public static double nComparacoes = 0;
    private No raiz;

    /**
     * Inicializa a arvore binária com a raiz nula
     */
    public ArvoreBinaria() {
        this.raiz = null;
    }

    public static void main(String[] args) {
        // gerar árvores a partir de n elementos ORDENADOS, com n variando de 1.000 até 9.000, com intervalo de 1.000.
        ArvoreBinaria arvore1000 = new ArvoreBinaria();

        System.out.println("Arvore de 1000 elementos:");
        for (int i = 0; i < 1000; i++) {
            arvore1000.raiz = arvore1000.insere(new Item(i), arvore1000.raiz);
        }
        System.out.println("Pesquisando o elemento 1000");
        arvore1000.pesquisa(new Item(1000), arvore1000.raiz);
        System.out.println("Foram realizadas " + nComparacoes + " comparacoes");
    }

    /**
     * @param reg o registro a ser inserido na árvore
     * @param p   o nó(raiz) onde o registro será inserido
     * @return retorna a árvore alterada
     */
    private No insere(Item reg, No p) {
        // se o nó p é nulo, o registro é inserido na raiz
        if (p == null) {
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
        } else if (reg.compara(p.reg) < 0) { // se o registro for menor que o nó, o registro é inserido na subárvore esquerda
            p.esq = insere(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) { // se o registro for maior que o nó, o registro é inserido na subárvore direita
            p.dir = insere(reg, p.dir);
        } else { // se o registro já existe, então nada é feito
            System.out.println("Erro: registro já existente");
        }
        return p;
    }

    /**
     * @param reg o item a ser pesquisado
     * @param p   a árvore a ser percorrida
     * @return retorna o registro encontrado
     */
    private Item pesquisa(Item reg, No p) {
        nComparacoes++;
        if (p == null) { // se a árvore for nula
            return null;
        } else if (reg.compara(p.reg) < 0) { // se o registro for menor que o nó, o registro é pesquisado na subárvore esquerda
            return pesquisa(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) { // se o registro for maior que o nó, o registro é pesquisado na subárvore direita
            return pesquisa(reg, p.dir);
        } else {
            return p.reg; // se o registro for igual ao nó, o registro é retornado
        }
    }

    private static class No {
        Item reg;
        No esq, dir;
    }
}
