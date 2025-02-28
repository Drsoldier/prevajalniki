#!/bin/bash
cd ..
make
cd prg
cmd=test$1
make $cmd