package fit.tdc.vn.QLKS.Repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.tdc.vn.QLKS.Entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	   @Query("SELECT b FROM Booking b WHERE b.room.roomId = :roomId AND " +
	           "(b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate)")
	    List<Booking> findOverlappingBookings(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);
}
