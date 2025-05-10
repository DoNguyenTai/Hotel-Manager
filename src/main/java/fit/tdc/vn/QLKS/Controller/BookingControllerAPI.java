package fit.tdc.vn.QLKS.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fit.tdc.vn.QLKS.Entities.Booking;
import fit.tdc.vn.QLKS.Entities.Customer;
import fit.tdc.vn.QLKS.Entities.Location;
import fit.tdc.vn.QLKS.Entities.Room;
import fit.tdc.vn.QLKS.Entities.DTO.BookingDTO;
import fit.tdc.vn.QLKS.Enum.StatusBooking;
import fit.tdc.vn.QLKS.Repository.BookingRepository;
import fit.tdc.vn.QLKS.Repository.CustomerRepository;
import fit.tdc.vn.QLKS.Repository.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingControllerAPI {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
    	if(bookingDTO.getCheckInDate().isBefore(bookingDTO.getCheckOutDate()) == false) {
            return ResponseEntity.badRequest().body(null);
    	}
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
        		bookingDTO.getRoomId(),
        		bookingDTO.getCheckInDate(),
        		bookingDTO.getCheckOutDate()
        );
        if (!overlappingBookings.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
    	Room room = roomRepository.findById(bookingDTO.getRoomId()).orElse(null);
    	Customer customer = customerRepository.findById(bookingDTO.getCustomerId()).orElse(null);
        Booking booking = new Booking(customer, room, bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate(), StatusBooking.DA_DAT);
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

//    @PostMapping
//    public Booking createBooking(@RequestBody Booking booking) {
//        // TODO: Thêm logic kiểm tra phòng trống
//        return bookingRepository.save(booking);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Integer id, @RequestBody Booking bookingDetails) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setCustomer(bookingDetails.getCustomer());
                    booking.setRoom(bookingDetails.getRoom());
                    booking.setCheckInDate(bookingDetails.getCheckInDate());
                    booking.setCheckOutDate(bookingDetails.getCheckOutDate());
                    booking.setStatus(bookingDetails.getStatus());
                    return ResponseEntity.ok(bookingRepository.save(booking));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    bookingRepository.delete(booking);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}