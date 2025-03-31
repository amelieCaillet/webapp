package com.openclassrooms.webapp;

import com.openclassrooms.webapp.controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc; //instance de l'objet obtenue grace au @AutoConfigureMockMvc

    @Test
    public void testGetEmployees() throws Exception {
        //type de requête
        mockMvc.perform(get("/"))
                //affiche le résultat de la requête dans la console
                .andDo(print())
                //vérification du résultat
                .andExpect(status().isOk())
                //contrôle du retour de la requête (vue retournée = vue attendue)
                .andExpect(view().name("home"))
                //contrôle du texte de la réponse
                .andExpect(content().string(containsString("Laurent")));
    }
}
