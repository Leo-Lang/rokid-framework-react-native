import React, { Component } from 'react';
import  {
    AppRegistry,
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
    ListView,
    NativeModules
} from 'react-native';


//imports rokid modules
import RKReactNative from '../rkreact';

//var of status lable
var gestureDetected = false;


class CV_demo extends React.Component {


    componentDidMount() {
	console.log("laoliang componentDidMount");

	//set rokid intent listener
	this.keyboardHideObserver = DeviceEventEmitter.addListener('RNIntent', (intent) => {

                console.log("laoliang nlp intent:" + intent);
		//decode intent, which is a json string
                var nlp = JSON.parse(intent);
                if (nlp.intent === "reactsdk_gesture_test") {
		    //set 30s timeout, after that, reporting detecting result according to status lable - gestureDetected
                    setTimeout(() => {
			if(gestureDetected!==true){
			    console.log("gesture: not detected!");
			}else{
			    console.log("gesture: palm detected!");
			}
		    },30000);
		    //call function through imported interface of RKReactNative, activate palm gesture detection by call function
		    //of rokid sdk, meanwhile setting both error and success callback
		    RKReactNative.redqueenService.detectGesturePalm("react_gesture_test",
			(e)=>{console.log("laoliang: " + e+" gesture: failed to detect palm");},
			(e)=>{console.log("laoliang: " + e+" gesture: success in detecting palm");gestureDetected=true})

		}else {
                    console.log("laoliang intent is not reactsdk_gesture_test");
                }
            });


	var RKReactEventManager = React.NativeModules.ReactEventManager;
        console.log("langneng RKReactEventManager:" + RKReactEventManager);
        RKReactEventManager.notifyEventChannelReady(true);
        console.log("langneng before set listener");

    }

    render() {
      return (
	//set a default view to show basic information of this example on screen
        <View style={styles.container}>
          <Text style={styles.welcome}>
            This is a rokid cv gesture demo!
          </Text>
        </View>
      );
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
// export class CV_demo as a seperate module, so in index.android.js, call module
// name,then the whole class is executed
module.exports = CV_demo;
