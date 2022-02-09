package com;

import java.util.HashSet;
import java.util.Objects;

public class Employee {
    private final HashSet<Skills> skillSet;
    private final String employeeName;

    public Employee(String name, HashSet<Skills> skills) {
        this.employeeName = name;
        skillSet = skills;
    }

    public HashSet<Skills> getSkillSet() {
        return skillSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(skillSet, employee.skillSet) && Objects.equals(employeeName, employee.employeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillSet, employeeName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "skillSet=" + skillSet +
                ", name='" + employeeName + '\'' +
                '}';
    }
}
