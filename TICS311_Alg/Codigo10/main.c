#include <stdio.h>
#include "avl.h"

int main()
{
  // creamos un AVL vacio
  nodo *root = NULL;

  // en el caso del AVL al insertar el root siempre puede cambiar (por las rotaciones)
  // importante siempre escribir root = insert(root, data)
  root = insert(root, 6);
  root = insert(root, 8);
  root = insert(root, 11);
  root = insert(root, 15);
  root = insert(root, 12);
  root = insert(root, 13);
  root = insert(root, 14);

  show_nodes_preorder(root); // notar que el arbol queda balanceado
  printf("\n");
  show_nodes_inorder(root);
  printf("\n");

  // obtenemos altura 2 en vez de altura 6 (caso no balanceado)
  printf("%d\n", root->height);  

  return 0;
}

