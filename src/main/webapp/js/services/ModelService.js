var app = angular.module('hage');

app.service('ModelService', [function () {

    this.posts = [];
    this.trendingHashtags = [];
    this.featuredUsers = [];
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
    
    this.follow= function(user) {
        this.user.following.push(user);
        user.followers.push(this.user);
    };

}]);