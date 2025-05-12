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
import fit.tdc.vn.QLKS.Entities.DTO.HotelDTO;
import fit.tdc.vn.QLKS.Repository.HotelRepository;
import fit.tdc.vn.QLKS.Repository.LocationRepository;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/hotels")
public class HotelControllerAPI {

    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDTO hotelDTO) {
    	Location location = locationRepository.findById(hotelDTO.getLocationId()).orElse(null);
    	if(location != null) {
    		Hotel hotel = new Hotel();
    		hotel.setAddress(hotelDTO.getAddress());
    		hotel.setEmail(hotelDTO.getEmail());
    		hotel.setName(hotelDTO.getName());
    		hotel.setPhone(hotelDTO.getPhone());
    		hotel.setStatus(hotelDTO.getStatus());
    		hotel.setLocationId(location);
    		hotelRepository.save(hotel);
            return ResponseEntity.ok(hotel);

    	}
    	return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
    	Location location = locationRepository.findById(hotelDTO.getLocationId()).orElse(null);
    	
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setName(hotelDTO.getName());
                    hotel.setLocationId(location);
                    hotel.setAddress(hotelDTO.getAddress());
                    hotel.setPhone(hotelDTO.getPhone());
                    hotel.setPhone(hotelDTO.getImage());
                    hotel.setEmail(hotelDTO.getEmail());
                    return ResponseEntity.ok(hotelRepository.save(hotel));
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
}
