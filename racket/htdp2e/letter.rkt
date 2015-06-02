;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname letter) (read-case-sensitive #t) (teachpacks ((lib "image.ss" "teachpack" "2htdp") (lib "batch-io.ss" "teachpack" "2htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "image.ss" "teachpack" "2htdp") (lib "batch-io.ss" "teachpack" "2htdp")))))
(define (main fst lst signature-name)
  (write-file (string-append fst "-" lst ".txt")
              (letter fst lst signature-name)))

(define (letter fst lst signature-name)
  (string-append
   (opening fst)
   "\n"
   (body fst lst)
   "\n"
   (closing signature-name)))

(define (opening fst)
  (string-append "Dear " fst ","))

(define (body fst lst)
  (string-append
   "we have discovered that all people with the last name "
   "\n"
   lst " have won our lottery. So, " fst ", "
   "\n"
   "hurry and pick up your prize."))

(define (closing signature-name)
  (string-append
   "Sincerely,"
   "\n"
   signature-name))
