
// var RKReactServiceManager = require('NativeModules').ReactServiceManager;
var RKReactTTSService = require('NativeModules').ReactTTSService;

var ttsService ={
  tts:function(content,errorCalllback,successCallback){
    console.log("tts start");
    RKReactTTSService.tts(content,errorCalllback,successCallback);
  },

  ttsStop:function(errorCalllback,successCallback){
    console.log("tts stop");
    RKReactTTSService.ttsStop(errorCalllback,successCallback);
  },

  ttsIsSpeaking:function(resultCallback){
    console.log("ttsIsSpeaking");
    RKReactTTSService.isSpeaking(resultCallback);
  },

  playLastTTS:function(){
    console.log("playLastTTS");
    RKReactTTSService.playLastTTS();
  },

  volumeUp:function(){
    console.log("volumeUp");
    RKReactTTSService.volumeUp();
  },

  volumeDown:function(){
    console.log("volumeDown");
    RKReactTTSService.volumeDown();
  }

};

module.exports = ttsService;
