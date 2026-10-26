package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void constructor_InitialCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10, 0.0);

        // Assert
        assertEquals(10, store.getTorpedoCount());
    }

    @Test
    void isEmpty_EmptyStore() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0, 0.0);

        // Assert
        assertTrue(store.isEmpty());
    }

    @Test
    void isEmpty_NonEmptyStore() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Assert
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedoes() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10, 0.0);

        // Act
        boolean result = store.fire(4);

        // Assert
        assertTrue(result);
        assertEquals(6, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedoes() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        boolean result = store.fire(5);

        // Assert
        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_ZeroTorpedoes() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> store.fire(0));

        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_NegativeTorpedoes() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> store.fire(-1));

        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_TooManyTorpedoes() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> store.fire(6));

        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_EmptyStore() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> store.fire(1));

        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_Failure() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_SequentialFiring() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        boolean firstResult = store.fire(2);
        boolean secondResult = store.fire(2);

        // Assert
        assertTrue(firstResult);
        assertTrue(secondResult);
        assertEquals(1, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }
}