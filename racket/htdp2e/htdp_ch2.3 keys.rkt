;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |htdp_ch2.3 keys|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
(require 2htdp/image)
(require 2htdp/universe)

; AllKeys is a String.
; interp. the sequence of keys pressed since big-bang created the canvas

; physical constants:
(define WIDTH 100)
(define HEIGHT 50)

(define MID-X (/ WIDTH 2))
(define MID-Y (/ HEIGHT 2))

; graphical constants:
(define MT (empty-scene WIDTH HEIGHT))

; AllKeys String -> AllKeys
; add ke (key event) to ak (all keys), the state of the world

(check-expect (remember "hello" " ") "hello ")
(check-expect (remember "hello " "w") "hello w")
(check-expect (remember "test" "\r") "test")
(check-expect (remember "test" "\t") "test")
(check-expect (remember "test" "\n") "test")

; Exercise 35
(define (remember ak ke)
  (cond [(string=? ke "\r") ak]
        [(string=? ke "\n") ak]
        [(string=? ke "\t") ak]
        [else (string-append ak ke)]))

; AllKeys -> Image
; render the ak string as a text (image) and place it into MT

(check-expect (show "hello")
              (place-image (text "hello" 11 "red") MID-X MID-Y MT))
(check-expect (show "mark")
              (place-image (text "mark" 11 "red") MID-X MID-Y MT))

(define (show ak)
  (place-image (text ak 11 "red") MID-X MID-Y MT))

(big-bang "" (on-key remember) (to-draw show))