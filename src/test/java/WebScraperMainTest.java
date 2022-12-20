import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebScraperMainTest {

    @Test
    void testGenerateAuthor() {
        assertEquals("JaneDoe , ", WebScraperMain.generateAuthor("JaneDoe"));
        assertEquals("", WebScraperMain.generateAuthor(""));
        assertEquals("JaneDoe ,, ", WebScraperMain.generateAuthor(", JaneDoe"));
    }

    @Test
    void testGenerateAuthor2() {
        try {
            WebScraperMain.generateAuthor(" ");
            fail();
        } catch (ArrayIndexOutOfBoundsException e){
            assertTrue(e instanceof ArrayIndexOutOfBoundsException);
        }
    }

    @Test
    void testGenerateAuthor3() {
        try {
            WebScraperMain.generateAuthor(" JaneDoe");
            fail();
        } catch (StringIndexOutOfBoundsException e){
            assertTrue(e instanceof StringIndexOutOfBoundsException);
        }
    }
}

