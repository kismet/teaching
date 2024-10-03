#include "simple_list.h"
#include <iostream>

using namespace  std;

/**
* Inserimento in testa ad una lista
 */
void insert(Node* head, int value) {
 //Creo il nodo con il valore da inserire
 Node* nuovo = new Node;
 nuovo->info = value;

 //Aggancio il nodo alla lista
 nuovo->next = head->next;

 //Aggiorno la testa
 head->next = nuovo;
}

/**
 * Inserimento in coda ad una lista
 */
void append(Node* head, int value) {
 //Creo il nodo con il valore da inserire
 Node* nuovo = new Node;
 nuovo->info = value;

 //Cerco la fine della lista
 Node* last=head->next;
 while (last!=nullptr) {
  head=last;
  last=head->next;
 }
 //Head ora punta all'ultimo nodo e quindi ci aggiungo il nuovo nodo
 head->next = nuovo;
 //Non è necessario perchè li inizializzo correttamente
 nuovo->next = nullptr;
}

/**
 * Stampa il contenuto di una lista
 */
void print(Node* head) {
 Node* current = head->next;
 while (current!=nullptr) {
  cout<<current->info;
  current = current->next;
  if(current != nullptr) {
   cout<<" -> ";
  }
 }
 cout<<endl;
}

/**
 * Restituisce la dimensione della lista
 */
int size(Node* head) {
 int i=0;
 while (head->next!=nullptr) {
  i++;
  head = head->next;
 }
 return i;
}

/**
 * Cancella tutti gli elementi della lista
 */
void clear(Node* head) {
 Node* original = head;
 Node* prec = head;
 while(head!=nullptr) {
  head=head->next;
  delete prec;
  prec = head;
 }
 original->next = nullptr;
}

/**
 * Crea una copia della lista
 */
Node* copy(Node* head) {
 Node* current = head->next;
 //Creo la nuova testa e la chiamo copia
 Node* copia = new Node;

 //prec sarà il cursore per scorrere la lista che creo
 Node* prec = copia;
 while (current!=nullptr) {
  //Creo il nodo nuodo
  Node* aux = new Node;
  aux->info = current->info; //Copio il valore dalla vecchia lista

  //Aggiungo il nodo aux alla lista, lo aggancio al precedente
  prec->next = aux;

  //Vado al successivo della lista di origine
  current = current->next;
  //Sposto il precedente
  prec = prec->next;
 }
 return copia;
}

/**
 * Cerca un elemento nella lista e restituisce il nodo a cui appartiene
 */
Node* find(Node* head, int value) {
 while (head->next != nullptr) {
  if(head->info == value) return head;
  head = head->next;
 }
 return nullptr;
}

/**
 * Inverte l'ordinamento della lista
 */
void reverse(Node* head);

/**
 * Inserimento in coda ad una lista prima della posizione specificata restituisce false se la dimensione
 * della lista è troppo piccola
 */
bool insert(Node* head, int pos, int value);
