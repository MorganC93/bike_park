package bike.park.controller.model;

import bike.park.entity.Trail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BikeParkTrail {

	private Long trailId;
	private String trailRating;
	private String trailCondition;
	
	public BikeParkTrail(Trail trail) {
		trailId = trail.getTrailId();
		trailRating = trail.getTrailRating();
		trailCondition = trail.getTrailCondition();
	}
}
