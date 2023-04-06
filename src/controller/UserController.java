package controller;

import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessage;
import modal.Role;
import modal.RoleName;
import modal.User;
import service.role.IRoleService;
import service.role.RoleServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    private IUserService userService = new UserServiceIMPL();
    private IRoleService roleService = new RoleServiceIMPL();
    public ResponseMessage register(SignUpDTO sign){
        if(userService.existedByUsername(sign.getUsername())){
            return new ResponseMessage("user_existed");
        }
        if(userService.existedByEmail(sign.getEmail())){
            return new ResponseMessage("email_existed");
        }
        Set<String> strRole = sign.getStrRole();
        Set<Role> roleSet = new HashSet<>();
        strRole.forEach(role->{
            switch (role){
                case "admin":
                    roleSet.add(roleService.findByName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByName(RoleName.PM));
                    break;
                default:
                    roleSet.add(roleService.findByName(RoleName.USER));
            }
        });
        User user = new User(sign.getId(), sign.getName(),sign.getUsername(),sign.getEmail(),sign.getPassword(),roleSet);
        userService.save(user);
        return new ResponseMessage("create_success");
    }
    public List<User> getListUser(){
        return userService.findAll();
    }
    public ResponseMessage login(SignInDTO signInDTO){
        if(userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())){
            return new ResponseMessage("login_success");
        } else {
            return new ResponseMessage("login_failed");
        }
    }
    public User getUserLogin(){
        return userService.getCurentUser();
    }
    public void createUser(User user){
        userService.save(user);
    }
}
