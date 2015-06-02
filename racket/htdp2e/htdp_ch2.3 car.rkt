;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |htdp_ch2.3 car|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
(require 2htdp/image)
(require 2htdp/universe)

; 'physical' constants
(define WHEEL-RADIUS 5)
(define WHEEL-DISTANCE (* WHEEL-RADIUS 5))
(define BODY-LENGTH (+ WHEEL-DISTANCE (* 6 WHEEL-RADIUS)))
(define BODY-HEIGHT (* WHEEL-RADIUS 2))

; graphical constants
(define WHL (circle WHEEL-RADIUS "solid" "black"))
(define BDY
  (above
   (rectangle (/ BODY-LENGTH 2) (/ BODY-HEIGHT 2) "solid" "red")
   (rectangle BODY-LENGTH BODY-HEIGHT "solid" "red")))
(define SPC (rectangle WHEEL-DISTANCE 1 "solid" "white"))
(define WH* (beside WHL SPC WHL))
(define CAR (underlay/xy BDY
                         WHEEL-RADIUS BODY-HEIGHT
                         WH*))

(define TREE
  (underlay/xy (circle 10 "solid" "green")
               9 15
               (rectangle 2 20 "solid" "brown")))

(define WORLD-WIDTH 300)
(define WORLD-HEIGHT 50)
(define BACKGROUND
  (place-image TREE
               (/ WORLD-WIDTH 2)
               (- WORLD-HEIGHT (/ (image-height TREE) 2))
               (empty-scene WORLD-WIDTH WORLD-HEIGHT)))

(define Y-CAR (- WORLD-HEIGHT (/ (image-height CAR) 2)))

; CarState is a Number
; the number of pixels between the left border and the car
(define SomeCarState 0)


; Function definitions

; main : CarState -> CarState
; launch the program from some initial state
(define (main ws)
  (big-bang ws
            (on-tick tock)
            (to-draw render)
            (stop-when end?)))

; CarState -> CarState
; the clock ticked, move the car by three pixels
(define (tock ws) (+ ws 3))
(check-expect (tock 0) 3)
(check-expect (tock 20) 23)
(check-expect (tock 78) 81)

; CarState String -> CarState
; reacts to key strokes
(define (ke ws str) ws)

; CarState Number Number String -> CarState
; reacts to mouse events
(define (me ws num1 num2 str) ws)

; CarState -> Image
; renders the scene to an image
(define (render ws)
  (place-car ws))

; CarState -> Image
(define (place-car ws)
  (place-image CAR ws Y-CAR BACKGROUND))

; CarState -> Boolean
; stop when cara reaches end of the world
(define (end? ws)
  (> ws WORLD-WIDTH))

; launch main
(main SomeCarState)