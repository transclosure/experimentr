var id    = null;
var workers = null;

init();

function init() {
  experimentr.hideNext();

  // load previous workers file
  d3.json('modules/consent/blocked-workers.json', function(err, d) {
    workers = d;
    d3.select('#workerId').attr('disabled', null);
  });

  d3.selectAll('#workerId')
    .on('keypress', function() { id = this.value.trim().toUpperCase(); })
    .on('blur', function() { id = this.value.trim().toUpperCase(); });

  d3.select('#consentYes').on('click', experimentr.next);

  d3.select('#checkId').on('click', validate);
}

function validate() {
  if( id ) {
    experimentr.addData(data);

    if( workers.indexOf(id) == -1 ) {
      d3.select('#consentYes').attr('disabled', null);
      data["id"] = id;
    } else {
      d3.select('#invalidMessage').style('display', 'inline');
    }
  }
}
