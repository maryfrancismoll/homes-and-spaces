package com.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "spaces")
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "daily_rate")
    private Float dailyRate;

    @Column(name = "address")
    private String address;

    @Column(name = "available_from")
    private Timestamp availableFrom;

    @Column(name = "available_to")
    private Timestamp availableTo;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", insertable = false, updatable=false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable=false)
    private UserInformation userInformation;

    public Space(Integer id, String title, Integer categoryId, String description, Float dailyRate, String address, Timestamp availableFrom, Timestamp availableTo, Long userId, Category category, UserInformation userInformation) {
        this(title, categoryId, description, dailyRate, address, availableFrom, availableTo, userId, category, userInformation);
        this.id = id;
    }

    public Space(String title, Integer categoryId, String description, Float dailyRate, String address, Timestamp availableFrom, Timestamp availableTo, Long userId, Category category, UserInformation userInformation) {
        this.title = title;
        this.categoryId = categoryId;
        this.description = description;
        this.dailyRate = dailyRate;
        this.address = address;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.userId = userId;
        this.category = category;
        this.userInformation = userInformation;
    }

    public Space() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Float dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Timestamp availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Timestamp getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Timestamp availableTo) {
        this.availableTo = availableTo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }
}
