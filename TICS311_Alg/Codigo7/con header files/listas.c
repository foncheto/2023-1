#include "listas.h"

// inserta dato valor al final de la lista (permitimos repeticiones de datos)
void insertar_elemento(nodo **ref_lista, int valor)
{
    if (*ref_lista == NULL) { // caso lista vacia
        nodo *nuevo_nodo = (nodo *)malloc(sizeof(nodo));
        nuevo_nodo->x = valor; 
        nuevo_nodo->next = NULL; 
        *ref_lista = nuevo_nodo;  // aca es donde modificamos la variable lista
    }
    else { // caso lista no vacia
        nodo *it = *ref_lista;
        while(it->next != NULL) { // importante preguntar it->next != NULL y no it != NULL
            it = it->next;
        }
        nodo *nuevo_nodo = (nodo *)malloc(sizeof(nodo));
        nuevo_nodo->x = valor; 
        nuevo_nodo->next = NULL; 
        it->next = nuevo_nodo;
    }
}
    
// imprime la lista completa
void imprimir_lista(nodo *lista)
{
    // recorrido simple de la lista
    nodo *it = lista;
    while(it != NULL) {
        printf("%d ", it->x);
        it = it->next;
    }
    printf("\n");
}

// funcion auxiliar
void swap(nodo *nodo1, nodo *nodo2)
{
    int tmp = nodo1->x;
    nodo1->x = nodo2->x;
    nodo2->x = tmp;
}

// ordena los datos de la lista (usa bubble sort)
void ordenar_lista(nodo *lista)
{
    if (lista == NULL){ // caso lista vacia
        return;
    }

    int swapped = 1; // indica si hubo algun swap
    nodo *it = lista;
    while(swapped == 1){
        swapped = 0;
        while (it->next != NULL){
            if(it->x > it->next->x) { // debemos hacer swap
                swap(it, it->next);
                swapped = 1;    
            }
            it = it->next;
        }
        it = lista; // inicializamos el iterador it
    }
}

// borra dato valor de la lista (si es que esta) 
// (si hay repeticiones borra la primera ocurrencia de valor)
void borrar_elemento(nodo **ref_lista, int valor)
{
  if(*ref_lista == NULL) { // caso lista vacia
    return;
  }

  if((*ref_lista)->x == valor) { // caso que hay que borrar primer elemento
    nodo* tmp = *ref_lista;
    *ref_lista = (*ref_lista)->next; // aca modificamos la variable lista
    free(tmp); // importante liberar la memoria del nodo
    return;
  }

  // caso general
  nodo *it = *ref_lista;
  while(it->next != NULL && it->next->x != valor) { 
    it = it->next;
  }
 
  if(it->next == NULL) { // caso que valor no esta en la lista
    return;
  }

  // sabemos que valor esta en la lista y hay que borrarlo
  nodo* tmp = it->next; 
  it->next = it->next->next;
  free(tmp); // importante liberar la memoria del nodo
}

// asumiendo que la lista esta ordenada, inserta el dato valor donde corresponde
void insertar_elemento_orden(nodo **ref_lista, int valor)
{
    if (*ref_lista == NULL) { // caso lista vacia
        nodo *nuevo_nodo = (nodo *)malloc(sizeof(nodo));
        nuevo_nodo->x = valor; 
        nuevo_nodo->next = NULL; 
        *ref_lista = nuevo_nodo; // aca modificamos la variable lista
        return;
    }

    if ((*ref_lista)->x >= valor) { // caso hay que insertar al principio de la lista
        nodo *nuevo_nodo = (nodo *)malloc(sizeof(nodo));
        nuevo_nodo->x = valor;
        nodo *tmp = *ref_lista; 
        *ref_lista = nuevo_nodo; // aca modificamos la variable lista
        nuevo_nodo->next = tmp;
        return;
    }

    nodo *it = *ref_lista;
    // buscamos el primer nodo tal que el dato del siguiente nodo es >= valor
    while(it->next != NULL && it->next->x < valor) {
        it = it->next;
    }
   
    nodo *nuevo_nodo = (nodo *)malloc(sizeof(nodo));
    nuevo_nodo->x = valor;
    nodo *tmp = it->next; 
    it->next = nuevo_nodo;
    nuevo_nodo->next = tmp;
}
