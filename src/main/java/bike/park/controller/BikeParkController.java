package bike.park.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import bike.park.controller.model.BikeParkData;
import bike.park.service.BikeParkService;
import lombok.extern.slf4j.Slf4j;

@ReadingConverter
@RequestMapping("/bike_park")
@Slf4j
public class BikeParkController {

	@Autowired
	public BikeParkService bikeParkService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BikeParkData createParkData(@RequestBody BikeParkData bikeParkData)
		throws Exception {
		log.info("Creating bike_park {}, bikeParkData");
		
		return bikeParkService.saveBikePark(bikeParkData);
	}
	
	@PutMapping("/bikeParkID {}")
	public BikeParkData updateBikePark(
			@PathVariable Long bikeParkId, @RequestBody BikeParkData bikeParkData)
			throws Exception {
		log.info("Recived request to update BikePark with ID: {}", bikeParkId);
		
		bikeParkData.setBikeParkId(bikeParkId); 
		
		BikeParkData updateBiekPark = bikeParkService.saveBikePark(bikeParkData);
		
		return updateBiekPark;
	}
	
	public List<BikeParkData> retriveBikeParkById(@PathVariable Long bikeParkId) {
		
		log.info("Retriving bike park wiht ID={}", bikeParkId);
		
		return bikeParkService.retriveBikeParkById();  
	}
	
	@DeleteMapping("/bikeParkId {}")
	public Map<String, String> deleteBikeParkById(@PathVariable Long bikeParkId) {
		log.info("Deleting bike park with ID={}" + bikeParkId);
		
		bikeParkService.deleteBikeParkById(bikeParkId);
		
		return Map.of("Message", " Deletion of bike Park with ID= "
				+ bikeParkId + " was seccessful.");  
	}
	
	
}
