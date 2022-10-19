package pwo.seq;

import pwo.utils.SequenceGenerator;

import java.math.BigDecimal;

/**
 * Klasa abstrakcyjna przeznaczona dla generatorów ciągów<br>
 *
 * @author Sebastian
 * @version 1.0.0
 */
abstract class Generator implements SequenceGenerator {

    protected int lastIndex = 0;

    protected BigDecimal current = null, f_1 = null, f_2 = null, f_3 = null;

    @Override
    public void reset() {
        lastIndex = 0;
    }

    @Override
    public final BigDecimal getTerm(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }

        if (i < lastIndex) {
            reset();
        }

        while (lastIndex <= i) {
            nextTerm();
        }

        return current;
    }
}
