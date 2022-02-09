package com.test;

import com.Brigade;
import com.Employee;
import com.Skills;
import com.Tender;
import com.exception.NoSuitableOfferException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TenderTest {
    private Employee engineer1;
    private Employee engineer2;
    private Employee engineer3;
    private Employee engineer4;
    private Employee engineer5;
    private Employee worker1;
    private Employee worker2;
    private Employee worker3;
    private Employee worker4;
    private Employee worker5;
    private Employee worker6;
    private Employee worker7;
    private Employee worker8;
    private Employee worker9;
    private Employee worker10;
    private Brigade theHornsAndHooves;
    private Brigade proFit;
    private Brigade metroPowerBrigade;
    private Tender tender;

    @Before
    public void setUp() {
        engineer1 = new Employee("Кульман Исаак", new HashSet<>(List.of(Skills.SUPERVISOR)));
        engineer2 = new Employee("Кузнецов Марк", new HashSet<>(List.of(Skills.FOREMAN)));
        engineer3 = new Employee("Фролов Андрей", new HashSet<>(List.of(Skills.FOREMAN, Skills.DRIVER)));
        engineer4 = new Employee("Колесников Александр", new HashSet<>(List.of(Skills.MASTER, Skills.NETWORKER)));
        engineer5 = new Employee("Денисов Дмитрий", new HashSet<>(List.of(Skills.MASTER, Skills.DRIVER)));
        worker1 = new Employee("Смирнов Александр", new HashSet<>(List.of(Skills.MASON, Skills.BETWORKER)));
        worker2 = new Employee("Шилов Михаил",
                new HashSet<>(List.of(Skills.MASON, Skills.BETWORKER, Skills.FITTER)));
        worker3 = new Employee("Антонов Александр", new HashSet<>(List.of(Skills.MASON)));
        worker4 = new Employee("Ермаков Лев", new HashSet<>(List.of(Skills.MASON)));
        worker5 = new Employee("Рогов Егор", new HashSet<>(List.of(Skills.MASON)));
        worker6 = new Employee("Смирнов Степан", new HashSet<>(List.of(Skills.FITTER, Skills.BETWORKER)));
        worker7 = new Employee("Овчинников Артур", new HashSet<>(List.of(Skills.FITTER, Skills.BETWORKER)));
        worker8 = new Employee("Лобанов Дмитрий", new HashSet<>(List.of(Skills.FITTER, Skills.DRIVER)));
        worker9 = new Employee("Миронов Артемий", new HashSet<>(List.of(Skills.NETWORKER)));
        worker10 = new Employee("Беляков Максим", new HashSet<>(List.of(Skills.DRIVER)));
        theHornsAndHooves = new Brigade(new BigDecimal(1000000),
                new ArrayList<>(List.of(engineer1, engineer2, engineer5, worker2, worker10)));
        proFit = new Brigade(new BigDecimal(950000),
                new ArrayList<>(List.of(engineer3, engineer4, worker1, worker3, worker4, worker5)));
        metroPowerBrigade = new Brigade(new BigDecimal(999000),
                new ArrayList<>(List.of(engineer4, worker5, worker6, worker7, worker8, worker9)));
    }

    @After
    public void tearDown() {
        engineer1 = null;
        engineer2 = null;
        engineer3 = null;
        engineer4 = null;
        engineer5 = null;
        worker1 = null;
        worker2 = null;
        worker3 = null;
        worker4 = null;
        worker5 = null;
        worker6 = null;
        worker7 = null;
        worker8 = null;
        worker9 = null;
        worker10 = null;
        theHornsAndHooves = null;
        proFit = null;
        metroPowerBrigade = null;
    }

    @Test
    public void testStartQualifySuccess() throws NoSuitableOfferException {
        tender = new Tender("Национальная библиотека 2", new BigDecimal(1000000));
        tender.addEmployeeRequirements(Skills.FOREMAN, 1);
        tender.addEmployeeRequirements(Skills.MASON, 1);
        tender.addEmployeeRequirements(Skills.DRIVER, 1);
        Brigade actual = theHornsAndHooves;
        Brigade expected = tender.startQualify(new ArrayList<>(List.of(theHornsAndHooves)));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStartQualifySuccess2() throws NoSuitableOfferException {
        tender = new Tender("Национальная библиотека 2", new BigDecimal(1000000));
        tender.addEmployeeRequirements(Skills.FOREMAN, 1);
        tender.addEmployeeRequirements(Skills.MASON, 1);
        tender.addEmployeeRequirements(Skills.DRIVER, 1);
        Brigade actual = proFit;
        Brigade expected = tender.startQualify(new ArrayList<>(List.of(theHornsAndHooves, proFit)));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStartQualifySuccess3() throws NoSuitableOfferException {
        tender = new Tender("Национальная библиотека 2", new BigDecimal(1000000));
        tender.addEmployeeRequirements(Skills.FOREMAN, 1);
        tender.addEmployeeRequirements(Skills.MASON, 1);
        tender.addEmployeeRequirements(Skills.DRIVER, 1);
        Brigade actual = proFit;
        Brigade expected = tender.startQualify(new ArrayList<>(List.of(theHornsAndHooves, proFit, metroPowerBrigade)));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStartQualifySuccess4() throws NoSuitableOfferException {
        tender = new Tender("Национальная библиотека 2", new BigDecimal(1000000));
        tender.addEmployeeRequirements(Skills.MASTER, 1);
        tender.addEmployeeRequirements(Skills.MASON, 1);
        tender.addEmployeeRequirements(Skills.DRIVER, 1);
        tender.addEmployeeRequirements(Skills.FITTER, 3);
        Brigade actual = metroPowerBrigade;
        Brigade expected = tender.startQualify(new ArrayList<>(List.of(theHornsAndHooves, proFit, metroPowerBrigade)));
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NoSuitableOfferException.class)
    public void testStartQualifyFailedForRequirements() throws NoSuitableOfferException {
        tender = new Tender("Национальная библиотека 2", new BigDecimal(1000000));
        tender.addEmployeeRequirements(Skills.MASTER, 2);
        tender.addEmployeeRequirements(Skills.MASON, 1);
        tender.addEmployeeRequirements(Skills.DRIVER, 1);
        tender.addEmployeeRequirements(Skills.FITTER, 3);
        tender.startQualify(new ArrayList<>(List.of(theHornsAndHooves, proFit, metroPowerBrigade)));
    }

    @Test(expected = NoSuitableOfferException.class)
    public void testStartQualifyFailedForPrice() throws NoSuitableOfferException {
        tender = new Tender("Национальная библиотека 2", new BigDecimal(800000));
        tender.addEmployeeRequirements(Skills.FOREMAN, 1);
        tender.addEmployeeRequirements(Skills.MASON, 1);
        tender.addEmployeeRequirements(Skills.DRIVER, 1);
        tender.startQualify(new ArrayList<>(List.of(theHornsAndHooves, proFit)));
    }
}
