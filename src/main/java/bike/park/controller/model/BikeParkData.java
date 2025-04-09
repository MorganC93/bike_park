package bike.park.controller.model;

import java.util.HashSet;
import java.util.Set;

import bike.park.entity.BikePark;
import bike.park.entity.GeoLocation;
import bike.park.entity.Trail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BikeParkData {

	private Long bikeParkId;
	private String parkName;
	private String directions;
	private String country;
	private String stateOrProvince;
	private GeoLocation geoLocation;
	
	
	
	public Set<TrailData> trails = new HashSet<>();
	
	public BikeParkData(BikePark bikePark) {
		bikeParkId = bikePark.getBikeParkId();
		parkName = bikePark.getParkName();
		directions = bikePark.getDirections();
		country = bikePark.getCountry();
		stateOrProvince = bikePark.getStateOrProvince();
		geoLocation = bikePark.getGeoLocation();
		
		for(Trail trail : bikePark.getTrails()) {
			trails.add(new TrailData(trail));   
		}
		
	}
		
	}


