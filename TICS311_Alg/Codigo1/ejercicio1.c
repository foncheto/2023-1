#include <stdio.h>

int es_primo(int n)
{
	for (int i=2; i < n; i++)
		if (n % i == 0) return 0;

	return 1;
}

int mcd(int n, int m)
{
	int mcd = 1;
	for(int i=1; i <= n && i <= m; i++)
	{
		if ((n % i == 0) && (m % i == 0))
			mcd = i;
	} 
	return mcd;
}

void imprimir_primos(int n)
{
	for(int i=2; i < n; i++)
	{
		if (es_primo(i))
			printf("%i\n", i);
	}
}

int main(int argc, char **argv) 
{
	printf("Es primo = %i\n", es_primo(17));
	printf("Mcd = %i\n", mcd(24, 60));
	imprimir_primos(20);
}
