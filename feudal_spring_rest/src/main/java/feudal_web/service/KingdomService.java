package feudal_web.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cooksys.ftd.assignments.collections.model.Feudal;

import feudal_web.exception.CustomValidationFailedException;

@Service
public class KingdomService {

	private AtomicInteger idGenerator = new AtomicInteger(0);
	private HashMap<Integer, Feudal> idToFeudalMap = new HashMap<>();	
	
	public KingdomService() {
		super();
	}
	
	public Integer add(Feudal feudal) {
		feudal.setId(idGenerator.incrementAndGet());
		idToFeudalMap.put(feudal.getId(), feudal);
		return feudal.getId();
	}

	public boolean has(Integer id) {
		return id != null && id > 0 && idToFeudalMap.containsKey(id);
	}
	
	public <T extends Feudal> boolean has(Integer id, Class<T> clazz) {
		return has(id) && (get(id, clazz) != null);
	}

	/**
	 * 
	 * @param id The id of the Feudal to be retrieved
	 * @return The Feudal that possesses the id, or null if no Feudal exists with that id
	 */
	public Feudal get(Integer id) {
		return idToFeudalMap.get(id);
	}
	
	public Collection<Feudal> getElements() {
		return idToFeudalMap.values();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Feudal> Collection<T> getElements(Class<T> feudalType) {
		return (Collection<T>) idToFeudalMap.values().stream().filter(value -> feudalType.isInstance(value)).collect(Collectors.toSet());
	}

	public <T extends Feudal> T get(Integer id, Class<T> clazz) {
		return get(id, clazz, true);
	}
	
	public <T extends Feudal> T get(Integer id, Class<T> clazz, boolean exceptionWhenNotFound) {
		Feudal feudal = get(id);
		if(clazz.isInstance(feudal))
			return clazz.cast(feudal);
		else if(exceptionWhenNotFound)
			throw new CustomValidationFailedException("Cannot find instance of " + clazz.getSimpleName() + " with id of [" + id + "]", HttpStatus.NOT_FOUND);
		return null;
	}

	public <T extends Feudal> void put(T feudal) {
		BeanUtils.copyProperties(feudal, get(feudal.getId(), feudal.getClass()));
	}

	public <T extends Feudal> void patch(T feudal) {
		idToFeudalMap.put(feudal.getId(), feudal);
	}

	public void delete(Integer id) {
		idToFeudalMap.remove(id);
	}
	
}
