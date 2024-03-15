

const createUserButton = document.querySelector('.create-user-button');
const USER_NAME_PATTERN = /^[a-zA-Z]{1}[a-zA-Z0-9_-]{3,10}$/;
const PASSWORD_PATTERN = /(?=(.*[0-9]))(?=.*[\!@#$%^*?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}/;
const TIME_OUT_DISPLAY_MSG = 8000;


createUserButton.addEventListener('click', (event) => {
	event.preventDefault();

	const form = document.getElementById("new-user-form");
	const formData = new FormData(form);
	const formDataObj = Object.fromEntries(formData.entries());

	const userName = formDataObj['new-user-form:username-create-user'];
	const password = formDataObj['new-user-form:password-create-user'];
	
	

	if(!USER_NAME_PATTERN.test(userName)){
		const divsAdvc = document.getElementById('user-name-advice');
		divsAdvc.style.display = 'block';
		setTimeout(()=>{divsAdvc.style.display = 'none'}, TIME_OUT_DISPLAY_MSG);
	}
	
	if(!PASSWORD_PATTERN.test(password)){
		const divsAdvc = document.getElementById('user-pass-advice');
		divsAdvc.style.display = 'block';
		setTimeout(()=>{divsAdvc.style.display = 'none'}, TIME_OUT_DISPLAY_MSG);
	}

	return false;
});

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

urlParams();
console.log(params);
