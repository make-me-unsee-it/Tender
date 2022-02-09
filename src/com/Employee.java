package com;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

public class Employee {
    private HashSet<Skills> skillSet;
    private final String name;

    public Employee(String name, Skills... skill) {
        this.name = name;
        skillSet = new HashSet<>();
        Collections.addAll(skillSet, skill);
    }

    public HashSet<Skills> getSkillSet() {
        return skillSet;
    }

    public String getName() {
        return name;
    }

    public void skillAdd(Skills skill) {
        skillSet.add(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(skillSet, employee.skillSet) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillSet, name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "skillSet=" + skillSet +
                ", name='" + name + '\'' +
                '}';
    }
}
