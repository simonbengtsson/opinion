var app = angular.module('opinion');

/**
 * Parses a string and makes links out of hashtags
 */
app.filter('hashTags', function () {
    return function (input) {
        // The & sign is for NOT matching html codes
        return input.replace(/[^&]#(\S+)/g, ' <a href="#/hashtags/$1">#$1</a> ');
    };
});