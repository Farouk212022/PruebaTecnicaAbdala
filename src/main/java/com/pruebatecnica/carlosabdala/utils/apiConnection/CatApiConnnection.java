package com.pruebatecnica.carlosabdala.utils.apiConnection;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Component
public class CatApiConnnection {

    public BufferedImage recoverImageFromApi() throws IOException {
        URL url = new URL("https://cataas.com/cat");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        System.out.println(connection+ "Connection");

        int responseCode = connection.getResponseCode();
        if (200 == responseCode) {
            InputStream inputStream = connection.getInputStream();
            return ImageIO.read(inputStream);
        } else {
            throw new IOException("Error al recuperar de la API. Estado de respuesta: " + responseCode);
        }
    }
}
