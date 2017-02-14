public class Algorithms {
    
    public static void main(String[] args) {
        int[] array = {1, 3, 5, 10, 12, 20, 34, 42, 59};
        testFind(array, 1, true);
        testFind(array, 2, false);
        testFind(array, 3, true);
        testFind(array, 4, false);
        testFind(array, 5, true);
        testFind(array, 7, false);
        testFind(array, 10, true);
        testFind(array, 11, false);
        testFind(array, 12, true);
        testFind(array, 15, false);
        testFind(array, 20, true);
        testFind(array, 29, false);
        testFind(array, 34, true);
        testFind(array, 39, false);
        testFind(array, 42, true);
        testFind(array, 49, false);
        testFind(array, 59, true);
        testFind(array, 71, false);
    }

    public static void testFind(int[] array, int val, boolean b) {
        System.out.println("Testing for (" + val + ") -> ");
        if (binarySearch(array, val) == b)
            System.out.println("\tTest passed.");
        else
            System.out.println("\tTest FAILED.");
        System.out.println("");
    }

    public static void find(int[] array, int val) {
        if (binarySearch(array, val))
            System.out.println(val + " found");
        else
            System.out.println(val + " not found.");
    }

    public static boolean binarySearch(int[] array, int val) {
        return binarySearch(array, val, 0, array.length-1);
    }

    private static boolean binarySearch(int[] array, int val, int low, int high) {
        if (low > high)
            return false;

        int mid = (high+low)/2;

        if (array[mid] == val)
            return true;
        else if (array[mid] < val)
            return binarySearch(array, val, mid+1, high);
        else
            return binarySearch(array, val, low, mid-1);
    }

}
