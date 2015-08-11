describe('json formatter', function () {
    beforeEach(function() {
        browser.get('http://localhost:8080/#/json');
    });

    it('should ask for input when visiting page first', function () {
        var messageVisitPageFirstTime = element(by.css('div.alert-grey'));

        expect(messageVisitPageFirstTime.isDisplayed()).toBeTruthy();
    });

    it('should say error message when invalid input', function () {
        var messageError = element(by.css('div.alert-danger'));

        browser.executeScript("var editor = $('.CodeMirror')[0].CodeMirror;editor.setValue('{');");

        element(by.id('formatJsonButtonId')).click();

        expect(messageError.isDisplayed()).toBeTruthy();
    });

    it('should say success message when valid input', function () {
        var messageError = element(by.css('div.alert-success'));

        browser.executeScript("var editor = $('.CodeMirror')[0].CodeMirror;editor.setValue('{}');");

        element(by.id('formatJsonButtonId')).click();

        expect(messageError.isDisplayed()).toBeTruthy();
    });
});




