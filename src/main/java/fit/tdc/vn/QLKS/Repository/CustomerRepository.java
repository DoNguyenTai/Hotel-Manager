package fit.tdc.vn.QLKS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fit.tdc.vn.QLKS.Entities.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByUsername(String username);

	
}
