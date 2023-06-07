#include <stdio.h>
#include <stdlib.h>

// estructura para nodos de arboles AVL
typedef struct n {
  int data;
  struct n *left;
  struct n *right;
  int height;
} nodo;

// muestra los nodos inorder (I, R, D)
void show_nodes_inorder(nodo *root);
// muestra los nodos preorder (R, I, D)
void show_nodes_preorder(nodo *root);
// muestra los nodos postorder (I, D, R)
void show_nodes_postorder(nodo *root);

// funcion auxiliar maximo
int max(int a, int b);

// funcion auxiliar height
// entrega la altura del nodo node
// OJO: la convencion es que si node == NULL entonces height = -1
int height(nodo *node);

// funcion auxiliar balance
// retorna el height del hijo izquierdo menos el height del hijo derecho
// Si node == NULL, entregamos 0
int balance(nodo *node);

// rota hacia la derecha el arbol con raiz y
// asume que la raiz y junto al pivote y->left no son NULL
// retorna la nueva raiz del arbol
nodo *rightRotate(nodo *y);

// rota hacia la izquiera el arbol con raiz x
// asume que la raiz x junto al pivote x->right no son NULL
// retorna la nueva raiz del arbol
nodo *leftRotate(nodo *x);


// inserta el dato data al arbol AVL
// retorna la nueva raiz del arbol
nodo *insert(nodo *node, int data);

// busca el dato data en el arbol AVL
// retorna el primer nodo que se encuentra con el dato, o
// si el dato no esta se retorna NULL
nodo *search(nodo *root, int data);


