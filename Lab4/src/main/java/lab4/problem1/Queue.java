package lab4.problem1;
/**
 * Describes an immutable Queue ADT.
 * @param <E> The data type to store in the ADT.
 */
public interface Queue<E> {

    /**
     * Adds an element to the queue.
     * @param element The element to add.
     * @return A new Queue with the element added.
     */
    Queue enqueue(E element);


    /**
     * Removes the least recently added element.
     * @return A new Queue with element removed.
     */
    Queue dequeue();


    /**
     * Returns but does not remove the least recently added element.
     * @return The element at the front of the Queue.
     */
    E front();
}
