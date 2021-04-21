package com.ydl.examantion.algorithm;

import com.ydl.examantion.service.impl.BlankServiceImpl;

import java.util.List;

public class PaperMaker {
    /**
     * 组卷
     * @param userWantPaper
     * @param qList
     * @return
     */
    public static Individual make(Paper userWantPaper,List<Question> qList) {

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.95, 2, 20);


        Population population = ga.initPopulation(100, userWantPaper, qList);

        ga.evalPopulation(population, userWantPaper);

        int generation = 1;
        while(ga.isTerminationConditionMet(population) == false && generation < 6000) {
            System.out.println(generation + "代 " +"Best solution: " + population.getFittest(0).toString());
            try {
                population = ga.crossoverPopulation(population);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            population = ga.mutatePopulation(population);
            ga.evalPopulation(population, userWantPaper);
            generation++;
        }
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest(0).toString());

        List<Question> questions = population.getFittest(0).getQuestions();
        System.out.println("----------------------------------------------");
        for(Question q:questions) {
            System.out.println(q);
        }
        return population.getFittest(0);
    }

}
