#include <stdio.h>
#include "bst.h"

int main()
{
  nodo *root = NULL;

  root = insert(root, 10);
  root = insert(root, 5);
  root = insert(root, 15);
  root = insert(root, 11);
  root = insert(root, 20);
  root = insert(root, 3);
  root = insert(root, 6);
  root = insert(root, 13);


  show_nodes_inorder(root);
  printf("\n");

/*
  nodo *out = search(root, 6);
  if (out == NULL) {
    printf("El dato no esta\n");
  }
  else {
    printf("%d\n", out->data);
  }
  */

  root = delete(root, 10);
  root = delete(root, 12);
  root = delete(root, 6);
  root = delete(root, 11);

  show_nodes_inorder(root);
  printf("\n");

  printf("El dato del root es: %d\n", root->data);


  return 0;
}

