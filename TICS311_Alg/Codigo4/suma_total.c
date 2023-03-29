#include <stdio.h>

int suma_total(int n)
{
	if (n == 1)
		return 1;
	else 
		return n + suma_total(n-1);
}

int main(void) 
{
	printf("Ingrese un numero positivo:\n");
	int n;
	scanf("%i", &n);
	printf("La suma de 1 a %i es %i\n", n, suma_total(n));
}

