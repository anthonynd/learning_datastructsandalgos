#include "LinkedList.h"

int main()
{
    LinkedList list;
    list.append(2);
    list.print();
    list.append(4);
    list.print();
    list.append(3849);
    list.print();
    list.append(-2);
    list.print();
    list.append(0);
    list.print();
    list.append(2);
    list.print();
    list.get(0);
    list.get(1);
    list.get(2);
    list.get(3);
    list.get(4);
    list.get(5);
    list.indexOf(0);
    list.indexOf(1);
    list.indexOf(2);
    list.indexOf(3);
    list.indexOf(4);
    list.indexOf(5);
    list.indexOf(-2);
    list.indexOf(-1);
    list.indexOf(-5);
    list.indexOf(3849);
    list.indexOf(4000);
    list.insert(2, 400);
    list.print();
    list.insert(0, 100);
    list.print();
    list.insert(8, 200);
    list.print();
    list.pop();
    list.print();
    list.pop(4);
    list.print();
    list.pop(0);
    list.print();
    list.pop(5);
    list.print();
    list.remove(3);
    list.print();
    list.remove(0);
    list.print();
    list.remove(3);
    list.print();
    list.remove(2);
    list.print();
    return 0;
}
