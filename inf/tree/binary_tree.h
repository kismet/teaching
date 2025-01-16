//
// Created by stefy on 12/12/2024.
//

typedef struct BinaryTree {
    int info;
    BinaryTree *left = nullptr;
    BinaryTree *right = nullptr;
} BinaryTree_t;

int tree_deep(BinaryTree_t *root);
void breadth_visit(BinaryTree *root);

void deep_visit_pre(BinaryTree *root);
void deep_visit_simmetric(BinaryTree *root);
void deep_visit_post(BinaryTree *root);

BinaryTree* balanced_right(BinaryTree* root);
BinaryTree* search(BinaryTree* root, int value);
BinaryTree* search_while(BinaryTree* root, int value);

BinaryTree* leftmost(BinaryTree* root);
BinaryTree* leftmost_while(BinaryTree* root);


#ifndef BINARY_TREE_H
#define BINARY_TREE_H

#endif //BINARY_TREE_H
