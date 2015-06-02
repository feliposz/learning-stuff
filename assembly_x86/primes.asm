;====================================================================
;primes.asm 
;Descrição: Programa de exemplo que gera números primos
;Fonte: Baseado no livro PC Assembly Language de Paul Carter
;Data: 04/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 first.asm
;
;Para gerar o executável (testado com o MingW):
;gcc -o primes.exe primes.obj asm_io.obj driver.c
;
;Dependências: asm_io.inc, asm_io.obj, driver.c
;
;Funcionamento: Consulte código equivalente em linguagem C
;====================================================================

; unsigned guess; /* current guess for prime */
; unsigned factor ; /* possible factor of guess */
; unsigned limit ; /* find primes up to this value */
; printf (”Find primes up to : ”);
; scanf(”%u”, &limit);
; printf (”2\n”); /* treat first two primes as */
; printf (”3\n”); /* special case */
; guess = 5; /* initial guess */
; while ( guess <= limit ) {
; 	/* look for a factor of guess */
;	 factor = 3;
;	 while ( factor *factor < guess &&
;		 guess % factor != 0 )
;		 factor += 2;
;	 if ( guess % factor != 0 )
;		 printf (”%d\n”, guess);
;	 guess += 2; /* only look at odd numbers */
; }

%include "asm_io.inc"

segment .data
prompt		db	"Encontre numeros primos ate' o limite: ", 0

segment .bss
limite		resd	1
tentativa	resd	1

segment .text
	global	_asm_main
_asm_main:
	enter	0,0
	pusha
	
	; recebe o limite máximo para gerar números primos
	mov	eax, prompt
	call	print_string
	call	read_int
	mov	[limite], eax
	
	; imprime os 2 primeiros primos que são casos especiais
	mov	eax, 2
	call	print_int
	call	print_nl
	mov	eax, 3
	call	print_int
	call	print_nl
	
	mov	dword [tentativa], 5	; tentativa inicial (2 e 3 já foram resolvidos, começa com 5)
enquanto_limite:
	; enquanto tentativa <= limite
	mov	eax, [tentativa]
	cmp	eax, [limite]		
	jnbe	fim_enquanto_limite
	
	; procura por fatores que dividem tentativa
	mov	ebx, 3			; fator = 3	
enquanto_fator:
	; enquanto (fator * fator < tentativa) e (tentativa % fator != 0) faça
	mov	eax, ebx		; EAX = fator
	mul	eax
	jo	fim_enquanto_fator	; overflow! número muito grande
	cmp	eax, [tentativa]	; fator * fator < tentativa
	jnb	fim_enquanto_fator
	mov	edx, 0
	mov	eax, [tentativa]
	div	ebx			; tentativa % fator != 0
	cmp	edx, 0
	je	fim_enquanto_fator
	
	add	ebx, 2			; fator += 2  (para cada fator que divide um número, há o seu quadrado que também o divide)
	jmp	enquanto_fator
fim_enquanto_fator:

	;NOTA: não é necessário testar novamente? (pq??? mas e se for overflow ou fim do loop? o que eu estou perdendo)
	;se tentativa % fator != 0
	;mov	edx, 0
	;mov	eax, [tentativa]
	;idiv	ebx			; tentativa % fator
	;mov	edx, 0			; != 0
	je	fim_se
	mov	eax, [tentativa]	; imprime tentativa
	call	print_int
	call	print_nl
fim_se:

	add	dword [tentativa], 2	; tentativa += 2 (só verifica números ímpares)
	jmp	enquanto_limite
fim_enquanto_limite:
		
	popa
	mov	eax, 0
	leave
	ret
	
