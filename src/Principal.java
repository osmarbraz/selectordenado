/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * Baseado nos slides 25 da aula do dia 22/09/2017  
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {
    
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
            B[i] = A[i];
        }

        for (int j = q + 1; j <= r; j++) {
            B[r + (q + 1) - j] = A[j];
        }
        int i = p;
        int j = r;
        for (int k = p; k <= r; k++) {
            if (B[i] <= B[j]) {
                A[k] = B[i];
                i = i + 1;
            } else {
                A[k] = B[j];
                j = j - 1;
            }
        }
    }

    /**
     * Mergesort sem sentinela.
     * Algoritmos de ordenação podem ser ou não in-place ou estáveis.
     * Um método de ordenação é estável se elementos iguais ocorrem no 
     * vetor ordenado na mesma ordem em que são passados na entrada.
     * O mergesort é estável. 
     * 
     * Complexidade no pior caso é Theta(n log n)
     *
     * @param A Vetor a ser ordenado
     * @param p Inicio do vetor
     * @param r Fim do vetor
     */
    public static void mergesort(int A[], int p, int r) {
        if (p < r) {                    //Theta(1)
            int q = (p + r) / 2;        //Theta(1)
            mergesort(A, p, q);         //T(n/2)
            mergesort(A, q + 1, r);     //T(n/2)
            merge(A, p, q, r);          //Theta(n)
        }
    }
    
    /**
     * Ordena o vetor A utilizando o Mergesort
     * @param A Vetor a ser ordenado
     * @param n Quantidade de elementos do vetor A
     */
    public static void ordene(int A[], int n){
        mergesort(A, 0, n-1);
    }
    
    /**
     * Recebe um vetor A[1...n] e devolve o valor do i-ésimo menor elemento de A.
     * A complexidade de tempo é O(n log n)
     * 
     * @param A Vetor com os valores
     * @param n Quantidade de elementos do vetor
     * @param i Posição do vetor desejada
     * @return Um valor do elemento da posição i do vetor
     */
    public static int selectOrdenado(int A[], int n, int i) {
        ordene(A, n);
        return A[i];
    }

    public static void main(String[] args) {
        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};

        //Quantidade de elementos
        int n = A.length;

        int menor = selectOrdenado(A, n, 0);
        
        System.out.println("Menor:" + menor);        
    }
    
}