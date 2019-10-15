//
//  Utilitis.cpp
//  InverOver
//
//  Created by Junhua on 28/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#include "Utilitis.h"

#include <cstdlib>
#include <algorithm>
#include <ctime>

double Utilitis::elapsedTime(){
    return (double)clock()/CLOCKS_PER_SEC;
}

void Utilitis::seed(){
    srand ((int)time(NULL));
    //srand (11111111);
}

double Utilitis::randomProbability(){
    return ((double) rand() / RAND_MAX);
}

int Utilitis::randomInt(int max){
    return rand() % max;
}