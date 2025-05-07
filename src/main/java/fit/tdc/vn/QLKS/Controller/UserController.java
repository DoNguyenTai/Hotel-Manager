package fit.tdc.vn.QLKS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fit.tdc.vn.QLKS.Entities.User;
import fit.tdc.vn.QLKS.Entities.Response.UserReponse;
import fit.tdc.vn.QLKS.Repository.UserRepository;
import fit.tdc.vn.QLKS.Util.JwtUtil;

@RestController
@RequestMapping("api")
public class UserController {
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/user")
	public List<User> all() {
		return repo.findAll();
	}
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return repo.save(user);
	}
	
	@PostMapping("/login")
    public UserReponse login(@RequestBody User user) {
		User userDB = repo.findByUsername(user.getUsername());
        if (userDB.getUsername() != null && user.getPassword().equals(userDB.getPassword())) {

        	String token = JwtUtil.generateToken(userDB.getUsername(), userDB.getId());
        	Long userId = JwtUtil.getUserId(token);
        	System.out.println("Userid : " + userId);
        	UserReponse userReponse = new UserReponse(token, userId, "200");

            return userReponse;
        }
        return null;
    }
	
	
	
}
