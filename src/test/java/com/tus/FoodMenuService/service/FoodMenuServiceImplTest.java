package com.tus.FoodMenuService.service;

import com.tus.FoodMenuService.entity.FoodAllergens;
import com.tus.FoodMenuService.entity.FoodMenu;
import com.tus.FoodMenuService.model.FoodMenuRequest;
import com.tus.FoodMenuService.repository.FoodAllergensRepository;
import com.tus.FoodMenuService.repository.FoodMenuRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoodMenuServiceImplTest {

    @InjectMocks
    private FoodMenuServiceImpl foodMenuService;

    @Mock
    private FoodMenuRepository foodMenuRepository;

    @Mock
    private FoodAllergensRepository foodAllergensRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFoodItem() {
        // Arrange
        FoodMenuRequest foodMenuRequest = new FoodMenuRequest();
        foodMenuRequest.setFoodName("Burger");
        foodMenuRequest.setFoodType("Fast Food");
        foodMenuRequest.setFoodPrice(200);
        foodMenuRequest.setFoodAllergens("Gluten, Soy, Egg");

        Set<FoodAllergens> allergensSet = new HashSet<>();
        FoodAllergens allergen1 = new FoodAllergens();
        allergen1.setFoodID(1L);
        allergen1.setFoodName("Gluten");
        allergen1.setFoodAllergens("Contains gluten");
        allergensSet.add(allergen1);
        FoodAllergens allergen2 = new FoodAllergens();
        allergen2.setFoodID(2L);
        allergen2.setFoodName("Soy");
        allergen2.setFoodAllergens("Contains soy");
        allergensSet.add(allergen2);
        FoodAllergens allergen3 = new FoodAllergens();
        allergen3.setFoodID(3L);
        allergen3.setFoodName("Egg");
        allergen3.setFoodAllergens("Contains egg");
        allergensSet.add(allergen3);

        FoodMenu foodMenu = new FoodMenu();
        foodMenu.setFoodID(1L);
        foodMenu.setFoodName(foodMenuRequest.getFoodName());
        foodMenu.setFoodType(foodMenuRequest.getFoodType());
        foodMenu.setFoodPrice(foodMenuRequest.getFoodPrice());
        foodMenu.setAllergens(allergensSet);

        when(foodAllergensRepository.findByfoodName("Gluten")).thenReturn(Optional.of(allergen1));
        when(foodAllergensRepository.findByfoodName("Soy")).thenReturn(Optional.of(allergen2));
        when(foodAllergensRepository.findByfoodName("Egg")).thenReturn(Optional.of(allergen3));
        when(foodMenuRepository.save(any(FoodMenu.class))).thenReturn(foodMenu);

        // Act
        long foodID = foodMenuService.addFoodItem(foodMenuRequest);

        // Assert
        assertEquals(0, foodID);
        verify(foodAllergensRepository, times(3)).findByfoodName(anyString());
        verify(foodMenuRepository, times(1)).save(any(FoodMenu.class));
    }

    @Test
    public void testGetFoodAllergensByIDWithInvalidID() {
        // Arrange
        long invalidFoodID = 999;

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            foodMenuService.getFoodAllergensByID(invalidFoodID);
        });
        assertTrue(exception.getMessage().contains("Unable to find food item with given ID."));
    }

}