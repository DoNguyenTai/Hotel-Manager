package fit.tdc.vn.QLKS.Entities.Response;

import org.springframework.http.HttpStatus;

import fit.tdc.vn.QLKS.Entities.Hotel;

public class HotelResponse {
	private Hotel hotel;
	private HttpStatus status;
	
	
	public HotelResponse(Hotel hotel, HttpStatus created) {
		super();
		this.hotel = hotel;
		this.status = created;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	
}
