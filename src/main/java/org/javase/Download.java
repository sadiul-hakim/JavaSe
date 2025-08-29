package org.javase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Download {
    public static void main(String[] args) throws IOException {
        URL url = URI.create("https://content.e-bookshelf.de/media/reading/L-13405579-ffd8579004.pdf").toURL();
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();
        Path path = Path.of("F:\\Spring Boot Persistence Best Practices Optimize Java Persistence Performance in Spring Boot Applications.pdf");
        Files.copy(is, path);
        System.out.println("Done");
    }
}
