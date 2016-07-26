//= wrapped

angular
    .module("blog.core")
    .factory("domainToManyConversion", domainToManyConversion);

function domainToManyConversion($injector) {
    var domainCache = {};
    return function(domainClass, property) {
        return function(domain) {
            if (domain[property] != null) {
                var Domain;
                if (!domainCache[domainClass]) {
                    domainCache[domainClass] = $injector.get(domainClass);
                }
                Domain = domainCache[domainClass];
                domain[property] = domain[property].map(function(obj) {
                    return new Domain(obj);
                });
            }
            return domain;
        };
    };
}