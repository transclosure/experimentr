javac -cp alloy4.2.jar RunAlloy.java
echo Main-Class: RunAlloy > MANIFEST.MF
echo "" >> MANIFEST.MF
cp alloy4.2.jar RunAlloy.jar
jar ufm RunAlloy.jar MANIFEST.MF *.class
rm MANIFEST.MF
rm *.class
