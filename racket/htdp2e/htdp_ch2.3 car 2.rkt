;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |htdp_ch2.3 car 2|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
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

(define BALL (circle 10 "solid" "blue"))

; AnimationState is a Number
; the number of clock ticks since the animation started
(define SomeAnimationState 0)


; Function definitions

; main : AnimationState -> AnimationState
; launch the program from some initial state
(define (main ws)
  (big-bang ws
            (on-tick tock)
            (to-draw render)
            (stop-when end?)))

; AnimationState -> AnimationState
; the clock ticked, increment the tick counter
(define (tock ws) (+ ws 1))
(check-expect (tock 0) 1)
(check-expect (tock 20) 21)
(check-expect (tock 78) 79)

; AnimationState String -> AnimationState
; reacts to key strokes
(define (ke ws str) ws)

; AnimationState Number Number String -> AnimationState
; reacts to mouse events
(define (me ws num1 num2 str) ws)

; AnimationState -> Image
; renders the scene to an image
(define (render ws)
  (place-ball-in-image ws (render-car ws)))

; AnimationState Image -> Image
; place a ball moving in a sine wave in the animation
(define (place-ball-in-image ws image)
  (place-image BALL
               (ball-x ws) (ball-y ws)
               image))

; AnimationState -> Number
; return, respectively the x and y coordinate for the ball
(define (ball-x ws) (- WORLD-WIDTH (* ws 3)))
(define (ball-y ws) (+ (/ WORLD-HEIGHT 2)
                       (* 10 (sin (deg2rad (* 20 ws))))))

; Number -> Number
; convert an angle in degrees to radians
(define (deg2rad angle) (* (/ angle 360.0) pi))

; AnimationState -> Image
(define (render-car ws)
  (place-image CAR (car-position ws) Y-CAR BACKGROUND))

; AnimationState -> Number
(define (car-position ws)
  (* ws 3))

; AnimationState -> Boolean
; stop when cara reaches end of the world
(define (end? ws)
  (> (car-position ws) WORLD-WIDTH))

; launch main
(main SomeAnimationState)