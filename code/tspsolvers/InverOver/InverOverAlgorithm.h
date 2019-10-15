//
//  InverOverAlgorithm.h
//  InverOver
//
//  Created by Junhua on 24/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#ifndef __InverOver__InverOverAlgorithm__
#define __InverOver__InverOverAlgorithm__

#include "TSP.h"
#include "Individual.h"

class InverOverAlgorithm
{
public:
    Individual* run(const int populationSize, const int time_limit, const long generations, const int max_gen_same_result, const TSP* tsp);
};

#endif /* defined(__InverOver__InverOverAlgorithm__) */
