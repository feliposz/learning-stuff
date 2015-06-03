// node C:\Local\Dev\node\teste_modulos.js
var mostrar = require('./mostrar'),
    geometria = require('./geometria'),
    modpasta = require('./modpasta');
mostrar('teste');
console.log(geometria.quadrado(10));
console.log(geometria.cubo(10));
console.log(geometria.areaCirculo(10));
console.log(geometria.circunferencia(10));

['quadrado', 'cubo', 'areaCirculo', 'circunferencia'].map(function (n) {
    var fn = geometria[n];
    var v = 5;
    if (fn) {
        console.log('geometria.' + n + '(' + v + ') => ' + fn(v));
    }
});

modpasta.ola()