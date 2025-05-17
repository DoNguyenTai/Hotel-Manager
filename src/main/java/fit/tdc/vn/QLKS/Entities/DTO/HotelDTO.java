package fit.tdc.vn.QLKS.Entities.DTO;

import java.util.List;

import fit.tdc.vn.QLKS.Entities.Hotel;
import fit.tdc.vn.QLKS.Entities.Location;
import fit.tdc.vn.QLKS.Entities.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelDTO {
		private Long id;
	    private String name;
	    private Long locationId;
	    private String address;
	    private String image;
	    private String phone;
	    private String email;
	    private String status;
	    private List<Room> rooms;
	    private Location location;
	    
	    public HotelDTO (Hotel hotel) {
			this.id = hotel.getHotelId();
			this.address = hotel.getAddress();
//			this.rooms = hotel.getRooms();
			this.location = hotel.getLocationId();
			this.name = hotel.getName();
			this.status = hotel.getStatus();
			this.phone = hotel.getPhone();
			this.email = hotel.getEmail();
			this.image = hotel.getImage();
			this.locationId = hotel.getLocationId().getLocationId();
		}
}
