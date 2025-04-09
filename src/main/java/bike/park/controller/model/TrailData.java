package bike.park.controller.model;

import java.util.HashSet;
import java.util.Set;

import bike.park.entity.Rider;
import bike.park.entity.Trail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrailData {

	private Long trailId;
	private String trailName;
	private String trailRating;
	private String trailCondition;
	private Set<RiderData> riders = new HashSet<>(); 
	
	public TrailData(Trail trail) {
		trailId = trail.getTrailId();
		trailRating = trail.getTrailRating();
		trailCondition = trail.getTrailCondition();
	
	for(Rider rider : trail.getRiders()) {
		riders.add(new RiderData(rider)); 
	}
}
//	public void BikeParkData(Trail trail) {
//		trailId = trail.getTrailId();
//		trailName = trail.getTrailName();
//		trailRating = trail.getTrailRating();
//		trailCondition = trail.getTrailCondition();
//		
//		for(BikePark bikePark : trail.getBikeParks()) {
//			bikePark.add(new BikeParkResponse(bikePark));
//		}
//	}
//	
//	static class BikeParkResponse{
//		
//		private Long bikeParkId;
//		private String parkName;
//		private String directions;
//		private String country;
//		private String stateOrProvince;
//		private GeoLocation geoLocation;
//		
//		BikeParkResponse(BikePark bikePark) {
//			bikeParkId = bikePark.getBikeParkId();
//			parkName = bikePark.getParkName();
//			directions = bikePark.getDirections();
//			country = bikePark.getCountry();
//			stateOrProvince = bikePark.getStateOrProvince();
//			geoLocation = bikePark.getGeoLocation();
//			
//			for(Rider rider : bikePark.getRiders()) {
//				rider.add(rider.getRiderId());
//			}
//		}
//	}
}
