package bike.park.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bike.park.controller.model.BikeParkData;
import bike.park.controller.model.RiderData;
import bike.park.controller.model.TrailData;
import bike.park.dao.BikeParkDao;
import bike.park.dao.RiderDao;
import bike.park.dao.TrailDao;
import bike.park.entity.BikePark;
import bike.park.entity.Rider;
import bike.park.entity.Trail;

@Service
public class BikeParkService {
	
	@Autowired
	private BikeParkDao bikeParkDao;
	
	@Autowired
	private RiderDao riderDao;
	
	@Autowired
	private TrailDao trailDao;
	
	
	public BikeParkData saveBikePark(BikeParkData bikeParkData) throws Exception{
		
		
		
		  if(bikeParkData == null) { throw new
		  Exception("BikeParkData cannot be null."); }
		  
		  Long bikeParkId = bikeParkData.getBikeParkId();
		  
		  BikePark bikePark;
		  
		  if (bikeParkId == null) { bikePark = findOrCreateBikePark(bikeParkId); } else
		  { bikePark = findBikeParkById(bikeParkId); }
		  
		  bikePark.setParkName(bikeParkData.getParkName());
		  
		  bikePark = bikeParkDao.save(bikePark);
		  
		  return bikeParkData;
		 
		
		
	}
	
	private void setBikeParkFeilds(BikePark bikePark, BikeParkData bikeParkData) {
		bikePark.setBikeParkId(bikeParkData.getBikeParkId());
		bikePark.setParkName(bikeParkData.getParkName());
		bikePark.setCountry(bikeParkData.getCountry());
		bikePark.setStateOrProvince(bikeParkData.getStateOrProvince());
		bikePark.setCountry(bikeParkData.getCountry());
		bikePark.setGeoLocation(bikeParkData.getGeoLocation());
	}

	private BikePark findBikeParkById(Long bikeParkId) {
		Optional<BikePark> bikeOP = bikeParkDao.findById(bikeParkId);
		
		if(bikeOP.isPresent()) {
			return bikeOP.get();
		} else {
			throw new NoSuchElementException("Bike Park with ID=" 
		+ bikeParkId + " does not exist.");
		}
	}

	private BikePark findOrCreateBikePark(Long bikeParkId) {
		BikePark bikePark;
		
		if (Objects.isNull(bikeParkId)) {
			bikePark = new BikePark();
		} else {
			bikePark = findBikeParkById(bikeParkId);
		}
		return new BikePark();
	}
	
	@Transactional(readOnly = true) 
	public List<BikeParkData> retriveBikeParkById() {
		
		List<BikePark> bikeParks = bikeParkDao.findAll();
		List<BikeParkData> result = new LinkedList<>();
		
		for(BikePark bikePark : bikeParks) {
			BikeParkData bpd = new BikeParkData(bikePark);
			
			result.add(bpd);
		}
		
		return result; 
	}
	
	@Transactional(readOnly = false)
	public void deleteBikeParkById(Long bikeParkId) {
		BikePark bikePark = findBikeParkById(bikeParkId);
		bikeParkDao.delete(bikePark);
	}
	
	@Transactional(readOnly = false)
	public RiderData saveRider(Long bikeParkId, RiderData riderData) {
		
		if (riderData == null) {
			throw new NoSuchElementException("RiderData cannot be null.");
		}
		
		Long riderId = riderData.getRiderId();
		
		Rider rider;
		
		if (riderData == null) {
			rider = findOrCreateRider(riderId, riderId);
		} else {
			rider = findRiderById(riderId, riderId);
		}
		
		setRiderFeilds(rider,riderData);
		
		return new RiderData(riderDao.save(rider));
	}

	private Rider findOrCreateRider(Long riderId, Long bikeParkId) {
		
		if (riderId == null) {
			return new Rider();
			
		}
		return findRiderById(bikeParkId, riderId);
	}

	private Rider findRiderById(Long riderId, Long bikeParkId) {
		
		Optional<Rider> or = riderDao.findById(riderId);
		
		if (or.isEmpty()) {
			throw new NoSuchElementException("Rider with ID " + riderId + " was not found.");
		}
		
		Rider rider = or.get();
		
		if (!rider.getTrails().equals(rider)) {
			throw new NoSuchElementException("Rider with ID " + riderId 
					+ " does not belong to bike park with ID " + bikeParkId);
		}
		
		return rider;
	}

	private Rider setRiderFeilds(Rider rider, RiderData riderData) {
		rider.setRiderId(riderData.getRiderId());
		rider.setRiderName(riderData.getRiderName());
		rider.setRiderEmail(riderData.getRiderEmail());
		
		return rider;
	}

	@Transactional(readOnly = false)
	public void DeleteRiderById(Long riderId) {
		Rider rider = findRiderById(riderId, riderId);
		riderDao.delete(rider);
	}
	
	@Transactional(readOnly = false)
	public TrailData saveTrail(Long bikeParkId, TrailData trailData) {
		if (trailData == null) {
			throw new NoSuchElementException("TrailData cannot be null.");
		}
		
		Long trailId = trailData.getTrailId();
		
		Trail trail;
		
		if (trailData == null) {
			trail = findOrCreateTrial(trailId,trailId);
		} else {
			trail = findTrailByID(trailId, trailId);
		}
		
		setTrailFeilds(trail, trailData); 
		
		trail = trailDao.save(trail);
		
		return new TrailData(trailDao.save(trail));
	}

	private Trail findOrCreateTrial(Long trailId, Long bikeParkId) {

		if ( trailId == null) {
			return new Trail();
		}
		
		return findTrailByID(trailId, trailId);  
	}

	private Trail findTrailByID(Long trailId, Long bikeParkId) { 
		Optional<Trail> ot = trailDao.findById(trailId);
		
		if ( ot.isEmpty()) {
			throw new NoSuchElementException("Trail with Id " + trailId + " was not found.");
		}
		
		Trail trail = ot.get();
		
		if (!trail.getBikePark().getBikeParkId().equals(trail)) {
			throw new IllegalArgumentException(
					"Trail with ID " + trailId + " does not belong to the bike park with ID " + bikeParkId);
		} 
		
		return trail;
	}

	private void setTrailFeilds(Trail trail, TrailData trailData) {
		trail.setTrailId(trailData.getTrailId());
		trail.setTrailName(trail.getTrailName());
		trail.setTrailRating(trailData.getTrailRating());
		trail.setTrailCondition(trail.getTrailCondition());
		
	}
	
	@Transactional(readOnly = false)
	private void deleteTrailById(Long trailId) {
		Trail trail = findTrailByID(trailId, trailId); 
		trailDao.delete(trail);
	}
	
}
