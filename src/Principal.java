/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 25 da aula do dia 22/09/2017  
 *
 * Problema de Seleção
 * Dado um conjunto A de n números inteiro e um inteiro i, 
 * determinar o i-ésimo menor elemento de A.
 * 
 * Ordene pode ser MergeSort ou HeapSort
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    /**
     * O piso (= floor) de um número real x é o resultado do arredondamento de x
     * para baixo. Em outras palavras, o piso de x é o único número inteiro i
     * tal que i<=x<i+1. Ex. O piso de 3.9 é 3.
     *
     * Em java pode ser utilizando Math.floor(double)
     *
     * @param x Numero real a ser cálculado o piso.
     * @return um valor inteiro com o piso de x.
     */
    public static int piso(double x) {
        //Pego a parte inteira de x
        int parteInteira = (int) x;
        //Pego a parte fracionária de x
        double parteFracionaria = x - parteInteira;
        //Retorno x subtraindo a parte fracionaria 
        return (int) (x - parteFracionaria);
    }        
    
    /**
     * Realiza a intercação sem sentinela
     *
     * @param A Vetor a ser ordenado
     * @param p Inicio do vetor
     * @param q Pivo do vetor
     * @param r Fim do vetor
     */
    public static void merge(int A[], int p, int q, int r) {
        int B[] = new int[r + 1];         
        for (int i = p; i <= q; i++) {
            B[i-1] = A[i-1];
        }
        for (int j = q + 1; j <= r; j++) {
            B[r + (q + 1) - j - 1] = A[j-1];
        }
        int i = p;
        int j = r;
        for (int k = p; k <= r; k++) {
            if (B[i-1] <= B[j-1]) {
                A[k-1] = B[i-1];
                i = i + 1;
            } else {
                A[k-1] = B[j-1];
                j = j - 1;
            }
        }
    }

    /**
     * Mergesort sem sentinela. Algoritmos de ordenação podem ser ou não
     * in-place ou estáveis. Um método de ordenação é estável se elementos
     * iguais ocorrem no vetor ordenado na mesma ordem em que são passados na
     * entrada. O mergesort é estável.
     *
     * Complexidade no pior caso é Theta(n log n)
     *
     * @param A Vetor a ser ordenado
     * @param p Inicio do vetor
     * @param r Fim do vetor
     */
    public static void mergesort(int A[], int p, int r) {
        if (p < r) {                    //Theta(1)
            int q = piso((p + r) / 2);  //Theta(1)
            mergesort(A, p, q);         //T(n/2)
            mergesort(A, q + 1, r);     //T(n/2)
            merge(A, p, q, r);          //Theta(n)
        }
    }

    /**
     * Ordena o vetor A utilizando o Mergesort
     *
     * @param A Vetor a ser ordenado
     * @param n Quantidade de elementos do vetor A
     */
    public static void ordene(int A[], int n) {
        mergesort(A, 1, n);
    }

    /**
     * Recebe um vetor A[1...n] e devolve o valor do i-ésimo menor elemento de
     * A. 
     * 
     * A complexidade de tempo é O(n log n)
     *
     * @param A Vetor com os valores
     * @param n Quantidade de elementos do vetor
     * @param i Posição do vetor desejada
     * @return Um valor do elemento da posição i do vetor
     */
    public static int selectOrdenado(int A[], int n, int i) {
        ordene(A, n);
        return A[i-1];
    }

    public static void main(String[] args) {
        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};

        //Quantidade de elementos
        int n = A.length;

        //Posição do i-ésimo termo
        int i = 1;
        int menor = selectOrdenado(A, n, i);
        System.out.println("1o menor:" + menor);
        
        //Posição do i-ésimo termo
        i = 3;
        menor = selectOrdenado(A, n, i);
        System.out.println("3o menor:" + menor);
        
        //Posição do maior termo
        i = n;
        menor = selectOrdenado(A, n, i);
        System.out.println("no maior:" + menor);      
    }
}
