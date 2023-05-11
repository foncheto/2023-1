#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//FALTA ACTUALIZAR MERGE Y QUICKSORT con lo que puso el profe!

void bubble_sort(int arr[], int n);
void selection_sort(int arr[], int n);
void insertion_sort(int arr[], int n);
void merge_sort(int arreglo[], int N);
void merge_sort_aux(int *arreglo, int ipos, int fpos);
void quick_sort(int arreglo[], int N);

int main() {
  srand(time(NULL)); // semilla para generar números aleatorios

  // arreglos aleatorios de longitud 100, 1000, 10000, 100000, 200000 y 500000
  int arr1[100], arr2[1000], arr3[10000], arr4[100000], arr5[200000],
      arr6[500000];

  // llenar los arreglos con números aleatorios
  for (int i = 0; i < 100; i++) {
    arr1[i] = rand() % 1000;
  }

  for (int i = 0; i < 1000; i++) {
    arr2[i] = rand() % 1000;
  }

  for (int i = 0; i < 10000; i++) {
    arr3[i] = rand() % 1000;
  }

  for (int i = 0; i < 100000; i++) {
    arr4[i] = rand() % 1000;
  }

  for (int i = 0; i < 200000; i++) {
    arr5[i] = rand() % 1000;
  }

  for (int i = 0; i < 500000; i++) {
    arr6[i] = rand() % 1000;
  }

  // imprimir los primeros 10 números de cada arreglo para verificar
  printf("Los primeros 10 numeros de los arreglos\n");
  printf("\narr1: ");
  for (int i = 0; i < 10; i++) {
    printf("%d ", arr1[i]);
  }
  printf("\narr1 has length %ld\n", sizeof(arr1) / sizeof(int));
  printf("\n");

  printf("arr2: ");
  for (int i = 0; i < 10; i++) {
    printf("%d ", arr2[i]);
  }
  printf("\narr2 has length %ld\n", sizeof(arr2) / sizeof(int));
  printf("\n");

  printf("arr3: ");
  for (int i = 0; i < 10; i++) {
    printf("%d ", arr3[i]);
  }
  printf("\narr3 has length %ld\n", sizeof(arr3) / sizeof(int));
  printf("\n");

  printf("arr4: ");
  for (int i = 0; i < 10; i++) {
    printf("%d ", arr4[i]);
  }
  printf("\narr4 has length %ld\n", sizeof(arr4) / sizeof(int));
  printf("\n");

  printf("arr5: ");
  for (int i = 0; i < 10; i++) {
    printf("%d ", arr5[i]);
  }
  printf("\narr5 has length %ld\n", sizeof(arr5) / sizeof(int));
  printf("\n");

  printf("arr6: ");
  for (int i = 0; i < 10; i++) {
    printf("%d ", arr6[i]);
  }
  printf("\narr6 has length %ld\n", sizeof(arr6) / sizeof(int));
  printf("\n");

  int flag = 1;

  while (flag) {
    printf("\nMENU\n");
    printf("1. Bubble Sort\n");
    printf("2. Selection Sort\n");
    printf("3. Insertion Sort\n");
    printf("4. Merge Sort\n");
    printf("5. Quick Sort\n");
    int opt = 0;
    printf("Ingrese ");
    scanf("%d", &opt);
    clock_t start_time = clock();
    switch (opt) {
    case 1:
      // sort arr1 using Bubble Sort
      start_time = clock();
      bubble_sort(arr1, sizeof(arr1) / sizeof(int));
      printf("Bubble Sort on arr1 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr2 using Bubble Sort
      start_time = clock();
      bubble_sort(arr2, sizeof(arr2) / sizeof(int));
      printf("Bubble Sort on arr2 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr3 using Bubble Sort
      start_time = clock();
      bubble_sort(arr3, sizeof(arr3) / sizeof(int));
      printf("Bubble Sort on arr3 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr4 using Bubble Sort
      start_time = clock();
      bubble_sort(arr4, sizeof(arr4) / sizeof(int));
      printf("Bubble Sort on arr4 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr5 using Bubble Sort
      start_time = clock();
      bubble_sort(arr5, sizeof(arr5) / sizeof(int));
      printf("Bubble Sort on arr5 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr6 using Bubble Sort
      start_time = clock();
      bubble_sort(arr6, sizeof(arr6) / sizeof(int));
      printf("Bubble Sort on arr6 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);
      break;
    case 2:

      // sort ar1 using Selection Sort
      start_time = clock();
      selection_sort(arr1, sizeof(arr1) / sizeof(int));
      printf("Selection Sort on arr1 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr2 using Selection Sort
      start_time = clock();
      selection_sort(arr2, sizeof(arr2) / sizeof(int));
      printf("Selection Sort on arr2 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr3 using Selection Sort
      start_time = clock();
      selection_sort(arr3, sizeof(arr3) / sizeof(int));
      printf("Selection Sort on arr3 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr4 using Selection Sort
      start_time = clock();
      selection_sort(arr4, sizeof(arr4) / sizeof(int));
      printf("Selection Sort on arr4 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr5 using Selection Sort
      start_time = clock();
      selection_sort(arr5, sizeof(arr5) / sizeof(int));
      printf("Selection Sort on arr5 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr6 using Selection Sort
      start_time = clock();
      selection_sort(arr6, sizeof(arr6) / sizeof(int));
      printf("Selection Sort on arr6 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);
      break;

    case 3:
      // sort arr1 using Insertion Sort
      start_time = clock();
      insertion_sort(arr1, sizeof(arr1) / sizeof(int));
      printf("Insertion Sort on arr1 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr2 using Insertion Sort
      start_time = clock();
      insertion_sort(arr2, sizeof(arr2) / sizeof(int));
      printf("Insertion Sort on arr2 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr3 using Insertion Sort
      start_time = clock();
      insertion_sort(arr3, sizeof(arr3) / sizeof(int));
      printf("Insertion Sort on arr3 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr4 using Insertion Sort
      start_time = clock();
      insertion_sort(arr4, sizeof(arr4) / sizeof(int));
      printf("Insertion Sort on arr4 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr5 using Insertion Sort
      start_time = clock();
      insertion_sort(arr5, sizeof(arr5) / sizeof(int));
      printf("Insertion Sort on arr5 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr6 using Insertion Sort
      start_time = clock();
      insertion_sort(arr6, sizeof(arr6) / sizeof(int));
      printf("Insertion Sort on arr6 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);
      break;

    case 4:
      // sort arr1 using Merge Sort
      start_time = clock();
      merge_sort(arr1, sizeof(arr1) / sizeof(int));
      printf("Merge Sort on arr1 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr2 using Merge Sort
      start_time = clock();
      merge_sort(arr2, sizeof(arr2) / sizeof(int));
      printf("Merge Sort on arr2 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr3 using Merge Sort
      start_time = clock();
      merge_sort(arr3, sizeof(arr3) / sizeof(int));
      printf("Merge Sort on arr3 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr4 using Merge Sort
      start_time = clock();
      merge_sort(arr4, sizeof(arr4) / sizeof(int));
      printf("Merge Sort on arr4 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr5 using Merge Sort
      start_time = clock();
      merge_sort(arr5, sizeof(arr5) / sizeof(int));
      printf("Merge Sort on arr5 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr6 using Merge Sort
      start_time = clock();
      merge_sort(arr6, sizeof(arr6) / sizeof(int));
      printf("Merge Sort on arr6 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);
      break;

    case 5:

      // sort arr1 using Quick Sort
      start_time = clock();
      quick_sort(arr1, sizeof(arr1) / sizeof(int));
      printf("Quick Sort on arr1 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr2 using Quick Sort
      start_time = clock();
      quick_sort(arr2, sizeof(arr2) / sizeof(int));
      printf("Quick Sort on arr2 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr3 using Quick Sort
      start_time = clock();
      quick_sort(arr3, sizeof(arr3) / sizeof(int));
      printf("Quick Sort on arr3 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr4 using Quick Sort
      start_time = clock();
      quick_sort(arr4, sizeof(arr4) / sizeof(int));
      printf("Quick Sort on arr4 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr5 using Quick Sort
      start_time = clock();
      quick_sort(arr5, sizeof(arr5) / sizeof(int));
      printf("Quick Sort on arr5 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);

      // sort arr6 using Quick Sort
      start_time = clock();
      quick_sort(arr6, sizeof(arr6) / sizeof(int));
      printf("Quick Sort on arr6 took %f seconds\n",
             ((double)clock() - start_time) / CLOCKS_PER_SEC);
      break;
    case 6:
      flag = 0;
    }
  }

  return 0;
}

void bubble_sort(int arr[], int n) {
int aux;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n-1-i; j++) {
      if (arr[j] > arr[j+1]) { 
        // swap entre arreglo[j] y arreglo[j+1]
        aux = arr[j];
        arr[j] = arr[j+1];
        arr[j+1] = aux;
      }
    }
  }  
}

void selection_sort(int arr[], int n) {
  int min_index, aux; 
  for (int i = 0; i < n-1; i++) { 
    min_index = i;    
    for (int j = i+1; j < n; j++) {
        if (arr[j] < arr[min_index]) {
            min_index = j; 
        }
    }
    // swap para poner al minimo en su posicion
    aux = arr[i];
    arr[i] = arr[min_index];
    arr[min_index] = aux;
  } 
}

void insertion_sort(int arr[], int n) {
  int j, value;
  for (int i = 1; i < n; i++) {
    value = arr[i]; // guardamos el valor actual
    j = i-1;
    // desplazamos los elementos a la derecha mientras sean mayores a value
    while (j >= 0 && arr[j] > value) {
      arr[j + 1] = arr[j];
      j--;
    }
    arr[j+1] = value; // ponemos value donde corresponde
  }
}

void merge_sort_aux(int *arreglo, int ipos, int fpos);

// La funcion merge hace el merge entre los dos subarreglos con rangos de posiciones [ipos...mpos] y [mpos+1...fpos]
// La funcion deja el resultado del merge en el mismo arreglo entre las posiciones [ipos...fpos]
// Ojo: La funcion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void merge(int *arreglo, int ipos, int mpos, int fpos);


// Merge Sort
// Utilizamos la funcion auxiliar merge_sort_aux para poder aplicar recursion de manera mas comoda
// Ordenamos el arreglo entero, es decir, llamamos a merge_sort_aux con posiciones 0 y N-1
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void merge_sort(int arreglo[], int N) 
{
  merge_sort_aux(arreglo, 0, N-1);
}

// Implementacion recursiva de merge_sort_aux
// Notar que el caso base es cuando ipos == fpos
void merge_sort_aux(int *arreglo, int ipos, int fpos)
{
  if (ipos == fpos) {
    return;
  }

  int mpos = ipos + (fpos - ipos)/2;
  merge_sort_aux(arreglo, ipos, mpos);
  merge_sort_aux(arreglo, mpos+1, fpos);
  merge(arreglo, ipos, mpos, fpos);
}

// Implementacion de merge
void merge(int *arreglo, int ipos, int mpos, int fpos)
{
  int N_left = mpos - ipos + 1;
  int N_right = fpos - mpos;
  // arreglos temporales izquierdo y derecho
  int arreglo_left[N_left];
  int arreglo_right[N_right];

  // rellenamos los arreglos
  for(int i = 0; i < N_left; i++) {
    arreglo_left[i] = arreglo[ipos + i];
  }

  for(int i = 0; i < N_right; i++) {
    arreglo_right[i] = arreglo[mpos + 1 + i];
  }

  // hacemos el merge
  int aux = ipos;
  int i = 0, j = 0;
  // este while termina apenas terminamos de leer alguno de los dos arreglo
  while (i < N_left && j < N_right) {
    if (arreglo_left[i] <= arreglo_right[j]) {
      arreglo[aux] = arreglo_left[i];
      i++;
    }
    else {
      arreglo[aux] = arreglo_right[j];
      j++;
    }
    aux++;
  }

  // Caso que terminamos de leer el arreglo derecho (en el primer while de arriba)
  // Escribimos todo lo que queda del arreglo izquierdo
  while (i < N_left) {
    arreglo[aux] = arreglo_left[i];
    i++;
    aux++;
  }

  // Caso que terminamos de leer el arreglo izquierdo (en el primer while de arriba)
  // Escribimos todo lo que queda del arreglo derecho
  while (j < N_right) {
    arreglo[aux] = arreglo_right[j];
    j++;
    aux++;
  }
}

void quick_sort_aux(int *arreglo, int ipos, int fpos);

// La funcion partition particiona el subarreglo entre las posiciones [ipos...fpos]
// El pivote es el primer elemento del subarreglo, es decir arreglo[ipos]
// Se deja el pivote en su posicion correcta, 
// al lado izquierdo quedan los menores que el pivote, al lado derecho los mayores que el pivote
// Ojo: En este implementacion los subarreglos izquierdo y derecho pueden quedar en cualquier orden internamente
// Lo importante es que el subarreglo izquiero tienen los menores al pivote y el derecho los mayores
// Ojo: La funcion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
int partition(int *arreglo, int ipos, int fpos);

// Quick Sort
// Utilizamos la funcion auxiliar quick_sort_aux para poder aplicar recursion de manera mas comoda
// Ordenamos el arreglo entero, es decir, llamamos a quick_sort_aux con posiciones 0 y N-1
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void quick_sort(int arreglo[], int N)
{
  quick_sort_aux(arreglo, 0, N-1);
}

// Implementacion recursiva de quick_sort_aux
// Notar que el caso base es cuando ipos >= fpos
void quick_sort_aux(int *arreglo, int ipos, int fpos)
{
  if (ipos >= fpos) {
    return;
  }

  int pivot_pos = partition(arreglo, ipos, fpos);
  quick_sort_aux(arreglo, ipos, pivot_pos-1);
  quick_sort_aux(arreglo, pivot_pos+1, fpos);
}

// Implementacion de partition
int partition(int *arreglo, int ipos, int fpos) 
{
  int aux;
  int pivot = arreglo[ipos]; // el pivote es el primer elemento
  int i = ipos + 1; // el i mantiene la posicion donde deberia ir el proximo elemento menor al pivote
  for(int j = ipos + 1; j <= fpos; j++) {
    if (arreglo[j] < pivot) {
      // swap entre arreglo[i] y arreglo[j]
      aux = arreglo[i];
      arreglo[i] = arreglo[j];
      arreglo[j] = aux;
      // es importante actualizar i
      i++;
    }
  }

  // la posicion i-1 es la posicion correcta del pivote
  // hacemos swap entre arreglo[i-1] y el pivote, es decir, arreglo[ipos]
  arreglo[ipos] = arreglo[i-1];
  arreglo[i-1] = pivot;

  return i-1;
}