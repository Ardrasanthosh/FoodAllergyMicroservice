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
@Table(name = "tbl_foodMenu")
public class FoodMenu
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    private long foodID;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "food_type")
    private String foodType;
    @Column(name = "food_price")
    private double foodPrice;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "food_allergens",
            joinColumns = @JoinColumn(name = "food_id", referencedColumnName = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id", referencedColumnName = "allergen_id"))
    private Set<FoodAllergens> allergens;
}
