#include <stdio.h>
#include <stdlib.h>

// estructura para nodos de arboles binarios
typedef struct n {
  int data;
  struct n *left;
  struct n *right;
} nodo;

// muestra los nodos inorder (I, R, D)
void show_nodes_inorder(nodo *root);
// muestra los nodos preorder (R, I, D)
void show_nodes_preorder(nodo *root);
// muestra los nodos postorder (I, D, R)
void show_nodes_postorder(nodo *root);

// inserta el dato data al arbol binario de busqueda
// retorna la nueva raiz del arbol
nodo *insert(nodo *node, int data);

// busca el dato data en el arbol binario de busqueda
// retorna el primer nodo que se encuentra con el dato, o
// si el dato no esta se retorna NULL
nodo *search(nodo *root, int data);

// funcion auxiliar: entrega el nodo con el valor minimo
// (el de mas a la izquierda)
nodo *minValueNode(nodo *node); 

// funcion auxiliar: entrega el nodo con el valor maximo
// (el de mas a la derecha)
nodo *maxValueNode(nodo *node); 

// elimina el primer nodo con dato igual a data
// retorna la nueva raiz del arbol
nodo *delete(nodo *root, int data);


