var app = angular.module('opinion');

app.filter('hashTags', function () {
    return function (input) {
        // The & sign is for not matching html codes
        return input.replace(/[^&]#(\S+)/g, ' <a href="#/hashtags/$1">#$1</a> ');
    };
});