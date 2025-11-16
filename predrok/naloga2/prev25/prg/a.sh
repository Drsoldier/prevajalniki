#!/bin/bash
cd ..
make clean all
cd prg

while [[ -n $1 ]]; do
    cmd="test$1"
    make "$cmd" OPTIONS=--dev-mode=on
    shift
done