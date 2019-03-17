package pl.mkpk.newhorizon.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    int id;

    @CsvBindByName
    String category;

    @CsvBindByName
    @Column(name = "recipe_name")
    String recipeName;

    @CsvBindByName
    int kcal;

    @CsvBindByName
    @Column(name = "preparation_steps")
    String preparationSteps;

    @CsvBindByName
    @Column(length = 4)
    int quantity01,
            quantity02,
            quantity03,
            quantity04,
            quantity05,
            quantity06,
            quantity07,
            quantity08,
            quantity09,
            quantity10,
            quantity11,
            quantity12,
            quantity13,
            quantity14,
            quantity15,
            quantity16,
            quantity17,
            quantity18,
            quantity19,
            quantity20;

    @CsvBindByName
    @Column(length = 3)
    String unit01,
            unit02,
            unit03,
            unit04,
            unit05,
            unit06,
            unit07,
            unit08,
            unit09,
            unit10,
            unit11,
            unit12,
            unit13,
            unit14,
            unit15,
            unit16,
            unit17,
            unit18,
            unit19,
            unit20;

    @CsvBindByName
    @Column(length = 50)
    String ingredient01,
            ingredient02,
            ingredient03,
            ingredient04,
            ingredient05,
            ingredient06,
            ingredient07,
            ingredient08,
            ingredient09,
            ingredient10,
            ingredient11,
            ingredient12,
            ingredient13,
            ingredient14,
            ingredient15,
            ingredient16,
            ingredient17,
            ingredient18,
            ingredient19,
            ingredient20;
}


