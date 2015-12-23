var j2c    = require('json2csv')
  , fs     = require('fs')
  , file   = process.argv[2]
  , _      = require('underscore')
  , fields = [ // EDIT THESE
	'postId',
	'workerId',
	'_start_f??OM',
	'_clicks_f??OM',
	'_clicktime_f??OM',
	'_clickorder_f??OM',
	'_scenariotime_f??OM',
	'_end_f??OM',
	'_time_f??OM',
	'_start_t??OM',
	'_clicks_t??OM',
	'_clicktime_t??OM',
	'_clickorder_t??OM',
	'_scenariotime_t??OM',
	'_end_t??OM',
	'_start_f??OM',
        '_clicks_f??OM',
        '_clicktime_f??OM',
        '_clickorder_f??OM',
        '_scenariotime_f??OM',
        '_end_f??OM',
	'_start_gomOM',
        '_clicks_gomOM',
        '_clicktime_gomOM',
        '_clickorder_gomOM',
        '_scenariotime_gomOM',
        '_end_gomOM',
	'_start_aomOM',
        '_clicks_aomOM',
        '_clicktime_aomOM',
        '_clickorder_aomOM',
        '_scenariotime_aomOM',
        '_end_aomOM',
	'_start_grnOM',
        '_clicks_grnOM',
        '_clicktime_grnOM',
        '_clickorder_grnOM',
        '_scenariotime_grnOM',
        '_end_grnOM',
	'_start_arnOM',
        '_clicks_arnOM',
        '_clicktime_arnOM',
        '_clickorder_arnOM',
        '_scenariotime_arnOM',
        '_end_arnOM',
	'comments'
    ]
  , data

fs.readFile(file, 'utf8', function (err, data) {
  if (err) console.log(err)

  data = JSON.parse(data)

  // filters any undefined data (it makes R scripting easier)
  data = filterUndefined(data)

  // use 'debug' for your workerId when testing experiments, 
  //   comment out if you want to analyze data from yourself
  data = filterDebug(data) 

  convert( data )
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
