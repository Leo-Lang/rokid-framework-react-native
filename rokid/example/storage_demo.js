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

import storageService from '../storage/asyncstorage';

class Storage_demo extends React.Component {

    render() {
      console.log("langneng render");
      return (
        <View style={styles.container}>
          <Text style={styles.welcome}>
            This is a storage demo!
          </Text>
        </View>
      );
    }

  componentWillMount() {
       storageService.saveData_async("langneng","langnengtest",(errorMsg)=>{
             console.log("langneng saveData_async error msg:"+errorMsg);
       },(successMsg)=>{
             console.log("langneng saveData_async success msg:"+successMsg);
       });

       console.log("langneng componentWillMount after saveData_sync");

       storageService.getData_async("langneng",(errorMsg)=>{
             console.log("langneng getData_async error msg:"+errorMsg);
       },(result)=>{
             console.log("langneng getData_async result:"+result);
       });

       storageService.deleteData_async("langneng",(errorMsg)=>{
             console.log("langneng deleteData_async error msg:"+errorMsg);
       },(successMsg)=>{
             console.log("langneng deleteData_async success msg:"+successMsg);
       });
      //  storageService.saveData_sync("langneng","langnengtest");

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

module.exports = Storage_demo;
