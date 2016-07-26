//= wrapped

angular
    .module("blog.core")
    .factory("domainConversion", domainConversion);

function domainConversion($injector) {
    var domainCache = {};
    return function(domainClass, property) {
        return function(domain) {
            var Domain;
            if (!domainCache[domainClass]) {
                domainCache[domainClass] = $injector.get(domainClass);
            }
            Domain = domainCache[domainClass];
            domain[property] = new Domain(domain[property]);
            return domain;
        };
    };
}