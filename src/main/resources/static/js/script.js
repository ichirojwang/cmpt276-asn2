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

