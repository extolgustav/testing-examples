package com.mercadolibre.calculadoracalorias;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculadoraCaloriasApplicationTests {
  @Autowired
  private MockMvc mockMvc;

  //a) Retorna as calorias totais do prato
  @Test
  void getDishTotalCalories() throws Exception {

    /*
    "name": "Ensalada",
    "ingredients": [

     */


    String request = "{\"name\": \"Ensalada\", \"ingredients\": ["
            + createIngredient("Calabaza", 100) + "," +
            createIngredient("Cebolla", 50) +
            "]}";


    this.mockMvc.perform(post("/calculate").contentType(MediaType.APPLICATION_JSON).content(request))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(jsonPath("$.sum($.ingredients[0].calories,$.ingredients[1].calories)")
                    .value(47));


  }






  //b) retorna as calorias de cada ingrediente
  @Test
  void getDishIngredientsCalories() throws Exception {
    String request = "{\"name\": \"Ensalada\", \"ingredients\": ["
            + createIngredient("Calabaza", 100) + "," +
            createIngredient("Cebolla", 50) +
            "]}";




    this.mockMvc.perform(post("/calculate")

            .contentType(MediaType.APPLICATION_JSON).content(request))

            .andDo(print())

            .andExpect(status().isCreated())

            .andExpect(jsonPath("$.ingredients[0].calories").value(24))
            .andExpect(jsonPath("$.ingredients[1].calories").value(23));


  }

  //c) ingrediente com mais calorias

  @Test
  void caloricIngredient() throws Exception {

    String request = "{\"name\": \"Ensalada\", \"ingredients\": ["
            + createIngredient("Calabaza", 100) + "," +
            createIngredient("Cebolla", 50) +
            "]}";

    this.mockMvc.perform(post("/calculate").contentType(MediaType.APPLICATION_JSON).content(request))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(jsonPath("max($.ingredients[0].calories,$.ingredients[1].calories)").value(24));
  }

  //d) Gera um endpoint para receber uma lista de pratos e devolver a lista processada.
  @Test
  void processListOfDishes() throws Exception {
    String request = "[" +
            "{\"name\": \"Ensalada\", \"ingredients\": [" +
            createIngredient("Calabaza", 100) + "," +
            createIngredient("Cebolla", 70) + "]}," +
            "{\"name\": \"Hamburguesa\", \"ingredients\": [" +
            createIngredient("Hamburguesa", 100) + "," +
            createIngredient("Pan de trigo blanco", 100) + "," +
            createIngredient("Cebolla", 70) + "]}" +
            "]";

    this.mockMvc.perform(
            post("/calculateAll")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].calories").value("56"))
            .andExpect(jsonPath("$[1].calories").value("517"));


  }

  private String createIngredient(String name, int weight) {
    return "{\"name\": \""+name+"\", \"weight\": "+weight+"}";
  }

}
