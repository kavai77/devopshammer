describe('json formatter', function () {
    beforeEach(function() {
        browser.get('http://localhost:8080/#/json');
    });

    it('should ask for input when visiting page first', function () {
        var messageVisitPageFirstTime = element(by.css('div.alert-grey'));

        expect(messageVisitPageFirstTime.isDisplayed()).toBeTruthy();
    });

    it('shuld say error message when empty input', function () {
        var messageError = element(by.css('div.alert-danger'));

        element(by.id('formatJsonButtonId')).click();

        expect(messageError.isDisplayed()).toBeTruthy();
    });

    //TODO: how to pass input to ui-codemirror????
/*    it('shuld say error message when invalid input', function () {
        var messageError = element(by.css('div.alert-danger'));

        element(by.css('padding-right: 0.1px;')).sendKeys('{');

        element(by.id('formatJsonButtonId')).click();

        expect(messageError.isDisplayed()).toBeTruthy();
    });*/
});




