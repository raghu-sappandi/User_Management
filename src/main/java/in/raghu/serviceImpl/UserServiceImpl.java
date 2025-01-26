package in.raghu.serviceImpl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.raghu.entity.Search_Query;
import in.raghu.entity.User;
import in.raghu.repo.UserRepo;
import in.raghu.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo repo;
	
	@Override
	public Integer saveUser(User user) {
		
		user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		user.setUserName(Base64.getEncoder().encodeToString(user.getUserName().getBytes()));
		
		return repo.save(user).getUserId();
	}


	
	@Override
	public List<User> getAllUsers() {
		
		List<User> users=repo.findAll();
		for(User iterate:users) {
			byte[] decodedBytes = Base64.getDecoder().decode(iterate.getUserName());
			String decodedUname = new String(decodedBytes);
			iterate.setUserName(decodedUname); 
		}
		return users;
	}
	

	
	@Override
	public boolean unameExist(String uname) {
		return repo.existsByUserName( Base64.getEncoder().encodeToString(uname.getBytes()));
    }


	
	@Override
	public boolean emailExist(String email) {
		// TODO Auto-generated method stub
		return repo.existsByEmail(email);
	}

	
	
	@Override
	public boolean login(String uName,String password) {
		// TODO Auto-generated method stub
		if ((repo.findByUserName(Base64.getEncoder().encodeToString(uName.getBytes())).
				getUserName().equals(Base64.getEncoder().encodeToString(uName.getBytes())))
				&& 
				(repo.findByUserName(Base64.getEncoder().encodeToString(uName.getBytes())).
				getPassword().equals( Base64.getEncoder().encodeToString(password.getBytes())	))) {
			return true;
		}
		
			return false;
	}

	
	
	@Override
	public List<User> getUserBySearch(Search_Query search) {
		// TODO Auto-generated method stub
//		if (!search.getUserName().equals(null)) {
			search.setUserName(Base64.getEncoder().encodeToString(search.getUserName().getBytes()));
		
		
		
		List<User> users= repo.findUsers(search.getUserId(), search.getFirstName(),
                search.getLastName(), search.getEmail(), search.getUserName());
		
		for(User iterate:users) {
			
			byte[] decodedBytes = Base64.getDecoder().decode(iterate.getUserName());
			String decodedUname = new String(decodedBytes);
			iterate.setUserName(decodedUname); 
		}
		
		return users;
		
	}

}
