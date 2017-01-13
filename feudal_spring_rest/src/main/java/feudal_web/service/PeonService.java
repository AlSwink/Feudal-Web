package feudal_web.service;

import org.springframework.stereotype.Service;

import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.service.KingdomService;
import feudal_web.dto.mapper.PeonMapper;
import feudal_web.dto.peon.PeonWithoutIdDto;

@Service
public class PeonService {

	private final KingdomService kingdomService;
	private final PeonMapper peonMapper;
	
	public PeonService(KingdomService kingdomService, PeonMapper peonMapper) {
		this.kingdomService = kingdomService;
		this.peonMapper = peonMapper;
	}

	public int add(PeonWithoutIdDto peonWithoutIdDto) {
		Peon peon = peonMapper.peonWithoutIdDtoToPeon(peonWithoutIdDto);
		peon.setParent(kingdomService.get(peonWithoutIdDto.getParentId(), Lord.class));
		return kingdomService.add(peon);
	}

	public PeonWithoutIdDto get(int id) {
		return peonMapper.peonToPeonWithoutIdDto(kingdomService.get(id, Peon.class, true));
	}

	public boolean has(int id) {
		return kingdomService.has(id, Peon.class);
	}

	public void put(int id, PeonWithoutIdDto peonDto) {
		Peon peon = peonMapper.peonWithoutIdDtoToPeon(peonDto);
		peon.setId(id);
		kingdomService.put(peon);
	}

	public void patch(int id, PeonWithoutIdDto peonDto) {
		Peon peon = peonMapper.peonWithoutIdDtoToPeon(peonDto);
		peon.setId(id);
		kingdomService.patch(peon);
	}

	public void delete(int id) {
		kingdomService.delete(id);
	}
}
