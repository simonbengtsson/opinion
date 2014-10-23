var app = angular.module('hage');

app.service('ModelService', [function () {

    this.posts = [];
    this.user = null;

    this.isHated = function(post) {
        return this.user && this.indexOfUser(post.hatingUsers, this.user) !== -1;
    };

    this.indexOfUser = function(usersArr, user) {
        for(var i = 0; i < usersArr.length; i++) {
            if(usersArr[i].username === user.username) {
                return i;
            }
        }
        return -1;
    };

}]);