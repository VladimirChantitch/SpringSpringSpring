package com.ipi.quiditchmanager.pojos;

import com.ipi.quiditchmanager.customvalidator.PhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Stadium name is required")
    private String name;
    private int capacity;
    @NotEmpty(message = "A phone number is required")
    @PhoneNumber
    private String phoneNumber;
    @OneToMany(mappedBy = "stadium")
    private List<Game> matches;

    public List<Game> getMatches() {
        return matches;
    }

    public void setMatches(List<Game> matches) {
        this.matches = matches;
    }

    public Stadium(String name, int capacity, String phoneNumber, List<Game> matches) {
        this.name = name;
        this.capacity = capacity;
        this.phoneNumber = phoneNumber;
        this.matches = matches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Stadium() {
    }
}
