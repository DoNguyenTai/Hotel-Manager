package fit.tdc.vn.QLKS.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fit.tdc.vn.QLKS.Entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}