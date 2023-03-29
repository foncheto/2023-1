#include <stdio.h>
#include <stdlib.h>

int * concatenar_arreglos(int *arreglo1, int n, int *arreglo2, int m)
{
	int *output = malloc((n+m)*sizeof(int));
	for(int i = 0; i < n; i++)
		output[i] = arreglo1[i];

	for(int i = 0; i < m; i++)
		output[n+i] = arreglo2[i];

	return output;

}

void liberar_arreglo(int **arreglo, int n)
{
	for (int i=0; i < n; i++)
		free(arreglo[i]);

	free(arreglo);
}

// versión incorrecta de liberar_arreglo
/*
void liberar_arreglo(int **arreglo, int n)
{	
	free(arreglo);
}
*/

int * combinar_arreglos(int *arreglo1, int n, int *arreglo2, int m)
{
	int *output = malloc((n+m)*sizeof(int));
	int pos1 = 0, pos2 = 0;
	int i = 0;
	// este while termina apenas terminamos de leer alguno de los dos arreglo
	while (pos1 < n && pos2 < m) {
		if (arreglo1[pos1] < arreglo2[pos2]){
			output[i] = arreglo1[pos1];
			pos1++;
		}
		else{
			output[i] = arreglo2[pos2];		
			pos2++;
		}
		i++;
	}

	// Caso que terminamos de leer el arreglo2 antes que arreglo1
  	// Escribimos todo lo que queda de arreglo1 en output
	while(pos1 < n) {
		output[i] = arreglo1[pos1];
		pos1++;
		i++;
	}
	
	// Caso que terminamos de leer el arreglo1 antes que arreglo2
  	// Escribimos todo lo que queda de arreglo2 en output
	while(pos2 < m) {
		output[i] = arreglo2[pos2];
		pos2++;
		i++;
	}

	return output;
}

int main(int argc, char **argv) 
{
	// Testeo concatenar_arreglo

	/*
	int A[] = {1,2,3,4,5};
	int B[] = {6,7,8};
	int *salida = concatenar_arreglos(A, 5, B, 3);
	for (int i=0; i < 8; i++)
		printf("%i ", salida[i]);
	printf("\n");
	*/

	// Testeo liberar_arreglo
	/*
	int **D = malloc(10*sizeof(int *));
	for(int i = 0; i<10; i++)
		D[i] = malloc(20*sizeof(int));
	
	for (int i=0; i<10; i++)
	{
		for (int j=0; j<20; j++)
			D[i][j] = i;
	}

	for (int i=0; i<10; i++)
	{
		for (int j=0; j<20; j++)
			printf("%i ", D[i][j]);
		printf("\n");
	}
		
	liberar_arreglo(D, 10);
	*/

	// Testeo combinar_arreglos
	/*
	int A[] = {3, 10, 15, 30, 89};
	int B[] = {5, 7, 12, 24};
	int *salida = combinar_arreglos(A, 5, B, 4);
	for (int i=0; i < 9; i++)
		printf("%i ", salida[i]);
	printf("\n");
	*/
	
}
