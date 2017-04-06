package net.mosur.tomasz.lab2.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tomek on 15/03/2017.
 */

public class CountBMITest {

    @Test
    public void massUnderZeroIsInvalid()
    {
        //GIVEN
            float testMass=-1f;
        //WHEN
            ICountBMI test = new CountBMIForKgM();
        //THEN
            boolean actual = test.isWeightValid(testMass);
            assertFalse(actual);
    }

    @Test
    public void isCalculationValid()
    {
        //GIVEN
        float testMass=70f;
        float testHeight=1.8f;
        //WHEN
        ICountBMI test = new CountBMIForKgM();
        //THEN
        float actual = test.countBMI(testMass, testHeight);
        assertEquals(21.6, actual, 0.1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void calculateThrowsException()
    {
        //GIVEN
        float testMass=-70f;
        float testHeight=1.8f;
        //WHEN
        ICountBMI test = new CountBMIForKgM();
        //THEN
        float actual = test.countBMI(testMass, testHeight);
    }

    @Test
    public void kgToLbConverter()
    {
        //GIVEN
        float testMasskG=70f;
        float testHeightM=1.8f;
        //WHEN
        ICountBMI testKg = new CountBMIForKgM();
        ICountBMI testLb = new CountBMIForLbFt();
        float lb = testMasskG*2.20462262f;
        float ft = testHeightM * 3.2808399f;
        //THEN
        float lbResult = testLb.countBMI(lb, ft);
        float kgResult = testKg.countBMI(testMasskG, testHeightM);
        assertEquals(lbResult, kgResult, 0.8);
    }
}
