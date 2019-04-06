package UnitTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;
import org.junit.jupiter.api.Test;
import src.StackHandler;

class StackHandlerTest {

    @Test
    void stackConsTest() {
        // Setup
        StackHandler sh = new StackHandler();      
        Stack<String> cons = sh.initConversationLog();
        String first = "Test Converation Log";
        String second = "newline";
        // Execution
        cons.push(first);
        cons.push(second);
        // Test
        assertEquals(second, cons.pop());
    }
    
    @Test
    void StackPathTest() {
        // Setup
        StackHandler sh = new StackHandler(); 
        Stack<String> path = sh.initFileLog();
        String first = "000-0.txt";
        String second = "newline";
        // Execution
        path.push(first);
        path.push(second);
        // Test
        assertEquals(second ,path.pop());
        
    }


}
