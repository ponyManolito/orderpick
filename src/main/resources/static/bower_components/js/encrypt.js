function encrypt(object){
	var key = CryptoJS.enc.Base64.parse('u/Gu5posvwDsXUnV5Zaq4g==');
	var iv = CryptoJS.enc.Base64.parse('5D9r9ZVzEYYgha93/aUK2w==');
	object.value =CryptoJS.AES.encrypt(object.value, key, { iv: iv });
}
