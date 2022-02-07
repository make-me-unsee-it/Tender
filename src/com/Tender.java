package com;

import com.exception.NoSuitableOfferException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Tender {
    private String objectName;
    private double objectPrice;
    private LinkedHashMap<Skills, Integer> brigadeRequirements;

    public Tender(String objectName, double objectPrice) {
        this.objectName = objectName;
        this.objectPrice = objectPrice;
        brigadeRequirements = new LinkedHashMap<>();
    }

    public void addEmployeeRequirements(Skills skill, Integer count) {
        brigadeRequirements.put(skill, count);
    }

    public String getObjectName() {
        return objectName;
    }

    public double getObjectPrice() {
        return objectPrice;
    }

    public HashMap<Skills, Integer> getBrigadeRequirements() {
        return brigadeRequirements;
    }

    public Brigade startQualify(Brigade... brigades) throws NoSuitableOfferException {
        double lowestProposal = objectPrice;
        int brigadeWinner = -1;
        for (int i = 0; i < brigades.length; i++) {
            HashMap<Skills, Integer> brigadeCapabilities = new HashMap<>();
            for (Employee employee : brigades[i].getWorkers()) {
                for (Skills skill : employee.getSkillSet()) {
                    if (!brigadeCapabilities.containsKey(skill)) brigadeCapabilities.put(skill, 1);
                    else brigadeCapabilities.put(skill, brigadeCapabilities.get(skill) + 1);
                }
            }
            for (Map.Entry<Skills,Integer> entry: brigadeRequirements.entrySet()) {
                    Skills key = entry.getKey();
                    Integer value = entry.getValue();
                    if ((!brigadeCapabilities.containsKey(key) && (!(brigadeCapabilities.get(key) >= value)))) break;
                    if (brigades[i].getProposal() <= lowestProposal) {
                        lowestProposal = brigades[i].getProposal();
                        brigadeWinner = i;
                    }
            }
        }
        if (brigadeWinner == -1) throw new NoSuitableOfferException
                ("Тендер закрыт в связи с отсуствием предложений, отвечающих условиям проведения закупки");
        return brigades[brigadeWinner];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tender tender)) return false;
        return Double.compare(tender.objectPrice, objectPrice) == 0 && Objects.equals(objectName, tender.objectName)
                && Objects.equals(brigadeRequirements, tender.brigadeRequirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectName, objectPrice, brigadeRequirements);
    }

    @Override
    public String toString() {
        return "Tender{" +
                "objectName='" + objectName + '\'' +
                ", objectPrice=" + objectPrice +
                ", brigadeRequirements=" + brigadeRequirements +
                '}';
    }
}
