package br.unifil.dc.lab2;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;

/**
 * Write a description of class ListaGravada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaGravada implements Transparencia
{
    /**
     * Constructor for objects of class ListaGravada
     */
    public ListaGravada(List<Integer> lista, List<Color> coresIndices, String nome) {
        this.lista = lista;
        this.nome = nome;
        this.coresIndices = coresIndices;

    }

    /**
     * Metodo que define o grafico, recebe um picel e a tela, com isso ele define a largura das colunas
     * a largura dos espaçamentos entre as colunas e a posiçao das coluna na tela e as dimensoes.
     *
     * @param pincel objeto para pintar na tela
     * @param contexto janela
     */
    public void pintar(Graphics2D pincel, JPanel contexto) {
        Dimension dim = contexto.getSize();
        pincel.setFont (new Font ("Monospaced", Font.PLAIN, 14));// troca a largura do picel para maior.
        pincel.drawString(this.nome, (int)(dim.width/18),(int)(dim.height/14));//deixa  a String no canto superior esquerdo da tela e responsivo.
        List<Integer> elementos = this.lista;
        List<Color> cores = this.coresIndices;
        pincel.setStroke(new BasicStroke(6));
        Integer[] valores = elementos.toArray(new Integer[elementos.size()]);
        List<Integer> aux = vetorProporcional(elementos);//calculo dos elementos proporcional do vetor
        final float LARGURA_PADRAO = 800;
        final float ALTURA_PADRAO = 600;
        float proporcaoLargura = dim.width/LARGURA_PADRAO;
        float proporcaoAltura = dim.height/ALTURA_PADRAO;
        int n = 0;//auxiliar para o espacemento entre as colunas
        int larguraColuna = (larguraColunas(aux,contexto));// largura das colunas
        int posicaoEixoY =(int) (espacamento(aux, contexto)/2);
        int k = (int)((proporcaoLargura*8));//auxiliar para a altura das coluna
        for(int i = 0; i < aux.size(); i++){
            int posicaoEixoX = (int)((400*proporcaoAltura - (aux.get(i)))) ;//posiçao das coluna no eixo X.
            pincel.setStroke(new BasicStroke(6));
            pincel.setColor(Color.BLACK);
            pincel.drawRect( posicaoEixoY, posicaoEixoX, larguraColuna, (int)(aux.get(i)));// desenha o coluna
            pincel.setColor(cores.get(i));
            if(cores.get(i) == null){
                pincel.setColor(Color.BLUE);
            }
            pincel.fillRect( posicaoEixoY, posicaoEixoX, larguraColuna, (int)(aux.get(i)));// pinta a coluna
            pincel.setColor(Color.black);
            pincel.drawString("" + valores[i], posicaoEixoY,420*proporcaoAltura);// String auxiliar para as informaçoes de varredura
            n=n+50;
            posicaoEixoY = (int)((posicaoEixoY + larguraColuna+k));// auxiliar para o espaçamento
        }

    }

    /**
     * Define a largura das colunas de acordo com o tamanho da tela
     * @param vals elementos a ser definido a largura
     * @param contexto tamanho da tela
     * @return largura das colunas do vetor vals
     */
    public int larguraColunas(List<Integer> vals,JPanel contexto){
        Dimension dim = contexto.getSize();
        final float LARGURA_PADRAO = 800;
        float proporcaoLargura = dim.width/LARGURA_PADRAO;
        int tamanhoLista = vals.size();
        int larguraR = (int)((proporcaoLargura*150/tamanhoLista));// proporçao da largura das coluna conforme o tamanho da tela
        return larguraR;
    }

    /**
     * Define o espaçamento entre as coluans de acordo com o tamanho da tela
     * @param vals elementos a ser definido o espamento entre eles
     * @param contexto tamanho da tela
     * @return espaçamento entre as colunas do vetor vals
     */
    public int espacamento(List<Integer> vals,JPanel contexto){
        Dimension dim = contexto.getSize();
        final float LARGURA_PADRAO = 800;
        final float ALTURA_PADRAO = 600;
        float proporcaoLargura = (dim.width/LARGURA_PADRAO);
        float proporcaoAltura = dim.height/ALTURA_PADRAO;
        int tamanhoLista = vals.size();
        final int rW = (int) (dim.width);
        int espaco = (int)((50*rW/800)/proporcaoLargura);// proporcao do espaço entre as colunas
        return espaco;
    }

    /**define a proporcionalidade da altura das colunas de acordo com o trabalho
     * altura maxima e 300, entao abaixo o algoritmo
     * usa uma regra de 3 para definir a proporcao
     *
     * @param vals valores para serem proporcionalisados
     * @return a proporcao dos valores de acordo com o trabalho
     */
    public List<Integer> vetorProporcional(List<Integer> vals){
        int maior = vals.get(0);
        for(int i = 0; i < vals.size()-1; i++){
            if (maior < vals.get(i+1)) {
                maior = vals.get(i+1);
            }
        }
        List<Integer> aux = vals;
        int r =0;
        for(int i = 0; i < vals.size(); i++){
            r = ((300*vals.get(i))/maior); // calcula a proporcao do vetor
                aux.set(i, r);
        }
        return aux;
    }
    
    private List<Integer> lista;
    private List<Color> coresIndices;
    private String nome;
}
