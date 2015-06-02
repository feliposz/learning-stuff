;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |2.2 variables and programs|) (read-case-sensitive #t) (teachpacks ((lib "convert.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "convert.rkt" "teachpack" "htdp")))))
;Exemplos de 2.2 Variables and Programs
(define (area-of-disk r)
  (* 3.14 (* r r)))

(define (area-of-ring outer inner)
  (- (area-of-disk outer)
     (area-of-disk inner)))

; Exercício 2.2.1

(define (F->C f)
  (/ (- f 32) 9/5))

(define (C->F c)
  (+ 32 (* c 9/5)))


;Carregar o teachpack convert.rkt

;(convert-gui F->C)
;(convert-repl F->C)
;(convert-file "in.dat" F->C "out.dat")

; Exercício 2.2.2

(define (dollar->euro d)
  (* d 1.3817))

; Exercício 2.2.3

(define (triangle-area b h)
  (* b h 0.5))

; Exercício 2.2.4

(define (convert3 a b c)
  (+ (* c 100)
     (* b 10)
     (* a 1)))

; Exercício 2.2.5

(define (f n)
  (+ (/ n 3) 2))

;(f 2) -> 2/3 + 2 = 2/3 + 6/3 = 8/3 = 2.66666...
;(f 5) -> 5/3 + 2 = 5/3 + 6/1 = 11/3 = 11.66666...
;(f 9) -> 9/3 + 2 = 3 + 2 = 5

(define (g n)
  (+ (* n n) 10))

;(g 2) -> 2*2 + 10 = 4 + 10 = 14
;(g 9) -> 9*9 + 10 = 81 + 10 = 91

(define (h n)
  (+ (* 1/2 n n) 20))

;(h 2) -> 2*2/2 + 20 = 22
;(h 9) -> 9*9/2 + 20 = 81/2 + 20 = 81/2 + 40/2 = 121/2 = 60.5

(define (i n)
  (- 2 (/ 1 n)))

;(i 2) -> 2 - 1/2 = 1.5
;(i 9) -> 2 - 1/9 = 18/9 - 1/9 = 17/9 = 1.888...
