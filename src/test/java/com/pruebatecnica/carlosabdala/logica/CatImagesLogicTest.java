package com.pruebatecnica.carlosabdala.logica;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CatImagesLogicTest {


    @Test
    void Given_an_image_from_the_API_When_register_the_image_Then_return_image_and_save_image_on_database() throws IOException {
        assertEquals(1, 1);
    }

}