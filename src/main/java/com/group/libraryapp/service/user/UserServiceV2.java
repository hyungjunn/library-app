package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.domain.user.UserReposritory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserReposritory userReposritory;

    public UserServiceV2(UserReposritory userReposritory) {
        this.userReposritory = userReposritory;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        userReposritory.save(new User(request.getName(), request.getAge()));
    }

    @Transactional
    public List<UserResponse> getUsers() {
        //return userReposritory.findAll(); << 왜 이게 아닌지 생각해!!
        return userReposritory.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userReposritory.findById(request.getId())
                .orElseThrow(IllegalAccessError::new);

        user.updateName(request.getName());
    }

    @Transactional
    public void deleteUser(String name) {
        // String readSql = "SELECT * FROM user WHERE name = ?";
        User user = userReposritory.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        if (user == null) {
            throw new IllegalArgumentException();
        }

        userReposritory.delete(user);
    }

    @Transactional
    public void deleteHistroy() {
        User user = userReposritory.findByName("임형준")
                .orElseThrow(IllegalArgumentException::new);
        user.removeAntHistroy();
    }

}
