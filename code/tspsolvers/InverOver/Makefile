CXX = g++
CXXFLAGS = -O2 -ggdb -Wall -std=c++11
LDFLAGS = 

SOURCES := $(wildcard ./*.cpp) 

OBJECTS := $(patsubst %.cpp, %.o, ${SOURCES}) 

inverover: $(OBJECTS)
	$(CXX) $(CXXFLAGS) -o $@ $(OBJECTS) $(LDFLAGS)  

clean:
	-rm $(OBJECTS) inverover
