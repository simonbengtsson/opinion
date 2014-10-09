var app = angular.module('hage');

app.service('ModelService', [function () {

    this.posts = [
        {content: 'This is a hage', hates: 100}
    ];
    this.users = [{ 
        firstname: 'Caroline', 
        posts: [
            {content: 'Jag hatar alla!!!', hates: 23}, 
            {content: '... när man försover sig :(:(:(:( :@ !!!', hates: 1234}
            ]
        }
    ];

}]);