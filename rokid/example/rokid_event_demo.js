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


class Event_demo extends React.Component {

    render() {
      return (
        <View style={styles.container}>
          <Text style={styles.welcome}>
            This is a rokid event receive demo!
          </Text>
        </View>
      );
    }

    componentDidMount() {
      var RKReactEventManager = NativeModules.ReactEventManager;
      this.keyboardHideObserver = DeviceEventEmitter.addListener(RKReactEventManager.RKIntent, (intent) => {
              console.log("langneng js receive rokid event intent:"+intent);
          });
      RKReactEventManager.notifyEventChannelReady(true);
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

module.exports = Event_demo;
