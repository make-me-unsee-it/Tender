package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Brigade {
    private ArrayList<Employee> workers;
    private double proposal;

    public Brigade (double proposal, Employee ... employees) {
        this.proposal = proposal;
        workers = new ArrayList<>();
        Collections.addAll(workers, employees);
    }

    public ArrayList<Employee> getWorkers() {
        return workers;
    }

    public double getProposal() {
        return proposal;
    }

    public void setProposal(double proposal) {
        this.proposal = proposal;
    }
    public void workersAdd(Employee employee) {
        workers.add(employee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brigade brigade)) return false;
        return Double.compare(brigade.proposal, proposal) == 0 && Objects.equals(workers, brigade.workers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workers, proposal);
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "workers=" + workers +
                ", proposal=" + proposal +
                '}';
    }
}
