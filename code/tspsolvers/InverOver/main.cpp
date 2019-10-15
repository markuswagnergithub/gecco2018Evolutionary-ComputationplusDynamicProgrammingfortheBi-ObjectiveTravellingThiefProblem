//
//  main.cpp
//  InverOver
//
//  Created by Junhua on 23/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#include <iostream>
#include <limits>

#include "TSP.h"
#include "TSPReader.h"
#include "InverOverAlgorithm.h"
#include "Utilitis.h"


void print_usage() {
    printf("Usage: inverover <output file> <input file> <tries> <time limit> <population size> <generations> <max generations with same result>\n");
    exit(-1);
}

int main(int argc, const char * argv[]) {
    
    if (argc < 3) {
        print_usage();
    }
    

    int tries = 1;
    int populationSize = 50;
    int time_limit = 3600;
    long maxGenerations = std::numeric_limits<long>::max();
    int max_gen_same_result = 5000; 
    TSPReader tspreader;
    
    const char* outputfilename = argv[1];    // the output file
    TSP tsp = tspreader.readTSPFile(argv[2]); // the input file
    
    if (argc > 3) {
        tries = atoi(argv[3]);
    }
    
    if (argc > 4) {
        time_limit = atoi(argv[4]);
    }
    
    if (argc > 5) {
        populationSize = atoi(argv[5]);
    }
    
    if (argc > 6) {
        maxGenerations = atol(argv[6]);
    }
    
    if (argc > 7) {
    	max_gen_same_result = atoi(argv[7]);
    }

    Utilitis::seed();
    InverOverAlgorithm ioa;
    
    long total = 0;
    long best = std::numeric_limits<long>::max();
    Individual* bestInd = NULL;
    
    for (int i=0;i<tries;i++){
        std::cout << "try:" << i + 1 << std::endl;
        Individual* re = ioa.run(populationSize, time_limit, maxGenerations, max_gen_same_result, &tsp);
        if (re->getFitness() < best){
            best = re->getFitness();
            bestInd = re;
        }
        total += re->getFitness();
        bestInd->output(outputfilename);
    }
    
    std::cout << "Best Overall Fitness:" << best << std::endl;
    std::cout << "Average Overall Fitness:" << total/tries << std::endl;
    std::cout << "elapsed time(second):" << Utilitis::elapsedTime() << std::endl;
    
    
    
    return 0;
}

