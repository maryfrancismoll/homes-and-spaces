package com.model;

/**
 * @author Maryfrancis Remo Moll
 *
 * enum contains the roles allowed in the app
 */
public enum UserRoleEnum {

    ADMIN(1L, "ADMIN"),
    USER(2L, "USER");

    private Long id;
    private String value;

    UserRoleEnum(Long id, String value){
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
