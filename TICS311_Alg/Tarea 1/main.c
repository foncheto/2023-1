#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// tamaño máximo de linea en los archivos
#define MAX_LINE_SIZE 1000

typedef struct v {
	float *data;
	int dim;
} vector;

typedef struct m {
	float **data;
	int row_dim;
	int col_dim;
} matrix;

// declaración de las funciones
vector create_vector_from_file(char *filename);
matrix create_matrix_from_file(char *filename):
void print_vector(vector v);
void print_matrix(matrix A);
void destroy_vector(vector v);
void destroy_matrix(matrix A);
matrix transpose_matrix(matrix A);
matrix sum_matrix_matrix(matrix A, matrix B);
matrix mult_matrix_matrix(matrix A, matrix B);
vector mult_matrix_vector(matrix A, vector v);


int main(int argc, char **argv) 
{
	
	// Acá haga sus tests 

	return 0;
}

// Acá puede definir sus funciones

