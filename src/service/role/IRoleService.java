package service.role;

import modal.Role;
import modal.RoleName;
import service.IGenericService;

import java.util.List;

public interface IRoleService  {
    List<Role> findAll();
    Role findByName(RoleName name);
}
