package feudal_kingdom_data_transfer_objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import feudal.web.dto.Car;
import feudal.web.dto.CarDto;
import feudal.web.mapper.CarMapper;

public class PeonMapperTest {

	@Test
	public void test() {
		
	    //given
	    Car car = new Car( "Morris", 5, "SEDAN" );
	 
	    //when
	    CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );
	 
	    //then
	    assertThat( carDto, notNullValue());
	    assertThat( carDto.getMake(), is("Morris"));
	    assertThat( carDto.getSeatCount(), is(5) );
	    assertThat( carDto.getType(), is("SEDAN") );
		
	    
/*	    
		Peon peon = new Peon("Mike", 100);
		
		PeonWithoutId peonWithoutId = PeonToPeonWithoutId.INSTANCE.peonToPeonWithoutId(peon);
		
		assertThat(peonWithoutId, notNullValue());
		assertThat(peonWithoutId.getName(), is("Mike"));
		assertThat(peonWithoutId.getSalary(), is(100));
*/		
	}

}
