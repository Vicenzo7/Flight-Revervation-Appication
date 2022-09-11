package com.vicenzo.flightreservation.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role extends AbstractEntity implements GrantedAuthority{

    private String name;

    @ManyToMany(mappedBy ="roles")
    private Set<User> users;


    // spring internally calls this method to check which role particular user has
    @Override
    public String getAuthority() {
        return name;
    }
}
