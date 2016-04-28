echo "tims class data" > results/data.csv


node src/convert.js results/data.json 	modules/+GOC.html,modules/+AOC.html	+GOC +AOC >> results/data.csv
node src/convert.js results/data.json 	modules/+GOM.html,modules/+AOM.html     +GOM +AOM >> results/data.csv



