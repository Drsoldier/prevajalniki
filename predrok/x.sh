id=63220285
imeMape1="naloge1"
imeMape2="naloge2"
imeMape3="naloge3"

cd "$imeMape1/prev25"
make clean
cd ..
zip -r "$id-$imeMape1" prev25
mv "$id-$2.zip" ..
cd ..

cd "$imeMape2/prev25"
make clean
cd ..
zip -r "$id-$imeMape2" prev25
mv "$id-$2.zip" ..
cd ..

cd "$imeMape3/prev25"
make clean
cd ..

zip -r "$id-$imeMape3" prev25
mv "$id-$2.zip" ..
cd ..