package feudal_web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.dto.PeonWithoutIdDto;
import feudal_web.dto.mapper.PeonMapper;
import feudal_web.service.KingdomService;

@RequestMapping("kingdom/peon")
@RestController
public class PeonController {

	private KingdomService kingdomService;
	private PeonMapper peonMapper;

	public PeonController(KingdomService kingdomService, PeonMapper peonMapper) {
		super();
		this.kingdomService = kingdomService;
		this.peonMapper = peonMapper;
	}

	@PostMapping
	public int add(@RequestBody PeonWithoutIdDto peonWithoutIdDto) {
		Peon peon = peonMapper.peonWithoutIdDtoToPeon(peonWithoutIdDto);
		peon.setParent(kingdomService.get(peonWithoutIdDto.getParentId(), Lord.class));
		return kingdomService.add(peon);
	}

	@GetMapping("{id}")
	public PeonWithoutIdDto getById(@PathVariable int id, HttpServletResponse httpResponse) {
		PeonWithoutIdDto dto = peonMapper.peonToPeonWithoutIdDto(kingdomService.get(id, Peon.class));
		if (dto == null)
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return dto;
	}
}
