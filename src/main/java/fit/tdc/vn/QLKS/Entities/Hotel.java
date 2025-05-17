package fit.tdc.vn.QLKS.Entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fit.tdc.vn.QLKS.Entities.DTO.RoomDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "hotels")
@ToString(exclude = "rooms")
@Getter
@Setter
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_id")
	private Long hotelId;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location locationId;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "image")
	private String image;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Room> rooms = new ArrayList<>();

}