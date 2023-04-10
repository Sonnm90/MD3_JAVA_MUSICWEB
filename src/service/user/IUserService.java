package service.user;

import modal.User;
import service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    boolean checkLogin(String username, String password);
    User getCurentUser();
    void logOutUser();
}
