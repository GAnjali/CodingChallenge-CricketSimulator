package helper;

import models.Match;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class InitializerTest {
    Initializer initializer;
    Match match;

    @Before
    public void init() throws IOException {
        initializer = new Initializer();
    }

    @Test
    public void testShouldVerifyMatchWhenInitializeMatchCalled() {
        match = initializer.initializeMatch();
        assertNotNull(match);
    }
}
