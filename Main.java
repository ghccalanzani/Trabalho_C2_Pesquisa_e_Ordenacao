import java.util.*;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        System.out.println("#################################################################");
        System.out.println("######## Trabalho C2 - Benchmark de métodos de ordenação ########");
        System.out.println("#################################################################");
        while (opcao != 0) {
            System.out.println("\nPara quantos elementos você deseja fazer o benchmark?" +
                    "\n1- 100 (Cem)" +
                    "\n2- 1000 (Mil)" +
                    "\n3- 10000 (Dez mil)" +
                    "\n4- 100000 (Cem mil)" +
                    "\n5- 1000000 (Um millhão)" +
                    "\n0- Sair");
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    testeCemElementos();
                    break;
                case 2:
                    testeMilElementos();
                    break;
                case 3:
                    testeDezMilElementos();
                    break;
                case 4:
                    testeCemMilElementos();
                    break;
                case 5:
                    testeUmMilhaoElementos();
                    break;
                case 0:
                    System.out.println("Programa encerrado!");
                    System.exit(0);
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }


    private static int partition(int a[], int start, int end) {
        int pivot = a[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {
            if (a[j] < pivot) {
                i++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[i + 1];
        a[i + 1] = a[end];
        a[end] = t;
        return (i + 1);
    }

    //Quick Sort
    public static long quickSort(int a[], int start, int end) {
        long comeco = System.currentTimeMillis();
        if (start < end) {
            int p = partition(a, start, end);
            quickSort(a, start, p - 1);
            quickSort(a, p + 1, end);
        }
        long tempo = System.currentTimeMillis() - comeco;
        return tempo;
    }

    private static void merge(int a[], int beg, int mid, int end) {
        int i, j, k;
        int n1 = mid - beg + 1;
        int n2 = end - mid;

        int LeftArray[] = new int[n1];
        int RightArray[] = new int[n2];

        for (i = 0; i < n1; i++)
            LeftArray[i] = a[beg + i];
        for (j = 0; j < n2; j++)
            RightArray[j] = a[mid + 1 + j];

        i = 0;
        j = 0;
        k = beg;

        while (i < n1 && j < n2) {
            if (LeftArray[i] <= RightArray[j]) {
                a[k] = LeftArray[i];
                i++;
            } else {
                a[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            a[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = RightArray[j];
            j++;
            k++;
        }
    }

    //Merge Sort
    public static long mergeSort(int a[], int beg, int end) {
        long comeco = System.currentTimeMillis();
        if (beg < end) {
            int mid = (beg + end) / 2;
            mergeSort(a, beg, mid);
            mergeSort(a, mid + 1, end);
            merge(a, beg, mid, end);
        }
        long tempo = System.currentTimeMillis() - comeco;
        return tempo;
    }

    //Shell Sort
    public static long shellSort(int[] arr) {
        long comeco = System.currentTimeMillis();
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > key) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = key;
            }
        }
        long tempo = System.currentTimeMillis() - comeco;
        return tempo;
    }

    //Bubble Sort
    public static long bubbleSort(int[] arr) {
        long comeco = System.currentTimeMillis();
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        long tempo = System.currentTimeMillis() - comeco;
        return tempo;
    }

    //Selection Sort
    public static long selectionSort(int[] arr) {
        long comeco = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        long tempo = System.currentTimeMillis() - comeco;
        return tempo;
    }

    //Insertion Sort
    public static long insertionSort(int[] array) {
        long comeco = System.currentTimeMillis();
        for (int i = 0; i < array.length; ++i) {
            int j = i;
            while (j > 0 && array[j - 1] > array[j]) {
                int key = array[j];
                array[j] = array[j - 1];
                array[j - 1] = key;
                j = j - 1;
            }
        }
        long tempo = System.currentTimeMillis() - comeco;
        return tempo;
    }

    public static void gerarNumeros() {
        //Gerar aleatórios, crescentes e decrescentes
        int aux = 1000001;
        for (int i = 0; i < 1000000; i++) {
            if (i < 100) {
                vetor100aleatorio[i] = 1 + (int) (Math.random() * 100);
                vetor100crescente[i] = i;
                vetor100decrescente[i] = aux - 1;
            }
            if (i < 1000) {
                vetor1000aleatorio[i] = 1 + (int) (Math.random() * 1000);
                vetor1000crescente[i] = i;
                vetor1000decrescente[i] = aux - 1;
            }
            if (i < 10000) {
                vetor10000aleatorio[i] = 1 + (int) (Math.random() * 10000);
                vetor10000crescente[i] = i;
                vetor10000decrescente[i] = aux - 1;
            }
            if (i < 100000) {
                vetor100000aleatorio[i] = 1 + (int) (Math.random() * 100000);
                vetor100000crescente[i] = i;
                vetor100000decrescente[i] = aux - 1;
            }
            if (i < 1000000) {
                vetor1000000aleatorio[i] = 1 + (int) (Math.random() * 1000000);
                vetor1000000crescente[i] = i;
                vetor1000000decrescente[i] = aux - 1;
            }
            aux--;
        }
    }

    public static void testeCemElementos() {
        gerarNumeros();
        System.out.println("Tempo Insertion Sort (números aleatórios): " + insertionSort(vetor100aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números aleatórios): " + selectionSort(vetor100aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números aleatórios): " + bubbleSort(vetor100aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números aleatórios): " + shellSort(vetor100aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números aleatórios): " + mergeSort(vetor100aleatorio, 0, vetor100aleatorio.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números aleatórios): " + quickSort(vetor100aleatorio, 0, vetor100aleatorio.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números crescentes): " + insertionSort(vetor100crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números crescentes): " + selectionSort(vetor100crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números crescentes): " + bubbleSort(vetor100crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números crescentes): " + shellSort(vetor100crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números crescentes): " + mergeSort(vetor100crescente, 0, vetor100crescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números crescentes): " + quickSort(vetor100crescente, 0, vetor100crescente.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números decrescentes): " + insertionSort(vetor100decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números decrescentes): " + selectionSort(vetor100decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números decrescentes): " + bubbleSort(vetor100decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números decrescentes): " + shellSort(vetor100decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números decrescentes): " + mergeSort(vetor100decrescente, 0, vetor100decrescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números decrescentes): " + quickSort(vetor100decrescente, 0, vetor100decrescente.length - 1) + " milisegundos");
    }

    public static void testeMilElementos() {
        gerarNumeros();
        System.out.println("Tempo Insertion Sort (números aleatórios): " + insertionSort(vetor1000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números aleatórios): " + selectionSort(vetor1000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números aleatórios): " + bubbleSort(vetor1000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números aleatórios): " + shellSort(vetor1000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números aleatórios): " + mergeSort(vetor1000aleatorio, 0, vetor1000aleatorio.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números aleatórios): " + quickSort(vetor1000aleatorio, 0, vetor1000aleatorio.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números crescentes): " + insertionSort(vetor1000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números crescentes): " + selectionSort(vetor1000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números crescentes): " + bubbleSort(vetor1000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números crescentes): " + shellSort(vetor1000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números crescentes): " + mergeSort(vetor1000crescente, 0, vetor1000crescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números crescentes): " + quickSort(vetor1000crescente, 0, vetor1000crescente.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números decrescentes): " + insertionSort(vetor1000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números decrescentes): " + selectionSort(vetor1000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números decrescentes): " + bubbleSort(vetor1000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números decrescentes): " + shellSort(vetor1000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números decrescentes): " + mergeSort(vetor1000decrescente, 0, vetor1000decrescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números decrescentes): " + quickSort(vetor1000decrescente, 0, vetor1000decrescente.length - 1) + " milisegundos");
    }

    public static void testeDezMilElementos() {
        gerarNumeros();
        System.out.println("Tempo Insertion Sort (números aleatórios): " + insertionSort(vetor10000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números aleatórios): " + selectionSort(vetor10000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números aleatórios): " + bubbleSort(vetor10000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números aleatórios): " + shellSort(vetor10000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números aleatórios): " + mergeSort(vetor10000aleatorio, 0, vetor10000aleatorio.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números aleatórios): " + quickSort(vetor10000aleatorio, 0, vetor10000aleatorio.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números crescentes): " + insertionSort(vetor10000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números crescentes): " + selectionSort(vetor10000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números crescentes): " + bubbleSort(vetor10000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números crescentes): " + shellSort(vetor10000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números crescentes): " + mergeSort(vetor10000crescente, 0, vetor10000crescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números crescentes): " + quickSort(vetor10000crescente, 0, vetor10000crescente.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números decrescentes): " + insertionSort(vetor10000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números decrescentes): " + selectionSort(vetor10000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números decrescentes): " + bubbleSort(vetor10000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números decrescentes): " + shellSort(vetor10000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números decrescentes): " + mergeSort(vetor10000decrescente, 0, vetor10000decrescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números decrescentes): " + quickSort(vetor10000decrescente, 0, vetor10000decrescente.length - 1) + " milisegundos");
    }

    public static void testeCemMilElementos() {
        gerarNumeros();
        System.out.println("Tempo Insertion Sort (números aleatórios): " + insertionSort(vetor100000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números aleatórios): " + selectionSort(vetor100000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números aleatórios): " + bubbleSort(vetor100000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números aleatórios): " + shellSort(vetor100000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números aleatórios): " + mergeSort(vetor100000aleatorio, 0, vetor100000aleatorio.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números aleatórios): " + quickSort(vetor100000aleatorio, 0, vetor100000aleatorio.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números crescentes): " + insertionSort(vetor100000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números crescentes): " + selectionSort(vetor100000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números crescentes): " + bubbleSort(vetor100000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números crescentes): " + shellSort(vetor100000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números crescentes): " + mergeSort(vetor100000crescente, 0, vetor100000crescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números crescentes): " + quickSort(vetor100000crescente, 0, vetor100000crescente.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números decrescentes): " + insertionSort(vetor100000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números decrescentes): " + selectionSort(vetor100000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números decrescentes): " + bubbleSort(vetor100000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números decrescentes): " + shellSort(vetor100000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números decrescentes): " + mergeSort(vetor100000decrescente, 0, vetor100000decrescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números decrescentes): " + quickSort(vetor100000decrescente, 0, vetor100000decrescente.length - 1) + " milisegundos");
    }

    public static void testeUmMilhaoElementos() {
        gerarNumeros();
        System.out.println("Tempo Insertion Sort (números aleatórios): " + insertionSort(vetor1000000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números aleatórios): " + selectionSort(vetor1000000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números aleatórios): " + bubbleSort(vetor1000000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números aleatórios): " + shellSort(vetor1000000aleatorio) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números aleatórios): " + mergeSort(vetor1000000aleatorio, 0, vetor1000000aleatorio.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números aleatórios): " + quickSort(vetor1000000aleatorio, 0, vetor1000000aleatorio.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números crescentes): " + insertionSort(vetor1000000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números crescentes): " + selectionSort(vetor1000000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números crescentes): " + bubbleSort(vetor1000000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números crescentes): " + shellSort(vetor1000000crescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números crescentes): " + mergeSort(vetor1000000crescente, 0, vetor1000000crescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números crescentes): " + quickSort(vetor1000000crescente, 0, vetor1000000crescente.length - 1) + " milisegundos");

        gerarNumeros();
        System.out.println("\nTempo Insertion Sort (números decrescentes): " + insertionSort(vetor1000000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Selection Sort (números decrescentes): " + selectionSort(vetor1000000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Bubble Sort (números decrescentes): " + bubbleSort(vetor1000000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Shell Sort (números decrescentes): " + shellSort(vetor1000000decrescente) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Merge Sort (números decrescentes): " + mergeSort(vetor1000000decrescente, 0, vetor1000000decrescente.length - 1) + " milisegundos");
        gerarNumeros();
        System.out.println("Tempo Quick Sort (números decrescentes): " + quickSort(vetor1000000decrescente, 0, vetor1000000decrescente.length - 1) + " milisegundos");
    }

    static int[] vetor100aleatorio = new int[100];
    static int[] vetor100crescente = new int[100];
    static int[] vetor100decrescente = new int[100];

    static int[] vetor1000aleatorio = new int[1000];
    static int[] vetor1000crescente = new int[1000];
    static int[] vetor1000decrescente = new int[1000];

    static int[] vetor10000aleatorio = new int[10000];
    static int[] vetor10000crescente = new int[10000];
    static int[] vetor10000decrescente = new int[10000];

    static int[] vetor100000aleatorio = new int[100000];
    static int[] vetor100000crescente = new int[100000];
    static int[] vetor100000decrescente = new int[100000];

    static int[] vetor1000000aleatorio = new int[1000000];
    static int[] vetor1000000crescente = new int[1000000];
    static int[] vetor1000000decrescente = new int[1000000];

}