//
// Created by stefy on 12/12/2024.
//

#include "binary_tree.h"
#include <iostream>

#include "../../../../../../../Program Files/JetBrains/CLion 2024.1.4/bin/mingw/x86_64-w64-mingw32/include/stdint.h"

using namespace std;

int max(int x, int y) {
    if(x > y) return x;
    return y;
}

int tree_deep(BinaryTree_t *root) {
    int deep = 0;
    if(root == nullptr) {
        return deep;
    } else {
        deep = 1;
        deep += max( tree_deep(root->left), tree_deep(root->right));
        return deep;
    }
}

int leaves(BinaryTree *root) {
    if(root == nullptr) {
        return 0;
    }else if( root->left == nullptr && root->right == nullptr) {
        return 1;
    }else {
        return leaves(root->left)+leaves(root->right);
    }
}

void breadth_visit_pre(BinaryTree *root) {
    if(root == nullptr) {
        return ;
    } else {
        cout<<root->info;
        breadth_visit_pre(root->left);
        breadth_visit_pre(root->right);
    }
}

void visit_level(BinaryTree *root, int l) {
    if(root == nullptr)
        return;
    if(l == 0) { // && root != nullptr
        cout<<root->info<<" ";
        return;
    }
    visit_level(root->left, l-1);
    visit_level(root->right, l-1);
}

void breadth_visit(BinaryTree *root) {
    int n = tree_deep(root);
    for (int i = 0; i < n; i++ ) {
        visit_level(root, i);
    }
}


void deep_visit_pre(BinaryTree *root) {
    if(root == nullptr) {
        return;
    }
    cout<<root->info<<" ";
    deep_visit_pre(root->left);
    deep_visit_pre(root->right);
}

void deep_visit_simmetric(BinaryTree *root) {
    if(root == nullptr) {
        return;
    }
    deep_visit_simmetric(root->left);
    cout<<root->info<<" ";
    deep_visit_simmetric(root->right);
}

void deep_visit_post(BinaryTree *root) {
    if(root == nullptr) {
        return;
    }
    deep_visit_post(root->left);
    deep_visit_post(root->right);
    cout<<root->info<<" ";
}

BinaryTree* leftmost_while(BinaryTree* root) {
    BinaryTree* last=root;
    while(root!=nullptr) {
        last=root;
        root=root->left;
    }
    return last;
}

BinaryTree* leftmost(BinaryTree* root) {
    if(root == nullptr) return nullptr;
    if(root->left == nullptr) return root;
    return leftmost(root->left);
}

BinaryTree* search(BinaryTree* root, int value) {
    if(root==nullptr) return nullptr;
    if(root->info == value) return root;
    if(root->info > value) return search(root->left, value);
    return search(root->right, value);
}
//Posso farla non ricorsiva? Si perchè vado sempre da un lato solo
BinaryTree* search_while(BinaryTree* root, int value) {
    BinaryTree* last=root;
    while(root!=nullptr) {
        if(root->info > value) root=root->left;
        else if(root->info < value) root=root->right;
        else return root;
    }
    return root;
}

BinaryTree* balanced_right_recursive(BinaryTree* root,int& w) {
    if(root==nullptr) {
        w=0;
        return nullptr;
    }else if(root->right == root->left) {
        return root;
    }
    BinaryTree* aux = nullptr;
    int dx=w+1;
    BinaryTree* right = balanced_right_recursive(root->right,dx);
    int sx=w-1;
    BinaryTree* left = balanced_right_recursive(root->left,sx);
    if(right != nullptr && dx > w) {
        w = dx;
        aux = right;
    }
    if(left != nullptr && sx > w) {
        w = sx;
        aux = left;
    }
    return aux;
}
BinaryTree* balanced_right(BinaryTree* root) {
    int weight = 0;
    return balanced_right_recursive(root,weight);
}

bool isBinarySearchTree(BinaryTree* root) {
    if(root == nullptr) return true;
    if(root->left == root->right) return true;
    if(root->left != nullptr) {
        if(root->info<=root->left->info) return false;
        if(!isBinarySearchTree(root->left)) return false;
    }
    if(root->right != nullptr) {
        if(root->info>=root->right->info) return false;
        if(!isBinarySearchTree(root->right)) return false;
    }
    return true;
}

int min(BinaryTree* root) {
    if(root == nullptr) {
        //Non ha senso trovare il minimo di un albero vuoto
        //la funzione potrebbe restituire qualunque valore
        return INT_MAX;
    }
    int minum = root->info;
    if( root->left != nullptr ) {
        int left = min(root->left);
        if(left < minum) minum = left;
    }
    if( root->right != nullptr ) {
        int right = min(root->right);
        if(right < minum) minum = right;
    }
    return minum;
}

int max(BinaryTree* root) {
    if(root == nullptr) {
        return INT_MIN;
    }
    int maximum = root->info;
    int left = max(root->left);
    int right = max(root->right);
    if(left > maximum) maximum = left;
    if(right > maximum) maximum = right;
    return maximum;
}

bool isSearchBinaryTree(BinaryTree* root) {
    if(root == nullptr) return root;
    if(root->left == root->right) return true;

    bool result = true;
    if(root->left!=nullptr && (root->info > min(root->right) ) ){
        result = false;
    }
    if(root->right!=nullptr && (root->info < max(root->right) ) ){
        result = false;
    }
    return result;
}
