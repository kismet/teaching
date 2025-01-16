cmake_minimum_required(VERSION 3.28)
project(tree)

set(CMAKE_CXX_STANDARD 17)

add_executable(tree main.cpp
        binary_tree.h
        binary_tree.cpp)
