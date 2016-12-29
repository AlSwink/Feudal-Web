package com.cooksys.ftd.assignments.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Feudal;
import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;

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
     * @param feudal the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Feudal feudal) {
        if(feudal == null || has(feudal) || (feudal instanceof Peon && !feudal.hasParent()))
        	return false;
        
        if(feudal.getParent() != null && !orgStructure.containsKey(feudal.getParent()))
    		add(feudal.getParent());
        
        allItems.add(feudal);
        
        if(feudal instanceof Lord)
           	orgStructure.put((Lord)feudal, new HashSet<Feudal>());
        
        if(feudal.hasParent())
        	orgStructure.get(feudal.getParent()).add(feudal);
        
        return true;
    }

    /**
     * @param feudal the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Feudal feudal) {
        return allItems.contains(feudal);
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
     * @param feudal
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<Lord> getParentChain(Feudal feudal) {
        List<Lord> chain = new ArrayList<>();
        if(!has(feudal))
        	return chain;
    	return getParentChain(feudal, chain);
        
    }
    
    private List<Lord> getParentChain(Feudal feudal, List<Lord> chain) {
        if(feudal.getParent() != null) {
        	chain.add(feudal.getParent());
        	getParentChain(feudal.getParent(), chain);
        }
        return chain;

    }
}
