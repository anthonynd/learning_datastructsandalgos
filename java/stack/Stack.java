import java.util.Scanner;

public class Stack {

    private int count = 0;
    private int initialSize;
    private int currentSize;
    private int[] stackArray;

    public static Stack StackTester;

    // public static void main(String[] args) {
    //     String s = "";
    //     while (!s.equals("q")) {
    //         Scanner scan = new Scanner(System.in);
    //         System.out.print(">> ");
    //         s = scan.next();

    //         handleInput(s);
    //     }
    // }

    public Stack() {
        initialSize = 10;
        currentSize = initialSize;
        stackArray = new int[initialSize];
    }

    public Stack(int size) {
        initialSize = size;
        currentSize = initialSize;
        stackArray = new int[size];
    }

    public int pop() {
        if (count ==  0)
            throw new ArrayIndexOutOfBoundsException("No items to pop in stack");
        else {
            int toPop = getTop();
            count--;
            return toPop;
        }
    }

    public void push(int newItem) {
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

    public int getCount() {
        return count;
    }

    public int getTop() {
        return stackArray[count - 1];
    }

    public void print() {
        System.out.print("[ ");
        for (int i = 0; i < count; i++) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println("]");
    }

    private static void handleInput(String s) {
        switch(s) {
            case "new":
                StackTester = new Stack();
                StackTester.print();
                System.out.println("");
                break;
            case "push":
                Scanner scan = new Scanner(System.in);
                int newItem = scan.nextInt();
                StackTester.push(newItem);
                StackTester.print();
                System.out.println("");
                break;
            case "pop":
                int popped = StackTester.pop();
                System.out.println(popped);
                StackTester.print();
                System.out.println("");
                break;
            case "count":
                System.out.println(StackTester.getCount());
                System.out.println("");
                break;
            case "print":
                StackTester.print();
                System.out.println("");
            case "q":
                break;
            case "default":
                break;
        }
    }

}
