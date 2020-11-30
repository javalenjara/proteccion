package com.proteccion.imgprocessor.services;

import com.proteccion.imgprocessor.entities.Image;

public interface ResizableService {
    Image getNewImageFitsA4(Image image);
}
