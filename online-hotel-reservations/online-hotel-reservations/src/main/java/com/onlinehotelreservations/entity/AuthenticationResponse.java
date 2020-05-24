package com.onlinehotelreservations.entity;

import java.io.Serializable;
import java.util.List;

public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;
    private final String type = "Bearer";
    private String username;
    private String firstName;
    private List<RoleEntity> accountRoles;

    public AuthenticationResponse(String jwttoken, String username, String firstName, List<RoleEntity> accountRoles) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.firstName = firstName;
        this.accountRoles = accountRoles;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<RoleEntity> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<RoleEntity> accountRoles) {
        this.accountRoles = accountRoles;
    }

}
