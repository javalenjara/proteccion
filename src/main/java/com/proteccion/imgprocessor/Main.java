package com.proteccion.imgprocessor;

import com.proteccion.imgprocessor.entities.Image;
import com.proteccion.imgprocessor.services.ResizableService;
import com.proteccion.imgprocessor.services.ResizableServiceImplForA4;
import com.proteccion.imgprocessor.services.SuitableService;
import com.proteccion.imgprocessor.services.SuitableServiceImplForA4;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String path = args[0];
        Image currentImage = Image.builder(path);
        SuitableService suitableService = new SuitableServiceImplForA4();
        ResizableService resizableService = new ResizableServiceImplForA4();
        Image newImage = resizableService.getNewImageFitsA4(currentImage);

        System.out.println(":::Current Image Stats:::");
        System.out.format("Original width = %d%n", currentImage.getDimension().getWidth());
        System.out.format("Original height = %d%n", currentImage.getDimension().getHeight());
        System.out.format("Original aspect ratio = %.2f%n", currentImage.getAspectRatio());
        System.out.println("Original image fits in A4? = " + suitableService.fitsInSheet(currentImage));
        System.out.println("Orientation for printing = " + suitableService.bestOrientation(currentImage));
        System.out.println();

        System.out.println(":::New Image Stats:::");
        System.out.format("New size: width = %d%n", newImage.getDimension().getWidth());
        System.out.format("New size: height = %d%n", newImage.getDimension().getHeight());
        System.out.format("New aspect ratio = %.2f%n", newImage.getAspectRatio());
        System.out.println("New image fits in A4? = " + suitableService.fitsInSheet(newImage));
        System.out.println("Orientation for printing = " + suitableService.bestOrientation(newImage));

    }

}
