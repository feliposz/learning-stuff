;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname htdp_ch2.4) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
; Example
(define (reward s)
  (cond
    [(<= 0 s 10) "bronze"]
    [(and (< 10 s) (<= s 20)) "silver"]
    [else "gold"]))

;(reward 21)

; Exercise 40

; TrafficLight -> TrafficLight
; given state s, determine the next state of the traffic light

(check-expect (traffic-light-next "red") "green")
(check-expect (traffic-light-next "green") "yellow")
(check-expect (traffic-light-next "yellow") "red")

(define (traffic-light-next s)
  (cond
    [(string=? "red" s) "green"]
    [(string=? "green" s) "yellow"]
    [(string=? "yellow" s) "red"]))

; Exercise 41

(require 2htdp/universe)
(require 2htdp/image)

; TrafficLight -> Image
; Render a circle in an empty scene given the current TrafficLight state
(define (render-traffic-light s)
  (place-image (circle 40 "solid" s)
               50 50
               (empty-scene 100 100)))

;(big-bang "red"
;          (on-tick traffic-light-next)
;          (to-draw render-traffic-light))

; Exercise 43 ...


; A LRCD (short for: launching rocket count down) is one of:
; - "resting"
; - a number in [-3, -1]
; - a non-negative number
; interp. a rocket resting on the ground, in count-down mode,
;   or the height of an in-flighr rocket

; physical constants
(define HEIGHT 300)
(define WIDTH 100)
(define YDELTA 3)

; graphical constants
(define BACKG (empty-scene WIDTH HEIGHT))
(define ROCKET (rectangle 5 30 "solid" "red"))

; LRCD -> Image
; render the state as a resting or flying rocket

(check-expect
 (show "resting")
 (place-image ROCKET
              10 (- HEIGHT (/ (image-height ROCKET) 2))
              BACKG))

(check-expect
 (show -2)
 (place-image (text "-2" 20 "red")
              10 (* 3/4 WIDTH)
              (place-image ROCKET
                           10 (- HEIGHT (/ (image-height ROCKET) 2))
                           BACKG)))

(check-expect
 (show 53)
 (place-image ROCKET 10 53 BACKG))

(check-expect
 (show HEIGHT)
 (place-image ROCKET 10 HEIGHT BACKG))

(define (show x)
  BACKG)

; LRCD KeyEvent -> LRCD
; start the count-down when space bar is pressed,
; and the rocket is resting

(define (launch x)
  x)

; LRCD -> LRCD
; raise the rocket by YDELTA if it is moving already
(define (fly x)
  x)

