package com.proteccion.imgprocessor.services;

import com.proteccion.imgprocessor.entities.Image;
import com.proteccion.imgprocessor.entities.Orientation;
import lombok.NonNull;

public class SuitableServiceImplForA4 implements SuitableService {

    public static final long A4WIDTH = 796;
    public static final long A4HEIGHT = 1123;

    @Override
    public boolean fitsInSheet(@NonNull Image image) {

        long imgCurrentWidth = image.getDimension().getWidth();
        long imgCurrentHeight = image.getDimension().getHeight();
        double imgCurrentAR = image.getAspectRatio();

        if (imgCurrentAR > 1){
            if(imgCurrentWidth <= A4HEIGHT) return true;
            else return false;
        }
        else if(imgCurrentAR < 1){
            if(imgCurrentHeight <= A4HEIGHT) return true;
            else return false;
        }
        else if (imgCurrentWidth <= A4WIDTH) return true;
        else return false;
    }

    @Override
    public Orientation bestOrientation(@NonNull Image image) {

        if (image.getAspectRatio() > 1){
            return Orientation.LANDSCAPE;
        }
        else return Orientation.PORTRAIT;
    }
}
