cmake_minimum_required(VERSION 3.28)
project(basic_bilist)

set(CMAKE_CXX_STANDARD 17)

add_executable(basic_bilist main.cpp
        bilist.h
        bilist.cpp)
