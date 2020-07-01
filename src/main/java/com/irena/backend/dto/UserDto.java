package com.irena.backend.dto;

public class UserDto implements Dto {
    private long id;
    private String name;
    private String password;

    public UserDto(String name, String password, long id) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public long getId() {
        return id;
    }

    public UserDto setId(long id) {
        this.id = id;
        return this;
    }
}
