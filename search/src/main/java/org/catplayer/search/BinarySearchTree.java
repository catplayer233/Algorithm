package org.catplayer.search;

import java.util.*;

/**
 * a standard binary search tree
 * <p>
 * this tree doesn't consider balance problem, for solving this problem you should see {@link RedBlackBST}
 * <p>
 * this class based {@link SymbolTable} and we also can have some special behaviors, such min, max, range and so on
 * <p>
 * We don't care the key's equal problem, when the key's compare method return 0,
 * we just replace the value and not replace the key. The actual key's content was ignored.
 * This is what the difference between the BST and jdk map interface.
 * If you want to see the normal usage you should look up the jdk map implements such as {@link java.util.TreeMap},{@link java.util.HashMap}
 * <p>
 * This class is not allowed null key and null value.
 *
 * @param <Key>   key type
 * @param <Value> value type
 * @author catplayer
 * @since 1.0
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private Node<Key, Value> root;

    private int size;//node number

    private final Comparator<Key> comparator;//if you want to use your own compare method

    private static final int EQUAL_TAG = 0;//when keys are equal

    private static final int SMALLER_TAG = -1;//latter greater

    private static final int GREATER_TAG = 1;//latter smaller

    public BinarySearchTree(Comparator<Key> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    /**
     * the internal tree node class represent the node of the BST
     * <p>
     * the node's rule:
     * <ol>parent's key greater than left</ol>
     * <ol>right's key greater than parent</ol>
     *
     * @param <K> key
     * @param <V> value
     */
    private static class Node<K extends Comparable<K>, V> {
        private final K key;
        private V value;
        private Node<K, V> left;//left child node, may be null
        private Node<K, V> right;//right child node, may be null

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "->" + value;
        }
    }

    /**
     * the wrapped result when we execute search
     *
     * @param <M> key type
     * @param <N> value type
     */
    private static class SearchResult<M extends Comparable<M>, N> {
        private final Node<M, N> searchedNode;
        private final int comparedResult;

        public SearchResult(Node<M, N> searchedNode, int comparedResult) {
            this.searchedNode = searchedNode;
            this.comparedResult = comparedResult;
        }
    }

    /**
     * store the key-value entry
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void put(Key key, Value value) {
        assertArgument(key, "key can not be NULL!");
        assertArgument(value, "value can not be NULL!");
        if (root == null) {
            //we just set root
            size++;
            root = new Node<>(key, value);
        }
        SearchResult<Key, Value> searchResult = searchKey(key, root);
        Node<Key, Value> searchedNode = searchResult.searchedNode;
        switch (searchResult.comparedResult) {
            case EQUAL_TAG:
                //replace origin value
                searchedNode.value = value;
                break;
            case SMALLER_TAG:
                //link to the node's left
                searchedNode.left = new Node<>(key, value);
                //size enqueue one
                size++;
                break;
            case GREATER_TAG:
                //link to the node's right
                searchedNode.right = new Node<>(key, value);
                size++;
        }
    }

    /**
     * retrieve the target key mapped value
     *
     * @param key target key
     * @return mapped value
     */
    @Override
    public Value get(Key key) {
        assertArgument(key, "key can not be NULL!");
        //if the root is null, no need to search
        if (root == null) {
            return null;
        }
        //start from root to dequeue target node
        //this node may be not the target node
        //so there we need to confirm
        SearchResult<Key, Value> searchResult = searchKey(key, root);
        if (searchResult.comparedResult != EQUAL_TAG) {
            return null;
        }
        return searchResult.searchedNode.value;
    }

    /**
     * search from a start point
     * <p>
     * when we find some node's key equals target key, we return the target key
     * <p>
     * else we return the nearest node so we guarantee the return node will never be null
     *
     * @param key       target key
     * @param startNode start point
     * @return target node or nearest node
     */
    private SearchResult<Key, Value> searchKey(Key key, Node<Key, Value> startNode) {
        return comparator != null ? searchByComparator(key, startNode) : searchByNatural(key, startNode);
    }

    /**
     * search via comparator
     *
     * @param key       target key
     * @param startNode start point
     * @return target node or nearest node
     */
    private SearchResult<Key, Value> searchByComparator(Key key, Node<Key, Value> startNode) {
        do {
            Key nodeKey = startNode.key;
            int compareResult = comparator.compare(key, nodeKey);
            if (compareResult < 0) {
                //smaller
                //find from left
                Node<Key, Value> left = startNode.left;
                if (left == null) {
                    return new SearchResult<>(startNode, SMALLER_TAG);
                } else {
                    startNode = left;
                }
            } else if (compareResult > 0) {
                //greater
                //find from right
                Node<Key, Value> right = startNode.right;
                if (right == null) {
                    return new SearchResult<>(startNode, GREATER_TAG);
                } else {
                    startNode = right;
                }
            } else {
                //equals return the node
                return new SearchResult<>(startNode, EQUAL_TAG);
            }
        } while (true);

    }

    /**
     * search by key's compareTo method
     *
     * @param key       target key
     * @param startNode start point
     * @return target node or nearest node
     */
    private SearchResult<Key, Value> searchByNatural(Key key, Node<Key, Value> startNode) {
        do {
            Key nodeKey = startNode.key;
            int compareResult = key.compareTo(nodeKey);
            if (compareResult < 0) {
                //smaller
                //find from left
                Node<Key, Value> left = startNode.left;
                if (left == null) {
                    return new SearchResult<>(startNode, SMALLER_TAG);
                } else {
                    startNode = left;
                }
            } else if (compareResult > 0) {
                //greater
                //find from right
                Node<Key, Value> right = startNode.right;
                if (right == null) {
                    return new SearchResult<>(startNode, GREATER_TAG);
                } else {
                    startNode = right;
                }
            } else {
                //equals return the node
                return new SearchResult<>(startNode, EQUAL_TAG);
            }
        } while (true);
    }

    /**
     * delete the target key's entry
     *
     * @param key target key
     */
    @Override
    public void delete(Key key) {
        assertArgument(key, "deleted key can not be NULL!");
        root = deleteNode(key, root);
    }

    /**
     * delete target node from a start point
     *
     * @param key       target node's key
     * @param startNode start point
     * @return after deleted node
     */
    private Node<Key, Value> deleteNode(Key key, Node<Key, Value> startNode) {
        //no need to delete
        if (startNode == null) {
            return null;
        }

        int comparedResult;
        if (comparator != null) {
            comparedResult = comparator.compare(key, startNode.key);
        } else {
            comparedResult = key.compareTo(startNode.key);
        }

        if (comparedResult < 0) {
            startNode.left = deleteNode(key, startNode.left);
        } else if (comparedResult > 0) {
            startNode.right = deleteNode(key, startNode.right);
        } else {
            //actual delete
            //if one size's node is null, we just connect parent's left or right link to other side node
            if (startNode.left == null) {
                size--;
                return startNode.right;
            }
            if (startNode.right == null) {
                size--;
                return startNode.left;
            }
            //if both side are not null, we should do the most complex operation
            //1) we keep a temp reference to the start node
            Node<Key, Value> t = startNode;
            //dequeue the successor node of the start node
            //2) we dequeue the successor node of the start node
            startNode = minNode(t.right);
            //now we delete the min node of the right side of the start node
            //or we can say we just removed the node x
            //3) we point the start node's right node which executed the operation of deleting min node to the to the x's right
            startNode.right = deleteMin(t.right);
            //4)then we point start node's left node to x's left
            startNode.left = t.left;
            //no need to execute size--, because the deleteMin method already execute this.
        }

        // final we return the x for replace the target node
        return startNode;
    }

    /**
     * delete the min node
     * <p>
     * start from root
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * delete min node from a start point
     *
     * @param startNode start point
     * @return the final node
     */
    private Node<Key, Value> deleteMin(Node<Key, Value> startNode) {
        if (startNode == null) {
            return null;
        }
        //if no left node, that means the startNode is the min node, so we return the right node
        if (startNode.left == null) {
            //this the only code to execute actual delete operation
            size--;
            return startNode.right;
        }
        //now start from the next left point
        startNode.left = deleteMin(startNode.left);
        return startNode;
    }

    /**
     * delete max node
     * <p>
     * start from the root node
     */
    public void deleteMax() {
        root = deleteMax(root);
    }

    /**
     * delete max node from a start point
     *
     * @param startNode start point
     * @return after deleted node
     */
    private Node<Key, Value> deleteMax(Node<Key, Value> startNode) {
        if (startNode == null) {
            return null;
        }
        if (startNode.right == null) {
            //no node are greater than startNode
            size--;
            return startNode.left;
        }
        startNode.right = deleteMax(startNode.right);
        return startNode;
    }


    /**
     * dequeue the min node from a start point
     *
     * @param startNode start point
     * @return min node
     */
    private Node<Key, Value> minNode(Node<Key, Value> startNode) {
        if (startNode.left == null) {
            return startNode;
        } else {
            return minNode(startNode.left);
        }
    }

    /**
     * check the target key's entry whether it exists
     *
     * @param key target key
     * @return true: exists
     */
    @Override
    public boolean contains(Key key) {
        return Objects.nonNull(get(key));
    }

    /**
     * check whether the symbol table is empty
     *
     * @return true: empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * the number of the entries which are the symbol table stored
     *
     * @return number of the entries
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Key> iterator() {
        return new BSTIterator<>(root);
    }

    /**
     * iterator based on a simple queue, in order to show the key by order
     *
     * @param <A> key's type
     */
    private static class BSTIterator<A extends Comparable<A>> implements Iterator<A> {

        private final SimpleQueue<A> simpleQueue;

        public BSTIterator(Node<A, ?> node) {
            this.simpleQueue = new SimpleQueue<>();
            LDREnqueue(node);
        }

        /**
         * enqueue node
         *
         * @param node start point
         */
        private void LDREnqueue(Node<A, ?> node) {
            if (node == null) {
                return;
            }
            //left
            if (node.left != null) {
                LDREnqueue(node.left);
            }
            //parent
            simpleQueue.enqueue(node.key);
            //right
            if (node.right != null) {
                LDREnqueue(node.right);
            }
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return !simpleQueue.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public A next() {
            return simpleQueue.dequeue();
        }
    }


    /**
     * null check
     *
     * @param argument the argument need to check
     * @param message  error message
     */
    private static void assertArgument(Object argument, String message) {
        if (Objects.isNull(argument))
            throw new IllegalArgumentException(message);

    }

    @Override
    public String toString() {
        //do LDR
        StringBuilder stringBuilder = new StringBuilder("[");
        String size = ", the size is " + size();
        LDRTraverse(root, stringBuilder);
        if (stringBuilder.toString().endsWith(",")) {
            return stringBuilder.substring(0, stringBuilder.lastIndexOf(",")) + "]" + size;
        }
        return stringBuilder.append("]").append(size).toString();
    }

    /**
     * do LDR traverse,
     * <p>
     * because the BST is left node smaller than parent node and parent node smaller than right node
     *
     * @param node          start node
     * @param stringBuilder for store the node's content
     */
    private void LDRTraverse(Node<Key, Value> node, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }
        //access left first
        if (node.left != null) {
            LDRTraverse(node.left, stringBuilder);
        }
        //then parent
        stringBuilder.append(node.toString()).append(",");
        //right last
        if (node.right != null) {
            LDRTraverse(node.right, stringBuilder);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> integerBST = new BinarySearchTree<>();
        //put
        integerBST.put(5, "88");
        integerBST.put(1, "99");
        integerBST.put(10, "999");
        integerBST.put(7, "990");
        integerBST.put(96, "09");
        integerBST.put(100, "99");

        System.out.println("before delete: " + integerBST);

        //delete
        integerBST.delete(5);

        //delete min
        integerBST.deleteMin();

        //delete max
        integerBST.deleteMax();

        System.out.println("contains key " + 5 + "? " + integerBST.contains(5));

        System.out.println("the BST is empty? " + integerBST.isEmpty());

        System.out.println("after delete: " + integerBST);

        for (int key : integerBST
        ) {
            System.out.println("get key: " + key);
        }
    }
}
