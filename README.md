experimentr
========

Experimentr is a backend for web-based visualization studies. 

Experiment modules are defined in `public/modules`.

Once modules are defined, they are loaded in order into the #experimentr div.
This div also contains controls (initially, only a Next button), to control experiment flow.
Eventually, more controls such as Previous and Quit should be added.

Also, sometimes the Next button is not needed, and it should be hidden until some sort of validation is completed.

Experimentr adds three elements to the page: 

- #experimentr div: to hold the module and controls
- #module div: holds module content
- #control div: holds controls for the modules

Currently, each module must be unique. Module re-use will be added in a later release.

Running the server
--------

Start redis: 

    redis-server

Run the server:

    sudo node app.js

Then access the page at [localhost](http://localhost).

Installation
-------

- Node.js: https://github.com/joyent/node/wiki/Installing-Node.js-via-package-manager#ubuntu-mint
- redis-server: http://redis.io/download
- bower: `npm install -g bower`
- fork this repo
- cd to this repo and run `npm install && bower install`

Testing experiments
-------

Use `debug` as your workerId when testing experiments, so you can filter out your data later
