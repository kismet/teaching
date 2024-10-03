cmake_minimum_required(VERSION 3.28)
project(basic_list)

set(CMAKE_CXX_STANDARD 17)

add_executable(basic_list main.cpp
        simple_list.h
        simple_list.cpp)
