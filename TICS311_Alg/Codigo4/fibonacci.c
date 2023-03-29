#include <stdio.h>

int fibonacci(int n)
{
	if (n == 1)
		return 0;
	if (n == 2)
		return 1;

	return fibonacci(n-1) + fibonacci(n-2);
}

int main(void) 
{
	printf("Ingrese un numero positivo:\n");
	int n;
	scanf("%i", &n);
	printf("El %i-esimo numero de Fibonacci es %i\n", n, fibonacci(n));
	return 0;
}


