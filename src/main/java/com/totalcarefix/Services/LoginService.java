package com.totalcarefix.Services;

import com.totalcarefix.DTO.LoginDto;
import com.totalcarefix.Entities.Users;
import com.totalcarefix.Entities.roles;
import com.totalcarefix.Repos.UsersRepo;
import com.totalcarefix.Repos.rolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private rolesRepo roleRepo;

    public LoginDto getToken(String email){
        Optional<Users> optionalUsers = Optional.ofNullable(usersRepo.findByEmail(email));
        Users users = optionalUsers.orElse(null);
        if (users != null) {
            int id=users.getRole_id();
          roles rol= roleRepo.findById(id).get();
          LoginDto loginDto=LoginDto.builder()
                  .role(rol.getName())
                  .email(users.getEmail())
                  .build();
          return loginDto;
        }
        else {
            return LoginDto.builder()
                    .role(null)
                    .email(null)
                    .build();
        }

    }
}
