package com.ydl.examantion.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class Population {
	private Individual[] population;
	private double populationFitness = 0.00;

	public Population(int size) {
		this.populationFitness = 0.00;
		this.population = new Individual[size];
	}


	public Individual[] getIndividuals() {
		return this.population;
	}


	public double getPopulationFitness() {
		return populationFitness;
	}


	public void setPopulationFitness(double populationFitness) {
		this.populationFitness = populationFitness;
	}


	public int size() {
		return this.population.length;
	}


	public Individual getFittest(int offset) {
		Arrays.sort(this.population, new Comparator<Individual>() {

			@Override
			public int compare(Individual o1, Individual o2) {
				if(o1.getFitness() > o2.getFitness()) {
					return -1;
				}else if(o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});
		return this.population[offset];
	}


	public void setInividual(int offset, Individual individual) {
		this.population[offset] = individual;
	}

	public Individual getIndividual(int index) {
		return population[index];
	}


	public void shuffle(){
		Random rand = new Random();
		for(int i=this.population.length-1; i>0; i--) {
			int index = rand.nextInt(i+1);
			 Individual temp = population[i];
			 population[i] = population[index];
			 population[index] = temp;
		}
	}
}
