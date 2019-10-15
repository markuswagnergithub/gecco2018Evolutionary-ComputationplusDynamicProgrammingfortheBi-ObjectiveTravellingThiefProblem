//
//  TSPReader.cpp
//  InverOver
//
//  Created by Junhua on 23/07/2015.
//  Copyright (c) 2015 Junhua. All rights reserved.
//

#include "TSPReader.h"

#include <fstream>
#include <sstream>
#include <iostream>
#include <algorithm>


TSP TSPReader::readTSPFile(const char* fileName)
{
    std::ifstream in(fileName);
    
    if (!in.good()) {
        in.close();
        std::cerr << "Cannot open the file " << fileName;
        exit(1);
    }
    
    std::map<std::string,std::string> headerData = readHeader(in);
    if(headerData.find("EDGE_WEIGHT_TYPE")->second == "EUC_2D"||
    		headerData.find("EDGE_WEIGHT_TYPE")->second == "CEIL_2D")
    {
        TSP tsp;
        tsp.name = headerData.find("NAME")->second;
        tsp.edgeWeightType = headerData.find("EDGE_WEIGHT_TYPE")->second;
        tsp.size = atoi( headerData.find("DIMENSION")->second.c_str());
        tsp.nodeList= ReadEuclidean2DFile(in);
        in.close();
        return tsp;
    }
    else
    {
        in.close();
        std::cerr << "Unsupported file type" << headerData.find("EDGE_WEIGHT_TYPE")->second;
        exit(1);
    }

}

std::map<std::string,std::string> TSPReader::readHeader(std::ifstream& input)
{
    
    std::string nameTag, name, typeTag, type, commentTag, comment,dimensionTag, dimension, edgeWightTypeTag, edgeWeightType, nodeCoordSectionTag;
    std::string line;
    std::map<std::string,std::string> mapHeaderData;
    do
    {
        char first;
        input >> first;
        input.putback(first);
        
        if(isdigit(first))
            break;
        
        std::getline(input,line);
        
        std::replace(line.begin(), line.end(), ':', ' ');
        std::stringstream instr(line);
        
        std::string key;
        std::string value;
        instr >> key >> value;
        mapHeaderData.insert(std::make_pair(key,value));
        
        
    }while(true);
    return mapHeaderData;
}

std::map<int, Node> TSPReader::ReadEuclidean2DFile(std::istream& in)
{
    //read file
    std::map<int, Node> locations;
    while(in.good())
    {
        Node currentCity;
        
        std::string city;

        in >> city >> currentCity.xPos >> currentCity.yPos;
        
        //currentCity.id++; //test
        //std::cout << "x:" << currentCity.xPos << " y:" << currentCity.yPos << std::endl;
        if(city != "EOF")
        {
            currentCity.id = atoi(city.c_str());
            locations.insert(std::make_pair(currentCity.id, currentCity));
        }
    }
    
    return locations;
}