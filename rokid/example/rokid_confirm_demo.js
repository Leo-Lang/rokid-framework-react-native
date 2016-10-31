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

//import RKReactNative from 'rokid-framework-react-native';
import RKReactNative from '../rkreact'

class Confirm_demo extends React.Component{
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          This is a rokid confirm demo!
        </Text>
      </View>
    );
  }

  componentDidMount(){
    //  RKReactNative.confirmService.confirmContent("movie","tickets","请问你要订哪部电影的票","movie_name",(errorMsg)=>{
    //       console.log("langneng confirmContent errorMsg:"+errorMsg);
    //  },(successMsg)=>{
    //       console.log("langneng confirmContent successMsg:"+successMsg);
    //  });

    RKReactNative.confirmService.confirmIf("movie","buy","确定购买吗",{
    name: '谍影重重',
    time: '21:00'
    },(errorMsg)=>{
          console.log("langneng confirmIf errorMsg:"+errorMsg);
     },(successMsg)=>{
          console.log("langneng confirmIf successMsg:"+successMsg);
     });
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

module.exports = Confirm_demo;
