//
//  TSP.h
//  InverOver
//
//  Created by Junhua on 23/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#ifndef __InverOver__TSP__
#define __InverOver__TSP__

#include <string>
#include <map>

class Node
{
public:
    int id;
    double xPos;
    double yPos;
    
};


class TSP
{
public:
    std::string name;
    std::string edgeWeightType;
    int size;
    std::map<int, Node> nodeList;

public:
    double getDistance(int nodeA_id, int nodeB_id) const;
    
};

#endif /* defined(__InverOver__TSP__) */
