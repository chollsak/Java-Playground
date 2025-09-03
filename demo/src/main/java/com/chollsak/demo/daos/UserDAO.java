package com.chollsak.demo.daos;

import com.chollsak.demo.models.UserModel;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    private List<UserModel> users = new ArrayList<>(
            List.of(
                    new UserModel("1", "Alice", "alice@gmial.com", 12),
                    new UserModel("2","Chollasak", "chollsak@gmial.com", 15),
                    new UserModel("3","Cristiano", "cr7@gmial.com", 21)
            )
    );

    public List<UserModel> getUsers() {
        return users;
    }

    public UserModel addUser(UserModel user){
        users.add(user);
        return user;
    }

    public UserModel removeUser(UserModel user){
        users.remove(user);
        return user;
    }

    public String updateUser(UserModel user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(user.getId())){
                users.set(i, user);
                return "user "+ user.getId()+ " is updated";
            }
        }
        return "no user with this id";
    }
}
