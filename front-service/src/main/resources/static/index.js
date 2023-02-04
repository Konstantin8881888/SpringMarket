angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage)
{
    $scope.tryToAuth = function ()
    {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response)
            {
                if (response.data.token)
                {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response)
            {
            });
    };

    $scope.tryToLogout = function ()
    {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function ()
    {
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function ()
    {
        if ($localStorage.winterMarketUser)
        {
            return true;
        } else
        {
            return false;
        }
    };

    if ($localStorage.winterMarketUser)
    {
        try
        {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.winterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        }
        catch (e)
        {
        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
    }

    $scope.loadProducts = function ()
    {
        $http.get("http://localhost:5555/core/api/v1/products").then(function (response)
        {
            let products = response.data;
            console.log(products);

            console.log(products[0].title);
            $scope.productsList = products;
        });
    }

    // $scope.loadProducts();


    $scope.showProductInfo = function (productId)
    {
        $http.get("http://localhost:5555/core/api/v1/products/" + productId).then(function (response)
        {
            alert(response.data.title);
        });
    }

    // $scope.deleteProductById = function (productId)
    // {
    //     $http.delete("http://localhost:5555/core/api/v1/products/" + productId).then(function (response)
    //     {
    //         alert("Delete!");
    //         $scope.loadProducts();
    //     });
    // }

    $scope.loadCart = function ()
    {
        $http.get("http://localhost:5555/cart/api/v1/cart").then(function (response)
        {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function ()
    {
        $http.post("http://localhost:5555/core/api/v1/orders").then(function (response)
        {
            alert("Order created!");
            $scope.loadCart();
        });
    }

    $scope.addToCart = function (productId)
    {
        $http.get("http://localhost:5555/cart/api/v1/cart/add/" + productId).then(function (response)
        {
            alert("Продукт добавлен в корзину.");
            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function (productId)
    {
        $http.get("http://localhost:5555/cart/api/v1/cart/remove/" + productId).then(function (response)
        {
            alert("Продукт удалён из корзины.");
            $scope.loadCart();
        });
    }

    $scope.clearCart = function ()
    {
        $http.get("http://localhost:5555/cart/api/v1/cart/clear").then(function (response)
        {
            alert("Корзина очищена.");
            $scope.loadCart();
        });
    }

    $scope.loadProducts();
    $scope.loadCart();
});