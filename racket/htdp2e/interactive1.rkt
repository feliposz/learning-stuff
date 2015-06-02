;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname interactive1) (read-case-sensitive #t) (teachpacks ((lib "image.ss" "teachpack" "2htdp") (lib "batch-io.ss" "teachpack" "2htdp") (lib "universe.ss" "teachpack" "2htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "image.ss" "teachpack" "2htdp") (lib "batch-io.ss" "teachpack" "2htdp") (lib "universe.ss" "teachpack" "2htdp")))))
(define (render t)
  (place-image
   (overlay
    (text (number->string t) 12 "red")
    (circle (/ t 2) "outline" "black"))
   100 100
   (empty-scene 200 200)))
  

;(big-bang 1234567890 (to-draw render))

(big-bang 250
          (on-tick sub1)
          (to-draw render)
          (stop-when zero?))

