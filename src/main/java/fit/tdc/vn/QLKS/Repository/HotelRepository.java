package fit.tdc.vn.QLKS.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.tdc.vn.QLKS.Entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	List<Hotel> findByLocationId_LocationId(Long locationId);

}