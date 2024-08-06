package com.gbs.auth.service.impl;

import com.gbs.auth.repository.UserRepository;
import com.gbs.auth.service.UserService;
import com.gbs.common.dto.AddressDTO;
import com.gbs.common.dto.UserDTO;
import com.gbs.common.entity.Address;
import com.gbs.common.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = convertToEntity(userDTO);
        User saveUser = userRepository.save(user);
        UserDTO response = convertToDTO(saveUser);
        return response;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO findByUuid(String uuid) {
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO findByPhone(String phone) {
        return null;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();

        List<Address> addresses = new ArrayList<>();
        List<AddressDTO> userAddresses = userDTO.getUserAddresses();
        for (AddressDTO addressDTO : userAddresses) {
            Address address = new Address();
            BeanUtils.copyProperties(addressDTO, address);
            address.setUser(user);
            addresses.add(address);
        }
        user.setUserAddresses(addresses);
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    private UserDTO convertToDTO(User user) {
        List<AddressDTO> addressDTOs = user.getUserAddresses().stream()
                .map(address -> {
                    AddressDTO addressDTO = new AddressDTO();
                    BeanUtils.copyProperties(address, addressDTO,"id","uuid","createdAt","createdBy","updatedAt","updatedBy");
                    return addressDTO;
                })
                .collect(Collectors.toList());

        UserDTO userDTO = new UserDTO();
        userDTO.setUserAddresses(addressDTOs);
        BeanUtils.copyProperties(user, userDTO, "createdAt","createdBy","updatedAt","updatedBy","password");
        return userDTO;
    }

}
