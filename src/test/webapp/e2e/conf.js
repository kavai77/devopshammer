exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['json-spec.js', 'xml-spec.js', 'url-spec.js', 'base64-spec.js']
};