package fit.tdc.vn.QLKS.Entities.Response;

import java.util.List;

public class LocationResponse {
	 private Long locationId;
	    private String name;
	    private String description;
	    private String image;
	    private List<HotelResponse> hotels;
		public Long getLocationId() {
			return locationId;
		}
		public void setLocationId(Long locationId) {
			this.locationId = locationId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public List<HotelResponse> getHotels() {
			return hotels;
		}
		public void setHotels(List<HotelResponse> hotels) {
			this.hotels = hotels;
		}
	    
	    
}
