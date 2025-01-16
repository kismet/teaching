#include "binary_tree.h"
#include <iostream>

using namespace std;



int main(int argc, char* argv[]) {
    BinaryTree *root = new BinaryTree;
    root->info = 10;
    root->left = new BinaryTree;
    root->left->info = 4;
    root->left->left = new BinaryTree;
    root->left->left->info = 1;
    root->left->right = new BinaryTree;
    root->left->right->info = 7;
    root->left->right->right = new BinaryTree;
    root->left->right->right->info = 9;
    root->right = new BinaryTree;
    root->right->info = 20;

    cout<<"POST: ";
    deep_visit_post(root);
    cout<<endl;

    cout<<"SIM.: ";
    deep_visit_simmetric(root);
    cout<<endl;

    cout<<"PRE : ";
    deep_visit_pre(root);
    cout<<endl;

    cout<<"LVL : ";
    breadth_visit(root);
    cout<<endl;

    cout<<"Left Mostest:"<<leftmost(root)->info<<endl;
    cout<<"Left Mostest:"<<leftmost_while(root)->info<<endl;
    cout<<"Balanced Right Most:"<<balanced_right(root)->info<<endl;

}
