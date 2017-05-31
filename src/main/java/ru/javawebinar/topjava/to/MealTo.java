package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Валера on 29.05.2017.
 */
public class MealTo implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    private String description;

    @Range(min = 10, max = 3000)
    @NotNull
    private Integer calories;

    @DateTimeFormat
    private LocalDateTime dateTime;

    public MealTo() {
    }

    public MealTo(Integer id, String description, int calories, LocalDateTime dateTime) {
        this.id = id;
        this.description = description;
        this.calories = calories;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", dateTime=" + dateTime +
                '}';
    }
}
