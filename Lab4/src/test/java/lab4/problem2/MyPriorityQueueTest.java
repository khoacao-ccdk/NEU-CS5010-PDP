package lab4.problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyPriorityQueueTest {

  MyPriorityQueue<Integer> q;

  @BeforeEach
  void setUp() {
    q = new MyPriorityQueue<>();
  }

  @Test
  void insert() {
    assertTrue(q.isEmpty());
    q.insert(7);
    assertFalse(q.isEmpty());
  }

  @Test
  void remove() {
    assertNull(q.remove());
    q.insert(5);
    assertEquals(5, q.remove());
  }

  @Test
  void front() {
    assertNull(q.front());
    q.insert(9);
    q.insert(4);
    q.insert(5);
    assertEquals(9, q.front());
  }

  @Test
  void isEmpty() {
    assertTrue(q.isEmpty());
    q.insert(5);
    assertFalse(q.isEmpty());
  }
}