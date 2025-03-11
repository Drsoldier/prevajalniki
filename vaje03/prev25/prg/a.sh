#!/bin/bash
cd ..
make
cd prg

while [[ -n $1 ]]; do
    cmd="test$1"
    make "$cmd"
    shift
done