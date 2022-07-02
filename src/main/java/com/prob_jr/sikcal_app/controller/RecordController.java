package com.prob_jr.sikcal_app.controller;

import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.repository.FoodSearch;
import com.prob_jr.sikcal_app.domain.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/record")
    public String record(@RequestParam("memberId") Long memberId,
                         @RequestParam("foodName") String foodName) {
        recordService.record(memberId, foodName);

        return "redirect:/search";
    }

    @GetMapping("/home/search")
    public String foodSearch(@ModelAttribute("foodSearch")FoodSearch foodSearch, Model model) {
        List<RecordFood> foods = recordService.findFoods(foodSearch);

        model.addAttribute("search", foods);

        return "/search";
    }

    @PostMapping("/home")
    public String cancelOrder() {
        

        return "redirect:/search";
    }

}
