#include <stdlib.h>
#include <stdio.h>

/* estructura nodo para trabajar con listas enlazadas
int data es el dato a guardar. */ 

typedef struct n {   
  int data;
  struct n *next;
} nodo_lista;

// Inserta el valor data al comienzo de la lista
// Retorna un puntero al primer nodo de la nueva lista
// OJO: el elemento se debe insertar al comienzo de la lista
nodo_lista *insertar_lista(nodo_lista *lista, int data);

// Busca el valor data en la lista partiendo desde el comienzo hasta el final
// Retorna un puntero al primer nodo con dato data, en caso que exista
// Si no, retorna NULL
nodo_lista *buscar_lista(nodo_lista *lista, int data);

// Imprime en pantalla la lista desde el comienzo hasta el final
void imprimir_lista(nodo_lista *lista);

