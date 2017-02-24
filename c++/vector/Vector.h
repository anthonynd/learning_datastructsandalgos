#ifndef VECTOR_H
#define VECTOR_H

#include <iostream>

class Vector {

    private:
        int itemCount;
        int initialCapacity;
        int currentCapacity;
        int *array;

        void resize( int newCapacity );
        void shiftDown( int index );
        void shiftUp( int index );

    public:
        Vector();
        ~Vector();

        void append( int item );
        void clear();
        int count();
        int indexOf( int item );
        void insert( int index, int item );
        bool isEmpty();
        int pop();
        int pop( int index );
        void print();
        bool remove( int item );
        int operator[]( int index ) 
        {
            if (index < 0 || index >= itemCount)
                throw std::out_of_range("Index out of vector range");
            return array[index];
        }

};

/**
 * Constructor
 */
Vector::Vector():
    itemCount( 0 ),
    initialCapacity( 16 ),
    currentCapacity( initialCapacity ),
    array( new int[currentCapacity] )
{

}

/**
 * Destructor
 */
Vector::~Vector()
{
    delete [] array;
}

/**
 * Appends an item to the end of the vector
 *
 * @param item the item to append
 */
void Vector::append( int item )
{
    insert( itemCount, item );
}

/**
 * Clears all items
 */
void Vector::clear()
{
    if (currentCapacity > initialCapacity)
    {
        delete [] array;
        array = new int[initialCapacity];
        currentCapacity = initialCapacity;
    }
    itemCount = 0;
}

/**
 * Returns the number of items
 *
 * @return number of items in the vector
 */
int Vector::count() 
{
    return itemCount;
}

/**
 * Returns index of the first occurrence of the item
 *
 * @param item item to look for
 * @return item index
 */
int Vector::indexOf( int item )
{
    for (int i = 0; i < itemCount; i++)
    {
        if (array[i] == item)
            return i;
    }
    return -1;
}

/**
 * Inserts an item at the specified index and pushes following items down
 *
 * @param index index to insert at
 * @param item item to insert
 */
void Vector::insert( int index, int item ) 
{
    if (itemCount >= currentCapacity)
    {
        currentCapacity *= 2;
        resize( currentCapacity );
    }
    shiftDown( index );
    array[index] = item;
    itemCount++;
}

/**
 * Checks whether the vector is empty
 *
 * @return true if empty, false if not
 */
bool Vector::isEmpty() 
{
    return itemCount == 0;
}

int Vector::pop()
{
    pop(itemCount - 1);
}

/**
 * Removes and returns the item at specified index
 * If no index is specified, removes and returns last item
 *
 * @param index (optional) index of item to remove
 * @return last item
 */
int Vector::pop( int index ) 
{
    if (itemCount <= 0)
        throw std::out_of_range("Vector underflow");
    int popped = array[index];
    shiftUp( index );
    itemCount--;
    return popped;
}

/**
 * Prints the vector
 */
void Vector::print()
{
    std::cout << "[";
    for (int i = 0; i < itemCount; i++)
    {
        if (i == 0)
            std::cout << array[i];
        else
            std::cout << ", " << array[i];
    }
    std::cout << "]" << std::endl;
}

/**
 * Removes the first occurrence of item and shifts following items up
 *
 * @param item item to remove
 * @return true on successful removal, false otherwise
 */
bool Vector::remove( int item ) 
{
    int index = indexOf( item );
    if (index < 0)
        return false;
    else
    {
        shiftUp(index);
        itemCount--;
    }
}

/**
 * Resizes the internal array
 *
 * @param newCapacity new array size
 */
void Vector::resize( int newCapacity )
{
    int *newArr = new int[newCapacity];
    for (int i = 0; i < itemCount; i++)
    {
        newArr[i] = array[i];
    }
    delete [] array;
    array = newArr;
}

/**
 * Shifts all items downwards starting from the specified index
 *
 * @param index shift down from this index
 */
void Vector::shiftDown( int index )
{
    for (int i = itemCount-1; i >= index; i--)
    {
        array[i+1] = array[i];
    }
}

/**
 * Shifts items up to specified index
 *
 * @param index shift up to this index
 */
void Vector::shiftUp( int index )
{
    if (index >= itemCount - 1)
        return;
    
    for (int i = index; i < itemCount; i++)
    {
        array[i] = array[i+1];
    }
}

#endif
