;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname htdp_ufo) (read-case-sensitive #t) (teachpacks ((lib "image.ss" "teachpack" "2htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "image.ss" "teachpack" "2htdp")))))
(require 2htdp/universe)

; constants
(define WIDTH 200)
(define HEIGHT 400)
(define CENTER-X (/ WIDTH 2))
(define CENTER-Y (/ HEIGHT 2))
(define ROCK (rectangle (/ WIDTH 2) 10 "solid" "gray"))
(define SKY (rectangle WIDTH HEIGHT "solid" "cornflowerblue"))
(define UFO (overlay (circle 10 "solid" "green")
                     (rectangle 40 4 "solid" "green")))
(define MTSCN
  (place-image ROCK CENTER-X (- HEIGHT (/ (image-height ROCK) 2))
               (place-image SKY CENTER-X CENTER-Y
                            (empty-scene WIDTH HEIGHT))))
(define UFO-CENTER-TO-BOTTOM
  (-  HEIGHT (image-height ROCK) (/ (image-height UFO) 2)))

; programs
(define (create-ufo-scene h)
  (cond
    [(<= h UFO-CENTER-TO-BOTTOM)
     (place-image UFO CENTER-X h MTSCN)]
    [(> h UFO-CENTER-TO-BOTTOM)
     (place-image UFO CENTER-X UFO-CENTER-TO-BOTTOM MTSCN)]))

; tests
(animate create-ufo-scene)
