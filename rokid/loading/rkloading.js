import { PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

var iface = {
    name:'RKloading',
    propTypes:{
      ...View.propTypes,
      loadingStatus: PropTypes.string
    },
};

module.exports = requireNativeComponent('RKLoadingView',iface);
