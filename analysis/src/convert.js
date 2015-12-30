var j2c    = require('json2csv')
  , fs     = require('fs')
  , file   = process.argv[2]
  , spec   = process.argv[3]
  , phrase = process.argv[4]
  , scenario = process.argv[5]
  , _      = require('underscore')
  , fields = getFields(spec, phrase, scenario)
  , data

function getFields(spec, phrase, scenario) {
  var p1, p2, s1, s2;
  if(phrase == 'O') {
    p1 = 'o';
    p2 = 'r';
  } else {
    p1 = 'r';
    p2 = 'o';
  }
  if(scenario == 'M') {
    s1 = 'm';
    s2 = 'n';
  } else {
    s1 = 'n';
    s2 = 'm';
  }
  return [	'workerId', 
  	  	'postId',
		'spec',
		'phrase',
		'scenario',
		'_clicks_f??'+phrase+scenario,
		'_time_f??'+phrase+scenario,
		'_clicks_t??'+phrase+scenario,
		'_time_t??'+phrase+scenario,
		'_clicks_'+spec+p1+s1+phrase+scenario,
		'_time_'+spec+p2+s2+phrase+scenario ];

}

fs.readFile(file, 'utf8', function (err, data) {
  if (err) console.log(err)

  data = JSON.parse(data)

  data = addCondition(data)

  // filters any undefined data (it makes R scripting easier)
  data = filterUndefined(data)

  // use 'debug' for your workerId when testing experiments, 
  //   comment out if you want to analyze data from yourself
  data = filterDebug(data) 

  if (data.length > 0) convert( data )
})

function convert(d) {
  var params = {
    data: d,
    fields: fields
  }
  j2c(params, function(err, csv) {
    if (err) console.log(err)
    console.log(csv)
  })
}

function filterUndefined (arr) {
  return _.filter(arr, function(row) {
    return _.every(fields, function(f) { return row[f] })
  })
}

function filterDebug (arr) {
  return _.filter(arr, function(row) {
    return row.workerId !== 'debug'
  })
}

function addCondition (arr) {
  return _.map(arr, function(row) {
    row.spec = spec;
    row.phrase = phrase;
    row.scenario = scenario;
    return row;
  })
}

