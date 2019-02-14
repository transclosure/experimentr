/* global require:true, console:true, process:true, __dirname:true */
'use strict'

// Example run command: `node app.js 9000 6380 true`; listen on port 9000, connect to redis on 6380, debug printing on.

var express     = require('express')
  , http        = require('http')
  , redis       = require('redis')
  , redisClient
  , port        = process.argv[2] || 9100
  , rport       = process.argv[3] || 9000
  , debug       = process.argv[4] || null
  , exec        = require('child_process').exec
  , fs          = require('fs');

// Database setup
redisClient = redis.createClient(rport)

redisClient.on('connect', function() {
  console.log('Connected to redis.')
})

// Data handling
var save = function save(d) {
  redisClient.hmset(d.postId, {"content": JSON.stringify(d)})
  if( true )
    console.log('saved to redis: ' + d.postId +', at: '+ (new Date()).toString())
}

// Server setup
var app = express()
app.use(express.bodyParser())
app.use(express.static(__dirname + '/public'))
app.use('/scripts', express.static(__dirname + '/node_modules/'));

// If the study has finished, write the data to file
app.post('/finish', function(req, res) {
  fs.readFile('public/modules/consent/blocked-workers.json', 'utf8', function(err,data) {
    if (err) console.log(err);
    var data = JSON.parse(data);
    data.push(req.body.workerId);
    data = JSON.stringify(data);
    fs.writeFile('public/modules/consent/blocked-workers.json', data, function(err) {
      if(err) console.log(err);
    });
  });

  res.send(200)
})

// Handle POSTs from frontend
app.post('/', function handlePost(req, res) {
  // Get experiment data from request body
  var d = req.body
  // If a postId doesn't exist, add one (it's random, based on date)
  if (!d.postId) d.postId = (+new Date()).toString(36)
  // Add a timestamp
  d.timestamp = (new Date()).getTime()
  // Save the data to our database
  save(d)
  // Send a 'success' response to the frontend
  res.send(200)
})

// Create the server and tell which port to listen to
http.createServer(app).listen(port, function (err) {
  if (!err) console.log('Listening on port ' + port)
})

// check-ex-spec
app.get('/timdemo', function handlePost(req, res) {
  var ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
  ip = ip.replace(/:/g,'');
  var dir = process.cwd()+"/alloy/";
  exec("java -jar Run_checker.jar timdemo "+ip+" "+req.query.ex1+" "+req.query.ex2+" "+req.query.ex3+" "+req.query.ex4+" "+req.query.ex5, {cwd: dir}, function(error, stdout, stderr) {
    res.send(stdout);
  });
})

app.get('/hw2_directedtree', function handlePost(req, res) {
  var ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
  ip = ip.replace(/:/g,'');
  var dir = process.cwd()+"/alloy/";
  exec("java -jar Run_checker.jar hw2_directedtree "+ip+" "+req.query.ex1+" "+req.query.ex2+" "+req.query.ex3+" "+req.query.ex4+" "+req.query.ex5, {cwd: dir}, function(error, stdout, stderr) {
    res.send(stdout);
  });
})
