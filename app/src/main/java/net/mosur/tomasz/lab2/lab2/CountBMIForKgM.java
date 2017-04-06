package net.mosur.tomasz.lab2.lab2;

/**
 * Created by Tomek on 15/03/2017.
 */

public class CountBMIForKgM implements ICountBMI {
    
    static final float MinMass = 10f;
    static final float MaxMass = 250f;
    static final float MinHeigth = 0.5f;
    static final float MaxMeigth = 2.5f;
    
    
    @Override
    public boolean isWeightValid(float weight) {
        return weight >= MinMass && weight <= MaxMass;
    }

    @Override
    public boolean isHeightValid(float height) {
        return height >= MinHeigth && height <= MaxMeigth;
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
