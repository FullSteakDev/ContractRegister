var form = document.getElementById('form');

    form.addEventListener('mousemove', (e) =>{

        var x = (window.innerWidth / 2 - e.pageX) / 12;
        var y = (window.innerHeight / 2 - e.pageY) / 12;

        form.style.transform = 'rotateX(' + x + 'deg) rotateY(' + y + 'deg)';
    });
    form.addEventListener('mouseleave', function(){
        form.style.transform = 'rotateX(0deg) rotateY(0deg)';
    })
var logins = document.getElementById('loginpanel');

logins.addEventListener('mousemove', (e) =>{

    var x = (window.innerWidth / 2 - e.pageX) / 12;
    var y = (window.innerHeight / 2 - e.pageY) / 12;

    btn.style.transform = 'rotateX(' + x + 'deg) rotateY(' + y + 'deg)';
});
logins.addEventListener('mouseleave', function(){
    logins.style.transform = 'rotateX(0deg) rotateY(0deg)';
})
function checkform() {
        var theform = document.getElementById("theform");
        var why = "";

        if (theform.CaptchaInput.value == "") {
            why += "- Please Enter CAPTCHA Code.\n";
        }
        if (theform.CaptchaInput.value != "") {
            if (ValidCaptcha(theform.CaptchaInput.value) == false) {
                why += "- The CAPTCHA Code Does Not Match.\n";
            }
        }
        if (why != "") {
            alert(why);
            event.preventDefault();
            return false;
        }
        return true;
    }

    var a = Math.ceil(Math.random() * 9) + '';
    var b = Math.ceil(Math.random() * 9) + '';
    var c = Math.ceil(Math.random() * 9) + '';
    var d = Math.ceil(Math.random() * 9) + '';
    var e = Math.ceil(Math.random() * 9) + '';

    var code = a + b + c + d + e;
    document.getElementById("txtCaptcha").value = code;
    document.getElementById("CaptchaDiv").innerHTML = code;

    // Validate input against the generated number
    function ValidCaptcha() {
        var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
        var str2 = removeSpaces(document.getElementById('CaptchaInput').value);
        if (str1 == str2) {
            return true;
        } else {
            return false;
        }
    }

    // Remove the spaces from the entered and generated code
    function removeSpaces(string) {
        return string.split(' ').join('');
    }