//
//  Individual.cpp
//  InverOver
//
//  Created by Junhua on 28/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#include "Individual.h"
#include "Utilitis.h"

#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
#include <unistd.h>

Individual::Individual(const std::vector<int> &tour, const TSP* tsp){
    fitness = -1;
    for (auto it = tour.begin(); it != tour.end(); ++it){
        _tour.push_back(*it);
    }
    
    _tsp = tsp;
    
}

long Individual::calculateFitness() const{
    double totalDistance = 0;
    for (unsigned int i = 0; i < _tour.size() - 1; i++) {
        totalDistance += _tsp->getDistance(_tour[i], _tour[i + 1]);
    }
    totalDistance += _tsp->getDistance(_tour[_tour.size() - 1], _tour[0]);
    
    return totalDistance;
}


long Individual::getFitness(){
    // Check that we have not already evaluated this individual.
    if (fitness >= 0) {
        return fitness;
    }
    
    // Cache this fitness to avoid doing many evaluations.
    fitness = calculateFitness();
    return fitness;
    
}

std::vector<int> Individual::getTour() const{
    return _tour;
}

int Individual::position(int city) const{
    for (unsigned int i=0;i<_tour.size();i++){
        if (_tour[i] == city)
            return i;
    }
    return -1;
}

bool Individual::isNeighborCity(int cityA, int cityB) const{
    
    int posA = position(cityA);
    int posB = position(cityB);
    
    if ( posA != -1 && posB != -1 && abs(posA - posB) == 1){
        return true;
    }else{
        return false;
    }
}

void Individual::reverseCities(int cityA, int cityB){
    int posA = position(cityA);
    int posB = position(cityB);
    
    //    if (posA == -1 || posA >= (posB - 1) || posA >= (_tour.size() - 2)
    //        || posB >= (_tour.size() - 1) || posB == -1){
    //        return;
    //    }
    
    std::reverse(_tour.begin()+ posA + 1, _tour.begin() + posB + 1);
    
}

std::string Individual::toString() const{
    std::stringstream sstream;
    sstream << "Individual{\n\tfitness:";
    sstream << fitness;
    sstream << "\n\ttour:";
    for (auto it = _tour.begin(); it != _tour.end(); ++it){
        sstream << *it << ' ';
    }
    sstream << "}\n";
    
    return sstream.str();
}


void Individual::output(const char* outputfilename){
	std::ofstream fout(outputfilename);
	fout << "Tour length " << fitness << std::endl;
	for (auto it = _tour.begin(); it != _tour.end(); ++it){
        fout << *it << ' ';
    }
	fout << std::endl;
	fout.close();
}


std::vector<int> generateRandomSequence(const TSP *tsp) {
    
    std::vector<int> res;
    for (auto it = tsp->nodeList.begin(); it != tsp->nodeList.end(); ++it) {
        res.push_back(it->first);
    }
    
    std::random_shuffle(res.begin(),res.end());
    
    return res;
    
}

std::vector<Individual> Population::generateRandomPopulation(int populationSize, const TSP *tsp){
    std::vector<Individual> individuals;
    
    for (int i = 0; i < populationSize; i++) {
        Individual ind(generateRandomSequence(tsp), tsp);
        individuals.push_back(ind);
    };
    
    return individuals;
};

