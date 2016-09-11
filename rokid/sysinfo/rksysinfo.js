var RKSysinfoService = require("NativeModules").SysinfoService;

var sysinfoService= {
    getRokidSystemInfo:function(errorCallback,successCallback){
        console.log("laoliang getting system info");
        RKSysinfoService.getRokidSystemInfo(errorCallback,successCallback);
    }
};

module.exports = sysinfoService;
