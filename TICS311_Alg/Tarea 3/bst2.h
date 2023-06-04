#include <stdio.h>
#include <stdlib.h>

// Estructura para usuarios
typedef struct u {
  char nombre[100]; 
  int edad;
} usuario;

// Estructura para nodos de arboles binarios
// el data es de tipo usuario
typedef struct m {
  usuario data;
  struct m *left;
  struct m *right;
} nodo_arbol;

// Inserta el usuario data en el arbol binario de busqueda
// Retorna la nueva raiz del arbol
nodo_arbol *insertar_arbol(nodo_arbol *node, usuario data);

// Imprime en pantalla el árbol en orden inorder
void imprimir_arbol_inorder(nodo_arbol *root);

