#include <stdio.h>

int palindrome_aux(int A[], int start, int end)
{
	if (start >= end)
		return 1;
	else 
	{
		/* si los extremos son iguales seguimos revisando 
		el subarreglo que corresponde (A[start+1...end-1]).
		Si no son iguales, sabemos que no es palindrome */

		if (A[start] == A[end])
			return palindrome_aux(A, start+1, end-1);
		else	
			return 0;
	}
}

/* para poder hacer recursion necesitamos manejar explicitamente
el comienzo y final del arreglo. 
Para esto, podemos definir una función auxiliar palindrome_aux
(otra opcion es cambiar directamente los parametros de palindrome) */

int palindrome(int A[], int n)
{
	return palindrome_aux(A, 0, n-1);
}

int main(void) 
{
	int A[] = {18, 11, 5, 11, 18};
	int n = 5;
	printf("%i\n", palindrome(A, n));
}

