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

//        /**
//         * 组卷过程
//         *
//         * @param rule
//         * @return
//         */
//        public static Exam generatePaper(RuleBean rule) {
//            Exam resultPaper = null;
//            // 迭代计数器
//            int count = 0;
//            int runCount = 100;
//            // 适应度期望值z
//            double expand = 0.98;
//            if (rule != null) {
//                // 初始化种群
//                Population population = new Population(20, true, rule);
//                System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());
//                while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
//                    count++;
//                    population = GA.evolvePopulation(population, rule);
//                    System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
//                }
//                System.out.println("进化次数： " + count);
//                System.out.println(population.getFitness().getAdaptationDegree());
//                resultPaper = population.getFitness();
//            }
//            return resultPaper;
//        }
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
