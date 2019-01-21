rm -rf temp

javac -cp alloy4.2.jar Run_hw2_directedtree.java
echo Main-Class: Run_hw2_directedtree > MANIFEST.MF
echo "" >> MANIFEST.MF
cp alloy4.2.jar Run_hw2_directedtree.jar
jar ufm Run_hw2_directedtree.jar MANIFEST.MF *.class
rm MANIFEST.MF
rm *.class

mkdir temp
