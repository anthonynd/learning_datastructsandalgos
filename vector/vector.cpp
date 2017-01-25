#include <iostream>

class Vector {

    private:
        int itemCount;
        int initialCapacity;
        int currentCapacity;

        int *array;

        void resize( int newCapacity );

    public:
        Vector( int len );
        ~Vector();

        int size();
        int capacity();
        bool isEmpty();
        int at( int index );
        void push( int item );
        void insert( int index, int item );
        void prepend( int item );
        void pop();
        void removeAt( int index );
        void remove( int item );
        int find( int item );

};



int main() {
    return 0;
}
