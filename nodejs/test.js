// node C:\Local\Dev\node\test.js

console.log(__dirname);
console.log(__filename);

function f1() {
    console.trace('inside f1()');
    console.timeEnd('setTimeout test');
}

function f2() {
    f1();
}

function f3() {
    f2();
}

function f4() {
    f3();
}


console.time('setTimeout test');
setTimeout(f4, 100);

console.time('many elements');
for (var i = 0; i < 100000; i++) {
    var x = Math.sqrt(Math.random());
}
console.timeEnd('many elements');
