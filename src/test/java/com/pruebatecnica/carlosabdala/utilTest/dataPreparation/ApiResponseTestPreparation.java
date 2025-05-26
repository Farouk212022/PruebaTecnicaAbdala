package com.pruebatecnica.carlosabdala.utilTest.dataPreparation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ApiResponseTestPreparation {

    public BufferedImage catApiTestResponsePreparation() {
        return new BufferedImage(10, 10, BufferedImage.TYPE_BYTE_GRAY);
    }

    public byte[] binaryImageTestPreparation(BufferedImage testImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(testImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
