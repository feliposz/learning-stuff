;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname convert-f2c) (read-case-sensitive #t) (teachpacks ((lib "image.ss" "teachpack" "2htdp") (lib "batch-io.ss" "teachpack" "2htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "image.ss" "teachpack" "2htdp") (lib "batch-io.ss" "teachpack" "2htdp")))))
(define (convert in out)
  (write-file out
              (number->string
               (f2c
                (string->number (read-file in))))))

(define (f2c f)
  (* 5/9 (- f 32)))

(convert "C:\\Dev\\Projects\\Racket\\sample.dat" "C:\\Dev\\Projects\\Racket\\out.dat")