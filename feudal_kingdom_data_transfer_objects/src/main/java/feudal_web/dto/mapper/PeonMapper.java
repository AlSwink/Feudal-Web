package feudal_web.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.dto.peon.PeonWithIdDto;
import feudal_web.dto.peon.PeonWithoutIdDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PeonMapper {
	
	@Mappings({
		@Mapping(source = "parent.id", target = "parentId")
	})
	PeonWithoutIdDto peonToPeonWithoutIdDto(Peon peon);
	
	@Mappings({
		@Mapping(source = "parent.id", target = "parentId")
	})
	PeonWithIdDto peonToPeonWithIdDto(Peon peon);
	
	Peon peonWithoutIdDtoToPeon(PeonWithoutIdDto peonWithoutIdDto);
	
	Peon peonWithIdDtoToPeon(PeonWithIdDto peonWithoutIdDto);
}
