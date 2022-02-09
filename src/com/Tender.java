package com;

import com.exception.NoSuitableOfferException;

import java.math.BigDecimal;
import java.util.*;

public class Tender {
    private final String tenderName;
    private final BigDecimal tenderPrice;
    private LinkedHashMap<Skills, Integer> brigadeRequirements;

    public Tender(String tenderName, BigDecimal tenderPrice) {
        this.tenderName = tenderName;
        this.tenderPrice = tenderPrice;
        brigadeRequirements = new LinkedHashMap<>();
    }

    public void addEmployeeRequirements(Skills skill, Integer count) {
        brigadeRequirements.put(skill, count);
    }

    public String getTenderName() {
        return tenderName;
    }

    public BigDecimal getTenderPrice() {
        return tenderPrice;
    }

    public LinkedHashMap<Skills, Integer> getBrigadeRequirements() {
        return brigadeRequirements;
    }

    public Brigade startQualify(ArrayList<Brigade> brigades) throws NoSuitableOfferException {
        BigDecimal lowestProposal = tenderPrice;
        int brigadeWinner = -1;
        for (int i = 0; i < brigades.size(); i++) {
            HashMap<Skills, Integer> brigadeCapabilities = brigadeCapabilitiesSummary(brigades, i);
            boolean check = brigadeCapabilitiesCheck(brigadeCapabilities, brigadeRequirements);
            if ((check) & ((brigades.get(i).getProposal().compareTo(lowestProposal) < 0)
                    | (brigades.get(i).getProposal().compareTo(lowestProposal) == 0))) {
                lowestProposal = brigades.get(i).getProposal();
                brigadeWinner = i;
            }
        }
        if (brigadeWinner == -1) throw new NoSuitableOfferException
                ("Тендер закрыт в связи с отсуствием предложений, отвечающих условиям проведения закупки");
        return brigades.get(brigadeWinner);
    }

    private HashMap<Skills, Integer> brigadeCapabilitiesSummary(ArrayList<Brigade> brigades, int i) {
        HashMap<Skills, Integer> brigadeCapabilities = new HashMap<>();
        for (Employee employee : brigades.get(i).getWorkers()) {
            for (Skills skill : employee.getSkillSet()) {
                if (!brigadeCapabilities.containsKey(skill)) brigadeCapabilities.put(skill, 1);
                else brigadeCapabilities.put(skill, brigadeCapabilities.get(skill) + 1);
            }
        }
        return brigadeCapabilities;
    }

    private boolean brigadeCapabilitiesCheck
            (HashMap<Skills, Integer> brigadeCapabilities, LinkedHashMap<Skills, Integer> brigadeRequirements) {
        for (Map.Entry<Skills, Integer> entry : brigadeRequirements.entrySet()) {
            Skills key = entry.getKey();
            Integer value = entry.getValue();
            if (!brigadeCapabilities.containsKey(key)) return false;
            else {
                if (brigadeCapabilities.get(key) < value) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tender tender)) return false;
        return Objects.equals(tenderName, tender.tenderName) && Objects.equals(tenderPrice, tender.tenderPrice)
                && Objects.equals(brigadeRequirements, tender.brigadeRequirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenderName, tenderPrice, brigadeRequirements);
    }

    @Override
    public String toString() {
        return "Tender{" +
                "objectName='" + tenderName + '\'' +
                ", objectPrice=" + tenderPrice +
                ", brigadeRequirements=" + brigadeRequirements +
                '}';
    }
}
