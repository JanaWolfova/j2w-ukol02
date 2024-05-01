
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class StrankaController {

    private static final String[] OBRAZKY = {
            "https://source.unsplash.com/1600x900/?nature",
            "https://source.unsplash.com/1600x900/?technology",
            "https://source.unsplash.com/1600x900/?travel",
            // další odkazy na obrázky...
    };

    private static final String[] CITATY = {
            "Citát 1",
            "Citát 2",
            "Citát 3",
            // další citáty...
    };

    @GetMapping("/stranka")
    public String zobrazStranku(Model model) {
        Random random = new Random();
        int indexObrazku = random.nextInt(OBRAZKY.length);
        int indexCitatu = random.nextInt(CITATY.length);

        String obrazek = OBRAZKY[indexObrazku];
        String citat = CITATY[indexCitatu];

        model.addAttribute("obrazek", obrazek);
        model.addAttribute("citat", citat);

        return "stranka";
    }
}
