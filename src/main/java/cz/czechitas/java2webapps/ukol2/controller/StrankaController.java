import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Controller
public class StrankaController {

    private static final String[] OBRAZKY = {
            "https://source.unsplash.com/1600x900/?nature"
    };

    private List<String> citaty;

    public StrankaController() {
        try {
            // Načtení citátů ze souboru
            this.citaty = Files.readAllLines(Paths.get("citaty.txt"));
        } catch (IOException e) {
            // Pokud nastane chyba při načítání citátů, není možné pokračovat, proto program ukončíme.
            throw new RuntimeException("Chyba při načítání citátů ze souboru.", e);
        }
    }

    @GetMapping("/stranka")
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
