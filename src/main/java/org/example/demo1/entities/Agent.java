package org.example.demo1.entities;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.demo1.enums.Specialite;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Agent extends Client {
    private Specialite specialite;
}
