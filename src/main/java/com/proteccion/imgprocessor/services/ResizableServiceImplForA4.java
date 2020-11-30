package com.proteccion.imgprocessor.services;

import com.proteccion.imgprocessor.entities.Dimension;
import com.proteccion.imgprocessor.entities.Image;

public class ResizableServiceImplForA4 implements ResizableService {

    SuitableService suitableImg = new SuitableServiceImplForA4();

    @Override
    public Image getNewImageFitsA4(Image image) {

        if (suitableImg.fitsInSheet(image)){
            return image;
        }
        else {
            return resizeImageMaintainingAR(image);
        }
    }

    private Image resizeImageMaintainingAR(Image image){

        long imgCurrentWidth = image.getDimension().getWidth();
        long imgCurrentHeight = image.getDimension().getHeight();
        double imgCurrentAR = image.getAspectRatio();

        long newWidth;
        long newHeight;

        if (imgCurrentAR > 1){
            if(imgCurrentWidth > SuitableServiceImplForA4.A4HEIGHT) {
                newWidth = SuitableServiceImplForA4.A4HEIGHT;
                newHeight = Math.round(newWidth / imgCurrentAR);
                Dimension newDimension = new Dimension(newWidth, newHeight);
                return new Image (newDimension, image.getPath());
            }
        }
        else if(imgCurrentAR < 1){
            if(imgCurrentHeight > SuitableServiceImplForA4.A4HEIGHT) {
                newHeight = SuitableServiceImplForA4.A4HEIGHT;
                newWidth = Math.round(newHeight * imgCurrentAR);
                Dimension newDimension = new Dimension(newWidth, newHeight);
                return new Image (newDimension, image.getPath());
            }
        }
        else if (imgCurrentWidth > SuitableServiceImplForA4.A4WIDTH) {
            newWidth = SuitableServiceImplForA4.A4WIDTH;
            newHeight = SuitableServiceImplForA4.A4WIDTH;
            Dimension newDimension = new Dimension(newWidth, newHeight);
            return new Image (newDimension, image.getPath());
        }

        return image;
    }
}
