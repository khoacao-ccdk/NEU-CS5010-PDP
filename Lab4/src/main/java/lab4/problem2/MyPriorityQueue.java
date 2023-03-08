package lab4.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a simple PriorityQueue class. This class works like a maxheap.
 *
 * @author Cody Cao
 */
public class MyPriorityQueue<E extends Comparable<E>> {

  List<E> ls;

  /**
   * Constructs a new MyPriorityQueue object
   */
  public MyPriorityQueue() {
    ls = new ArrayList<>();
  }

  /**
   * Inserts an element to the PriorityQueue
   *
   * @param e object to insert
   */
  public void insert(E e) {
    ls.add(e);
    Collections.sort(ls);
  }

  /**
   * Remove the element with the highest order from the PriorityQueue
   *
   * @return the element being removed
   */
  public E remove() {
    if (ls.size() > 0) {
      return ls.remove(ls.size() - 1);
    }
    return null;

  }

  /**
   * @return the element with the highest order in the PriorityQueue, return null if the queue is
   * empty
   */
  public E front() {
    if (!isEmpty()) {
      return ls.get(ls.size() - 1);
    }
    return null;
  }

  /**
   * @return true if there is no element in the PriorityQueue, false otherwise
   */
  public boolean isEmpty() {
    return (ls.size() == 0);
  }
}
