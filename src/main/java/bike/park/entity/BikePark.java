package bike.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class BikePark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bikeParkId;
	
	private String parkName;
	private String directions;
	private String country;
	private String stateOrProvince;
	
	@Embedded
	private GeoLocation geoLocation;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "bikePark", cascade = CascadeType.ALL)
	private Set<Trail> trails = new HashSet<>();
	
	
}
