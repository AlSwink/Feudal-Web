package feudal_web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.dto.PeonWithoutIdDto;
import feudal_web.service.KingdomService;
import feudal_web.service.PeonService;

@RequestMapping("kingdom/peon")
@RestController
public class PeonController {

	private final PeonService peonService;

	public PeonController(KingdomService kingdomService, PeonService peonService) {
		super();
		this.peonService = peonService;
	}

	@PostMapping
	public int add(@RequestBody PeonWithoutIdDto peonWithoutIdDto) {
		return peonService.add(peonWithoutIdDto);
	}

	@GetMapping("{id}")
	public PeonWithoutIdDto getById(@PathVariable int id, HttpServletResponse httpResponse) {
		PeonWithoutIdDto dto = peonService.get(id);
		if (dto == null)
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return dto;
	}
}
