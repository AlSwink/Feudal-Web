package com.cooksys.ftd.assignments.collections.model;

public class Lord implements Feudal {

	private int id;
	private String name;
	private int salary;
	private Lord parent;
	
	public Lord() {
		// TODO Auto-generated constructor stub
	}
	
    public Lord(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Lord(String name, int salary, Lord parent) {
        this.name = name;
        this.salary = salary;
        this.parent = parent;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Lord getParent() {
		return parent;
	}

	public void setParent(Lord parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + salary;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lord other = (Lord) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}

	@Override
	public boolean hasParent() {
		return parent != null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
