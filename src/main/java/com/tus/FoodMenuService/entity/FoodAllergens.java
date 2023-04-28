package com.tus.FoodMenuService.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_foodAllergens")
public class FoodAllergens
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "allergen_id")
    private long foodID;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "food_Allergens")
    private String foodAllergens;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "food_allergens",
            joinColumns = @JoinColumn(name = "allergen_id", referencedColumnName = "allergen_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "food_id"))
    private Set<FoodMenu> foodMenus;
}
