package net.mosur.tomasz.lab2.lab2;

/**
 * Created by Tomek on 04/04/2017.
 */

public class CountBMIForLbFt extends CountBMIForKgM implements ICountBMI {
    public float countBMI(float weight, float height)
    {
        return super.countBMI(weight*0.45359237f, height*0.3084f);
    }

}
