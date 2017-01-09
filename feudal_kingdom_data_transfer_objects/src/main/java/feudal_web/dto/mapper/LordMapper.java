package feudal_web.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import com.cooksys.ftd.assignments.collections.model.Lord;

import feudal_web.dto.lord.LordWithIdDto;
import feudal_web.dto.lord.LordWithoutIdDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LordMapper {
	
	@Mappings({
		@Mapping(source = "parent.id", target = "parentId")
	})
	LordWithoutIdDto lordToLordWithoutIdDto(Lord lord);
	
	@Mappings({
		@Mapping(source = "parent.id", target = "parentId")
	})
	LordWithIdDto lordToLordWithIdDto(Lord lord);
	
	Lord lordWithoutIdDtoToLord(LordWithoutIdDto lordWithoutIdDto);
	
	Lord lordWithIdDtoTolord(LordWithIdDto lordWithoutIdDto);
}
