package com.example.bankmanagement.Service;

import com.example.bankmanagement.Exception.ApiException;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public void register(User user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    //admin
    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

    public void updateUser(User user,Integer auth){
        User user1 =authRepository.findUserById(auth);
        user1.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user1.setUsername(user.getUsername());
        authRepository.save(user1);
    }

    public void deleteUser(Integer id){
        User user = authRepository.findUserById(id);
        if(user==null){
            throw new ApiException("User Not Found");
        }
        authRepository.delete(user);
    }


}
