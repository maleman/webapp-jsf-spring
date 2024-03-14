const params = {};
const urlParams = () => {
	const queryStr = window.location.search;
	const paramPairs = queryStr.substr(1).split('&');

	for (var i = 0; i < paramPairs.length; i++) {
		var parts = paramPairs[i].split('=');
		params[parts[0]] = parts[1];
	}
	return params;
};

const setError = () => {
	urlParams();
	if(params.hasOwnProperty('error')){
		const msgDiv = document.getElementById('login-error-msg');
		msgDiv.style.display = 'block';
	}

}

setError();
