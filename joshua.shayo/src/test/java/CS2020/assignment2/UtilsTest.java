package CS2020.assignment2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit test for Util
 */
public class UtilsTest 
{
    /*
     * Tests checkIfBornOnWeekend
     */
    @Test
    public void checkCheckIfBornOnWeekend()
    {
        
        assertTrue(Utils.checkIfBornOnWeekend("22 Mar 2003"));
        assertTrue(Utils.checkIfBornOnWeekend("18 Jun 1977"));
        assertTrue(Utils.checkIfBornOnWeekend("5 Dec 1993"));
        
        assertFalse(Utils.checkIfBornOnWeekend("6 Dec 1993"));
        assertFalse(Utils.checkIfBornOnWeekend("1 Feb 2012"));
        assertFalse(Utils.checkIfBornOnWeekend("30 Sep 2047"));
    }
}
