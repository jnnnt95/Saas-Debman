var url = "public/php";

function changePasswordOnEnter(event) {
    if (event.keyCode == 13) {
        changePassword();
    }
}

function changePassword() {
    var newPass = document.getElementById('newPass').value;
    var email = document.getElementById('email').value;
    if (verifyFields()) {
        if (verifyPasswordCoincidence()) {
            if (verifyPasswordCorrespondence()) {
                var xhr;
                xhr = new XMLHttpRequest();

                xhr.open('POST', url + '/resetPassword.php', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        if (xhr.responseText == '1') {
                            alertSuccess();
                        }
                        else {
                            alertFailure(xhr.responseText);
                        }
                    }
                }
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.send("newPass=" + newPass + "&email=" + email);
            }
            else {
                alertNoCorrespondence();
            }
        }
        else {
            alertNoCoincidence();
        }
    }
    else {
        alertEmptyFields();
    }
}

function verifyPasswordCorrespondence() {
    var newPass = document.getElementById('newPass').value;
    return eval("/^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$/").test(newPass);
}

function verifyPasswordCoincidence() {
    var newPass = document.getElementById('newPass').value;
    var newPassRep = document.getElementById('newPassRep').value;
    return newPass == newPassRep
}

function alertNoCoincidence() {
    document.getElementById('alert').innerHTML = "<span style='color:red'>Las contraseñas no coinciden, inténtelo nuevamente</span>";
}

function alertSuccess() {
    document.getElementById('alert').innerHTML = "";
    document.getElementById('content').innerHTML = "<span style='color:green'>Contraseña reestablecida adecuadamente</span>";
}

function alertFailure(response) {
    document.getElementById('alert').innerHTML = `<span style='color:red'>La contraseña no pudo ser reestablecida: ${response} </span>`;
}

function verifyFields() {
    var email = document.getElementById('email').value;
    var newPass = document.getElementById('newPass').value;
    var newPassRep = document.getElementById('newPassRep').value;
    if (email == "") {
        return false;
    }
    if (newPass == "") {
        return false;
    }
    if (newPassRep == "") {
        return false;
    }
    return true;
}

function alertEmptyFields() {
    var emptyFieldsList = "";
    var email = document.getElementById('email').value;
    var newPass = document.getElementById('newPass').value;
    var newPassRep = document.getElementById('newPassRep').value;
    if (email == "") {
        if (emptyFieldsList != "") {
            emptyFieldsList += ", ";
        }
        emptyFieldsList += "'correo electrónico'";
    }
    if (newPass == "") {
        if (emptyFieldsList != "") {
            emptyFieldsList += ", ";
        }
        emptyFieldsList += "'nueva contraseña'";
    }
    if (newPassRep == "") {
        if (emptyFieldsList != "") {
            emptyFieldsList += ", ";
        }
        emptyFieldsList += "'repetir contraseña'";
    }
    var message = "<span style='color:red'>Se omitieron los campos <strong>" + emptyFieldsList + "</strong>, recuerde que todos ellos deben estar diligenciados.</span>";
    document.getElementById('alert').innerHTML = message;
}

function alertNoCorrespondence() {
    document.getElementById('alert').innerHTML = "<span style='color:red'>Contraseña inválida, no olvide tener en cuenta la <strong>nota</strong></span>";
}