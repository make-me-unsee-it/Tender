package com;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class Brigade {
    private final ArrayList<Employee> workers;
    private final BigDecimal proposal;

    public Brigade (BigDecimal proposal, ArrayList<Employee> employees) {
        this.proposal = proposal;
        workers = employees;
    }

    public ArrayList<Employee> getWorkers() {
        return workers;
    }

    public BigDecimal getProposal() {
        return proposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brigade brigade)) return false;
        return Objects.equals(workers, brigade.workers) && Objects.equals(proposal, brigade.proposal);
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
