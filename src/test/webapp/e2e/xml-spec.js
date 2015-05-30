describe('json formatter', function () {
    beforeEach(function() {
        browser.get('http://localhost:8080/#/xml');
    });

    it('should ask for input when visiting page first', function () {
        var messageVisitPageFirstTime = element(by.css('div.alert-grey'));

        expect(messageVisitPageFirstTime.isDisplayed()).toBeTruthy();
    });

    it('shuld say error message when empty input', function () {
        var messageError = element(by.css('div.alert-danger'));

        element(by.id('formatXmlButtonId')).click();

        expect(messageError.isDisplayed()).toBeTruthy();
    });
});




