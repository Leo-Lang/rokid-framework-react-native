/**
 * Copyright 2014-2016, Rokid Corporation Ltd.
 * All rights reserved.
 *
 * //This source code is licensed under the BSD-style license found in the
 * //LICENSE file in the root directory of this source tree. An additional grant
 * //of patent rights can be found in the PATENTS file in the same directory.
 *
 * @providesModule redqueenService
 * @flow
 */


//import rokid computer vision module RedqueenModule to local constant holder RKRedqueenService
const RKRedqueenService = require('NativeModules').RedqueenModule;

//re-organize RKRedqueenService features to build a react native service module with name of redqueenService 
var redqueenService ={

/*
  detectFaceId:function(callingName,errorCalllback,successCallback){
    console.log("detecting face id, called by " + callingName);
    RKRedqueenService.detectFaceId(callingName,errorCalllback,successCallback);
  },
*/
  //gesture single static palm detection
  detectGesturePalm:function(callingName,errorCalllback,successCallback){
    console.log("detecting gesture of single static palm, called by " + callingName);
    RKRedqueenService.detectGesturePalm(callingName,errorCalllback,successCallback);
  },

  //gesture motion of waving hand detection
  detectGestureWaveHand:function(callingName,errorCalllback,successCallback){
    console.log("detecting gesture motion of waving hand, called by " + callingName);
    RKRedqueenService.detectGestureWaveHand(callingName,errorCalllback,successCallback);
  },

  //gesture motion of palm to fist detection
  detectGesturePalm:function(callingName,errorCalllback,successCallback){
    console.log("detecting gesture motion of palm to fist, called by " + callingName);
    RKRedqueenService.detectGesturePalm2Fist(callingName,errorCalllback,successCallback);
  },

  //gesture motion of palm sliding detection, both slide to left and slide to right
  detectGesturePalmSlide:function(callingName,errorCalllback,successCallback){
    console.log("detecting gesture motion of palm sliding, both to left and to right, called by " + callingName);
    RKRedqueenService.detectGesturePalmSlide(callingName,errorCalllback,successCallback);
  },
  //gesture motion of palm sliding to left detection
  detectGesturePalmSlideLeft:function(callingName,errorCalllback,successCallback){
    console.log("detecting gesture motion of palm sliding to left, called by " + callingName);
    RKRedqueenService.detectGesturePalmSlideLeft(callingName,errorCalllback,successCallback);
  },

  //gesture motion of palm sliding to right detection
  detectGesturePalmSlideRight:function(callingName,errorCalllback,successCallback){
    console.log("detecting gesture of palm sliding to right, called by " + callingName);
    RKRedqueenService.detectGesturePalmSlideRight(callingName,errorCalllback,successCallback);
  },

};

//export the re-organized module
module.exports = redqueenService;
