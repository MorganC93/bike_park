package bike.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Rider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long riderId;
	
	private String riderName;
	
	@Column(unique = true)
	private String riderEmail;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "riders", cascade = CascadeType.PERSIST)
	private Set<Trail> trails = new HashSet<>();
	
	
}
