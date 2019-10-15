//
//  TSPReader.h
//  InverOver
//
//  Created by Junhua on 23/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#ifndef __InverOver__TSPReader__
#define __InverOver__TSPReader__

#include "TSP.h"
#include <map>

class TSPReader
{
public:
    TSP readTSPFile(const char* filename);

private:
    std::map<std::string,std::string> readHeader(std::ifstream& input);
    std::map<int, Node> ReadEuclidean2DFile(std::istream& input);
};

#endif /* defined(__InverOver__TSPReader__) */
