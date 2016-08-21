import React, {
    AppRegistry,
    Component,
    Animated,
    Easing,
    View,
    StyleSheet,
    Text,
    Image,
    PanResponder,
    DeviceEventEmitter,
    AppState,
    BackAndroid,
    ListView
} from 'react-native';

import RKReactNative from '../rkreact';

class TTS_demo extends React.Component {

    render() {
      return (
        <View style={styles.container}>
          <Text style={styles.welcome}>
            This is a rokid tts demo!
          </Text>
        </View>
      );
    }

    componentDidMount() {
        /*
         *this is the demo of tts
         */
        // RKReactNative.ttsService.tts("这是TTS的例子", (errorMsg) => {
        //     console.log("demo tts errormsg:" + errorMsg);
        // }, (callbackNum) => {
        //     console.log("demo tts callbackNum:" + callbackNum);
        // });

        /*
         * this is the demo of tts stop
         */
        // RKReactNative.ttsService.tts("这是TTS stop的例子,会被中断", (errorMsg) => {
        //     console.log("demo tts error msg:" + errorMsg);
        // }, (callbackNum) => {
        //     console.log("demo tts callbackNum:" + callbackNum);
        // });
        //
        //
        // setTimeout(()=>RKReactNative.ttsService.ttsStop((errorMsg) => {
        //     console.log("demo tts error msg:" + errorMsg);
        // }, (callbackNum) => {
        //     console.log("demo tts stop callback num :" + callbackNum);
        // }),2000);

          /*
           *  this is the demo of tts isSpeaking
           */
          // RKReactNative.ttsService.ttsIsSpeaking((isSpeaking)=>{
          //     console.log("demo tts isSpeaking?:"+isSpeaking);
          // });

          /*
           *  this is the demo of play last tts
           */
          // RKReactNative.ttsService.tts("这是重新播放上条TTS的例子", (errorMsg) => {
          //     console.log("demo tts error msg:" + errorMsg);
          // }, (callbackNum) => {
          //     console.log("demo tts callbackNum:" + callbackNum);
          //     RKReactNative.ttsService.playLastTTS();
          // });

          /*
           *  this is the demo of tts volume up
           */
          // RKReactNative.ttsService.tts("开始提高音量,这是提高TTS音量的例子", (errorMsg) => {
          //     console.log("demo tts error msg:" + errorMsg);
          // }, (callbackNum) => {
          //     console.log("demo tts callbackNum:" + callbackNum);
          // });
          // setTimeout(()=>RKReactNative.ttsService.volumeUp(),1000);

          /*
           *  this is the demo of tts volume down
           */
          RKReactNative.ttsService.tts("开始降低音量,这是降低TTS音量的例子", (errorMsg) => {
              console.log("demo tts error msg:" + errorMsg);
          }, (callbackNum) => {
              console.log("demo tts callbackNum:" + callbackNum);
          });
          setTimeout(()=>RKReactNative.ttsService.volumeDown(),1000);
    }


}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 26,
    textAlign: 'center',
    margin: 10,
  },

});

module.exports = TTS_demo;
