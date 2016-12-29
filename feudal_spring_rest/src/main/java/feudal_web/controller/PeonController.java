package feudal_web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.ftd.assignments.collections.Kingdom;
import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.service.KingdomService;

@RequestMapping("kingdom/peon")
@RestController
public class PeonController {

	private KingdomService kingdomService;

	public PeonController(KingdomService kingdomService) {
		super();
		this.kingdomService = kingdomService;
	}
	
	@PostMapping
	public int add(@RequestBody Peon peon) {
		return kingdomService.add(peon);
	}
}
