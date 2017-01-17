package feudal_web.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import feudal_web.dto.peon.PeonWithoutIdDto;
import feudal_web.dto.validation.group.EnsureParentNotNull;
import feudal_web.service.LordService;
import feudal_web.service.PeonService;

@Validated
@RequestMapping("kingdom/peon")
@RestController
public class PeonController {

	private final PeonService peonService;
	private final LordService lordService;

	public PeonController(PeonService peonService, LordService lordService) {
		super();
		this.peonService = peonService;
		this.lordService = lordService;
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable Integer id, HttpServletResponse response) {
		if(!peonService.has(id))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping
	public List<PeonWithoutIdDto> getByLord(@RequestParam("lord") Integer lordId, HttpServletResponse response) {
		lordService.has(lordId);
		return peonService.getByLord(lordId);
	}
	
	@GetMapping("{id}")
	public PeonWithoutIdDto get(@PathVariable Integer id, HttpServletResponse response) {
		PeonWithoutIdDto dto = peonService.get(id);
		if (dto == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return dto;
	}
	
	@PostMapping
	public Integer add(@RequestBody @Validated(EnsureParentNotNull.class) PeonWithoutIdDto peon, HttpServletResponse response) {
		Integer result = peonService.add(peon);
		if(result > 0)
			response.setStatus(HttpServletResponse.SC_CREATED);
		return result;

	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Integer id, @RequestBody @Validated PeonWithoutIdDto peon, HttpServletResponse response) {
		peonService.put(id, peon);
	}
	
	@PatchMapping("{id}")
	public void patch(@PathVariable Integer id, @RequestBody @Validated(EnsureParentNotNull.class) PeonWithoutIdDto peon, HttpServletResponse response) {
		peonService.patch(id, peon);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		peonService.delete(id);
	}

}
