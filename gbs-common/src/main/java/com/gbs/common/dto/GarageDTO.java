package com.gbs.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarageDTO extends BaseEntityDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String address;

    private int totalSlots;

    private List<AddressDTO> addresses;
}
