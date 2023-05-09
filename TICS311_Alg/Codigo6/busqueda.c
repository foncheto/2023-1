#include <stdio.h>

// Algoritmos de Busqueda
// Entregan la posicion de x en el arreglo o -1 si no esta

int linear_search(int *arreglo, int N, int x);
int binary_search(int *arreglo, int ipos, int fpos, int x);
int binary_search_v2(int *arreglo, int ipos, int fpos, int x);
int exponential_search(int *arreglo, int N, int x);

int main() 
{
  int N = 9;
  int arreglo[9] = {10, 20, 30, 40, 50, 60, 70, 80, 90};
  int x;

  for (int i=0; i<N; i++) {
    printf("%d ", arreglo[i]);
  }

  printf("\nIngrese el numero x a buscar: ");
  scanf("%d", &x);

  //printf("Busqueda Lineal:\nLa posicion del elemento %d en el arreglo es %d\n", x, linear_search(arreglo, N, x));
  //printf("Busqueda Binaria:\nLa posicion del elemento %d en el arreglo es %d\n", x, binary_search(arreglo, 0, N-1, x));
  //printf("Busqueda Binaria v2:\nLa posicion del elemento %d en el arreglo es %d\n", x, binary_search_v2(arreglo, 0, N-1, x));
  printf("Busqueda Exponencial:\nLa posicion del elemento %d en el arreglo es %d\n", x, exponential_search(arreglo, N, x));
}

// busqueda lineal
int linear_search(int *arreglo, int N, int x)
{
  for (int i = 0; i < N; i++) {
    if (arreglo[i] == x) {
      return i;
    }
  }
  return -1;
}

// version iterativa de busqueda binaria
// ipos es la posicion inicial del arreglo, fpos es la posicion final del arreglo
// el largo del arreglo seria fpos - ipos + 1
int binary_search(int *arreglo, int ipos, int fpos, int x)
{
  int mpos;

  while (ipos <= fpos) {
    mpos = ipos + (fpos - ipos + 1)/2; // puede ser tambien mpos = ipos + (fpos - ipos)/2;
    if (arreglo[mpos] == x) {
      return mpos;
    }
    else if (arreglo[mpos] > x) {
      fpos = mpos-1;
    }
    else {
      ipos = mpos + 1;
    }
  }

  return -1;
}

// version recursiva de busqueda binaria
// ipos es la posicion inicial del arreglo, fpos es la posicion final del arreglo
// el largo del arreglo seria fpos - ipos + 1
int binary_search_v2(int *arreglo, int ipos, int fpos, int x)
{
  if (ipos > fpos)
    return -1;
  else
  {
    int mpos = ipos + (fpos - ipos + 1)/2; // puede ser tambien mpos = ipos + (fpos - ipos)/2;
    if (arreglo[mpos] == x) {
      return mpos;
    }
    else if (arreglo[mpos] > x) {
      return binary_search_v2(arreglo, ipos, mpos-1, x);
    }
    else {
      return binary_search_v2(arreglo, mpos+1, fpos, x);
    }
  }
}

// busqueda exponencial
int exponential_search(int *arreglo, int N, int x)
{
  if (arreglo[0] == x) {
    return 0;
  }

  int i = 1;
  while (i < N && arreglo[i] <= x) {
    i = i * 2;
  }

  if (i < N) {
    return binary_search(arreglo, i/2, i, x);
  }
  else {
    return binary_search(arreglo, i/2, N-1, x);
  }
}