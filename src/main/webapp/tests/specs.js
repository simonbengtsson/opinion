describe('NetworkService tests', function () {

    var service = null;

    beforeEach(function () {
        var $injector = angular.injector(['ng', 'opinion']);
        service = $injector.get('NetworkService');
    });

    it('getPosts', function (done) {
        service.getPosts().then(function(res) {
            expect(res.length > 0).toBeTruthy();
            done();
        });
    });

});