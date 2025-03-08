id=63220285
imeMape=$1
cd "$imeMape/prev25"
make clean
cd ..
cd ..
cp -a ./$imeMape/. ./$id-$2/
zip -r "$id-$2" "$id-$2"

rm -r "$id-$2"