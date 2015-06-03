var PI = Math.PI;

exports.quadrado = function (lado) {
    return lado*lado;
}

exports.cubo = function (lado) {
    return lado*lado*lado;
}

exports.areaCirculo = function (raio) {
    return PI * raio * raio;
}

exports.circunferencia = function (raio) {
    return 2 * PI * raio;
}
