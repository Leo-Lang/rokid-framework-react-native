
'use strict';

const LumenService = require('NativeModules').LumenService;

var RKLumenService = {
	getRgb:function(colorIndex, colorType) {
    		return LumenService.getRgb(colorIndex, colorType);
	}

	attention:function(angle, flags) {
		LumenService.attention(angle, flags);
	}

	theme:function(color, type, mode, availFlag) {
		LumenService.theme(color, type, mode, availFlag);
	}

	brightness:function(mode, brightness) {
		LumenService.brightness(mode, brightness);
	}

	setBrightness:function(mode, value, showMe) {
		LumenService.setBrightness(mode, value, showMe);
	}

	hibernate:function() {
		LumenService.hibernate();
	}

	fetchTheme:function(mode) {
		return LumenService.fetchTheme();
	}

	fetchSystemBrightness:function() {
		return LumenService.fetchSystemBrightness();
	}

	drawLumen:function(color, brightness, layout, durationMillisec) {
		LumenService.drawLumen(color, brightness, layout, durationMillisec);
	}
};

module.exports = RKLumenService;
