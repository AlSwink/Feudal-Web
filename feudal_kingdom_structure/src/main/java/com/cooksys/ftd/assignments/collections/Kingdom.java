package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Feudal;
import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class Kingdom implements Hierarchy<Feudal, Lord> {
	
	private HashMap<Lord, Set<Feudal>> orgStructure = new HashMap<>();
	private HashSet<Feudal> allItems = new HashSet<>();
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Feudal capitalist) {
        if(capitalist == null || has(capitalist) || (capitalist instanceof Peon && !capitalist.hasParent()))
        	return false;
        
        if(capitalist.getParent() != null && !orgStructure.containsKey(capitalist.getParent()))
    		add(capitalist.getParent());
        
        allItems.add(capitalist);
        
        if(capitalist instanceof Lord)
           	orgStructure.put((Lord)capitalist, new HashSet<Feudal>());
        
        if(capitalist.hasParent())
        	orgStructure.get(capitalist.getParent()).add(capitalist);
        
        return true;
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Feudal capitalist) {
        return allItems.contains(capitalist);
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Feudal> getElements() {
        return new HashSet<Feudal>(allItems);
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<Lord> getParents() {
        return new HashSet<Lord>(orgStructure.keySet());
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Feudal> getChildren(Lord fatCat) {
        return new HashSet<Feudal>(orgStructure.get(fatCat) != null ? orgStructure.get(fatCat) : new HashSet<Feudal>());
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<Lord, Set<Feudal>> getHierarchy() {
    	HashMap<Lord, Set<Feudal>> result = new HashMap<>();
    	for(Lord fatCat : orgStructure.keySet())
    		result.put(fatCat, new HashSet<Feudal>(orgStructure.get(fatCat)));
    	return result;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<Lord> getParentChain(Feudal capitalist) {
        List<Lord> result = new ArrayList<>();
        if(!has(capitalist))
        	return result;
    	return getParentChain(capitalist, result);
        
    }
    
    private List<Lord> getParentChain(Feudal capitalist, List<Lord> chain) {
        if(capitalist.getParent() != null) {
        	chain.add(capitalist.getParent());
        	getParentChain(capitalist.getParent(), chain);
        }
        return chain;

    }
}
