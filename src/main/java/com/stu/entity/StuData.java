package com.stu.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by zxg on 2018/7/16.
 */
public class StuData implements Serializable {
    private String id;
    private String name;
    private LocalDate birthday;
    private String description;
    private Integer avgScore;

    @Override
    public String toString() {
        return "com.stu.entity.StuData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", avgScore=" + avgScore +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Integer avgScore) {
        this.avgScore = avgScore;
    }

    public StuData() {
    }

    public StuData(String id, String name, LocalDate birthday, String description, Integer avgScore) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.description = description;
        this.avgScore = avgScore;
    }
}
