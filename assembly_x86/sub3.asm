;====================================================================
;sub3.asm
;Descrição: Exemplo de uso de subrotinas/subprogramas
;com a convenção de chamada da linguagem C.
;Data: 11/04/2012
;====================================================================

%include "asm_io.inc"

segment .data
soma	dd	0

segment .bss
valor	resd	1

segment .text
	global	_asm_main
_asm_main:
	enter 0,0
	pusha
	
; i = 1
; soma = 0
; enquanto (recebe_int(i, &valor), valor != 0)
;    soma += valor
;    i++
; mostra_soma(sum)

	mov	edx, 1		; i = 1
enquanto:
	; coloca os parametros para o subprograma
	push	edx		; coloca o valor de i (edx)
	push	dword valor	; coloca o ENDEREÇO de valor (&valor)
	call	recebe_int	; recebe_int(i, &valor)
	add	esp, 8		; remove os 2 parametros (i, &valor) da pilha
	
	mov 	eax, [valor]
	cmp	eax, 0
	je	fim_enquanto	; valor != 0
	
	add	[soma], eax	; soma += valor
	inc	edx		; i++
	jmp	enquanto
fim_enquanto:

	push	dword [soma]	
	call	mostra_soma	; mostra_soma(soma)
	pop	ecx		; remove soma da pilha
	
	popa
	mov	eax, 0
	leave
	ret

; Exemplo de passagem de parâmetros por referência (ponteiro)
	
; subprograma: recebe_int
; equivalente: void recebe_int(int i, int* valor)
; parametros: 
;   número da entrada [EBP+12]
;   endereço para armazenar valor recebido [EBP+8]
; notas:
;   os valores de eax e ebx são destruídos

segment .data
msg_recebe	db	") Insira um numero inteiro (0 para sair): ", 0 
	
segment .text
recebe_int:
	push	ebp
	mov	ebp, esp
	
	mov	eax, [ebp+12]	; imprime i
	call	print_int
	mov	eax, msg_recebe	; imprime a mensagem de prompt
	call	print_string
	
	call	read_int	; recebe o valor
	mov	ebx, [ebp+8]	; ebx = endereço valor (usa como ponteiro)
	mov	[ebx], eax	; *valor = eax
	
	pop	ebp
	ret

; Exemplo de passagem de parâmetro por valor
	
; subprograma: mostra_soma
; equivalente: void mostra_soma(int soma)
; parametros:
;   valor a ser exibido [EBP+8]
; notas:
;   o valor de eax é destruído

segment .data
msg_soma	db	"A soma dos valores inseridos e' ", 0

segment .text
mostra_soma:
	push	ebp
	mov	ebp, esp
	
	mov	eax, msg_soma
	call	print_string
	
	mov	eax, [ebp+8]
	call	print_int
	call	print_nl
	
	pop	ebp
	ret

	
