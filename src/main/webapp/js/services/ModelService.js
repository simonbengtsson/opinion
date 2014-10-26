var app = angular.module('opinion');

app.service('ModelService', [
    function () {

        this.posts = [];
        this.trendingHashtags = [];
        this.featuredUsers = [];
        this.user = null;

        this.indexOfUser = function (usersArr, user) {
            if(!usersArr) return -1; 
            for (var i = 0; i < usersArr.length; i++) {
                if (usersArr[i].username === user.username) {
                    return i;
                }
            }
            return -1;
        };

        this.follow = function (user) {
            this.user.following.push(user);
            user.followers.push(this.user);
        };

    }
]);