package org.elsys.ip.security;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
    @Id
    private String username;

    private String passwordHash;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
