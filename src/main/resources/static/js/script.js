var btnHome = document.querySelector('input[value="Home"]');
var btnAdd = document.querySelector('input[value="Add"]');
var btnAbout = document.querySelector('input[value="About"]');

btnHome.addEventListener('click', function(evt) {
    evt.preventDefault();
    window.location.href = '/home.html'; 
});
btnAdd.addEventListener('click', function(evt) {
    evt.preventDefault();
    window.location.href = '/add.html'; 
});
btnAbout.addEventListener('click', function(evt) {
    evt.preventDefault();
    window.location.href = '/about.html'; 
});

var btnEdit = document.getElementById('edit');
var btnConfirm = document.getElementById('confirm');
var toHideEl = document.querySelectorAll('.to-hide > span');
var toHide = document.querySelectorAll('.to-hide')

var formName = document.getElementById('name');
var formWidth = document.getElementById('width');
var formHeight = document.getElementById('height');
var formColor = document.getElementById('colorInput');

btnEdit.addEventListener('click', function(evt) {
    evt.preventDefault();
    btnConfirm.setAttribute('type', 'submit');
    for (let i = 0; i < toHide.length; i++) {
        console.log(toHideEl[i].textContent);
        toHide[i].setAttribute('style', 'display: none;');
    }

    formName.setAttribute('type', 'name');
    formName.setAttribute('value', toHideEl[0].textContent);

    formWidth.setAttribute('type', 'number');
    formWidth.setAttribute('value', toHideEl[1].textContent);

    formHeight.setAttribute('type', 'number');
    formHeight.setAttribute('value', toHideEl[2].textContent);

    formColor.setAttribute('type', 'color');
    formColor.setAttribute('value', '#' + toHideEl[3].textContent)

})