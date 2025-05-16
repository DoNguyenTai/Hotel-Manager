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
import fit.tdc.vn.QLKS.Entities.Response.HotelResponse;
import fit.tdc.vn.QLKS.Entities.Response.LocationResponse;
import fit.tdc.vn.QLKS.Repository.LocationRepository;



@RestController
@RequestMapping("api/location")
public class LocationCotrollerAPI {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping
	public ResponseEntity<?> getAllLocationsWithHotels() {
        return ResponseEntity.ok(locationRepository.findAll());
    }
	
	@GetMapping("/hotel/{locationId}")
	public ResponseEntity<?> getLocationWithHotels(@PathVariable Long locationId) {
		System.out.println(locationId);
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setLocationId(location.getLocationId());
        locationResponse.setName(location.getName());
        locationResponse.setDescription(location.getDescription());
        locationResponse.setImage(location.getImage());

        List<HotelResponse> hotelResponses = location.getHotels().stream().map(h -> {
        	HotelResponse hotelResponse = new HotelResponse();
        	hotelResponse.setHotelID(h.getHotelId());
        	hotelResponse.setName(h.getName());
        	hotelResponse.setAddress(h.getAddress());
            hotelResponse.setPhone(h.getPhone());
            hotelResponse.setEmail(h.getEmail());
            hotelResponse.setStatus(h.getStatus());
            hotelResponse.setImage(h.getImage());
            return hotelResponse;
        }).toList();

        locationResponse.setHotels(hotelResponses);
        return ResponseEntity.ok(locationResponse);
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
