package in.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.raghu.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
		boolean existsByUserName(String uname);
		boolean existsByEmail(String email);
		User findByUserName(String uName);
		User findByPassword(String password);
		
		@Query("SELECT u FROM User u WHERE"
				 + "( :uId IS NULL OR u.userId = :uId ) AND " +
                  "( :fName IS NULL OR u.firstName LIKE %:fName% ) AND " +
                  "( :lName IS NULL OR u.lastName LIKE %:lName% ) AND " +
                  "( :email IS NULL OR u.email LIKE %:email% ) AND " +
                  "( :uName IS NULL OR u.userName LIKE %:uName% ) ")
		List<User> findUsers(Integer uId, String fName, String lName, String email, String uName);
}
