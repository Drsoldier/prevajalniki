id=63220285
imeMape=$1
rm "$id-$2.zip"
cd "$imeMape/prev25"
make clean
cd ..

zip -r "$id-$2" prev25
mv "$id-$2.zip" ..
cd ..