package fit.tdc.vn.QLKS.Entities.DTO;

import java.math.BigDecimal;

import fit.tdc.vn.QLKS.Enum.StatusRoom;


public class RoomDTO {

	    private String roomNumber;
	    private Long roomTypeId;
	    private StatusRoom status;
	    private BigDecimal price;
	    private String image;
	    private int capacity;
	    private String description;
	    private Long hotelId;
		public String getRoomNumber() {
			return roomNumber;
		}
		public void setRoomNumber(String roomNumber) {
			this.roomNumber = roomNumber;
		}
		public Long getRoomTypeId() {
			return roomTypeId;
		}
		public void setRoomTypeId(Long roomTypeId) {
			this.roomTypeId = roomTypeId;
		}
		public StatusRoom getStatus() {
			return status;
		}
		public void setStatus(StatusRoom status) {
			this.status = status;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public int getCapacity() {
			return capacity;
		}
		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Long getHotelId() {
			return hotelId;
		}
		public void setHotelId(Long hotelId) {
			this.hotelId = hotelId;
		}
		
	    
	    
}
