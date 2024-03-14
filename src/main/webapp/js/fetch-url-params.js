
const urlParams = () => {
	const queryStr = window.location.search;
	const paramPairs = queryStr.substr(1).split('&');
	const params = {};
	for (var i = 0; i < paramPairs.length; i++) {
		var parts = paramPairs[i].split('=');
		params[parts[0]] = parts[1];
	}
	return params;
};