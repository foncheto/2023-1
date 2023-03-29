#include <stdio.h>

void invertir(char *str)
{
	if (*str == '\0')
		return;

	invertir(str+1); // imprimimos el inverso del substring str[1..]
	printf("%c", *str); // imprimimos str[0]
}

int main(void) 
{
	char *str = "Hola como estas";
	invertir(str);
	printf("\n");
}

