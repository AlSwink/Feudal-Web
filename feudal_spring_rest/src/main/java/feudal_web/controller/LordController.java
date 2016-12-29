package feudal_web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.ftd.assignments.collections.Kingdom;
import com.cooksys.ftd.assignments.collections.model.Lord;

import feudal_web.service.KingdomService;

@RequestMapping("kingdom/lord")
@RestController
public class LordController {
	
	private KingdomService kingdomService;

	public LordController(KingdomService kingdom) {
		super();
		this.kingdomService = kingdom;
	}
	
	@PostMapping
	public int add(@RequestBody Lord lord) {
		return kingdomService.add(lord);
	}
	
}
