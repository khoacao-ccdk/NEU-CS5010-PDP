package lab4.problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StackTest {
  Stack<Integer> st;

  @BeforeEach
  void setUp() {
    st = new StackTestHelper<>();
  }

  @Test
  void push() {
    st = st.push(2);
    assertEquals(2, st.top());
  }

  @Test
  void remove() {
    st = st.push(6);
    st = st.push(7);
    assertEquals(7, st.top());
    st = st.remove();
    assertEquals(6, st.top());
    st = st.remove();
    assertNull(st.top());
  }

  @Test
  void top() {
    st = st.push(6);
    assertEquals(6, st.top());
    st = st.remove();
    assertNull(st.top());
  }
}