public class BinarySearchTree {

    public static void main(String[] args) {
        int[] arr = {22, 7, 20, 34, 2, 0, 31, -4, 43, 13, 28, 100, 2};

        BinarySearchTree b = new BinarySearchTree();
        System.out.println("size: " + b.getSize());

        for (int i : arr)
            b.add(i);

        for (int j : arr) {
            System.out.println("Contains (" + j + ") -> ");
            if (b.contains(j))
                System.out.println("  Test passed.");
            else
                System.out.println("  TEST FAILED.");
            System.out.println("Contains (" + (j+1) + ") -> ");
            if (b.contains(j+1) == false)
                System.out.println("  Test passed.");
            else
                System.out.println("  TEST FAILED.");
            System.out.println("");
        }

        System.out.println("Min -> ");
        if (b.min() == -4)
            System.out.println("  Test passed.");
        else
            System.out.println("  TEST FAILED.");
        System.out.println("");
        System.out.println("Max -> ");
        if (b.max() == 100)
            System.out.println("  Test passed.");
        else
            System.out.println("  TEST FAILED.");
        System.out.println("");

        System.out.println("size: " + b.getSize());
        printBST(b);
        b.remove(100);
        printBST(b);
        b.remove(-4);
        printBST(b);
        b.remove(7);
        printBST(b);
        b.remove(34);
        printBST(b);
        b.remove(43);
        printBST(b);
        b.remove(22);
        printBST(b);
    }

    public static void printBST(BinarySearchTree b) {
        System.out.println("Print: ");
        System.out.print("    ");
        b.print();
        System.out.println("");
    }

    protected class Node {

        private Node parent;
        private Node left;
        private Node right;
        private int key;

        public Node(int k) {
            this.key = k;
            this.parent = null;
            this.left = null;
            this.right = null;

        }

        public int getKey() {
            return key;
        }

        public void setKey(int k) {
            this.key = k;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node p) {
            this.parent = p;
        }

        public Node getLeft() {
            return left;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public void setLeft(Node l) {
            this.left = l;
        }

        public Node getRight() {
            return right;
        }

        public boolean hasRight() {
            return right != null;
        }

        public void setRight(Node r) {
            this.right = r;
        }

        public boolean hasNoChildren() {
            return left == null && right == null;
        }

        public boolean hasOneChild() {
            return (left != null && right == null) || (left == null && right != null);
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public Node getChild () {
            if (hasOneChild() == false)
                return null;

            if (hasLeft())
                return left;
            if (hasRight())
                return right;
            return null;
        }

        public boolean isLeftChild() {
            return this == parent.getLeft();
        }

        public boolean isRightChild() {
            return this == parent.getRight();
        }

    }

    /**
     * BST Fields
     */
    private Node root;
    private int size;

    /**
     * BST Constructor
     */
    public BinarySearchTree() {
        size = 0;
        root = null;
    }

    /**
     * Inserts a key into the tree
     * @param int k key
     */
    public void add(int k) {
        if (isEmpty()) {
            createRoot(k);
        } else {
            Node prev = null;
            Node curr = root;
            while(true) {
                if (curr == null) {
                    curr = new Node(k);
                    curr.setParent(prev);
                    if (k > prev.getKey())
                        prev.setRight(curr);
                    else
                        prev.setLeft(curr);
                    break;
                }
                else if (k > curr.getKey()){
                    prev = curr;
                    curr = curr.getRight();
                } else {
                    prev = curr;
                    curr = curr.getLeft();
                }
            }
        }
        size++;
    }

    /**
     * Clears all elements from the tree
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Checks whether the tree contains the specified key
     * @param  int k key
     * @return true if the key exists in the tree, false otherwise
     */
    public boolean contains(int k) {
        return findInTree(root, k) != null;
    }

    /**
     * Returns the largest key in the tree
     * @return Largest key in the tree
     */
    public int max() {
        return max(root).getKey();
    }

    /**
     * Returns the smallest key in the tree
     * @return Smallest key in the tree
     */
    public int min() {
        return min(root).getKey();
    }

    /**
     * Returns the number of elements in the tree
     * @return Size of tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks whether the tree is empty or not
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Prints the tree with in-order traversal
     */
    public void print() {
        printTree(root);
    }

    /**
     * Removes a key from the tree
     * @param  int k key
     * @return     [description]
     */
    public boolean remove(int k) {
        Node toRemove = find(k);

        System.out.println("");
        System.out.println("Removing " + toRemove.getKey());

        if (toRemove == null)
            return false;

        Node parent = toRemove.getParent();

        if (toRemove.hasNoChildren()) {
            if (toRemove.isLeftChild())
                parent.setLeft(null);
            else if (toRemove.isRightChild())
                parent.setRight(null);
        }

        else if (toRemove.hasOneChild()) {
            Node child = toRemove.getChild();
            if (toRemove.isLeftChild())
                parent.setLeft(child);
            else if (toRemove.isRightChild())
                parent.setRight(child);
        }

        else if (toRemove.hasTwoChildren()) {
            Node successor = successor(toRemove);
            Node successorP = successor.getParent();
            if (successor.hasOneChild()) {
                Node c = successor.getChild();
                successorP.setRight(c);
            }
            toRemove.setKey(successor.getKey());
            if (successor.isLeftChild())
                successorP.setLeft(null);
            else if (successor.isRightChild())
                successorP.setRight(null);
            successor.setParent(null);
        }
        size--;

        return true;
    }

    private void createRoot(int k) {
        Node r = new Node(k);
        this.root = r;
    }

    private Node find(int k) {
        return findInTree(root, k);
    }

    private Node findInTree(Node r, int k) {
        if (r == null)
            return null;

        if (k == r.getKey())
            return r;
        else if (k > r.getKey())
            return findInTree(r.getRight(), k);
        else
            return findInTree(r.getLeft(), k);
    }

    private Node max(Node r) {
        Node p = null;
        Node n = r;
        while (n != null) {
            p = n;
            n = n.getRight();
        }
        return p;
    }

    private Node min(Node r) {
        Node p = null;
        Node n = r;
        while (n != null) {
            p = n;
            n = n.getLeft();
        }
        return p;
    }

    private Node predecessor(Node n) {
        return max(n.getLeft());
    }

    private Node successor(Node n) {
        return min(n.getRight());
    }

    private void printTree(Node r) {
        if (r.hasLeft()) {
            printTree(r.getLeft());
        }
        System.out.print(r.getKey() + " ");
        if (r.hasRight()) {
            printTree(r.getRight());
        }
    }

}
