package bike.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bike.park.entity.Rider;

public interface RiderDao extends JpaRepository<Rider, Long> {

}
