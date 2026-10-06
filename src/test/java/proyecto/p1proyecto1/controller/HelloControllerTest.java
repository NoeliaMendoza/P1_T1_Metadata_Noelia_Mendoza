package proyecto.p1proyecto1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeValoresPorDefecto() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "<h1 style=\"color: blue\">Hola Mundo!</h1>"
                ));
    }

    @Test
    void homeConNombre() throws Exception {
        mockMvc.perform(get("/home")
                        .param("name", "Juan"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "<h1 style=\"color: blue\">Hola Juan!</h1>"
                ));
    }

    @Test
    void homeConNombreEIngles() throws Exception {
        mockMvc.perform(get("/home")
                        .param("name", "Juan")
                        .param("language", "en"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "<h1 style=\"color: green\">Hello Juan!</h1>"
                ));
    }

    @Test
    void homeConNombreEPortugues() throws Exception {
        mockMvc.perform(get("/home")
                        .param("name", "Carlos")
                        .param("language", "pt"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "<h1 style=\"color: gray\">Olá Carlos!</h1>"
                ));
    }
}
