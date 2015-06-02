#lang slideshow
(require slideshow/flash)
(require slideshow/code)

(define c (circle 10))
(define r (rectangle 10 20))

(define (square n)
  (filled-rectangle n n ))

(define (four p)
  (define two-p (hc-append p p ))
  (vc-append two-p two-p))

(define (checker p1 p2)
  (let ([p12 (hc-append p1 p2)]
        [p21 (hc-append p2 p1)])
    (vc-append p12 p21)))

(define (checkerboard p)
  (let* ([rb (colorize p "red")]
         [br (colorize p "black")]
         [c (checker rb br) ]
         [c4 (four c)])
    (four c4)))

(define (series mk)
  (hc-append 5 (mk 5) (mk 10) (mk 20)))

(define series-alt
  (lambda (mk)
    (hc-append 5 (mk 5) (mk 10) (mk 20))))

(define (rgb-series mk)
  (vc-append
   (series (lambda (sz) (colorize (mk sz) "red")))
   (series (lambda (sz) (colorize (mk sz) "green")))
   (series (lambda (sz) (colorize (mk sz) "blue")))))

(define (my-colored-series mk)
  (apply vc-append
         (map (lambda (color)
                (series (lambda (sz) (colorize (mk sz) color))))
              (list "red" "green" "blue" "yellow" "black"))))

(define (cmyk-maker mk)
  (lambda (sz)
    (vc-append (colorize (mk sz) "cyan")
               (colorize (mk sz) "magenta")
               (colorize (mk sz) "yellow")
               (colorize (mk sz) "black"))))

(define (rainbow p)
  (map (lambda (color)
         (colorize p color))
       (list "red" "orange" "yellow" "green" "blue" "purple")))


;(checkerboard (disk 35))
;(apply vc-append (cons 20 (rainbow (disk 30))))

(provide rainbow square checkerboard)