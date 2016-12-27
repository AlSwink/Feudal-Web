package com.cooksys.ftd.assignments.collections.properties;

import com.cooksys.ftd.assignments.collections.generators.Cat;
import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class WageSlaveProperties {

    @Property
    public void noOwnerConstructor(String name, int salary) {
        Peon slave = new Peon(name, salary);
        assertEquals("WageSlave#getName() did not return the value passed to new WageSlave(name,...)", name, slave.getName());
        assertEquals("WageSlave#getSalary() did not return the value passed to new WageSlave(..., salary,...)", salary, slave.getSalary());
        assertNull("WageSlave#getParent() did not return null when constructed without an owner", slave.getParent());
        assertFalse("WageSlave#hasParent() did not return false when constructed without an owner", slave.hasParent());
    }

    @Property
    public void fullConstructor(String name, int salary, @Cat Lord owner) {
        Peon slave = new Peon(name, salary, owner);
        assertEquals("WageSlave#getName() did not return the value passed to new WageSlave(name,...)", name, slave.getName());
        assertEquals("WageSlave#getSalary() did not return the value passed to new WageSlave(..., salary,...)", salary, slave.getSalary());
        assertEquals("WageSlave#getParent() did not return the value passed to new WageSlave(..., owner)", owner, slave.getParent());
        assertTrue("WageSlave#hasParent() did not return true when constructed without an owner", slave.hasParent());
    }

    @Property
    public void noOwnerValueEquality(String name, int salary) {
        Peon a = new Peon(name, salary);
        Peon b = new Peon(name, salary);
        assertEquals("WageSlave#equals() did not return true when both instances were constructed with the same name and salary values", a, b);
    }

    @Property
    public void fullValueEquality(String name, int salary, @Cat Lord owner) {
        Peon a = new Peon(name, salary, owner);
        Peon b = new Peon(name, salary, owner);
        assertEquals("WageSlave#equals() did not return true when both instances were constructed with the same name, salary, and owner values", a, b);
    }
}
