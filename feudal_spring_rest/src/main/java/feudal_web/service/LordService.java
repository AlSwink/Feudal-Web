package feudal_web.service;

import org.springframework.stereotype.Service;

import com.cooksys.ftd.assignments.collections.model.Lord;

import feudal_web.dto.lord.LordWithoutIdDto;
import feudal_web.dto.mapper.LordMapper;

@Service
public class LordService {

	private final KingdomService kingdomService;
	private final LordMapper LordMapper;
	
	public LordService(KingdomService kingdomService, LordMapper LordMapper) {
		this.kingdomService = kingdomService;
		this.LordMapper = LordMapper;
	}

	public Integer add(LordWithoutIdDto LordWithoutIdDto) {
		Lord Lord = LordMapper.lordWithoutIdDtoToLord(LordWithoutIdDto);
		Lord.setParent(kingdomService.get(LordWithoutIdDto.getParentId(), Lord.class, false));
		return kingdomService.add(Lord);
	}

	public LordWithoutIdDto get(Integer id) {
		return LordMapper.lordToLordWithoutIdDto(kingdomService.get(id, Lord.class, true));
	}

	public boolean has(Integer id) {
		return kingdomService.has(id, Lord.class);
	}

	public void put(Integer id, LordWithoutIdDto lordDto) {
		Lord lord = LordMapper.lordWithoutIdDtoToLord(lordDto);
		lord.setId(id);
		kingdomService.put(lord);
	}

	public void delete(Integer id) {
		kingdomService.delete(id);
	}

	public void patch(Integer id, LordWithoutIdDto lordDto) {
		Lord lord = LordMapper.lordWithoutIdDtoToLord(lordDto);
		lord.setId(id);
		kingdomService.patch(lord);
	}
}
