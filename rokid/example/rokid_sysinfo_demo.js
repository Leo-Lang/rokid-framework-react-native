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
//import RKReactNative from 'rokid-framework-react-native';

class Sysinfo_demo extends React.Component {


    componentDidMount() {
        console.log("laoliang componentDidMount");


        //call function through imported interface of RKReactNative, activate palm gesture detection by call function
        //of rokid sdk, meanwhile setting both error and success callback
        /*RKReactNative.redqueenService.detectGesturePalm(
            (e)=> {
                console.log("laoliang: " + e);
            },
            (e)=> {
                console.log("laoliang: " + e);
            })*/
        RKReactNative.sysinfoService.getRokidSystemInfo(
            (errorMsg)=> {
                console.log("laoliang: error message = " + errorMsg);
            },
            (successMsg)=> {
                console.log("laoliang: rokid sysinfo = " + successMsg);
            })

    }

    render() {
        return (
            //set a default view to show basic information of this example on screen
            <View style={styles.container}>
                <Text style={styles.welcome}>
                    This is a rokid sysinfo demo!
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
// export class Sysinfo_demo as a seperate module, so in index.android.js, call module
// name,then the whole class is executed
module.exports = Sysinfo_demo;
