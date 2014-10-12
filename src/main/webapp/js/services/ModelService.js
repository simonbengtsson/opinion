var app = angular.module('hage');

app.service('ModelService', [function () {

    this.posts = [
        {content: 'This is a hage', hates: 100}
    ];
    this.users = [{ 
        firstname: 'Ada Lovelace', 
        posts: [
            {content: 'BILJETTKONTROLLANTER >:((((', hates: 23423}, 
            {content: 'When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home', hates: 1234}
            ],
        profilePic: 'http://cpuboss.com/blog/wp-content/uploads/2013/10/Wikipedia_ada-lovelace_FullyC_001.jpg',
        userInfo: 'H8 ppl - LUV c0mput3rz'
        }
    ];

}]);