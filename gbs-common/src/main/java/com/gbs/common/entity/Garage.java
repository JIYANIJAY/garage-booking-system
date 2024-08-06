package com.gbs.common.entity;

import com.gbs.common.helper.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Entity
@Table(name = "GARAGES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityScan
public class Garage extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TOTAL_SLOTS")
    private int totalSlots;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "garage")
    private List<Address> addresses;
}
