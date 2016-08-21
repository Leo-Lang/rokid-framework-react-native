var React = require('react-native');
var{
  View
} = React;
var { requireNativeComponent,PropTypes} = require('react-native');

var iface = {
    name:'RKloading',
    propTypes:{
      ...View.propTypes,
      loadingStatus: PropTypes.string
    },
};

module.exports = requireNativeComponent('RKLoadingView',iface);
