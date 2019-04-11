

import static org.junit.jupiter.api.Assertions.assertAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.time.Duration.*;
import java.util.LinkedList;
import java.util.stream.Stream;

import examples.DefaultVertex;

@DisplayName("A special test case")
class DefaultVertexTest {

	@Test
	@DisplayName("Custom test that fails")
	void test() {
		fail("Not yet implemented");
	}
	
    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

    @Test
    void standardAssertions() {
        assertEquals(2, 1+1);
        assertEquals(4, 2*2,
                "The optional failure message is now the last parameter");
        assertTrue('b' < 'a', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }
    
    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
            () -> assertEquals("Doe", "Jane"),
            () -> assertEquals("Doe", "Doe")
        );
    }
    
    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
            () -> {
                String firstName = null;
                assumeFalse(firstName == null);
                assertNotNull(firstName);

                // Executed only if the previous assertion is valid.
                assertAll("first name",
                    () -> assertTrue(firstName.startsWith("J")),
                    () -> assertTrue(firstName.endsWith("e"))
                );
            },
            () -> {
                // Grouped assertion, so processed independently
                // of results of first name assertions.
                String lastName = "Rodrigues";
                assertNotNull(lastName);

                // Executed only if the previous assertion is valid.
                assertAll("last name",
                    () -> assertTrue(lastName.startsWith("D")),
                    () -> assertTrue(lastName.endsWith("e"))
                );
            }
        );
    }
    
    @Test
    void exceptionTesting() {
    	DefaultVertex x = new DefaultVertex("a");
        Exception exception = assertThrows(ArithmeticException.class, () ->
           x.getId() );
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceededWithMethod() {
    	DefaultVertex x = new DefaultVertex("a");
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofNanos(1), () -> x.getId());
        assertEquals("a", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    } 
    
    static Stream <Arguments> llProvider () {
    	LinkedList<Integer> l1 = new LinkedList <>();
    	l1.add(1);
    	l1.add(2);
    	l1.add(3);
    	LinkedList<Integer> l2 = new LinkedList <>();
    	l2.add(1);
    	l2.add(2);
    	l2.add(3);
    	return Stream.of(
    			Arguments.of(l1,l1));
    }
    
    @ParameterizedTest
    @MethodSource("llProvider")
    public void testEquals123(LinkedList<Integer> a, LinkedList<Integer> b) { 
    	// Preconditions - a and b have the same values but are different objects
    	assumeFalse(a==b, () -> "a and b must be different objects");
    	// Execution steps - run equality operator 	
    	boolean result = a.equals(b);  
    	// Postconditions / Expected behavior -assert that result is true 
    	assertEquals(true, result); 
  	} 


    
    
    
}
