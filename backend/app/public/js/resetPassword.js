var url="public/php";function changePasswordOnEnter(e){13==e.keyCode&&changePassword()}function changePassword(){var e,n=document.getElementById("newPass").value,t=document.getElementById("email").value;verifyFields()?verifyPasswordCoincidence()?verifyPasswordCorrespondence()?((e=new XMLHttpRequest).open("POST",url+"/resetPassword.php",!0),e.onreadystatechange=function(){4==e.readyState&&200==e.status&&("1"==e.responseText?alertSuccess():alertFailure(e.responseText))},e.setRequestHeader("Content-Type","application/x-www-form-urlencoded"),e.send("newPass="+n+"&email="+t)):alertNoCorrespondence():alertNoCoincidence():alertEmptyFields()}function verifyPasswordCorrespondence(){var newPass=document.getElementById("newPass").value;return eval("/^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$/").test(newPass)}function verifyPasswordCoincidence(){return document.getElementById("newPass").value==document.getElementById("newPassRep").value}function alertNoCoincidence(){document.getElementById("alert").innerHTML="<span style='color:red'>Las contraseñas no coinciden, inténtelo nuevamente</span>"}function alertSuccess(){document.getElementById("alert").innerHTML="",document.getElementById("content").innerHTML="<span style='color:green'>Contraseña reestablecida adecuadamente</span>"}function alertFailure(e){document.getElementById("alert").innerHTML=`<span style='color:red'>La contraseña no pudo ser reestablecida: ${e} </span>`}function verifyFields(){var e=document.getElementById("email").value,n=document.getElementById("newPass").value,t=document.getElementById("newPassRep").value;return""!=e&&(""!=n&&""!=t)}function alertEmptyFields(){var e="";""==document.getElementById("email").value&&(""!=e&&(e+=", "),e+="'correo electrónico'"),""==document.getElementById("newPass").value&&(""!=e&&(e+=", "),e+="'nueva contraseña'"),""==document.getElementById("newPassRep").value&&(""!=e&&(e+=", "),e+="'repetir contraseña'");var n="<span style='color:red'>Se omitieron los campos <strong>"+e+"</strong>, recuerde que todos ellos deben estar diligenciados.</span>";document.getElementById("alert").innerHTML=n}function alertNoCorrespondence(){document.getElementById("alert").innerHTML="<span style='color:red'>Contraseña inválida, no olvide tener en cuenta la <strong>nota</strong></span>"}