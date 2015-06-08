$(function () {
    $('.picedit_input').picEdit({
        maxWidth: 300
    });
    $('.datetimepicker').datetimepicker({
        locale: 'ru',
        format: 'DD.MM.YYYY HH:mm'
    });

});

$(window).load(function () {
    $('.mosaic-pictures').gridify({
        srcNode: 'img', // grid items (class, node)
        margin: '20px', // margin in pixel, default: 0px
        width: '250px', // grid item width in pixel, default: 220px
        max_width: '', // dynamic gird item width if specified, (pixel)
        resizable: true, // re-layout if window resize
        transition: 'all 0.5s ease' // support transition for CSS3, default: all 0.5s ease
    });
});