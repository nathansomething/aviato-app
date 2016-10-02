
var app = angular.module('aviatoApp', ['ui.bootstrap', 'ngAnimate'])

app.controller('InfoPane', function($scope) {

});

app.controller('AboutAviato', function($scope) {
  $scope.employees = ["Erlich_Bachman", "Nathan_Goodman", "Amal_Nazeem",
                      "Raghavprosanna", "Sahith_Nallapareddy"];
});

app.controller('BookSession', function($scope) {

  $scope.calendar_shown = false;
  $scope.booking_form_page = 1;

  $scope.show_calendar = function() {
    $scope.calendar_shown = true;
  };

});
