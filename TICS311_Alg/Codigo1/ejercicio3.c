#include <stdio.h>

int largo(char string[])
{
	int count = 0;
	for (int i=0; string[i] != '\0'; i++)
		count++;
	return count;
}

int iguales(char string1[], char string2[])
{
	if (largo(string1) != largo(string2))
		return 0;
	for (int i=0; i < largo(string1); i++)
		if (string1[i] != string2[i])
			return 0;

	return 1;
}

int main(int argc, char **argv) 
{
	printf("Largo = %i\n", largo("Kloklo"));
	printf("Iguales = %i\n", iguales("Klo", "Klhoe"));
}
