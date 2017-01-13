package feudal_web.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
	
	public int add(Feudal feudal) {
		feudal.setId(idGenerator.incrementAndGet());
		idToFeudalMap.put(feudal.getId(), feudal);
		return feudal.getId();
	}

	public boolean has(int id) {
		return id > 0 && idToFeudalMap.containsKey(id);
	}
	
	public <T extends Feudal> boolean has(int id, Class<T> clazz) {
		return has(id) && (get(id, clazz) != null);
	}

	/**
	 * 
	 * @param id The id of the Feudal to be retrieved
	 * @return The Feudal that possesses the id, or null if no Feudal exists with that id
	 */
	public Feudal get(int id) {
		return idToFeudalMap.get(id);
	}
	
	public Collection<Feudal> getElements() {
		return idToFeudalMap.values();
	}

	public <T extends Feudal> T get(int id, Class<T> clazz) {
		return get(id, clazz, true);
	}
	
	public <T extends Feudal> T get(int id, Class<T> clazz, boolean exceptionWhenNotFound) {
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

	public void delete(int id) {
		idToFeudalMap.remove(id);
	}
	
}
