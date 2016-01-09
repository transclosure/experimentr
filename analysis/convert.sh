echo "workerId,postId,experimentID,formerTask,latterTask,formerClicks,formerTime,formerClickorder,formerClicktime,formerScenarioTime,latterClicks,latterTime,latterClickorder,latterClicktime,latterScenarioTime" > results/data.csv

node src/convert.js results/data.json 	GOM,AOM,GRC,ARC		teach	walkthrough 	>> results/data.csv
node src/convert.js results/data.json 	GOM,AOM,GRC,ARC		GOM		GRC 			>> results/data.csv
node src/convert.js results/data.json 	GOM,AOM,GRC,ARC		AOM		ARC 			>> results/data.csv

node src/convert.js results/data.json 	GRC,ARC,GOM,AOM		teach	walkthrough 	>> results/data.csv
node src/convert.js results/data.json 	GRC,ARC,GOM,AOM 	GOM		GRC 			>> results/data.csv
node src/convert.js results/data.json 	GRC,ARC,GOM,AOM 	AOM		ARC 			>> results/data.csv

node src/convert.js results/data.json 	GOC,AOC,GRM,ARM		teach	walkthrough 	>> results/data.csv
node src/convert.js results/data.json 	GOC,AOC,GRM,ARM 	GRM		GOC 			>> results/data.csv
node src/convert.js results/data.json 	GOC,AOC,GRM,ARM 	ARM		AOC 			>> results/data.csv

node src/convert.js results/data.json 	GRM,ARM,GOC,AOC		teach	walkthrough 	>> results/data.csv
node src/convert.js results/data.json 	GRM,ARM,GOC,AOC 	GRM		GOC 			>> results/data.csv
node src/convert.js results/data.json 	GRM,ARM,GOC,AOC 	ARM		AOC 			>> results/data.csv
