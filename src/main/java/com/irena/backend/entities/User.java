package com.irena.backend.entities;

import com.irena.backend.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    private String name;
    private String password;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(UserDto userDto) {
        this.name = userDto.getName();
        if (userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        } else {
            this.password = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        }
    }

    public UserDto toDto() {
        return new UserDto(this.name, this.password, this.getId());
    }

    public boolean passwordMatch(String password) {
        return this.password.equals(password);
    }
}
