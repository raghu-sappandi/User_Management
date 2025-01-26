package in.raghu.service;

import java.util.List;

import in.raghu.entity.Search_Query;
import in.raghu.entity.User;

public interface UserService {
	public Integer saveUser(User user);

	public List<User> getAllUsers();

	public boolean unameExist(String uname);

	public boolean emailExist(String email);

	public boolean login(String Uname, String Password);

	public List<User> getUserBySearch(Search_Query search);
}
