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

int main()
{
    // creamos un arbol binario vacio
    nodo *root = NULL;

    // agregamos manualmente algunos nodos al arbol binario
    nodo *tmp = (nodo *)malloc(sizeof(nodo));
    tmp->data = 0;
    tmp->left = NULL;
    tmp->right = NULL;

    root = tmp;

    tmp = (nodo *)malloc(sizeof(nodo));
    tmp->data = 1;
    tmp->left = NULL;
    tmp->right = NULL;

    root->left = tmp;

    tmp = (nodo *)malloc(sizeof(nodo));
    tmp->data = 3;
    tmp->left = NULL;
    tmp->right = NULL;

    root->right = tmp;

    tmp = (nodo *)malloc(sizeof(nodo));
    tmp->data = 5;
    tmp->left = NULL;
    tmp->right = NULL;

    root->left->right = tmp;

    tmp = (nodo *)malloc(sizeof(nodo));
    tmp->data = 6;
    tmp->left = NULL;
    tmp->right = NULL;

    root->right->left = tmp;

    // aplicamos los recorridos
    show_nodes_inorder(root);
    printf("\n");
    show_nodes_preorder(root);
    printf("\n");
    show_nodes_postorder(root);
    printf("\n");

    return 0;
}

// muestra los nodos inorder (I, R, D)
void show_nodes_inorder(nodo *root)
{
    if (root == NULL) {
        return;
    }
    show_nodes_inorder(root->left);
    printf("%d ", root->data);
    show_nodes_inorder(root->right);

}

// muestra los nodos preorder (R, I, D)
void show_nodes_preorder(nodo *root)
{
    if (root == NULL) {
        return;
    }
    printf("%d ", root->data);
    show_nodes_preorder(root->left);
    show_nodes_preorder(root->right);

}

// muestra los nodos postorder (I, D, R)
void show_nodes_postorder(nodo *root)
{
    if (root == NULL) {
        return;
    }
    show_nodes_postorder(root->left);
    show_nodes_postorder(root->right);
    printf("%d ", root->data);

}