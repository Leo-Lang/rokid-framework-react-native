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

import RKReactNative from '../rkreact';

class MobileDemo extends React.Component {

    render() {
      return (
        <View style={styles.container}>
          <Text style={styles.welcome}>
            This is a rokid mobile demo!
          </Text>
        </View>
      );
    }

    componentDidMount() {
          RKReactNative.mobileService.sendMessageToMobile("{\"event\":\"sendMessageToMobileTest\"}");
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

module.exports = MobileDemo;
