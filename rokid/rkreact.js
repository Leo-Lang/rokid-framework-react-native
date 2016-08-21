var RKReactNative = {
    get ttsService(){ return require('./tts/rktts.js');},
    get redqueenService(){ return require('./cv/rkredqueen.js');},
};

module.exports = RKReactNative;
