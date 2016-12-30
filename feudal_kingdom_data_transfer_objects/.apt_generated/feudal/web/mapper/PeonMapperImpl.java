package feudal.web.mapper;

import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;
import feudal_web.dto.PeonWithIdDto;
import feudal_web.dto.PeonWithoutIdDto;
import feudal_web.dto.mapper.PeonMapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2016-12-29T21:34:28-0600",
    comments = "version: 1.0.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_112 (Oracle Corporation)"
)
@Component
public class PeonMapperImpl implements PeonMapper {

    @Override
    public PeonWithoutIdDto peonToPeonWithoutIdDto(Peon peon) {
        if ( peon == null ) {
            return null;
        }

        PeonWithoutIdDto peonWithoutIdDto = new PeonWithoutIdDto();

        peonWithoutIdDto.setParentId( peonParentId( peon ) );
        peonWithoutIdDto.setName( peon.getName() );
        peonWithoutIdDto.setSalary( peon.getSalary() );

        return peonWithoutIdDto;
    }

    @Override
    public PeonWithIdDto peonToPeonWithIdDto(Peon peon) {
        if ( peon == null ) {
            return null;
        }

        PeonWithIdDto peonWithIdDto = new PeonWithIdDto();

        peonWithIdDto.setParentId( peonParentId_( peon ) );
        peonWithIdDto.setId( peon.getId() );
        peonWithIdDto.setName( peon.getName() );
        peonWithIdDto.setSalary( peon.getSalary() );

        return peonWithIdDto;
    }

    @Override
    public Peon peonWithoutIdDtoToPeon(PeonWithoutIdDto peonWithoutIdDto) {
        if ( peonWithoutIdDto == null ) {
            return null;
        }

        Peon peon = new Peon();

        peon.setName( peonWithoutIdDto.getName() );
        peon.setSalary( peonWithoutIdDto.getSalary() );

        return peon;
    }

    @Override
    public Peon peonWithIdDtoToPeon(PeonWithIdDto peonWithoutIdDto) {
        if ( peonWithoutIdDto == null ) {
            return null;
        }

        Peon peon = new Peon();

        peon.setId( peonWithoutIdDto.getId() );
        peon.setName( peonWithoutIdDto.getName() );
        peon.setSalary( peonWithoutIdDto.getSalary() );

        return peon;
    }

    private int peonParentId(Peon peon) {

        if ( peon == null ) {
            return 0;
        }
        Lord parent = peon.getParent();
        if ( parent == null ) {
            return 0;
        }
        int id = parent.getId();
        return id;
    }

    private int peonParentId_(Peon peon) {

        if ( peon == null ) {
            return 0;
        }
        Lord parent = peon.getParent();
        if ( parent == null ) {
            return 0;
        }
        int id = parent.getId();
        return id;
    }
}
