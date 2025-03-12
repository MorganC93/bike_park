package bike.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "bike_park_rider",
			joinColumns = @JoinColumn(name = "bike_park_id"),
			inverseJoinColumns = @JoinColumn(name = "rider_id"))
	private Set<Rider> riders = new HashSet<>(); 
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "bike_park_trail",
			joinColumns = @JoinColumn(name = "bike_park_id"),
			inverseJoinColumns = @JoinColumn(name = "trail_id"))
	private Set<Trail> trails = new HashSet<>();
	
	
}
