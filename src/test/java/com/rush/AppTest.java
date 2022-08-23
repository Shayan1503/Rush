package com.rush;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testConstructor() {
        try {
            new App();
            new App(5, 500);
        } catch (Exception e) {
            fail("Construction failed.");
        }
    }

    @Test
    public void testMain() {
        try {
            App.main(null);
        } catch (Exception e) {
            fail("Main failed");
        }
    }
}
