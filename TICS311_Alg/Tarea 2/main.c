#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE_LENGTH 1024
#define MAX_USERS 1000

// FALTA HACER QUICKSORT O MERGESORT PARA ORDENAR LOS USUARIOS POR EDAD

typedef struct {
    char nombre[100];
    int edad;
} Usuario;

void quick_sort_aux(int *arreglo, int ipos, int fpos);
int partition(int *arreglo, int ipos, int fpos);
void quick_sort(Usuario *arreglo, int N);


int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Debe proporcionar el nombre del archivo como argumento.\n");
        return 1;
    }

    FILE *archivo = fopen(argv[1], "r");
    if (archivo == NULL) {
        printf("No se pudo abrir el archivo.\n");
        return 1;
    }

    Usuario usuarios[MAX_USERS];
    int numUsuarios = 0;
    char linea[MAX_LINE_LENGTH];

    while (fgets(linea, sizeof(linea), archivo) != NULL) {
        if (numUsuarios == MAX_USERS) {
            printf("Se ha alcanzado el límite máximo de usuarios.\n");
            break;
        }

        // Obtener nombre y edad de la línea
        char *token = strtok(linea, ",");
        if (token == NULL) {
            printf("Formato de archivo incorrecto en la línea: %s", linea);
            continue;
        }

        strncpy(usuarios[numUsuarios].nombre, token, sizeof(usuarios[numUsuarios].nombre));
        usuarios[numUsuarios].nombre[sizeof(usuarios[numUsuarios].nombre) - 1] = '\0'; // Asegurarse de terminar la cadena

        token = strtok(NULL, ",");
        if (token == NULL) {
            printf("Formato de archivo incorrecto en la línea: %s", linea);
            continue;
        }

        usuarios[numUsuarios].edad = atoi(token);
        numUsuarios++;
    }

    fclose(archivo);

    // Imprimir la información almacenada en el arreglo de structs
    for (int i = 0; i < numUsuarios; i++) {
        printf("Usuario %d:\n", i + 1);
        printf("Nombre: %s\n", usuarios[i].nombre);
        printf("Edad: %d\n", usuarios[i].edad);
        printf("\n");
    }

    return 0;
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
void quick_sort(Usuario *arreglo, int N)
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
