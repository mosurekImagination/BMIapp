package net.mosur.tomasz.lab2.lab2;

/**
 * Created by Tomek on 15/03/2017.
 */

public class CountBMIForKgM implements ICountBMI {
    
    static final float MINMASS = 10f;
    static final float MAXMASS = 250f;
    static final float MINGEIGHT = 0.5f;
    static final float MAXHEIGHT = 2.5f;
    
    
    @Override
    public boolean isWeightValid(float weight) {
        return weight >= MINMASS && weight <= MAXMASS;
    }

    @Override
    public boolean isHeightValid(float height) {
        return height >= MINGEIGHT && height <= MAXHEIGHT;
    }

    boolean argsInvalid(float weight, float height)
    {
        return !(isWeightValid(weight)&&isHeightValid(height));
    }

    @Override
    public float countBMI(float weight, float height) throws IllegalArgumentException{
        if(argsInvalid(weight, height)){ throw new IllegalArgumentException(); }
        return weight/(height*height);
    }
}
