package bike.park.service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bike.park.controller.model.BikeParkData;
import bike.park.dao.BikeParkDao;
import bike.park.dao.RiderDao;
import bike.park.dao.TrailDao;
import bike.park.entity.BikePark;

@Service
public class BikeParkService {
	
	@Autowired
	private BikeParkDao bikeParkDao;
	
	@Autowired
	private RiderDao riderDao;
	
	@Autowired
	private TrailDao trailDao;
	
	
	public BikeParkData saveBikePark(Long riderId, BikeParkData bikeParkData) throws Exception{
		
		
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
	
	
}
