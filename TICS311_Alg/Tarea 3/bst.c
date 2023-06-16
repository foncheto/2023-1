#include "bst.h"

// Inserta el valor data en el arbol binario de busqueda
// Retorna la nueva raiz del arbol
nodo_arbol *insertar_arbol(nodo_arbol *node, int data)
{
    nodo_arbol *nuevo_nodo = (nodo_arbol *)malloc(sizeof(nodo_arbol));
    nuevo_nodo->data = data;
    nuevo_nodo->left = NULL;
    nuevo_nodo->right = NULL;

    if (node == NULL)
    {
        // Si el árbol está vacío, el nuevo nodo se convierte en la raíz
        return nuevo_nodo;
    }
    else
    {
        // Si el árbol no está vacío, insertamos el nuevo nodo como la nueva raíz
        nuevo_nodo->left = node;
        return nuevo_nodo;
    }
}

// Busca el valor data en el arbol binario de busqueda
// Retorna el primer nodo que se encuentra con valor data, si existe,
// o NULL en caso contrario
nodo_arbol *buscar_arbol(nodo_arbol *root, int data)
{
    nodo_arbol *nodo_actual = root;
    while (nodo_actual != NULL)
    {
        if (nodo_actual->data == data)
        {
            return nodo_actual;
        }
        if (data < nodo_actual->data)
        {
            nodo_actual = nodo_actual->left;
        }
        else
        {
            nodo_actual = nodo_actual->right;
        }
    }
    return NULL;
}

// Retorna la altura del arbol
int altura_arbol(nodo_arbol *root)
{
    nodo_arbol *nodo_actual = root;
    int altura = 0;
    while (nodo_actual != NULL)
    {
        altura++;
        if (nodo_actual->left != NULL)
        {
            nodo_actual = nodo_actual->left;
        }
        else
        {
            nodo_actual = nodo_actual->right;
        }
    }
    return altura;
}

// Imprime en pantalla el árbol en orden inorder
void imprimir_arbol_inorder(nodo_arbol *root)
{
    nodo_arbol *nodo_actual = root;
    if (nodo_actual != NULL)
    {
        imprimir_arbol_inorder(nodo_actual->left);
        printf("%d ", nodo_actual->data);
        imprimir_arbol_inorder(nodo_actual->right);
    }
}
