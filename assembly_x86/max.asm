;====================================================================
;max.asm 
;Descrição: Programa de exemplo em assembly que lê dois números e
;determina qual deles é o maior, utilizando alguns truques, mas sem
;fazer uso de desvio condicional.
;
;Nota: O objetivo do exemplo é, além de mostrar o uso dos operadores
;de bit, mostrar que é possível fazer operações condicionais sem
;usar instruções de desvio. Os processadores modernos tem a capacidade
;de adiantar a execução de instruções posteriores, mas esta vantagem
;se perde no uso de desvios. Devido à alta complexidade de se criar
;e modificar código escrito desta forma. Estas técnicas devem limitar-
;se a casos extremos, em que um trecho de código é executado milhares
;(ou milhões, bilhões...) de vezes e que o ganho de desempenho pode
;se justificado (o que não é o caso neste exemplo).
;
;Fonte: Extraído do livro PC Assembly Language de Paul Carter
;Data: 07/04/2012
;====================================================================

%include "asm_io.inc"

segment .data
msg_1		db	"Insira um numero: ", 0
msg_2		db	"Insira outro numero: ", 0
msg_maior	db	"O maior numero e' ", 0

segment .bss
num1		resd	1	;Primeiro número inserido

segment .text
	global	_asm_main
_asm_main:
	enter	0,0
	pusha
	
	; Recebe os 2 números
	
	mov	eax, msg_1
	call	print_string
	call	read_int
	mov	[num1], eax	;salva primeiro número (= num1)
	
	mov	eax, msg_2
	call	print_string
	call	read_int	;segundo número em EAX (= num2)
	
	; Determina o maior sem usar desvio condicional
	
	xor	ebx, ebx	; EBX = 0
	cmp	eax, [num1]	; compara o segundo com o primeiro
	setg	bl		; BL =  num2 > num 1
				;          1 : 0
				
	; preparar a máscara para identificar o maior
	neg	ebx		; 0xFFFFFFFF : 0
	mov	ecx, ebx	; 0xFFFFFFFF : 0 -> máscara para num2
	and	ecx, eax	;       num2 : 0 -> aplica máscara
	not	ebx		;          0 : 0xFFFFFFFF -> máscara para num1
	and	ebx, [num1]	;          0 : num1 -> aplica máscara
	or	ecx, ebx	;       num2 : num1
	
	; Imprime resultado
	
	mov	eax, msg_maior
	call	print_string
	mov	eax, ecx
	call	print_int
	call	print_nl
	
	; Fim
	
	popa
	mov	eax, 0
	leave
	ret
	