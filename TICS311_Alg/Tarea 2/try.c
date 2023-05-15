#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE_LENGTH 1024
#define MAX_USERS 1000

typedef struct {
    char nombre[100];
    int edad;
} Usuario;

void swap(Usuario *a, Usuario *b) {
    Usuario temp = *a;
    *a = *b;
    *b = temp;
}

int partition(Usuario *arr, int low, int high) {
    int pivot = arr[high].edad;
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
        if (arr[j].edad < pivot || (arr[j].edad == pivot && strcmp(arr[j].nombre, arr[high].nombre) < 0)) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

void quickSort(Usuario *arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

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

    // Ordenar usuarios por edad utilizando Quicksort
    quickSort(usuarios, 0, numUsuarios - 1);

    // Imprimir la información almacenada en el arreglo de structs
    for (int i = 0; i < numUsuarios; i++) {
        printf("Usuario %d:\n", i + 1);
        printf("Nombre: %s\n", usuarios[i].nombre);
        printf("Edad: %d\n", usuarios[i].edad);
        printf("\n");
    }

    return 0;
}