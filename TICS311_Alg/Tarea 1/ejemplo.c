#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// tamaño máximo de linea en los archivos
#define MAX_LINE_SIZE 1000

int main(int argc, char **argv) 
{
	/* 
	Asumimos que cada linea del archivo contiene substrings separados por espacio. 
	Este codigo lee el archivo linea por linea y va imprimiendo estos substrings. 
	*/
	FILE *fp = fopen("ejemplo.txt", "r");
	char buffer[MAX_LINE_SIZE]; 
	char *token; // almacena los substrings

	while(fgets(buffer, MAX_LINE_SIZE, fp) != NULL) {
		token = strtok(buffer, " "); // extraemos el 1er substring de la linea
		while(token != NULL) { // extraemos los siguientes substrings
			printf("%s\n", token);
			token = strtok(NULL, " ");
		}
	}

	fclose(fp);
	return 0;
}


