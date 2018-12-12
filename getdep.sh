rm -rf public/components/codemirror
wget https://codemirror.net/codemirror.zip
unzip codemirror.zip
mv codemirror-5.42.0 public/components/codemirror
rm codemirror.zip

wget http://alloytools.org/download/alloy4.2_2015-02-22.jar
mv alloy4.2_2015-02-22.jar alloy/alloy4.2.jar

wget http://cs.brown.edu/research/plt/dl/aluminum/jar/Aluminum09.jar
mv Aluminum09.jar alloy/Aluminum09.jar
