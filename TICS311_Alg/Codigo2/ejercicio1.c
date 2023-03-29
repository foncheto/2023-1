#include <stdio.h>

// Opcion haciendo una copia del arreglo
/* Ojo que en C los arreglos de largo variable no son estandar
(y son poco eficientes), es decir algún compilador podría no permitirlos. 
En general es mejor evitarlos */
/*
void invertir(int *arreglo, int n)
{
	int tmp[n];
	for (int i=0; i < n; i++)
		tmp[i] = arreglo[i];
	
	for (int i=0; i < n; i++)
		arreglo[i] = tmp[n-1-i];
}
*/

// opcion sin hacer una copia del arreglo
void invertir(int *arreglo, int n)
{
	for (int i=0; i < n/2; i++)
	{
		int aux = arreglo[i];
		arreglo[i] = arreglo[n-1-i];
		arreglo[n-1-i] = aux;
	}
}

void swap_arreglo(int *arreglo, int largo, int i, int j)
{
	int aux = arreglo[i];
	arreglo[i] = arreglo[j];
	arreglo[j] = aux;
}

int main(int argc, char **argv) 
{
	int A[] = {1,2,3,4,5};
	invertir(A, 5);
	for(int i = 0; i < 5; i++)
		printf("%i\n", A[i]);

	/*
	int B[] = {1,2,3,4,5};
	swap_arreglo(B, 5, 2, 4);
	for(int i = 0; i < 5; i++)
		printf("%i\n", B[i]);
	*/
}
