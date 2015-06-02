;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |2.3 word problems|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;2.3 Word Problems

;Ex 2.3.1

(define (tax v)
  (* v 15/100))

(define (grosspay h)
  (* h 12))

(define (netpay h)
  (- (grosspay h) (tax (grosspay h))))

;Ex 2.3.2

(define (sum-coins p n d q)
  (+ (* p 0.01)
     (* n 0.05)
     (* d 0.10)
     (* q 0.25)))

; Ex 2.3.3

(define (tickets-sold a)
  (* a 5.00))

(define (show-cost a)
  (+ 20 (* a 0.50)))

(define (total-profit a)
  (- (tickets-sold a) (show-cost a)))

