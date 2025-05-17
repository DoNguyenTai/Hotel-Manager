package fit.tdc.vn.QLKS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fit.tdc.vn.QLKS.Entities.Customer;
import fit.tdc.vn.QLKS.Entities.Hotel;
import fit.tdc.vn.QLKS.Entities.Room;
import fit.tdc.vn.QLKS.Entities.RoomType;
import fit.tdc.vn.QLKS.Entities.DTO.RoomDTO;
import fit.tdc.vn.QLKS.Entities.DTO.UserDTO;
import fit.tdc.vn.QLKS.Entities.Response.UserReponse;
import fit.tdc.vn.QLKS.Repository.CustomerRepository;
import fit.tdc.vn.QLKS.Util.JwtUtil;

@RestController
@RequestMapping("api")
public class UserController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/user")
	public List<Customer> all() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
    public ResponseEntity<Customer> find(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@GetMapping("/user/by-username/{username}")
    public ResponseEntity<Customer> find(@PathVariable String username) {
        return ResponseEntity.ok(customerRepository.findByUsername(username));
    }
	
	 @PutMapping("/user/{id}")
	    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody UserDTO userDTO) {
	        return customerRepository.findById(id)
	                .map(user -> {
	                	user.setCccd(userDTO.getCccd());
	                	user.setPhone(userDTO.getPhone());
	                	user.setFullname(userDTO.getFullName());
//	                	user.setPassword(userDTO.getPassword());
	                	
	            		
	                    return ResponseEntity.ok(customerRepository.save(user));
	                })
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	
	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody Customer customer) {
		return ResponseEntity.ok(customerRepository.save(customer)) ;
	}
	
	@PostMapping("/login")
    public ResponseEntity<UserReponse> login(@RequestBody Customer customer) {
		Customer customerDB = customerRepository.findByUsername(customer.getUsername());
        if (customerDB.getUsername() != null && customer.getPassword().equals(customerDB.getPassword())) {

        	String token = JwtUtil.generateToken(customerDB.getUsername(), customerDB.getId());
        	Long userId = JwtUtil.getUserId(token);
        	System.out.println("Userid : " + userId);
        	UserReponse userReponse = new UserReponse(token, userId, "200");

            return ResponseEntity.ok(userReponse);
        }
        return ResponseEntity.notFound().build();
    }
	
	
	
}
