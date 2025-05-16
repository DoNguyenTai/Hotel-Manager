package fit.tdc.vn.QLKS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.tdc.vn.QLKS.Entities.Hotel;
import fit.tdc.vn.QLKS.Entities.Room;
import fit.tdc.vn.QLKS.Enum.StatusRoom;

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByStatus(StatusRoom statusRoom);

	List<Room> findByHotel_HotelId(Long hotelID);


}