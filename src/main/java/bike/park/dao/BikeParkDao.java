package bike.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bike.park.entity.BikePark;

public interface BikeParkDao extends JpaRepository<BikePark, Long> {

}
