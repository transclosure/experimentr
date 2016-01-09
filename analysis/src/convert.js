var j2c           = require('json2csv')
  , fs            = require('fs')
  , file          = process.argv[2]
  , experimentID  = process.argv[3]
  , formerTask    = process.argv[4]
  , latterTask    = process.argv[5]
  , _             = require('underscore')
  , fields        = getFields()
  , data;

function getFields() {
  var experimentFields = ['workerId','postId','experimentID','formerTask','latterTask'];
  var taskFields = [];
  for (taskID of [formerTask,latterTask]) {
    taskFields = taskFields.concat([  '_clicks_'+experimentID+'_'+taskID,
                                      '_time_'+experimentID+'_'+taskID,
                                      '_clickorder_'+experimentID+'_'+taskID,
                                      '_clicktime_'+experimentID+'_'+taskID,
                                      '_scenariotime_'+experimentID+'_'+taskID]);
  }
  return experimentFields.concat(taskFields);
}

fs.readFile(file, 'utf8', function (err, data) {
  if (err) console.log(err)

  data = JSON.parse(data)

  data = addConditions(data);

  // filters any undefined data (it makes R scripting easier)
  data = filterUndefined(data)

  // use 'debug' for your workerId when testing experiments, 
  //   comment out if you want to analyze data from yourself
  //data = filterDebug(data) 

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

function addConditions (arr) {
  return _.map(arr, function(row) {
    row.formerTask = formerTask;
    row.latterTask = latterTask;
    return row;
})
}
