#include "listas.h"
// Inserta el valor data al comienzo de la lista
// Retorna un puntero al primer nodo de la nueva lista
// OJO: el elemento se debe insertar al comienzo de la lista
nodo_lista *insertar_lista(nodo_lista *lista, int data)
{
    nodo_lista *tmp = (nodo_lista *)malloc(sizeof(nodo_lista));
    tmp->data = data;
    tmp->next = lista;
    lista = tmp;
    return lista;
}

// Busca el valor data en la lista partiendo desde el comienzo hasta el final
// Retorna un puntero al primer nodo con dato data, en caso que exista
// Si no, retorna NULL
nodo_lista *buscar_lista(nodo_lista *lista, int data)
{
    nodo_lista *nodo_actual = lista;
    while (nodo_actual != NULL)
    {
        if (nodo_actual->data == data)
        {
            return nodo_actual;
        }
        nodo_actual = nodo_actual->next;
    }
    return NULL;
}

// Imprime en pantalla la lista desde el comienzo hasta el final
void imprimir_lista(nodo_lista *lista)
{
    nodo_lista *nodo_actual = lista;
    while (nodo_actual != NULL)
    {
        printf("%d ", nodo_actual->data);
        nodo_actual = nodo_actual->next;
    }
}