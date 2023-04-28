package com.tus.FoodMenuService.controller;

import com.tus.FoodMenuService.entity.FoodMenu;
import com.tus.FoodMenuService.model.FoodAllergensResponse;
import com.tus.FoodMenuService.model.FoodMenuRequest;
import com.tus.FoodMenuService.model.FoodMenuResponse;
import com.tus.FoodMenuService.repository.FoodMenuRepository;
import com.tus.FoodMenuService.service.FoodMenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1/foodmenu")
public class FoodMenuController
{
    @Autowired
    private FoodMenuService foodMenuService;

    @Autowired
    private FoodMenuRepository foodMenuRepository;

    @PostMapping()
    public ResponseEntity<Long> addFoodItem(@RequestBody FoodMenuRequest foodMenuRequest)
    {
        long foodID = foodMenuService.addFoodItem(foodMenuRequest);
        return new ResponseEntity<>(foodID, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FoodMenuResponse> getFoodItemByID(@PathVariable("id") long foodID)
    {
        FoodMenuResponse foodMenuResponse = foodMenuService.getFoodItemByID(foodID);
        return new ResponseEntity<>(foodMenuResponse, HttpStatus.OK);
    }

    @GetMapping("/aller/{id}")
    public ResponseEntity<FoodAllergensResponse> getFoodAllergensByID(@PathVariable("id") long foodID)
    {
        FoodAllergensResponse foodAllergensResponse = foodMenuService.getFoodAllergensByID(foodID);
        return new ResponseEntity<>(foodAllergensResponse, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<FoodMenuResponse>> getAllFoodItems()
    {
        List<FoodMenu> foodMenuList = foodMenuRepository.findAll();
        List<FoodMenuResponse> foodMenuResponseList = foodMenuList.stream()
                .map(foodMenu -> new FoodMenuResponse(
                        foodMenu.getFoodID(),
                        foodMenu.getFoodName(),
                        foodMenu.getFoodType(),
                        foodMenu.getFoodPrice()
                ))
                .collect(Collectors.toList());
        log.info("All the food items fetched.");
        return ResponseEntity.ok(foodMenuResponseList);
    }
}
