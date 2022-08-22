public class ArvoreBinaria {
    private int nComparacoes = 0;
    private No raiz;

    /**
     * Inicializa a arvore binária com a raiz nula
     */
    public ArvoreBinaria() {
        this.raiz = null;
    }

    public int getnComparacoes() {
        return nComparacoes - 1;
    }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz);
    }

    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz);
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
            return p;
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
