package com.yutong.practice.Controller;

import java.util.stream.Collectors;
import com.yutong.practice.Domain.Ingredient;
import com.yutong.practice.Domain.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO","FLour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO","Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF","Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN","Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO","Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("CHED","Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("SLSA","Salas", Ingredient.Type.SAUCE)
        ) ;

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }
        model.addAttribute("design",new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }
}
