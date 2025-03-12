package bike.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bike.park.entity.Trail;

public interface TrailDao extends JpaRepository<Trail, Long> {

}
