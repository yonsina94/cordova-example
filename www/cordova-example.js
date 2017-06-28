var exec = require('cordova/exec');

function EchoPlugin() {};

EchoPlugin.prototype.echo = (options, success, error) => {
    options = options || {}; // (option != null) ? options : {}

    exec(success, error, "cordovaExample", "echo", [{ message: options.message || "" }]);
}


EchoPlugin.prototype.calculate = (operation, sucess, error) => {
    operation = operation || { operation: "suma", val1: 1, val2: 1 }

    exec(sucess, error, "cordovaExample", "calculate", [operation])
}


var echoPlugin = new EchoPlugin();
module.exports = echoPlugin;

/*exports.coolMethod = function(arg0, success, error) {
    exec(success, error, "cordova-example", "coolMethod", [arg0]);
}; */