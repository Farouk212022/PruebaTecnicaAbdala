package com.pruebatecnica.carlosabdala.logica;

import com.pruebatecnica.carlosabdala.bd.CatImage.CatImage;
import com.pruebatecnica.carlosabdala.bd.CatImage.CatImageRepository;
import com.pruebatecnica.carlosabdala.utilTest.dataPreparation.ApiResponseTestPreparation;
import com.pruebatecnica.carlosabdala.utilTest.dataPreparation.CatImageTestPreparation;
import com.pruebatecnica.carlosabdala.utils.apiConnection.CatApiConnnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CatImagesLogicTest {

    @InjectMocks
    CatImagesLogic catImagesLogic;

    @Mock
    CatApiConnnection catApiConnnection;

    @Mock
    CatImageRepository catImageRepository;

    ApiResponseTestPreparation apiResponseTestPreparation = new ApiResponseTestPreparation();
    CatImageTestPreparation catImageTestPreparation = new CatImageTestPreparation();


    @Test
    void Given_an_image_from_the_API_When_register_the_image_Then_return_image_and_save_image_on_database() throws IOException {
        BufferedImage testImage = apiResponseTestPreparation.catApiTestResponsePreparation();
        byte[] binaryImage = apiResponseTestPreparation.binaryImageTestPreparation(testImage);
        List<CatImage> returnedList = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        Mockito.when(catApiConnnection.recoverImageFromApi()).thenReturn(testImage);
        Mockito.when(catImageRepository.findCatImageByBinary(binaryImage)).thenReturn(returnedList);

        ResponseEntity<?> expectedResponse = ResponseEntity.ok().headers(headers).body(binaryImage);
        ResponseEntity<?> receivedResponse = catImagesLogic.registerImage();

        Mockito.verify(catImageRepository, Mockito.atLeastOnce()).findCatImageByBinary(binaryImage);
        ArgumentCaptor<CatImage> catImageCaptor = ArgumentCaptor.forClass(CatImage.class);
        Mockito.verify(catImageRepository).save(catImageCaptor.capture());

        Assertions.assertEquals(expectedResponse, receivedResponse);
    }

    @Test
    void Given_an_image_from_the_API_When_register_the_repeated_image_Then_return_image_and_update_image_register() throws IOException {
        BufferedImage testImage = apiResponseTestPreparation.catApiTestResponsePreparation();
        byte[] binaryImage = apiResponseTestPreparation.binaryImageTestPreparation(testImage);
        CatImage catImage = catImageTestPreparation.prepareTestCatImage(1L, binaryImage, true);
        List<CatImage> returnedList = new ArrayList<>();
        returnedList.add(catImage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        Mockito.when(catApiConnnection.recoverImageFromApi()).thenReturn(testImage);
        Mockito.when(catImageRepository.findCatImageByBinary(binaryImage)).thenReturn(returnedList);

        ResponseEntity<?> expectedResponse = ResponseEntity.ok().headers(headers).body(binaryImage);
        ResponseEntity<?> receivedResponse = catImagesLogic.registerImage();

        Mockito.verify(catImageRepository, Mockito.atLeastOnce()).findCatImageByBinary(binaryImage);
        ArgumentCaptor<CatImage> catImageCaptor = ArgumentCaptor.forClass(CatImage.class);
        Mockito.verify(catImageRepository).save(catImageCaptor.capture());

        Assertions.assertEquals(expectedResponse, receivedResponse);
    }

    @Test
    void Given_an_error_When_register_the_repeated_image_Then_return_error_message() throws IOException {
        Mockito.when(catApiConnnection.recoverImageFromApi()).thenThrow(new RuntimeException("Error Simulado"));

        ResponseEntity<?> expectedResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al traer la imagen desde la API");
        ResponseEntity<?> receivedResponse = catImagesLogic.registerImage();

        Assertions.assertEquals(expectedResponse, receivedResponse);
    }

    @Test
    void Given_the_number_of_registers_When_request_the_number_of_registers_Then_return_the_number_of_registers() {
        Long numberTest = 10L;

        Mockito.when(catImageRepository.count()).thenReturn(numberTest);

        ResponseEntity<?> expectedResponse = ResponseEntity.status(HttpStatus.OK).body("Imágenes guardadas: " + numberTest+1);
        ResponseEntity<?> receivedResponse = catImagesLogic.uniqueRegistersCount();

        Mockito.verify(catImageRepository).count();

        Assertions.assertEquals(expectedResponse, receivedResponse);
    }

    @Test
    void Given_an_error_When_request_the_number_of_registers_Then_return_error_message() {
        Mockito.when(catImageRepository.count()).thenThrow(new RuntimeException("Error Simulado"));
        ResponseEntity<?> expectedResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al traer la imagen desde la API");
        ResponseEntity<?> receivedResponse = catImagesLogic.uniqueRegistersCount();

        Assertions.assertEquals(expectedResponse, receivedResponse);
    }
}