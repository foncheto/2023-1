#include <stdio.h>

int minimo(int A[], int n)
{
	if (n == 1)
		return A[0];

	/* comparamos el valor del ultimo elemento A[n-1] 
	con el minimo del subarreglo A[0...n-2] */

	int aux = minimo(A, n-1);

	if(A[n-1] < aux)
		return A[n-1];
	else
		return aux;
}

int main(void) 
{
	int A[] = {34, 5, 2, 19, 99};
	int n = 5;
	printf("El minimo es %i\n", minimo(A, n));
}

