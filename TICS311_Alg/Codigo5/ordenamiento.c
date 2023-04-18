#include <stdio.h>

// Algoritmos de Ordenamiento
void bubble_sort_simple(int *arreglo, int N);
void bubble_sort_optimizado(int *arreglo, int N);
void selection_sort(int *arreglo, int N);
void insertion_sort(int *arreglo, int N);
void merge_sort(int *arreglo, int N);
void quick_sort(int *arreglo, int N);
void quick_sort_v2(int *arreglo, int N);

// Funcion auxiliar
void print_arreglo(int *arreglo, int N);

int main(void)
{	
  // Arreglo de ejemplo, N es el largo del arreglo
  int N = 20;
  int arreglo[20] = {9, 10, 3, 7, 23, 12, 8, 15, 2, 18, 1, 35, 101, 22, 4, 34, 11, 20, 19, 6};
  print_arreglo(arreglo, N);

  // Escoja el algoritmo que quiere probar
  bubble_sort_simple(arreglo, N);
  //bubble_sort_optimizado(arreglo, N);
  //selection_sort(arreglo, N);
  //insertion_sort(arreglo, N);
  //merge_sort(arreglo, N);
  //quick_sort(arreglo, N);
  //quick_sort_v2(arreglo, N);

  print_arreglo(arreglo, N);
}

// Imprime el arreglo
void print_arreglo(int *arreglo, int N)
{
  for(int i = 0; i < N; i++){
    printf("%d ", arreglo[i]);
  }
  printf("\n");
}

// Bubble sort version simple
// En cada iteracion revisa TODAS las parejas de entradas consecutivas en el arreglo (posiciones i e i+1)
// Por simplicidad, hacemos N iteraciones (sabemos que no necesitamos mas)
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)

void bubble_sort_simple(int *arreglo, int N)
{
  int aux;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N-1; j++) {
      if (arreglo[j] > arreglo[j+1]) { 
        // swap entre arreglo[j] y arreglo[j+1]
        aux = arreglo[j];
        arreglo[j] = arreglo[j+1];
        arreglo[j+1] = aux;
      }
    }
  }  
}

// Bubble sort version optimizada
// En la iteracion i revisa las parejas de entradas consecutivas en el arreglo (posiciones i e i+1), 
// ignorando las ultimas i parejas (los ultimos i elementos ya estan en su posicion correcta)
// Por simplicidad, hacemos N iteraciones (sabemos que no necesitamos mas)
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)

void bubble_sort_optimizado(int *arreglo, int N)
{
  int aux;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N-1-i; j++) {
      if (arreglo[j] > arreglo[j+1]) { 
        // swap entre arreglo[j] y arreglo[j+1]
        aux = arreglo[j];
        arreglo[j] = arreglo[j+1];
        arreglo[j+1] = aux;
      }
    }
  }  
}

// Selection Sort
// Calculamos iterativamente el minimo de los elementos restantes (partimos con todos los elementos), 
// y lo movemos a la posicion que le corresponde
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)

void selection_sort(int *arreglo, int N)
{
  int min_index, aux; 
  for (int i = 0; i < N-1; i++) { 
    min_index = i;    
    for (int j = i+1; j < N; j++) {
        if (arreglo[j] < arreglo[min_index]) {
            min_index = j; 
        }
    }
    // swap para poner al minimo en su posicion
    aux = arreglo[i];
    arreglo[i] = arreglo[min_index];
    arreglo[min_index] = aux;
  } 
}

// Insertion Sort
// En cada iteracion i=1...N-1, insertamos arreglo[i] en el subarreglo entre [0...i-1], 
// el cual sabemos que ya esta ordenado
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)

void insertion_sort(int *arreglo, int N)
{
  int j, value;
  for (int i = 1; i < N; i++) {
    value = arreglo[i]; // guardamos el valor actual
    j = i-1;
    // desplazamos los elementos a la derecha mientras sean mayores a value
    while (j >= 0 && arreglo[j] > value) {
      arreglo[j + 1] = arreglo[j];
      j--;
    }
    
    arreglo[j+1] = value; // ponemos value donde corresponde
  }
}

