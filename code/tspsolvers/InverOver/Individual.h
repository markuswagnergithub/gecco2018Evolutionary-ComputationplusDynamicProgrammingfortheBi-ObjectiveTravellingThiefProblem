//
//  Individual.h
//  InverOver
//
//  Created by Junhua on 28/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#ifndef __InverOver__Individual__
#define __InverOver__Individual__

#include "TSP.h"
#include <vector>

class Individual{
private:
    std::vector<int> _tour;
    long fitness;
    const TSP *_tsp;
    
    
public:
    Individual(const std::vector<int> &tour, const TSP* tsp);
    long calculateFitness() const;
    long getFitness();
    std::string toString() const;
    std::vector<int> getTour() const;
    int position(int city) const;
    bool isNeighborCity(int cityA, int cityB) const;
    void reverseCities(int cityA, int cityB);
    void output(const char* outputfilename);
};




class Population{
public:
    static std::vector<Individual> generateRandomPopulation(int populationSize, const TSP *tsp);
};

#endif /* defined(__InverOver__Individual__) */
