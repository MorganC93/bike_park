package bike.park.controller.model;

import bike.park.entity.Rider;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RiderData {

	private Long riderId;
	private String riderName;
	private String riderEmail;
	
	public RiderData(Rider rider) {
		riderId = rider.getRiderId();
		riderName = rider.getRiderName();
		riderEmail = rider.getRiderEmail();
	}
	
}
