angular.module('app', []).controller('indexController', function ($scope, $http)
{
$scope.loadProducts = function()
{
$http.get("http://localhost:8189/winter/api/v1/products").then(function(response)
{
let products = response.data;
console.log(products);

console.log(products[0].title);
$scope.productsList = products;
});
}

$scope.loadProducts();



$scope.showProductInfo = function(productId)
{
$http.get("http://localhost:8189/winter/api/v1/products/" + productId).then(function(response)
{
alert(response.data.title);
});
}

$scope.deleteProductById = function(productId)
{
$http.delete("http://localhost:8189/winter/api/v1/products/" + productId).then(function(response)
{
alert("Delete!");
$scope.loadProducts();
});
}
});