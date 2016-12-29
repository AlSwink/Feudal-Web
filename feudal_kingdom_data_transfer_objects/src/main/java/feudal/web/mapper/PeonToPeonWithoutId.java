package feudal.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeonToPeonWithoutId {
	
	PeonToPeonWithoutId INSTANCE = Mappers.getMapper(PeonToPeonWithoutId.class);
	
//	PeonWithoutId peonToPeonWithoutId(Peon peon);
//	Peon peonWithoutIdToPeon(PeonWithoutId peonWithoutId);
	
}
