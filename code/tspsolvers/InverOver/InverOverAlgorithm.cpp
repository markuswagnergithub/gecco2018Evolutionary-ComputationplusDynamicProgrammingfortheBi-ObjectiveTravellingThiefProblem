//
//  InverOverAlgorithm.cpp
//  InverOver
//
//  Created by Junhua on 24/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#include "InverOverAlgorithm.h"
#include "Utilitis.h"


#include <iostream>
#include <limits>
#include <sstream>
#include <cmath>

int nextElement(const Individual &ind, const int city){
    
    int pos = ind.position(city);
    if (pos <0) return ind.getTour().at(0);
    else pos++;
    if ((unsigned)pos >= ind.getTour().size()){
        return ind.getTour().at(0);
    }else{
        return ind.getTour().at(pos);
    }
};

void printGenerationDetails(long generation, std::vector<Individual> &population,
                            long bestGenerationFitness, const Individual * bestInGeneration){
    if (generation%100!=0) {
        return;
    }
    
    std::stringstream genResult;
    
    genResult << "Iteration:" << generation << std::endl;
    genResult << "All Fitness:" << std::endl;
    
    // Generation average fitness and sd:
    double totalFitness = 0;
    for (auto it = population.begin(); it != population.end(); ++it) {
        totalFitness += it->getFitness();
        genResult << it->getFitness() << " ";
    }
    genResult << std::endl;
    double average = totalFitness/population.size();
    
    //log the result of this generation

    genResult << "Average:" << average << std::endl;
    genResult << "Best so far:" << bestGenerationFitness;
    genResult << " Time:" << Utilitis::elapsedTime() << std::endl;
    genResult << bestInGeneration->toString() << std::endl;
    
    std::cout << genResult.str();
    
}

void printFinalResults(long bestFitness, const Individual *bestInd){
    std::stringstream result;
    
    result << "Best Fitness:" << bestFitness << std::endl;
    result << "Best Individual:" << std::endl;
    result << bestInd->toString() << std::endl;
    
    std::cout << result.str();
}


Individual* InverOverAlgorithm::run(const int populationSize, const int time_limit, const long generations, const int max_gen_same_result, const TSP* tsp){

    double start_time = Utilitis::elapsedTime();
    
    
    // Random initialisation of the population P.
    std::vector<Individual> lstPopulation = Population::generateRandomPopulation(populationSize, tsp);

    //for debug
//    for (auto it = lstPopulation.begin(); it != lstPopulation.end(); ++it) {
//        std::cout<< it->toString() << std::endl;
//    }
//
//    
//    std::cout << "pop size:" << lstPopulation.size() << std::endl;
//    std::cout << "last " << lstPopulation[lstPopulation.size()-1].toString() << std::endl;
//    for (int i=0;i<55;i++){
//        std::cout << nextElement(lstPopulation[lstPopulation.size()-1], i) << " ";
//    }
//    std::cout << std::endl;

    
    Individual* bestInd = NULL;
    long bestFitness = std::numeric_limits<long>::max();
	int src = 0; //same result counter
    // A low probability p.
    const double p = 0.02;

    // Main algorithm
    for (long i = 0; i < generations; i++) {
        
        for (unsigned int j = 0; j < lstPopulation.size(); j++) {
            
            // Create copy of current individual; S'.
            Individual indvS_prime(lstPopulation[j].getTour(), tsp);
            
            std::vector<int> remainingCities = indvS_prime.getTour();
            
            // Select a random city c from S'.
            int position = Utilitis::randomInt((int)remainingCities.size());
            int c = remainingCities[position];
            
            remainingCities.erase(remainingCities.begin() + position);
            
            while (true) {
                // City c'.
                int c_prime = c;
                
                if (Utilitis::randomProbability() <= p) {
                    // Select the city c' from the remaining cities in S'
                    int pos = Utilitis::randomInt((int)remainingCities.size());
                    c_prime = remainingCities[pos];
                    remainingCities.erase(remainingCities.begin() + pos);
                    
                } else {
                    // Select random individual from P
                    int selectedInd = Utilitis::randomInt((int)lstPopulation.size());
                    
                    // Assign to c' the next city c in the selected
                    // individual.
                    c_prime = nextElement(lstPopulation[selectedInd], c);
                    
                }
                // Neighbouring city c in S' is c'. Exit from repeat loop.
                if (indvS_prime.isNeighborCity(c, c_prime))
                    break;
                
                // Inverse the section from the next city of city c to the
                // city c' in S'
                indvS_prime.reverseCities(c, c_prime);
                c = c_prime;
            }
            
            
            // Evaluate and decide whether we want the new individual.
            if (indvS_prime.getFitness() <= lstPopulation[j].getFitness()) {
                lstPopulation[j] = indvS_prime;
            }
        }
        
        Individual *bestGeneraInd = NULL;
        long bestGeneraFitness = std::numeric_limits<long>::max();
        for (auto it = lstPopulation.begin(); it != lstPopulation.end(); ++it) {
            if (it->getFitness() < bestGeneraFitness) {
                bestGeneraInd = new Individual(it->getTour(),tsp);
                bestGeneraFitness = bestGeneraInd->getFitness();
            }
        }
        
        // Log the result of this generation
        printGenerationDetails(i, lstPopulation, bestGeneraFitness,
                                      bestGeneraInd);
        
        if (bestGeneraFitness < bestFitness) {
            bestInd = bestGeneraInd;
            bestFitness = bestGeneraFitness;
            src = 0;
        }
        src++;
        if (src > max_gen_same_result) {
        	break;
        }
        if (Utilitis::elapsedTime() - start_time >= time_limit){
            break;
        }
    }
    
    printFinalResults(bestFitness, bestInd);
    
    return bestInd;
}