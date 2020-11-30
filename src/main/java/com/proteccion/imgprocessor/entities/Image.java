package com.proteccion.imgprocessor.entities;

import lombok.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "internalBuilder")
public class Image {

    private Dimension dimension;
    private double aspectRatio;
    @NonNull
    private String path;

    public Image(Dimension dimension, @NonNull String path) {
        this.dimension = dimension;
        this.aspectRatio = setAspectRatio(dimension);
        this.path = path;
    }

    public static Image builder(String path) throws IOException {

        File actualImageFile = new File(path);
        Dimension currentImageDimension = getFileImageSize(actualImageFile);

        return Image.internalBuilder()
                .path(path)
                .dimension(currentImageDimension)
                .aspectRatio(setAspectRatio(currentImageDimension))
                .build();
    }

    private static double setAspectRatio(@NonNull Dimension currentImageDimension) {
        return (double) currentImageDimension.getWidth() / currentImageDimension.getHeight();
    }

    private static Dimension getFileImageSize(@NonNull File imgFile) throws IOException {

        int position = imgFile.getName().lastIndexOf(".");
        if (position == -1) {
            throw new IOException("No extension for file: " + imgFile.getAbsolutePath());
        }

        String fileExtension= imgFile.getName().substring(position + 1);
        if (!fileExtension.equalsIgnoreCase("jpg")){
            throw new IOException("Format " + fileExtension + " not allowed. JPG only.");
        }

        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(fileExtension);
        int width;
        int height;
        while(iter.hasNext()) {
            ImageReader reader = iter.next();
            try (ImageInputStream iiStream = new FileImageInputStream(imgFile)) {
                reader.setInput(iiStream);
                width = reader.getWidth(reader.getMinIndex());
                height = reader.getHeight(reader.getMinIndex());
                return new Dimension(width, height);
            } catch (IOException e) {
                System.err.println("Error reading: " + imgFile.getAbsolutePath());
            } finally {
                reader.dispose();
            }
        }
        return null;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
