package feudal_web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import feudal_web.dto.lord.LordWithoutIdDto;
import feudal_web.dto.peon.PeonWithoutIdDto;
import feudal_web.dto.validation.group.EnsureParentNotNull;
import feudal_web.service.PeonService;

@Validated
@RequestMapping("kingdom/peon")
@RestController
public class PeonController {

	private final PeonService peonService;

	public PeonController(PeonService peonService) {
		super();
		this.peonService = peonService;
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable int id, HttpServletResponse response) {
		if(!peonService.has(id))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public PeonWithoutIdDto get(@PathVariable int id, HttpServletResponse response) {
		PeonWithoutIdDto dto = peonService.get(id);
		if (dto == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return dto;
	}
	
	@PostMapping
	public int add(@RequestBody @Validated(EnsureParentNotNull.class) PeonWithoutIdDto peon, HttpServletResponse response) {
		int result = peonService.add(peon);
		if(result > 0)
			response.setStatus(HttpServletResponse.SC_CREATED);
		return result;

	}
	
	@PutMapping("{id}")
	public void put(@PathVariable int id, @RequestBody @Validated PeonWithoutIdDto peon, HttpServletResponse response) {
		peonService.put(id, peon);
	}
	
	@PatchMapping("{id}")
	public void patch(@PathVariable int id, @RequestBody @Validated(EnsureParentNotNull.class) PeonWithoutIdDto peon, HttpServletResponse response) {
		peonService.patch(id, peon);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		peonService.delete(id);
	}

}
