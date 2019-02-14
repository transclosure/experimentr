rm -rf temp

javac -cp alloy4.2.jar Run_checker.java
echo Main-Class: Run_checker > MANIFEST.MF
echo "" >> MANIFEST.MF
cp alloy4.2.jar Run_checker.jar
jar ufm Run_checker.jar MANIFEST.MF *.class
rm MANIFEST.MF
rm *.class

mkdir temp
