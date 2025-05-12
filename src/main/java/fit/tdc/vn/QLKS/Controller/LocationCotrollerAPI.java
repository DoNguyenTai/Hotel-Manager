package fit.tdc.vn.QLKS.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fit.tdc.vn.QLKS.Entities.Customer;
import fit.tdc.vn.QLKS.Entities.Location;
import fit.tdc.vn.QLKS.Entities.DTO.HotelDTO;
import fit.tdc.vn.QLKS.Entities.DTO.LocationDTO;
import fit.tdc.vn.QLKS.Repository.LocationRepository;



@RestController
@RequestMapping("api/location")
public class LocationCotrollerAPI {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping
	public List<LocationDTO> getAllLocationsWithHotels() {
        List<Location> locations = locationRepository.findAll();

        return locations.stream().map(location -> {
            LocationDTO dto = new LocationDTO();
            dto.setLocationId(location.getLocationId());
            dto.setName(location.getName());
            dto.setDescription(location.getDescription());
            dto.setImage(location.getImage());

            List<HotelDTO> hotelDTOs = location.getHotels().stream().map(hotel -> {
            	HotelDTO h = new HotelDTO();
                h.setHotelId(hotel.getHotelId());
                h.setName(hotel.getName());
                h.setAddress(hotel.getAddress());
                h.setPhone(hotel.getPhone());
                h.setImage(hotel.getImage());
                h.setEmail(hotel.getEmail());
                h.setStatus(hotel.getStatus());
                return h;
            }).collect(Collectors.toList());

            dto.setHotels(hotelDTOs);
            return dto;
        }).collect(Collectors.toList());
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Location> getLocationByID(@PathVariable Long id){
		return locationRepository.findById(id).map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
		
		
	}
	
	@PostMapping
	public Location store(@RequestBody Location location) {
		return locationRepository.save(location);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        return locationRepository.findById(id)
                .map(location -> {
                	locationRepository.delete(location);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

	
	
	
	
	
}
