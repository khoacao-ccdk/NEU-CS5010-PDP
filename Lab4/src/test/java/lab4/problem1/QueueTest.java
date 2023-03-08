package lab4.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueTest {
  Queue<Integer> q;
  @BeforeEach
  void setUp() {
    q = new QueueTestHelper<>();

  }

  @Test
  void enqueue() {
    q = q.enqueue(6);
    assertEquals(6, q.front());
  }

  @Test
  void dequeue() {
    q = q.enqueue(4);
    q = q.enqueue(7);
    q = q.dequeue();
    assertEquals(7, q.front());
    q = q.dequeue();
    assertNull(q.front());
  }

  @Test
  void front() {
    q = q.enqueue(7);
    assertEquals(7, q.front());
  }
}