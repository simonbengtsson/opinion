var app = angular.module('hage');

app.service('ModelService', [function () {

    this.posts = [
        {content: 'This is a hage', hates: 100}
    ];

}]);