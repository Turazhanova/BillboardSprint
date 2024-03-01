package com.example.billboardproject.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Billboard extends BaseEntity{
    private String billboard_url;
    private String location;
    private String city;
    private String type;
    private String size;
    private boolean isHasLightning;
    private double price;
    private boolean isActive;
}
