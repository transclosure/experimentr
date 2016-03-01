echo "workerId,postId,experimentID,taskID,clicks,time,clickorder,clicktime,scenarioTime,why,honestly" > results/data.csv

node src/convert.js results/data.json 	walkthrough_prime,teach_prime,BRC,ORC,BOM,OOM  walkthrough_prime  >> results/data.csv
node src/convert.js results/data.json   walkthrough_prime,teach_prime,BRC,ORC,BOM,OOM  teach_prime  >> results/data.csv
node src/convert.js results/data.json   walkthrough_prime,teach_prime,BRC,ORC,BOM,OOM  BRC  >> results/data.csv
node src/convert.js results/data.json   walkthrough_prime,teach_prime,BRC,ORC,BOM,OOM  ORC  >> results/data.csv
node src/convert.js results/data.json   walkthrough_prime,teach_prime,BRC,ORC,BOM,OOM  BOM  >> results/data.csv
node src/convert.js results/data.json   walkthrough_prime,teach_prime,BRC,ORC,BOM,OOM  OOM  >> results/data.csv

