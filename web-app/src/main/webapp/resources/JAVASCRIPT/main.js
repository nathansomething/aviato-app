
var app = angular.module('aviatoApp', ['ui.bootstrap', 'ngAnimate'])

app.controller('InfoPane', function($scope) {

});

app.controller('AboutAviato', function($scope) {
  $scope.employees = ["Erlich_Bachman", "Nathan_Goodman", "Amal_Nazeem",
                      "Raghavprosanna", "Sahith_Nallapareddy"];
});
