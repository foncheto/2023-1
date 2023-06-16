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
  for (int i = 0; i < 100; i++)
  {
    arr1[i] = rand() % 1000;
  }

  for (int i = 0; i < 1000; i++)
  {
    arr2[i] = rand() % 1000;
  }

  for (int i = 0; i < 10000; i++)
  {
    arr3[i] = rand() % 1000;
  }

  for (int i = 0; i < 100000; i++)
  {
    arr4[i] = rand() % 1000;
  }

  for (int i = 0; i < 200000; i++)
  {
    arr5[i] = rand() % 1000;
  }

  for (int i = 0; i < 500000; i++)
  {
    arr6[i] = rand() % 1000;
  }
  nodo_lista *lista1 = NULL; // nodo inicial de la lista
  nodo_arbol *arbol1 = NULL; // nodo inicial del arbol

  // Insertar elementos del arreglo en la lista y medir tiempo
  clock_t inicio_lista = clock();
  for (int i = 0; i < 100; i++)
  {
    lista1 = insertar_lista(lista1, arr1[i]);
  }
  clock_t fin_lista = clock();
  double tiempo_lista = ((double)(fin_lista - inicio_lista)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en lista (100 elementos): %.6f segundos\n", tiempo_lista);

  clock_t inicio_lista2 = clock();
  for (int i = 0; i < 1000; i++)
  {
    lista1 = insertar_lista(lista1, arr2[i]);
  }
  clock_t fin_lista2 = clock();
  double tiempo_lista2 = ((double)(fin_lista2 - inicio_lista2)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en lista (1000 elementos): %.6f segundos\n", tiempo_lista2);

  clock_t inicio_lista3 = clock();
  for (int i = 0; i < 10000; i++)
  {
    lista1 = insertar_lista(lista1, arr3[i]);
  }
  clock_t fin_lista3 = clock();
  double tiempo_lista3 = ((double)(fin_lista3 - inicio_lista3)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en lista (10000 elementos): %.6f segundos\n", tiempo_lista3);

  clock_t inicio_lista4 = clock();
  for (int i = 0; i < 100000; i++)
  {
    lista1 = insertar_lista(lista1, arr4[i]);
  }
  clock_t fin_lista4 = clock();
  double tiempo_lista4 = ((double)(fin_lista4 - inicio_lista4)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en lista (100000 elementos): %.6f segundos\n", tiempo_lista4);

  clock_t inicio_lista5 = clock();
  for (int i = 0; i < 200000; i++)
  {
    lista1 = insertar_lista(lista1, arr5[i]);
  }
  clock_t fin_lista5 = clock();
  double tiempo_lista5 = ((double)(fin_lista5 - inicio_lista5)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en lista (200000 elementos): %.6f segundos\n", tiempo_lista5);

  clock_t inicio_lista6 = clock();
  for (int i = 0; i < 500000; i++)
  {
    lista1 = insertar_lista(lista1, arr6[i]);
  }
  clock_t fin_lista6 = clock();
  double tiempo_lista6 = ((double)(fin_lista6 - inicio_lista6)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en lista (500000 elementos): %.6f segundos\n", tiempo_lista6);

  // Insertar elementos del arreglo en el arbol y medir tiempo
  clock_t inicio_arbol = clock();
  for (int i = 0; i < 100; i++)
  {
    arbol1 = insertar_arbol(arbol1, arr1[i]);
  }
  clock_t fin_arbol = clock();
  double tiempo_arbol = ((double)(fin_arbol - inicio_arbol)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en arbol (100 elementos): %.6f segundos\n", tiempo_arbol);

  clock_t inicio_arbol2 = clock();
  for (int i = 0; i < 1000; i++)
  {
    arbol1 = insertar_arbol(arbol1, arr2[i]);
  }
  clock_t fin_arbol2 = clock();
  double tiempo_arbol2 = ((double)(fin_arbol2 - inicio_arbol2)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en arbol (1000 elementos): %.6f segundos\n", tiempo_arbol2);

  clock_t inicio_arbol3 = clock();
  for (int i = 0; i < 10000; i++)
  {
    arbol1 = insertar_arbol(arbol1, arr3[i]);
  }
  clock_t fin_arbol3 = clock();
  double tiempo_arbol3 = ((double)(fin_arbol3 - inicio_arbol3)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en arbol (10000 elementos): %.6f segundos\n", tiempo_arbol3);

  clock_t inicio_arbol4 = clock();
  for (int i = 0; i < 100000; i++)
  {
    arbol1 = insertar_arbol(arbol1, arr4[i]);
  }
  clock_t fin_arbol4 = clock();
  double tiempo_arbol4 = ((double)(fin_arbol4 - inicio_arbol4)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en arbol (100000 elementos): %.6f segundos\n", tiempo_arbol4);

  clock_t inicio_arbol5 = clock();
  for (int i = 0; i < 200000; i++)
  {
    arbol1 = insertar_arbol(arbol1, arr5[i]);
  }
  clock_t fin_arbol5 = clock();
  double tiempo_arbol5 = ((double)(fin_arbol5 - inicio_arbol5)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en arbol (200000 elementos): %.6f segundos\n", tiempo_arbol5);

  clock_t inicio_arbol6 = clock();
  for (int i = 0; i < 500000; i++)
  {
    arbol1 = insertar_arbol(arbol1, arr6[i]);
  }
  clock_t fin_arbol6 = clock();
  double tiempo_arbol6 = ((double)(fin_arbol6 - inicio_arbol6)) / CLOCKS_PER_SEC;
  printf("Tiempo total de inserción en arbol (500000 elementos): %.6f segundos\n", tiempo_arbol6);

  clock_t alturaArbol = clock();
  int altura = altura_arbol(arbol1);
  clock_t fin_altura = clock();
  double tiempo_altura = ((double)(fin_altura - alturaArbol)) / CLOCKS_PER_SEC;
  printf("Altura del arbol: %d\n", altura);
  printf("Tiempo total de altura del arbol: %.6f segundos\n", tiempo_altura);

  return 0;
}