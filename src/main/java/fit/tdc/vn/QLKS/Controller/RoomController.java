package fit.tdc.vn.QLKS.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fit.tdc.vn.QLKS.Entities.Hotel;
import fit.tdc.vn.QLKS.Entities.Location;
import fit.tdc.vn.QLKS.Entities.Room;
import fit.tdc.vn.QLKS.Entities.RoomType;
import fit.tdc.vn.QLKS.Entities.DTO.HotelDTO;
import fit.tdc.vn.QLKS.Entities.DTO.RoomDTO;
import fit.tdc.vn.QLKS.Enum.StatusRoom;
import fit.tdc.vn.QLKS.Repository.HotelRepository;
import fit.tdc.vn.QLKS.Repository.LocationRepository;
import fit.tdc.vn.QLKS.Repository.RoomRepository;
import fit.tdc.vn.QLKS.Repository.RoomTypeRepository;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    
    @Autowired
    private HotelRepository hotelRepository;
    


    @GetMapping
    public List<Room> all() {
        return roomRepository.findAll();
    }
    
 

    @GetMapping("/{id}")
    public ResponseEntity<Room> find(@PathVariable Long id) {
        return roomRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/by-location/{hotelID}")
    public List<Room> getHotelsByLocation(@PathVariable Long hotelID) {
        return roomRepository.findByHotel_HotelId(hotelID);
    }
    
    @PostMapping
    public ResponseEntity<Room> store(@RequestBody RoomDTO roomDTO) {

    	Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElse(null);
    	RoomType roomType = roomTypeRepository.findById(roomDTO.getRoomTypeId()).orElse(null);
    	
    	if(hotel != null && roomType != null) {
    		Room room = new Room();
    		room.setCapacity(roomDTO.getCapacity());
    		room.setDescription(roomDTO.getDescription());
    		room.setHotel(hotel);
    		room.setImage(roomDTO.getImage());
    		room.setPrice(roomDTO.getPrice());
    		room.setRoomNumber(roomDTO.getRoomNumber());
    		room.setRoomTypeId(roomType);
    		room.setStatus(roomDTO.getStatus());
            return ResponseEntity.ok(roomRepository.save(room));
    	}
    	return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateHotel(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
    	Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElse(null);
    	RoomType roomType = roomTypeRepository.findById(roomDTO.getRoomTypeId()).orElse(null);    	
        return roomRepository.findById(id)
                .map(room -> {
                	room.setCapacity(roomDTO.getCapacity());
            		room.setDescription(roomDTO.getDescription());
            		room.setHotel(hotel);
            		room.setImage(roomDTO.getImage());
            		room.setPrice(roomDTO.getPrice());
            		room.setRoomNumber(roomDTO.getRoomNumber());
            		room.setRoomTypeId(roomType);
            		room.setStatus(roomDTO.getStatus());
            		
                    return ResponseEntity.ok(roomRepository.save(room));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotelRepository.delete(hotel);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/available")
    public List<Room> getRoomAvailable() {
    	return roomRepository.findByStatus(StatusRoom.CON_PHONG);
    }

    
}
