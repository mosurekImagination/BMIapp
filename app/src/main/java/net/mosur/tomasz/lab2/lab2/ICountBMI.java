package net.mosur.tomasz.lab2.lab2;

/**
 * Created by Tomek on 15/03/2017.
 */

public interface ICountBMI {

    boolean isWeightValid(float weight);
    boolean isHeightValid(float height);
    float countBMI(float weight, float height);

}

