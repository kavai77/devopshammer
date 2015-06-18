// module
var devOpsHammer = angular.module('devOpsHammer', ['ngRoute', 'ui.codemirror', 'atSequenceDiagram']);

app.config(["sequenceDiagramProvider", "$sceProvider", function(sequenceProvider, $sceProvider){
    // set common option.
    sequenceProvider.option({theme: "hand"});

    // enable language-sequence directive.
    sequenceProvider.enableLanguageDirective();
}]);