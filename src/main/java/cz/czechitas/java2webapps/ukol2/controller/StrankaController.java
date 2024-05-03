package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.io.IOException;


@Controller
public class StrankaController {

    private static final String[] OBRAZKY = {
            "https://source.unsplash.com/1600x900/?nature"
    };


    private static List<String> citaty;

    static {
        try {
            citaty = readAllLines("citaty.txt");
        } catch (Exception e) {
            throw new RuntimeException("Error loading quotes from file: citaty.txt", e);
        }
    }


    private static List<String> readAllLines(String resource) throws IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


        try (InputStream inputStream = classLoader.getResourceAsStream(resource);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {


            return reader.lines().collect(Collectors.toList());
        }
    }

    @GetMapping("/")
    public ModelAndView zobrazStranku() {
        ModelAndView model = new ModelAndView("index");
        Random random = new Random();
        int indexCitatu = random.nextInt(citaty.size());
        String citat = citaty.get(indexCitatu);
        String obrazek = OBRAZKY[random.nextInt(OBRAZKY.length)];
        model.addObject("obrazek", obrazek);
        model.addObject("citat", citat);
        return model;
    }
}
