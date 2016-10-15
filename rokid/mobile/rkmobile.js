var NativeModules = require('react-native').NativeModules;
var RKMobileNative = NativeModules.MobileService;

var RKMobile = {
    sendMessageToMobile:function(msg){
      console.log("langneng sendMessageToMobile");
      console.log("langneng RKMobileNative:"+RKMobileNative);
       RKMobileNative.sendMessageToMobile(msg);
    }
};

module.exports = RKMobile;
