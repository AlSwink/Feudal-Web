package feudal_web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.ftd.assignments.collections.Kingdom;
import com.cooksys.ftd.assignments.collections.model.Lord;

@RestController
@RequestMapping("kingdom")
public class FeudalController {
	
	private Kingdom kingdom;

	public FeudalController(Kingdom kingdom) {
		this.kingdom = kingdom;
	}
	
	@GetMapping
	public String test() {
		return "Hello World!";
	}
	
	@PostMapping("/lord")
	public boolean postLord(@RequestBody Lord lord) {
		return kingdom.add(lord);
	}
	
}
