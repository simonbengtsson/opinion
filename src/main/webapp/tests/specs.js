
describe('ModelService tests', function () {

    var service = null;

    beforeEach(function () {
        var $injector = angular.injector(['ng', 'hage']);
        service = $injector.get('ModelService');
    });

    it('Index of user', function () {
        var users = [
            {username: "user1"},
            {username: "user2"},
            {username: "user3"},
            {username: "user4"}
        ];
        expect(service.indexOfUser(users, {username: "user4"})).toEqual(3);
        expect(service.indexOfUser(users, {username: "user1"})).toEqual(0);
        expect(service.indexOfUser(users, {username: "user5"})).toEqual(-1);
    });
    
});