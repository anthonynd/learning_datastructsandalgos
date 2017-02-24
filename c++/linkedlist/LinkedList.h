#ifndef LINKED_LIST_H
#define LINKED_LIST_H

#include <iostream>
#include <stdio.h>

using std::cout;
using std::endl;

struct Node
{
    int value;
    Node* next;

    Node( int v, Node* n ): value( v ), next( n ) {}
};

class LinkedList 
{
    public:
        LinkedList();
        ~LinkedList();
        
        void append( int item );
        void clear();
        int get( int index );
        int getSize();
        int indexOf( int item );
        void insert( int index, int item );
        bool isEmpty();
        int pop();
        int pop( int index );
        void prepend( int item );
        bool remove( int item );
        void print();

    private:
        Node* head;
        Node* tail;
        int size;

        Node* getNode( int index );
        Node* popNode( int index );
};

LinkedList::LinkedList():
    head( NULL ),
    tail( NULL ),
    size( 0 )
{

}

LinkedList::~LinkedList()
{

}

void LinkedList::append( int item )
{
    printf(">> append(%i)\n", item);
    Node* n = new Node( item, NULL );
    if (size == 0)
    {
        head = n;
    }
    else
    {
        tail->next = n;
    }
    tail = n;
    ++size;
}

void LinkedList::clear()
{
    size = 0;
    head = NULL;
    tail = NULL;
}

int LinkedList::get( int index )
{
    int ret = getNode( index )->value;
    printf( ">> get(%i): %i\n", index, ret );
    return ret;
}

int LinkedList::getSize()
{
    return size;
}

int LinkedList::indexOf( int item )
{
    Node* n = head;
    int index = 0;
    while (n != NULL)
    {
        if (n->value == item)
        {
            // printf(">> indexOf(%i): %i\n", item, index);
            return index;
        }
        else
        {
            n = n->next;
            ++index;
        }
    }
    // printf(">> indexOf(%i): -1\n", item);
    return -1;
}

void LinkedList::insert( int index, int item )
{
    printf( ">> insert(%i, %i)\n", index, item );
    if (index < 0 || index > size)
    {
        throw std::out_of_range("Index out of list range.");
    }
    else if (index == 0)
    {
        prepend( item );
    }
    else if (index == size)
    {
        append( item );
    }
    else
    {
        // Get the node previous to the index specified
        Node* prev = getNode( index - 1 );

        // Create a new node, setting it's next pointer to point to the previous node's next
        Node* n = new Node( item, prev->next );

        // Set the previous node's next to now point to the new node
        prev->next = n;

        // Increment size
        ++size;
    }
}

bool LinkedList::isEmpty()
{
    return size == 0;
}

int LinkedList::pop()
{
    // Get the second last node in the list
    Node* secondLast = getNode(size - 2);

    // Get the value stored in the last node
    int popped = secondLast->next->value;

    // Set the next of the second last node to NULL
    secondLast->next = NULL;

    // Set the tail to now point to the second last node
    tail = secondLast;

    // Decrement size
    --size;

    printf(">> pop(): %i\n", popped);

    // Return the value that was stored in the last node
    return popped;
}

int LinkedList::pop( int index )
{
    int popped = popNode( index )->value;
    --size;
    printf( ">> pop(%i): %i\n", index, popped );
    return popped;
}

void LinkedList::prepend( int item )
{
    Node* n = new Node( item, NULL );
    if (size == 0)
    {
        tail = n;
    }
    else
    {
        n->next = head;
    }
    head = n;
    size++;
}

bool LinkedList::remove( int item )
{
    int index = indexOf( item );
    if (index == -1)
    {
        printf( ">> remove(%i): %s\n", item, "false");
        return false;
    }
    else
    {
        pop( index );
        printf( ">> remove(%i): %s\n", item, "true");
        return true;
    }
}

void LinkedList::print()
{
    Node* n = head;
    cout << " [";
    while (n != NULL)
    {
        if (n != head)
        {
            cout << ", ";
        }
        cout << n->value;
        n = n->next;
    }
    cout << "]" << endl;
}

Node* LinkedList::getNode( int index )
{
    if (index < 0 || index >= size)
    {
        throw std::out_of_range("Index out of list range");
    }
    else
    {
        Node* n = head;
        for (int i = 0; i < index; ++i)
        {
            n = n->next;
        }
        return n;
    }
}

Node* LinkedList::popNode( int index )
{
    if (index < 0 || index >= size)
    {
        throw std::out_of_range("Index out of list range");
    }
    else if (index == 0)
    {
        Node* popped = head;
        head = head->next;
        return popped;
    }
    else if (index == size - 1)
    {
        Node* popped = tail;
        Node* secondLast = getNode( size - 2 );
        secondLast->next = NULL;
        tail = secondLast;
        return popped;
    }
    else
    {
        Node* prev = getNode( index - 1 );
        Node* popped = prev->next;
        prev->next = popped->next;
        return popped;
    }
}

#endif
