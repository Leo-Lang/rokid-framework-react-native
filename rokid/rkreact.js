var RKReactNative = {
    get ttsService(){ return require('./tts/rktts.js');},
    get redqueenService(){ return require('./cv/rkredqueen.js');},
    get confirmService(){ return require('./confirm/rkconfirm.js');}
};

module.exports = RKReactNative;
