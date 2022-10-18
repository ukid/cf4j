package es.upm.etsisi.cf4j.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Math {

    @Test
    void range() {
        Assertions.assertArrayEquals(Maths.range(2), new int[]{0, 1, 2});
        Assertions.assertArrayEquals(Maths.range(1, 3), new int[]{1, 2, 3});
        Assertions.assertArrayEquals(Maths.range(1, 5, 2), new int[]{1, 3, 5});
        Assertions.assertArrayEquals(Maths.range(1, 6, 2), new int[]{1, 3, 5});
        Assertions.assertArrayEquals(Maths.range(1, 5, 2, false), new int[]{1, 3});
    }
}
