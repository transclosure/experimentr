rm -rf temp

javac -cp alloy4.2.jar RunSimple.java
echo Main-Class: RunSimple > MANIFEST.MF
echo "" >> MANIFEST.MF
cp alloy4.2.jar RunSimple.jar
jar ufm RunSimple.jar MANIFEST.MF *.class
rm MANIFEST.MF
rm *.class

mkdir temp
