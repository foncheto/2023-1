#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "listas.h"
#include "bst.h"

int main(int argc, char *argv[])
{	
  // Haga aca sus experimentos de listas y arboles
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
  nodo_lista *lista1 = NULL; //nodo inicial de la lista
  nodo_arbol *arbol1 = NULL; //raiz del arbol

  // Insertar elementos del arreglo en la lista y medir tiempo
  clock_t inicio_lista = clock();
  for (int i = 0; i < 100; i++) {
      insertar_final(&lista1, arr1[i]);
  }
  clock_t fin_lista = clock();
  double tiempo_lista = ((double)(fin_lista - inicio_lista)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en lista (100 elementos): %.6f segundos\n", tiempo_lista);

  // Insertar elementos del arreglo en el árbol y medir tiempo
  clock_t inicio_arbol = clock();
  for (int i = 0; i < 100; i++) {
      insertar_nodo(&arbol1, arr1[i]);
  }
  clock_t fin_arbol = clock();
  double tiempo_arbol = ((double)(fin_arbol - inicio_arbol)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en árbol (100 elementos): %.6f segundos\n", tiempo_arbol);

  // Continuar con los demás arreglos y mediciones

  return 0;
}