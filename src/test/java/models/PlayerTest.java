package models;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class PlayerTest {
    @Test
    public void testShouldCreateAPlayer() {
        Player player = new Player("Kirat", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false);
        assertNotNull(player);
    }
}
