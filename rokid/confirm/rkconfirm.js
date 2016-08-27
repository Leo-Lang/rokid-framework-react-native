var RKReactConfirmService = require("NativeModules").ConfirmService;
                                     
var confirmService= {
    confirmContent:function(domain,intent,ttsContent,slot,errorCalllback,successCallback){
        console.log("langneng confirmService confirmContent");
        RKReactConfirmService.confirmContent(domain,intent,ttsContent,slot,errorCalllback,successCallback);
    },

    confirmIf:function(domain,intent,ttsContent,slots,errorCalllback,successCallback){
        console.log("langneng confirmService confirmIf");
        RKReactConfirmService.confirmIf(domain,intent,ttsContent,slots,errorCalllback,successCallback);
    }
};

module.exports = confirmService;
