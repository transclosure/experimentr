javac -cp alloy4.2.jar RunAlloy.java
echo Main-Class: RunAlloy > MANIFEST.MF
echo "" >> MANIFEST.MF
cp alloy4.2.jar RunAlloy.jar
jar ufm RunAlloy.jar MANIFEST.MF *.class
rm MANIFEST.MF
rm *.class

javac -cp Aluminum09.jar RunAluminum.java
echo Main-Class: RunAluminum > MANIFEST.MF
echo "" >> MANIFEST.MF
cp Aluminum09.jar RunAluminum.jar
jar ufm RunAluminum.jar MANIFEST.MF *.class
rm MANIFEST.MF
rm *.class
