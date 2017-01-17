package feudal_web.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;

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

	public Integer add(PeonWithoutIdDto peonWithoutIdDto) {
		Peon peon = peonMapper.peonWithoutIdDtoToPeon(peonWithoutIdDto);
		peon.setParent(kingdomService.get(peonWithoutIdDto.getParentId(), Lord.class));
		return kingdomService.add(peon);
	}

	public PeonWithoutIdDto get(Integer id) {
		return peonMapper.peonToPeonWithoutIdDto(kingdomService.get(id, Peon.class, true));
	}

	public boolean has(Integer id) {
		return kingdomService.has(id, Peon.class);
	}

	public void put(Integer id, PeonWithoutIdDto peonDto) {
		Peon peon = peonMapper.peonWithoutIdDtoToPeon(peonDto);
		peon.setId(id);
		kingdomService.put(peon);
	}

	public void patch(Integer id, PeonWithoutIdDto peonDto) {
		Peon peon = peonMapper.peonWithoutIdDtoToPeon(peonDto);
		peon.setId(id);
		kingdomService.patch(peon);
	}

	public void delete(Integer id) {
		kingdomService.delete(id);
	}

	public List<PeonWithoutIdDto> getByLord(Integer lordId) {
		if(lordId == null)
			return Collections.emptyList();
		return kingdomService.getElements(Peon.class).stream()
				.filter(peon -> lordId.equals(peon.getParent().getId()))
				.map(peonOfLord -> peonMapper.peonToPeonWithoutIdDto(peonOfLord))
				.collect(Collectors.toList());
	}
}
