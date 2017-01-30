using System;

class Stack 
{

    private int count = 0;
    private int initialSize;
    private int currentSize;
    private int[] stackArray;

    static void Main()
    {
        Stack MyStack = new Stack();
        // Put stack commands here
    }

    public Stack()
    {
        initialSize = 10;
        currentSize = initialSize;
        stackArray = new int[initialSize];
    }

    public Stack(int size)
    {
        initialSize = size;
        currentSize = initialSize;
        stackArray = new int[initialSize];
    }

    public void Push(int newItem)
    {
        if (count == currentSize) {
            currentSize *= 2;
            int[] temp = new int[currentSize];
            for (int i = 0; i < count; i++) {
                temp[i] = stackArray[i];
            }
            stackArray = temp;
        }

        stackArray[count] = newItem;
        count++;
    }

    public int Pop()
    {
        if (count == 0)
            throw new System.IndexOutOfRangeException("No item to pop in stack");
        else {
            int popped = Top();
            count--;
            return popped;
        }
    }

    public int Top()
    {
        return stackArray[count - 1];
    }

    public void Print()
    {
        Console.Write("[ ");
        for (int i = 0; i < count; i++) {
            Console.Write(stackArray[i] + " ");
        }
        Console.WriteLine("]");
    }

    public int Count
    {
        get { return count; }
    }
    
}
