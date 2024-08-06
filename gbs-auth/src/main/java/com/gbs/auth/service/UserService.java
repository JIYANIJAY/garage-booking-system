package com.gbs.auth.service;


import com.gbs.common.dto.UserDTO;

public interface UserService {

    public UserDTO save(UserDTO userDTO);
    public UserDTO update(UserDTO userDTO);
    public UserDTO findByUuid(String uuid);
    public UserDTO findByEmail(String email);
    public UserDTO findByPhone(String phone);
}
