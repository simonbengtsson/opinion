var app = angular.module('hage');

app.service('NetworkService', ['ModelService', '$http', 'API_URL', function (model, $http, API_URL) {

    this.posts = [
        {content: 'lskdjf'}
    ];

    this.getPosts = function () {
        return $http.get(API_URL + '/posts');
    };

}]);