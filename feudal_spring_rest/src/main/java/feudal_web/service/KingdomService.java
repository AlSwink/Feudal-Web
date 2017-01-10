package feudal_web.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cooksys.ftd.assignments.collections.Kingdom;
import com.cooksys.ftd.assignments.collections.model.Feudal;
import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.exception.CustomValidationFailedException;

@Service
public class KingdomService {

	private Kingdom kingdom;
	private ArrayList<Feudal> feudalIdTracker = new ArrayList<>();
	
	public KingdomService(Kingdom kingdom) {
		super();
		this.kingdom = kingdom;
	}
	
	public int add(Feudal feudal) {
		feudalIdTracker.add(feudal);
		feudal.setId(feudalIdTracker.size());
		kingdom.add(feudal);
		return feudal.getId();
	}

	public boolean has(int id) {
		return id > 0 && id <= feudalIdTracker.size();
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
		if(has(id))
			return feudalIdTracker.get(--id);
		else
			return null;
	}
	
	public Set<Feudal> getElements() {
		return kingdom.getElements();
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

	public void put(Peon peon) {
		//get em
		feudalIdTracker.get(peon.getId() - 1);
		//change em
	}
	
}
