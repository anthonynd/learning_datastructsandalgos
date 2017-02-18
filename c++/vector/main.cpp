#include <iostream>
#include "Vector.h"

int main() 
{
    int arr[] = {14, 0, 12, 67, 1, 9, 26, 31, -7, -10, 1, 2, 48, 19, 29, 90, 100};
    Vector v;
    for (int i = 0; i < sizeof(arr)/sizeof(int); i++)
    {
        v.append(arr[i]);
        std::cout << v.count() << ": ";
        v.print();
    }
    std::cout << "Index of 67: " << v.indexOf(67) << std::endl;
    v.insert(0, 243);
    std::cout << v.count() << ": ";
    v.print();
    v.remove(69);
    std::cout << v.count() << ": ";
    v.print();
    v.remove(67);
    std::cout << v.count() << ": ";
    v.print();
    int vSize = v.count();
    for (int i = 0; i < vSize; i++)
    {
        v.pop();
        std::cout << v.count() << ": ";
        v.print();
    }
    return 0;
}
