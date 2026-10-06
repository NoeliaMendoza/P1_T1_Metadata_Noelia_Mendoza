package proyecto.p1proyecto1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import proyecto.p1proyecto1.config.AppInfoProperties;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InfoController.class)
@Import(AppInfoProperties.class)
@ActiveProfiles("dev")
class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void debeRetornarInformacionDeLaAplicacion() throws Exception {

        mockMvc.perform(get("/api/info"))
                .andDo(print())

                // Código HTTP 200
                .andExpect(status().isOk())

                // Verifica campos del JSON
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.environment").exists())

                // Verifica valores
                .andExpect(jsonPath("$.name").value("Gestor de Inventario"))
                .andExpect(jsonPath("$.environment").value("dev"))

                // Verifica formato de email
                .andExpect(jsonPath("$.developerEmail",
                        matchesPattern("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")));
    }
}