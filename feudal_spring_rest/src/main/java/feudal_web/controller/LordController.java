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

import feudal_web.dto.lord.LordWithoutIdDto;
import feudal_web.service.LordService;

@Validated
@RequestMapping("kingdom/lord")
@RestController
public class LordController {
	
	private LordService lordService;

	public LordController(LordService lordService) {
		super();
		this.lordService = lordService;
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable int id, HttpServletResponse response) {
		if(!lordService.has(id))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public LordWithoutIdDto get(@PathVariable int id, HttpServletResponse response) {
		LordWithoutIdDto dto = lordService.get(id);
		if (dto == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return dto;
	}
	
	@PostMapping
	public int add(@RequestBody @Valid LordWithoutIdDto lord, HttpServletResponse response) {
		int result = lordService.add(lord);
		if(result > 0)
			response.setStatus(HttpServletResponse.SC_CREATED);
		return result;
	}
	
}
