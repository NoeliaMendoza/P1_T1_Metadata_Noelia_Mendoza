package proyecto.p1proyecto1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/home")
    public String home(
            @RequestParam(value = "name", defaultValue = "Mundo") String name,
            @RequestParam(value = "language", defaultValue = "es") String language) {

        if (language.equals("es")) {
            return String.format("<h1 style=\"color: blue\">Hola %s!</h1>", name);
        }

        if (language.equals("en")) {
            return String.format("<h1 style=\"color: green\">Hello %s!</h1>", name);
        }

        if (language.equals("pt")) {
            return String.format("<h1 style=\"color: gray\">Olá %s!</h1>", name);
        }

        return String.format(
                "<h1 style=\"color: red\">Lenguaje '%s' no soportado</h1>",
                language
        );
    }
}
