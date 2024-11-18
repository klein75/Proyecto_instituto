package com.instituto.demoj.Profile.domain.entity;

import com.instituto.demoj.User.domain.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Profile")
public class ProfileEntity {

    @Id 
    private Long id; 

    @OneToOne 
    @MapsId
    @JoinColumn(name = "Users_id") 
    private User user; 

    private String bio;

    private String image;

}
