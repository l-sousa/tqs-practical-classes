import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    /*O professor fez referência a estes métodos depois de eu já ter feito o exercício a criar a stack nova
    em cada teste.
    *
    * Apesar disso, percebi como podem ser aplicados estes métodos para apenas ter uma stack na classe toda e
    * poder modificar a stack antes ou depois de cada teste: quer para limpar, adicionar membros, etc...
    */

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void isEmptyOnInit() {
        Stack<String> stack = new Stack<>();

        assertTrue(stack.isEmpty(), "Empty stack should be empty!");
    }

    @Test
    public void sizeOnInit() {
        Stack<String> stack = new Stack<>();
        assertEquals(0, stack.size());
    }

    @Test
    public void sizeAfterNPushes() {
        Stack<String> stack = new Stack<>();
        stack.push("Aveiro 1");
        stack.push("Aveiro 2");
        stack.push("Aveiro 3");

        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void valueOnPop() {
        Stack<String> stack = new Stack<>();

        stack.push("Aveiro");

        assertEquals("Aveiro", stack.pop());
    }

    @Test
    public void valueOnPeek() {
        Stack<String> stack = new Stack<>();

        stack.push("Aveiro");

        int sizeBeforePeek = stack.size();

        assertEquals("Aveiro", stack.peek());
        assertEquals(sizeBeforePeek, stack.size());
    }

    @Test
    public void sizeAfterNPops() {
        Stack<String> stack = new Stack<>();

        stack.push("Aveiro 1");
        stack.push("Aveiro 2");
        stack.push("Aveiro 3");
        stack.push("Aveiro 4");

        assertEquals(4, stack.size());

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void popOnEmptyStack() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    public void peekOnEmptyStack() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, stack::peek);
    }

}