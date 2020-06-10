package org.catplayer.datastruct.queue;

/**
 * a  priority  queue
 *
 * @param <Key> stored key type in the queue
 * @author catplayer
 * @since 1.0
 */
public interface MaxPriorityQueue<Key extends Comparable<Key>> {

    /**
     * insert a key in the queue
     *
     * @param key key
     */
    void insert(Key key);

    /**
     * get the max priority key
     *
     * @return max priority key
     */
    Key max();

    /**
     * remove the max priority key
     *
     * @return the removed key
     */
    Key delMax();

    /**
     * check the queue whether is empty
     *
     * @return empty return true
     */
    boolean isEmpty();

    /**
     * get the queue current size
     *
     * @return queue size
     */
    int size();

}
