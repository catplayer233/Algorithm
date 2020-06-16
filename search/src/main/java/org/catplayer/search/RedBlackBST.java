package org.catplayer.search;

import java.util.Iterator;

public class RedBlackBST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    /**
     * store the key-value entry
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void put(Key key, Value value) {

    }

    /**
     * retrieve the target key mapped value
     *
     * @param key target key
     * @return mapped value
     */
    @Override
    public Value get(Key key) {
        return null;
    }

    /**
     * delete the target key's entry
     *
     * @param key target key
     */
    @Override
    public void delete(Key key) {

    }

    /**
     * check the target key's entry whether it exists
     *
     * @param key target key
     * @return true: exists
     */
    @Override
    public boolean contains(Key key) {
        return false;
    }

    /**
     * check whether the symbol table is empty
     *
     * @return true: empty
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * the number of the entries which are the symbol table stored
     *
     * @return number of the entries
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Key> iterator() {
        return null;
    }
}