// Funciones auxiliares para Merge Sort

// La funcion merge_sort_aux ordena el subarreglo entre las posiciones ipos y fpos (inclusive)
// Ojo: La funcion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void merge_sort_aux(int *arreglo, int ipos, int fpos);

// La funcion merge hace el merge entre los dos subarreglos con rangos de posiciones [ipos...mpos] y [mpos+1...fpos]
// La funcion deja el resultado del merge en el mismo arreglo entre las posiciones [ipos...fpos]
// Ojo: La funcion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void merge(int *arreglo, int ipos, int mpos, int fpos);


// Merge Sort
// Utilizamos la funcion auxiliar merge_sort_aux para poder aplicar recursion de manera mas comoda
// Ordenamos el arreglo entero, es decir, llamamos a merge_sort_aux con posiciones 0 y N-1
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void merge_sort(int *arreglo, int N) 
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


// Funciones auxiliares para Quick Sort

// La funcion quick_sort_aux ordena el subarreglo entre las posiciones ipos y fpos (inclusive)
// Ojo: La funcion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
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
void quick_sort(int *arreglo, int N)
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


// Funciones auxiliares para Quick Sort V2

// La funcion quick_sort_aux_v2 ordena el subarreglo entre las posiciones ipos y fpos (inclusive)
// Ojo: La funcion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void quick_sort_aux_v2(int *arreglo, int ipos, int fpos);

// La funcion partition_v2 particiona el subarreglo entre las posiciones [ipos...fpos]
// El pivote es el ULTIMO elemento del subarreglo, es decir arreglo[fpos]
// Se deja el pivote en su posicion correcta, 
// al lado izquierdo quedan los menores que el pivote, al lado derecho los mayores que el pivote
// Ojo: En este implementacion los subarreglos izquierdo y derecho pueden quedar en cualquier orden internamente
// Lo importante es que el subarreglo izquiero tienen los menores al pivote y el derecho los mayores
// Ojo: La funcion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
int partition_v2(int *arreglo, int ipos, int fpos);

// Quick Sort V2
// Utilizamos la funcion auxiliar quick_sort_aux_v2 para poder aplicar recursion de manera mas comoda
// Ordenamos el arreglo entero, es decir, llamamos a quick_sort_aux con posiciones 0 y N-1
// Esta implementacion modifica el arreglo de entrada (NO entrega un nuevo arreglo)
void quick_sort_v2(int *arreglo, int N)
{
  quick_sort_aux_v2(arreglo, 0, N-1);
}

// Implementacion recursiva de quick_sort_aux_v2
// Notar que el caso base es cuando ipos >= fpos
void quick_sort_aux_v2(int *arreglo, int ipos, int fpos)
{
  if (ipos >= fpos) {
    return;
  }

  int pivot_pos = partition_v2(arreglo, ipos, fpos);
  quick_sort_aux_v2(arreglo, ipos, pivot_pos-1);
  quick_sort_aux_v2(arreglo, pivot_pos+1, fpos);
}

// Implementacion de partition_v2
int partition_v2(int *arreglo, int ipos, int fpos) 
{
  int aux;
  int pivot = arreglo[fpos]; // el pivote es el ultimo elemento
  int i = ipos; // el i mantiene la posicion donde deberia ir el proximo elemento menor al pivote
  for(int j = ipos; j <= fpos - 1; j++) {
    if (arreglo[j] < pivot) {
      // swap entre arreglo[i] y arreglo[j]
      aux = arreglo[i];
      arreglo[i] = arreglo[j];
      arreglo[j] = aux;
      // es importante actualizar i
      i++;
    }
  }

  // la posicion i es la posicion correcta del pivote
  // hacemos swap entre arreglo[i] y el pivote, es decir, arreglo[fpos]
  arreglo[fpos] = arreglo[i];
  arreglo[i] = pivot;

  return i;
}