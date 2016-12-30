package feudal_web.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.ftd.assignments.collections.Kingdom;
import com.cooksys.ftd.assignments.collections.model.Feudal;
import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.validator.Validators;

@Service
public class KingdomService {

	private Kingdom kingdom;
	private Validators validators;
	private ArrayList<Feudal> feudalIdTracker = new ArrayList<>();
	
	public KingdomService(Kingdom kingdom, Validators validators) {
		super();
		this.validators = validators;
		this.kingdom = kingdom;
	}
	
	public int add(Feudal feudal) {
		validators.validate(feudal);
		feudalIdTracker.add(feudal);
		feudal.setId(feudalIdTracker.size());
		kingdom.add(feudal);
		return feudal.getId();
	}

	public boolean has(int id) {
		return id > 0 && id <= feudalIdTracker.size();
	}

	/**
	 * 
	 * @param id The id of the Feudal to be retrieved
	 * @return The Feudal that possesses the id, or null if no Feudal exists with that id
	 */
	public Feudal get(int id) {
		// TODO handle index out of bounds exception
		if(has(id))
			return feudalIdTracker.get(--id);
		else
			return null;
	}

	public Set<Feudal> getElements() {
		return kingdom.getElements();
	}

	@SuppressWarnings("unchecked")
	public <T extends Feudal> T get(int id, Class<T> clazz) {
		Feudal feudal = get(id);
		if(clazz.isInstance(feudal))
			return (T) feudal;
		return null;
	}
	
}
