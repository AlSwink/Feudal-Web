package com.cooksys.ftd.assignments.collections.properties;

import com.cooksys.ftd.assignments.collections.generators.Cat;
import com.cooksys.ftd.assignments.collections.model.Lord;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class FatCatProperties {

    @Property
    public void noOwnerConstructor(String name, int salary) {
        Lord cat = new Lord(name, salary);
        assertEquals("FatCat#getName() did not return the value passed to new FatCat(name,...)", name, cat.getName());
        assertEquals("FatCat#getSalary() did not return the value passed to new FatCat(..., salary,...)", salary, cat.getSalary());
        assertNull("FatCat#getParent() did not return null when constructed without an owner", cat.getParent());
        assertFalse("FatCat#hasParent() did not return false when constructed without an owner", cat.hasParent());
    }

    @Property
    public void fullConstructor(String name, int salary, @Cat Lord owner) {
        Lord cat = new Lord(name, salary, owner);
        assertEquals("FatCat#getName() did not return the value passed to new FatCat(name,...)", name, cat.getName());
        assertEquals("FatCat#getSalary() did not return the value passed to new FatCat(..., salary,...)", salary, cat.getSalary());
        assertEquals("FatCat#getParent() did not return the value passed to new FatCat(..., owner)", owner, cat.getParent());
        assertTrue("FatCat#hasParent() did not return true when constructed without an owner", cat.hasParent());
    }

    @Property
    public void noOwnerValueEquality(String name, int salary) {
        Lord a = new Lord(name, salary);
        Lord b = new Lord(name, salary);
        assertEquals("FatCat#equals() did not return true when both instances were constructed with the same name and salary values", a, b);
    }

    @Property
    public void fullValueEquality(String name, int salary, @Cat Lord owner) {
        Lord a = new Lord(name, salary, owner);
        Lord b = new Lord(name, salary, owner);
        assertEquals("FatCat#equals() did not return true when both instances were constructed with the same name, salary, and owner values", a, b);
    }
}
