#include <stdio.h>
#include <limits.h>

int maximo(int arreglo[], int largo)
{
	int max = INT_MIN; // basta poner un número pequeño 
	for (int i=0; i < largo; i++)
	{
		if (arreglo[i] > max)
			max = arreglo[i];
	}
	return max;
}

int distintos(int arreglo[], int largo)
{
	for (int i=0; i < largo; i++)
		for (int j = i+1; j < largo; j++)
			if (arreglo[i] == arreglo[j])
				return 0;

	return 1;
}

int main(int argc, char **argv) 
{
	int arreglo[] = {35, 65, 32, 30, 12, 45};
	int arreglo2[] = {35, 65, 32, 30, 650, 45};
	printf("Maximo = %i\n", maximo(arreglo, 6));
	printf("Distintos = %i\n", distintos(arreglo2, 6));
}
