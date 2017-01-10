package feudal_web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import feudal_web.dto.peon.PeonWithoutIdDto;
import feudal_web.service.KingdomService;
import feudal_web.service.PeonService;

@Validated
@RequestMapping("kingdom/peon")
@RestController
public class PeonController {

	private final PeonService peonService;

	public PeonController(KingdomService kingdomService, PeonService peonService) {
		super();
		this.peonService = peonService;
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable int id, HttpServletResponse response) {
		if(!peonService.has(id))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public PeonWithoutIdDto getById(@PathVariable int id, HttpServletResponse httpResponse) {
		PeonWithoutIdDto dto = peonService.get(id);
		if (dto == null)
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return dto;
	}
	
	@PostMapping
	public int add(@RequestBody @Valid PeonWithoutIdDto peonWithoutIdDto) {
		return peonService.add(peonWithoutIdDto);
	}

}
