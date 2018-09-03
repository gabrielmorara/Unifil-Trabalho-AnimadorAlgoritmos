package br.unifil.dc.lab2;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Write a description of class Gravador here.
 *
 * @author (Gabriel Morara Ribeiro)
 * @version (a version number or a date)
 */
public class Gravador
{
    /**
     *
     */
    public Gravador() {
        this.seqGravacoes = new ArrayList<Transparencia>();
    }

    /**Copia a lista passada como parametro, e atualiza no Jpanel as mudança
     *
     * @param lista parametro a ser gravado na tela
     * @param nome String a ser printada na tela
     */
    public void gravarLista(List<Integer> lista, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }

    /**Grava uma posicao da lista e o destaca na cor amarela
     *
     * @param lista lista a ser verificada
     * @param i indice a ser destacada de amarelo
     * @param nome parametro a ser colocado na tela
     */
    public void gravarIndiceDestacado(List<Integer> lista, int i, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }

    /**Troca as cor para cinza dos incice que sao comparados .
     *
     * @param lista
     * @param i idice a ser comparado com j.
     * @param j indice a ser compado com i.
     */
    public void gravarComparacaoSimples(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.GRAY);
        cores.set(j, Color.GRAY);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }
    /**Troca as cor para Verde dos incice que sao comparados
     * Utilizado para Ordenaçao por quickSrot
     *
     * @param lista
     * @param i idice a ser comparado com j.
     * @param j indice a ser compado com i.
     */
    public void gravarPorquick(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.GREEN);
        cores.set(j, Color.GREEN);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Lado Esquerdo / Lado Direito");
        seqGravacoes.add(gravacao);
    }

    /**Destaca os indice que serao trocados na cor amarela.
     *
     * @param lista lista que contem o indice a ser trocado
     * @param i indice a ser trocado por j.
     * @param j indice a ser trocado por i.
     */
    public void gravarPosTrocas(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        cores.set(j, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Pós-troca");
        seqGravacoes.add(gravacao);
    }

    /**Troca a cor para Verde e vermelho dos indice comparados
     * utilizado para gravar as subdivisao das listas
     *
     * @param lista
     * @param 'i' idice a ser comparado com j(Final).
     * @param 'j' indice a ser compado com i(inicio).
     */
    public void gravarPorBuscaBinaria(List<Integer> lista, int inicio, int fim){
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(inicio, Color.GREEN);
        cores.set(fim, Color.RED);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Posiçao inicial: verde e Final: Vermelha");
        seqGravacoes.add(gravacao);
    }
    /**Troca as cor para cinza dos incice que sao comparados .
     * utilizado para gravar as subdivisao das listas
     *
     * @param lista
     * @param i idice a ser comparado com j.
     * @param j indice a ser compado com i.
     */
    public void gravarSubdivisao(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.GREEN);
        cores.set(j-1, Color.RED);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Subdivisao");
        seqGravacoes.add(gravacao);
    }

    /**
     * pega o filme atual (quadro)
     * @return a gravaçao atual
     */
    public ListIterator<Transparencia> getFilme() {
        return seqGravacoes.listIterator();
    }

    /**
     *
     * @param numElems valor a ser passado como tamanho da lista de cores
     * @return a lista de cores
     */
    private static List<Color> novaListaColors(int numElems) {
        ArrayList<Color> lista = new ArrayList<>(numElems);
        for (; numElems > 0; numElems--) lista.add(null);
        return lista;
    }

    /**
     *metodo de seqGgravaçoes
     */
    private List<Transparencia> seqGravacoes;
}
