angular.module('student-app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/university/students/';

    $scope.loadStudents = function () {
        $http({
            url: contextPath,
            method: 'GET'
        }).then(function (response) {
            $scope.students = response.data;
        });
    };

    $scope.showInfoStudent = function (id) {
        $http({
            url: contextPath + id,
            method: 'GET'
        }).then(function successCallback(response) {
            alert("ID: " + response.data.id + " Название: " + response.data.name + " Оценка: " + response.data.mark);
        }, function failureCallback() {
            alert("Студент отсутствует");
        });
    };

    $scope.deleteStudent = function (id) {
        $http({
            url: contextPath + id,
            method: 'DELETE'
        }).then(function successCallback(response) {
            console.log(response)
            alert("Студент ID:" + response.data + " удален")
            $scope.loadStudents();
        });
    };

    $scope.decrement = function (id, mark) {
        $http({
            url: contextPath + id,
            method: 'POST',
            params: {
                mark: (mark - 1)
            }
        }).then(function successCallback() {
            $scope.loadStudents();
        }, function failureCallback() {
            alert("Оценка не может быть меньше 1");
        })
    };

    $scope.increment = function (id, mark) {
        $http({
            url: contextPath + id,
            method: 'POST',
            params: {
                mark: (mark + 1)
            }
        }).then(function successCallback() {
            $scope.loadStudents();
        }, function failureCallback() {
            alert("Оценка не может быть больше 5");
        })
    };

    $scope.createStudent = function () {
        if ($scope.new_student == null) {
            alert('Форма не заполнена');
            return;
        }
        $http({
            url: contextPath,
            method: 'PUT',
            params: {
                name: $scope.new_student.name,
                mark: $scope.new_student.mark
            }
        }).then(function successCallback(response) {
            $scope.new_student = null;
            alert('Студент успешно добавлен');
            $scope.loadStudents();
        }, function failureCallback(response) {
            alert(response.data.messages);
        });
    }

    $scope.disabledDecrement = function () {
        alert("Оценка не может быть меньше 1")
    }

    $scope.disabledIncrement = function () {
        alert("Оценка не может быть больше 5")
    }

    $scope.loadStudents();
});