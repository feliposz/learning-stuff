;====================================================================
;max.asm 
;Descri��o: Programa de exemplo em assembly que l� dois n�meros e
;determina qual deles � o maior, utilizando alguns truques, mas sem
;fazer uso de desvio condicional.
;
;Nota: O objetivo do exemplo �, al�m de mostrar o uso dos operadores
;de bit, mostrar que � poss�vel fazer opera��es condicionais sem
;usar instru��es de desvio. Os processadores modernos tem a capacidade
;de adiantar a execu��o de instru��es posteriores, mas esta vantagem
;se perde no uso de desvios. Devido � alta complexidade de se criar
;e modificar c�digo escrito desta forma. Estas t�cnicas devem limitar-
;se a casos extremos, em que um trecho de c�digo � executado milhares
;(ou milh�es, bilh�es...) de vezes e que o ganho de desempenho pode
;se justificado (o que n�o � o caso neste exemplo).
;
;Fonte: Extra�do do livro PC Assembly Language de Paul Carter
;Data: 07/04/2012
;====================================================================

%include "asm_io.inc"

segment .data
msg_1		db	"Insira um numero: ", 0
msg_2		db	"Insira outro numero: ", 0
msg_maior	db	"O maior numero e' ", 0

segment .bss
num1		resd	1	;Primeiro n�mero inserido

segment .text
	global	_asm_main
_asm_main:
	enter	0,0
	pusha
	
	; Recebe os 2 n�meros
	
	mov	eax, msg_1
	call	print_string
	call	read_int
	mov	[num1], eax	;salva primeiro n�mero (= num1)
	
	mov	eax, msg_2
	call	print_string
	call	read_int	;segundo n�mero em EAX (= num2)
	
	; Determina o maior sem usar desvio condicional
	
	xor	ebx, ebx	; EBX = 0
	cmp	eax, [num1]	; compara o segundo com o primeiro
	setg	bl		; BL =  num2 > num 1
				;          1 : 0
				
	; preparar a m�scara para identificar o maior
	neg	ebx		; 0xFFFFFFFF : 0
	mov	ecx, ebx	; 0xFFFFFFFF : 0 -> m�scara para num2
	and	ecx, eax	;       num2 : 0 -> aplica m�scara
	not	ebx		;          0 : 0xFFFFFFFF -> m�scara para num1
	and	ebx, [num1]	;          0 : num1 -> aplica m�scara
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
	