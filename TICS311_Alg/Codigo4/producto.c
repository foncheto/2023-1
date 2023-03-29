#include <stdio.h>

int producto(int a, int b)
{
	if (b == 0)
		return 0;
	else 
		return a + producto(a, b-1);
}

int main(void) 
{
	printf("Ingrese dos numeros positivos separados por espacio:\n");
	int x, y;
	scanf("%i %i", &x, &y);
	printf("El producto de %i y %i es %i\n", x, y, producto(x, y));
}

