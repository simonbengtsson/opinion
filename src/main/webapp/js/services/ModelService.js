var app = angular.module('hage');

app.service('ModelService', ['NetworkService', function (network) {

    this.posts = [
        {content: 'Long long long long long long long long long long long long long long long long long long long long long long long hate text for testing layout for long(er) text', hates: []},
        {content: "BILJETTKONTROLLANTER >:((((", hates: []},
        {content: "When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home", hates: []},
        {content: "Hello world! :D ..... not.", hates: []}
    ];

    this.user = {
        id: 1,
        firstname: 'Britney',
        lastname: 'Spears',
        posts: [
            {content: 'long long long hate text for testing layout for long(er) text', hates: 0},
            {content: "BILJETTKONTROLLANTER >:((((", hates: 23423},
            {content: "When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home", hates: 1234},
            {content: "Hello world! :D ..... not.", hates: 52}
        ],
        profilePic: 'http://blog.ramedepat.ro/wp-content/uploads/2014/08/britnez-sp.jpg'
    };

    // @deprecated
    this.users = [
        {
            firstname: 'Ada Lovelace',
            posts: [
                {content: 'Long long long long long long long long long long long long long long long long long long long long long long long hate text for testing layout for long(er) text', hates: 0},
                {content: "BILJETTKONTROLLANTER >:((((", hates: 23423},
                {content: "When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home", hates: 1234},
                {content: "Hello world! :D ..... not.", hates: 52}
            ],
            profilePic: 'http://cpuboss.com/blog/wp-content/uploads/2013/10/Wikipedia_ada-lovelace_FullyC_001.jpg',
            userInfo: 'H8 ppl - LUV c0mput3rz'
        }
    ];

    this.hate = function (post) {
        console.log('Post hated: ' + post);
        //network.createHate(post);
    }

}]);