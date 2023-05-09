#include <stdlib.h>
#include <stdio.h>

/* estructura nodo para trabajar con listas enlazadas
int x es el dato a guardar. 
En general, los datos podrian ser varias variables de cualquier tipo 
(por ejemplo estructuras) */ 

typedef struct n {   
  int x;
  struct n *next;
} nodo;

/* OJO: las funciones insertar_elemento, borrar_elemento e insertar_elemento_orden
deben modificar la lista por lo cual el parametro es un puntero a la lista, 
es decir, una variable de tipo nodo **ref_lista */ 

// inserta dato valor al final de la lista (permitimos repeticiones de datos)
void insertar_elemento(nodo **ref_lista, int valor);

// borra dato valor de la lista (si es que esta) 
// (si hay repeticiones borra la primera ocurrencia de valor)
void borrar_elemento(nodo **ref_lista, int valor);

// imprime la lista completa
void imprimir_lista(nodo *lista);

// funcion auxiliar
void swap(nodo *nodo1, nodo *nodo2);

// ordena los datos de la lista (usa bubble sort)
void ordenar_lista(nodo *lista);

// asumiendo que la lista esta ordenada, inserta el dato valor donde corresponde
void insertar_elemento_orden(nodo **ref_lista, int valor);
