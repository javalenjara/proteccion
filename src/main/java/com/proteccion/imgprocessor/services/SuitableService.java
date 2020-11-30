package com.proteccion.imgprocessor.services;

import com.proteccion.imgprocessor.entities.Image;
import com.proteccion.imgprocessor.entities.Orientation;
import lombok.NonNull;

public interface SuitableService {

    boolean fitsInSheet(@NonNull Image image);
    Orientation bestOrientation(@NonNull Image image);
}
