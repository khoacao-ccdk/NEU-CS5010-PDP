package lab4.problem1;

import java.util.LinkedList;

public class StackTestHelper<E> implements Stack{

  private LinkedList<Object> ll;

  public StackTestHelper(){
    this.ll = new LinkedList<>();
  }

  protected StackTestHelper(LinkedList<Object> ll){
    this.ll = ll;
  }

  @Override
  public Stack push(Object element) {
    LinkedList<Object> otherLL = new LinkedList<>();
    otherLL.addAll(this.ll);
    otherLL.addFirst(element);
    StackTestHelper<Object> other = new StackTestHelper<>(otherLL);
    return other;
  }

  @Override
  public Stack remove() {
    LinkedList<Object> otherLL = new LinkedList<>();
    otherLL.addAll(this.ll);
    if(!otherLL.isEmpty())
      otherLL.pollFirst();
    StackTestHelper<Object> other = new StackTestHelper<>(otherLL);
    return other;
  }

  @Override
  public Object top() {
    if(ll.isEmpty())
      return null;
    return ll.peekFirst();
  }
}
