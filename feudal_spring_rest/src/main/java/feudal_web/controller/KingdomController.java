package feudal_web.controller;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.ftd.assignments.collections.model.Feudal;

import feudal_web.service.KingdomService;

@RestController
@RequestMapping("kingdom")
public class KingdomController {
	
	private KingdomService kingdomService;

	public KingdomController(KingdomService kingdomService) {
		this.kingdomService = kingdomService;
	}
	
	/**
	 * Uses HEAD http method to determine if a resource exists without the overhead of retrieving the resource itself.
	 * Returns default response code of 200 if id is found.
	 * Changes response code to 404 (not found) if id is not found.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.HEAD)
	public void has(@PathVariable int id, HttpServletResponse httpResponse) {
		if(!kingdomService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	/**
	 * Returns the Feudal with the id designated in the path. If no resource is found with that id, returns no content with a 404 status code
	 * @param id Path variable that will be the id of the resource if found
	 * @param httpResponse
	 * @return
	 */
	@GetMapping("{id}")
	public Feudal getById(@PathVariable int id, HttpServletResponse httpResponse) {
		Feudal feudal = kingdomService.get(id);
		if(feudal == null)
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return feudal;
	}
	
	@GetMapping
	public Collection<Feudal> get() {
		return kingdomService.getElements();
	}
}
