package com.pruebatecnica.carlosabdala.utilTest.dataPreparation;

import com.pruebatecnica.carlosabdala.bd.CatImage.CatImage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CatImageTestPreparation {

    public CatImage prepareTestCatImage(Long id, byte[] binaryImage, boolean exist) {
        CatImage testObject = new CatImage();
        testObject.setBinaryImage(binaryImage);
        testObject.setCreationDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        if (exist) {
            testObject.setLastCallDateTime(LocalDateTime.of(2025, 1, 18, 0, 0, 0,0));
        } else {
            testObject.setLastCallDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        }

        testObject.setIdImage(id);
        return testObject;
    }
}
