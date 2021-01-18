$.ajaxSetup({
  contentType: "application/json; charset=utf-8"
});

$("#btnCancelRegister").click(function () {
    window.location.replace("index.html");
});

$("#btnCancelLogin").click(function () {
    window.location.replace("index.html");
});

$("#btnRegisterDone").click(function () {
    window.location.replace("index.html");
});

$("#btnRegisterOk").click(function () {
    window.location.replace("index.html");
});


$('#authType').click(function () {
    if ($('#authType:checked').length > 0) {
        $('#gmail-field').show();
        $('#googleauthlabel').hide();
        $('#emailauthlabel').show();
    } else {
        $('#gmail-field').hide();
        $('#googleauthlabel').show();
        $('#emailauthlabel').hide();
    }
})

var loginType = "";
$("#btnRegister").click(function () {
	if ($('#authType:checked').length > 0) {
        var inputValue = $("#login").val() + "/" + $("#gmail").val() ;
    } else {
        var inputValue = $("#login").val() + "/" +  "N";
    }
    $.post("/user/register/" + inputValue, JSON.stringify({"encryptedData": encrypt($("#password").val())}), function (data, status) {
        if (data != 'SEND_EMAIL_OTP') {        	 
            $("#tokenQr").attr("src", "https://zxing.org/w/chart?cht=qr&chs=250x250&chld=M&choe=UTF-8&chl=otpauth://totp/2FaExample.com?secret=" + data + "&issuer=2FaExample");
            $("#tokenValue").text(data);
            $("#modalRegister").modal('show');
        } else {
        	$('#modalEmailRegister').modal('show');
        }        
    },
);
});

$("#btnLogin").click(function () {
    $('#msgLoginFailed').hide();
    var inputValue =$("#login").val();    
    $.post("/authenticate/authenticateUser/" + inputValue, JSON.stringify({"encryptedData": encrypt($("#password").val())}), function (data, status) {
        if (data == 'AUTHENTICATED') {
            window.location.replace("secured.html");
        } else if (data == "SEND_EMAIL_OTP" || data == "SEND_GOTP") {
            loginType = data;
            $("#modalLoginCheckToken").modal('show');
        } else {
            $('#msgLoginFailed').show();
        }
    }).fail(function () {
        $('#msgLoginFailed').show();
    });
});

$("#btnTokenVerify").click(function () {
    $('#msgTokenCheckFailed').hide();
    if (loginType == "SEND_EMAIL_OTP") {
        tokenType = "otpTokenCheck";
    } else if (loginType == "SEND_GOTP") {
        tokenType = "gotpTokenCheck";
    } else {
        tokenType = "";
    }
    $.post("/authenticate/" + tokenType + "/" + $("#login").val() + "/" + $("#loginToken").val(), function (data, status) {
        if (data == 'AUTHENTICATED') {
            window.location.replace("secured.html");
        } else {
            $('#msgTokenCheckFailed').show();
        }
    }).fail(function () {
        $('#msgTokenCheckFailed').show();
    });
});

$("#btnLogout").click(function () {
    $.post("/authenticate/logout", function (data, status) {
        window.location.replace("index.html")
    });
});

//Add CryptoJS Script Dynamically

var script = document.createElement("script");
script.src = "https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js";
document.head.appendChild(script);

// Encryption function

const secretKey = "run4Fun";

function encrypt(value) {
    var encrypted = CryptoJS.AES.encrypt(value, secretKey);
    encrypted = encrypted.toString();
    console.log("Cipher text: " + encrypted)

    return encrypted;
}





// AES 

// var AesUtil = function (keySize, iterationCount) {
//     this.keySize = keySize / 32;
//     this.iterationCount = iterationCount;
// };

// AesUtil.prototype.generateKey = function (salt, passPhrase) {
//     var key = CryptoJS.PBKDF2(
//         passPhrase,
//         CryptoJS.enc.Hex.parse(salt),
//         { keySize: this.keySize, iterations: this.iterationCount });
//     return key;
// }

// AesUtil.prototype.encrypt = function (salt, iv, passPhrase, plainText) {
//     var key = this.generateKey(salt, passPhrase);
//     var encrypted = CryptoJS.AES.encrypt(
//         plainText,
//         key,
//         { iv: CryptoJS.enc.Hex.parse(iv) });
//     return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
// }

// AesUtil.prototype.decrypt = function (salt, iv, passPhrase, cipherText) {
//     var key = this.generateKey(salt, passPhrase);
//     var cipherParams = CryptoJS.lib.CipherParams.create({
//         ciphertext: CryptoJS.enc.Base64.parse(cipherText)
//     });
//     var decrypted = CryptoJS.AES.decrypt(
//         cipherParams,
//         key,
//         { iv: CryptoJS.enc.Hex.parse(iv) });
//     return decrypted.toString(CryptoJS.enc.Utf8);
// }


// Encryption function

// const secretKey = "1234567891234567";

// function encrypt(value) {
//     var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
//     var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);

//     var aesUtil = new AesUtil(128, 1000);
//     var ciphertext = aesUtil.encrypt(salt, iv, secretKey, value);

//     var aesEncrypted = (iv + "::" + salt + "::" + ciphertext);
//     var encrypted = btoa(aesEncrypted);

//     return encrypted;
// }

