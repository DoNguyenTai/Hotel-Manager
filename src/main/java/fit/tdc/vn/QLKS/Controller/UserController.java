package fit.tdc.vn.QLKS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fit.tdc.vn.QLKS.Entities.Customer;
import fit.tdc.vn.QLKS.Entities.Response.UserReponse;
import fit.tdc.vn.QLKS.Repository.CustomerRepository;
import fit.tdc.vn.QLKS.Util.JwtUtil;

@RestController
@RequestMapping("api")
public class UserController {
	@Autowired
	private CustomerRepository repo;
	
	@GetMapping("/user")
	public List<Customer> all() {
		return repo.findAll();
	}
	
	@PostMapping("/register")
	public Customer register(@RequestBody Customer customer) {
		return repo.save(customer);
	}
	
	@PostMapping("/login")
    public UserReponse login(@RequestBody Customer customer) {
		Customer customerDB = repo.findByUsername(customer.getUsername());
        if (customerDB.getUsername() != null && customer.getPassword().equals(customerDB.getPassword())) {

        	String token = JwtUtil.generateToken(customerDB.getUsername(), customerDB.getId());
        	Long userId = JwtUtil.getUserId(token);
        	System.out.println("Userid : " + userId);
        	UserReponse userReponse = new UserReponse(token, userId, "200");

            return userReponse;
        }
        return null;
    }
	
	
	
}
