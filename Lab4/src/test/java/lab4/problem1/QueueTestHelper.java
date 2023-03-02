package lab4.problem1;

import java.util.LinkedList;
import java.util.List;

public class QueueTestHelper<E> implements Queue{
  private LinkedList<Object> ll;

  public QueueTestHelper(){
    this.ll = new LinkedList<>();
  }
  protected QueueTestHelper(LinkedList ll){
    this.ll = ll;
  }

  @Override
  public Queue enqueue(Object element) {
    LinkedList<Object> otherLL = new LinkedList<>();
    otherLL.addAll(ll);
    otherLL.addLast(element);
    Queue<Object> other = new QueueTestHelper<>(otherLL);
    return other;
  }

  @Override
  public Queue dequeue() {
    LinkedList<Object> otherLL = new LinkedList<>();
    otherLL.addAll(ll);
    if(!otherLL.isEmpty())
      otherLL.pollFirst();
    Queue<Object> other = new QueueTestHelper<>(otherLL);
    return other;
  }

  @Override
  public Object front() {
    if(ll.isEmpty())
      return null;
    return ll.peekFirst();
  }
}
