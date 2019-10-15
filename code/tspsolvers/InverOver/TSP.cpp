//
//  TSP.cpp
//  InverOver
//
//  Created by Junhua on 23/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#include "TSP.h"
#include <cmath>
#include <iostream>

double TSP::getDistance(int nodeA_id, int nodeB_id) const{
    Node n1 = nodeList.find(nodeA_id)->second;
    Node n2 = nodeList.find(nodeB_id)->second;
    
    //std::cout << "Node1:" << n1.id << " Node2:" << n2.id << std::endl;
    if (edgeWeightType == "EUC_2D")
    {
    	return round(sqrt(pow((n1.xPos - n2.xPos), 2)
                           + pow((n1.yPos - n2.yPos), 2)));
    }else{
    	return ceil(sqrt(pow((n1.xPos - n2.xPos), 2)
                           + pow((n1.yPos - n2.yPos), 2)));
    }
    
}