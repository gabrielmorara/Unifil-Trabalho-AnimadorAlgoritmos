package br.unifil.dc.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Write a description of class AlgoritmosAnimados here.
 *
 * @author (Gabriel Morara Ribeiro)
 * @version (a version number or a date)
 */
public class AlgoritmosAnimados {
    public static Gravador listaEstatica(List<Integer> valores) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Valores da lista imutável");
        return anim;
    }

    /**
     * Busca uma chave na lista valores
     *
     * @param valores lista a ser verificado se tem a chave
     * @param chave   numero a ser verificado se tem em valores
     * @return retorna um objeto da classe gravador de lista,
     * com uma String "Chave encontrado" caso tenha a chave
     */
    public static Gravador buscaSequencial(List<Integer> valores, int chave) {
        Gravador anim = new Gravador();//instancia um novo gravador
        anim.gravarLista(valores, "Inicio de busca sequencial");//chama o metodo gravarLista e passa como paramentos a lista de valores e uma String, para gravar a lista inicial .
        int i = 0;// indice a ser passado para a classe gravar como auxiliar
        anim.gravarIndiceDestacado(valores, i, "Busca sequencial");//na lista valores destacada de amarelo o indice i e escreve na String "Busca Sequancial".
        while (i < valores.size() && valores.get(i) != chave) {//percore ate achar a chave ou acabar o vetor.
            i++;//indice ++, para ir percorendo a lista
            anim.gravarIndiceDestacado(valores, i, "Busca sequencial");//destaca de amarelo o indici i, na lista de valores
        }// fim do while
        if (i < valores.size()) {//verifica se encontrou a chave na lsta de valores
            anim.gravarIndiceDestacado(valores, i, "Chave encontrada");// se sim destada esse indice.
        } else {
            anim.gravarLista(valores, "Chave não encontrada");//senao  a String aparece no Jpanel,"chave não encontrada"
        }
        return anim;// retorna o gravador para a classe AnimadorAlgoritmo para a interface grafica fazer o trabalho
    }

    /**
     * Busca em vals uma chave
     *
     * @param vals  lista de valores inteiros
     * @param chave numero inteiro a ser verificado se esta contido em vals.
     * @return retorna um objeto da classe gravador de lista,
     * com uma String "Chave encontrado" caso tenha a chave
     */
    public static Gravador buscaBinaria(List<Integer> vals, int chave) {
        Gravador anim = new Gravador();//instancia um novo gravador
        ordenarPorBolha(vals);
        anim.gravarLista(vals, "Inicio de busca Binaria");
        int meio;
        int inicio, fim;
        inicio = 0;
        fim = vals.size() - 1;
        int resultado = buscaBinariaInt(vals, chave);
        anim.gravarPorBuscaBinaria(vals, inicio, fim);
        int i = 0;
        while (inicio <= fim && vals.size() > i) {
            i++;
            meio = (inicio + fim) / 2;
            anim.gravarPorBuscaBinaria(vals, inicio, fim);
            if (vals.get(meio) == chave) {
                break;
            }
            if (vals.get(meio) < chave) {
                inicio = meio + 1;
            }
            if (vals.get(meio) > chave) {
                fim = meio - 1;
            }
        }
        if (resultado >= 0) {
            anim.gravarIndiceDestacado(vals, resultado, "Indice Encontrado em Amarelo");
        } else {
            anim.gravarLista(vals, "Chave não encontrada");
        }

        return anim;
    }

    /**
     * metodo auxiliar da BuscaBinaria
     *
     * @param vals  lista de valores inteiros
     * @param chave numero inteiro a ser verificado se enta contido em vals.
     * @return retorno um numero da posicao da chave se estiver em vals,
     * senao retorna -1.
     */
    public static int buscaBinariaInt(List<Integer> vals, int chave) {
        int meio;
        int inicio, fim;
        inicio = 0;
        fim = vals.size() - 1;
        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            if (vals.get(meio) == chave) {
                return meio;
            } else if (vals.get(meio) < chave) inicio = meio + 1;
            else if (vals.get(meio) > chave) fim = meio - 1;
        }
        return -1;
    }

    /**
     * Ordena uma lista de inteiros usando o método da bolha.
     * Usando o objeto da classe Gravador para gravar as alteraçoes(ordenaçoes),
     * na lista
     *
     * @param vals lista de inteiros, não nula.
     * @return retorna a lista ordenada de forma crescente
     */
    public static Gravador ordenarPorBolha(List<Integer> vals) {
        Gravador anim = new Gravador();
        anim.gravarLista(vals, "Disposição inicial");
        assert vals != null : "Lista não pode ser nula.";
        boolean houveTroca;
        do {
            houveTroca = false;
            // Varredura
            for (int i = 0; i < vals.size() - 1; i++) {
                if (vals.get(i) > vals.get(i + 1)) {
                    anim.gravarComparacaoSimples(vals, i, i + 1);
                    trocar(vals, i, i + 1);
                    anim.gravarPosTrocas(vals, i, i + 1);
                    houveTroca = true;
                }
            }
        } while (houveTroca);
        anim.gravarLista(vals, "Disposição Final");
        return anim;
    }

    /**
     * Ordena uma lista de inteiros usando o método de Seleçao.
     * Usando o objeto da classe Gravador para gravar as alteraçoes(ordenaçoes),
     * na lista
     *
     * @param vals lista de inteiros, não nula.
     * @return retorna a lista ordenada de forma crescente
     */
    public static Gravador ordenarPorSelecao(List<Integer> vals) {
        Gravador anim = new Gravador();
        anim.gravarLista(vals, "Disposição inicial");
        assert vals != null : "Lista não pode ser nula.";
        boolean houveTroca;
        for (int i = 0; i < vals.size() - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vals.size(); j++) {
                anim.gravarComparacaoSimples(vals, menor, j);
                if (vals.get(menor) > vals.get(j)) {
                    anim.gravarIndiceDestacado(vals, j, "Menor indice atual");
                    menor = j;
                }
            }
            trocar(vals, i, menor);
            anim.gravarPosTrocas(vals, i, menor);
        }
        anim.gravarLista(vals, "Disposição Final");
        return anim;
    }

    /**
     * Ordena uma lista de inteiros usando o método da Inserçao.
     * Usando o objeto da classe Gravador para gravar as alteraçoes(ordenaçoes),
     * na lista
     *
     * @param vals lista de inteiros, não nula.
     * @return retorna a lista ordenada de forma crescente
     */
    public static Gravador ordenarPorInsercao(List<Integer> vals) {
        Gravador anim = new Gravador();
        anim.gravarLista(vals, "Disposição inicial");
        assert vals != null : "Lista não pode ser nula.";
        for (int i = 1; i < vals.size(); i++) {
            int elemAtual = vals.get(i);
            int posicaoTroca;

            for (posicaoTroca = i; posicaoTroca > 0; posicaoTroca--) {
                anim.gravarComparacaoSimples(vals, posicaoTroca - 1, i);
                if (vals.get(posicaoTroca - 1) < elemAtual) {
                    break;
                }
                vals.set(posicaoTroca, vals.get(posicaoTroca - 1));
                anim.gravarPosTrocas(vals, posicaoTroca, posicaoTroca - 1);
            }
            vals.set(posicaoTroca, elemAtual);
            anim.gravarPosTrocas(vals, posicaoTroca, i);
        }
        anim.gravarLista(vals, "Disposição Final");
        return anim;
    }

    /**
     * Ordena uma lista de inteiros usando o método de Ordenaçao MergeSort(reunião).
     *
     * @param vals lista de inteiros, não nula.
     * @param e    primeiro indice da lista
     * @param d    ultimo indice da lista
     * @return retorna a lista ordenada de forma crescente
     */
    public static Gravador ordenarPorMergesort(List<Integer> vals, int e, int d) {
        //ex : 4,6,7,3,5,1,2,8
        Gravador anim = new Gravador();
        anim.gravarLista(vals, "Disposição inicial");
        //caso base
        final int numElems = d - e;
        if (numElems <= 1) return anim;
        //subdivisao
        final int meio = ((e + d) / 2);
        anim.gravarSubdivisao(vals, e, meio);
        ordenarPorMergesort(vals, e, meio);
        anim.gravarSubdivisao(vals, e, meio);
        anim.gravarSubdivisao(vals, meio, d);
        ordenarPorMergesort(vals, meio, d);
        anim.gravarSubdivisao(vals, meio, d);
        merge(vals, e, d, anim);
        anim.gravarLista(vals, "Disposição Final");
        return anim;
    }

    /**
     * Metodo auxiliar do "ordenarPorMergesort",
     * reuni os elemntos de vals apos as subdivisoes
     *
     * @param vals lista de inteiros, não nula.
     * @param e    primeiro indice da lista
     * @param d    ultimo  indice da lista
     * @param n
     * @return lista ordenada.
     */
    private static Gravador merge(List<Integer> vals, int e, int d, Gravador n) {
        Gravador anim = new Gravador();
        anim = n;
        final int meio = (e + d) / 2;
        int topoEsq = e, topoDir = meio;
        ArrayList<Integer> intercalados = new ArrayList<>(d - e);
        while (topoEsq < meio && topoDir < d) {
            anim.gravarComparacaoSimples(vals, topoEsq, topoDir);
            if (vals.get(topoEsq) < vals.get(topoDir)) {
                intercalados.add(vals.get(topoEsq));
                topoEsq++;
            } else {
                intercalados.add(vals.get(topoDir));
                topoDir++;
            }
        }
        while (topoEsq < meio) {
            intercalados.add(vals.get(topoEsq));
            topoEsq++;
        }
        while (topoDir < d) {
            intercalados.add(vals.get(topoDir));
            topoDir++;
        }

        int j = 0;
        for (int i = e; i < d; i++) {
            vals.set(i, intercalados.get(j));
            anim.gravarPosTrocas(vals, i, j);
            j++;
        }
        return anim;
    }

    /**Ordena um Vetor pelo metodo quickSort
     *
     * @param vals lsita de vals a ser ordenada
     * @return retorna a lista ordenada de forma crescente
     */
    public static Gravador ordenarPorquicksort(List<Integer> vals) {
        Gravador anim = new Gravador();
        anim.gravarLista(vals, "Disposição inicial");
        anim.gravarPorquick(vals, 0, vals.size()-1);
        anim.gravarIndiceDestacado(vals,(vals.size()-1)/2,"Indice em amarelo eo Pivo");
        ordenar(vals, 0, vals.size()-1, anim);
        anim.gravarLista(vals, "Disposição final");
        return anim;
    }

    /**
     * Ordena a parte esquerta e direita do vetor
     * @param vals
     * @param i indice 0 da lista
     * @param j ultimo indice da lista
     * @param anim retorna o vetor parcialmente ordenado
     */
    private static void ordenar(List<Integer> vals, int i, int j, Gravador anim) {   //9,6,3,2,5,8,7,4,1,1,4,7,8,5,2,3,6,9,0
        if (i < j) {
            int pivo = separar(vals, i, j, anim);
            ordenar(vals, i, pivo - 1, anim); // mudando o pivo para esquerda
            ordenar(vals, pivo + 1, j, anim); // mudando o pivo para direita
        }
    }

    /** Separa o vetor, escolhendo o pivo
     *
     * @param vals lista a ser achado o pivo
     * @param i inicio da lista
     * @param j fim da lista
     * @param anim objeto gravador para destacar na janela
     * @return retorna o pivo
     */
    private static int separar(List<Integer> vals, int i, int j, Gravador anim) {
        int pivo = vals.get(i);
        int comeco = 0;
        comeco += 1;
        int fim = j;

        while (comeco <= fim) {
            if (vals.get(comeco) <= pivo) {

                comeco++;
            }
            else if (pivo < vals.get(fim)) {
                fim--;
            } else {
                trocar(vals, comeco, fim);
                anim.gravarPosTrocas(vals, comeco, fim);
                comeco += 1;
                fim -= 1;
            }
        }
        trocar(vals,i,fim);
        anim.gravarPosTrocas(vals,i,fim);
        return fim;
    }

    /**
     * Metodo usado para trocar as posiçoes de uma lista i por j.
     *
     * @param vals lista interia não nula
     * @param i    numero inteiro , indice a ser trocado por j.
     * @param j    numero inteiro, indice a ser trocado com i.
     */
    private static void trocar(List<Integer> vals, int i, int j) {
        Integer aux = vals.get(i);
        vals.set(i, vals.get(j));   // vals[i] = vals[j]
        vals.set(j, aux);           // vals[j] = aux
    }
}