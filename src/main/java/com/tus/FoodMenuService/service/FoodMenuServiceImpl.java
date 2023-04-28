package com.tus.FoodMenuService.service;

import com.tus.FoodMenuService.entity.FoodAllergens;
import com.tus.FoodMenuService.entity.FoodMenu;
import com.tus.FoodMenuService.model.FoodAllergensResponse;
import com.tus.FoodMenuService.model.FoodMenuRequest;
import com.tus.FoodMenuService.model.FoodMenuResponse;
import com.tus.FoodMenuService.repository.FoodAllergensRepository;
import com.tus.FoodMenuService.repository.FoodMenuRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class FoodMenuServiceImpl implements FoodMenuService
{
    @Autowired
    private FoodMenuRepository foodMenuRepository;

    @Autowired
    private FoodAllergensRepository foodAllergensRepository;

    @Override
    public long addFoodItem(FoodMenuRequest foodMenuRequest)
    {
        log.info("In the addFoodItem method, trying to add food item.");

        FoodMenu foodMenu = FoodMenu.builder()
                        .foodName(foodMenuRequest.getFoodName())
                                .foodType(foodMenuRequest.getFoodType())
                                        .foodPrice(foodMenuRequest.getFoodPrice())
                                                .build();

        //Find all the allergens
        String[] allergensArray = foodMenuRequest.getFoodAllergens().split(",\\s*");

        Set<FoodAllergens> newSet = new HashSet<>();
        for (String allergen : allergensArray) {
            Optional<FoodAllergens> foodAllergensOptional = foodAllergensRepository.findByfoodName(allergen);
            if (foodAllergensOptional.isPresent())
            {
                newSet.add(foodAllergensOptional.get());
            }
        }

        //add to foodMenu
        foodMenu.setAllergens(newSet);

        foodMenuRepository.save(foodMenu);
        log.info("Food item successfully saved in the database.");

//        FoodAllergens foodAllergens = FoodAllergens.builder()
//                .foodID(foodMenu.getFoodID())
//                .foodName(foodMenuRequest.getFoodName())
//                .foodAllergens(foodMenuRequest.getFoodAllergens())
//                .build();
//
//        foodAllergensRepository.save(foodAllergens);

        return foodMenu.getFoodID();
    }

    @Override
    public FoodMenuResponse getFoodItemByID(long foodID)
    {
        log.info("Get the food item for the food item ID: {}", foodID);

        FoodMenu foodMenu = foodMenuRepository.findById(foodID)
                .orElseThrow(() -> new RuntimeException("Unable to find food item with given ID."));

        FoodMenuResponse foodMenuResponse = FoodMenuResponse.builder()
                .foodID(foodMenu.getFoodID())
                .foodName(foodMenu.getFoodName())
                .foodType(foodMenu.getFoodType())
                .foodPrice(foodMenu.getFoodPrice())
                .build();

        log.info("Food item with food item id {}, fetch operation completed", foodID);
        return foodMenuResponse;
    }

    @Override
    public FoodAllergensResponse getFoodAllergensByID(long foodID)
    {
        log.info("Get the corresponding allergens for the food item ID: {}", foodID);

        FoodAllergens foodAllergens = foodAllergensRepository.findById(foodID)
                .orElseThrow(() -> new RuntimeException("Unable to find food item with given ID."));

        FoodAllergensResponse foodAllergensResponse = FoodAllergensResponse.builder()
                .foodID(foodAllergens.getFoodID())
                .foodName(foodAllergens.getFoodName())
                .foodAllergens(foodAllergens.getFoodAllergens())
                .build();

        log.info("Food item with food item id {}, fetch operation completed", foodID);
        return foodAllergensResponse;
    }
}
