package org.example.model;

import java.security.Permission;
import java.util.List;
import java.util.UUID;

public class Role {
    private final Long id;
    private String name;
    private List<Permission> permissions ;

    public Role(){
        this.id = UUID.randomUUID().timestamp();
    }

    public Role(String name){
        this();
        this.name = name;
    }
}
