package org.catplayer.search;

/**
 * a symbol table is an abstraction of directory with key-value pair property.
 * <p>
 * It also contains some behaviors, such as {@code put} to store and {@code dequeue} to retrieve ...
 * <p>
 * the key and value we use generic type for keeping them are same type in order to guarantee search performance and other goals.
 *
 * @param <Key>   key type
 * @param <Value> stored value type
 * @author catplayer
 * @since 1.0
 */
public interface SymbolTable<Key, Value> extends Iterable<Key> {

    /**
     * store the key-value entry
     *
     * @param key   key
     * @param value value
     */
    void put(Key key, Value value);

    /**
     * retrieve the target key mapped value
     *
     * @param key target key
     * @return mapped value
     */
    Value get(Key key);

    /**
     * delete the target key's entry
     *
     * @param key target key
     */
    void delete(Key key);

    /**
     * check the target key's entry whether it exists
     *
     * @param key target key
     * @return true: exists
     */
    boolean contains(Key key);

    /**
     * check whether the symbol table is empty
     *
     * @return true: empty
     */
    boolean isEmpty();

    /**
     * the number of the entries which are the symbol table stored
     *
     * @return number of the entries
     */
    int size();

}
