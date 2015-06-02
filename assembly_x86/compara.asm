;==============================================================
;compara.asm
;Descrição: Um programa simples para comparação de números, que
;faz uso de instruções de comparação e desvios incondicionais
;(jmp) e condicionais (jz, jnz, js, jns, jl, jg, je, etc.)
;
;Funcionamento:
;
;Segue o pseudo-código do programa:
;
;repita
;  escreva("Comparacao de numeros")
;  escreva("Digite o primeiro numero: ")
;  leia(num_a)
;  escreva("Digite o segundo numero:  ")
;  leia(num_b)
;  se num_a > num_b entao
;    escreva("O primeiro numero e' maior que o segundo.")
;  senao
;    se num_a < num_b entao
;      escreva("O primeiro numero e' menor que o segundo.")
;    senao
;      escreva("O primeiro numero e' igual ao segundo.")
;    fimse
;  fimse
;  se num_a = 0 entao
;    escreva("O primeiro numero e' zero.")
;  senao
;    escreva("O primeiro numero nao e' zero.")
;  fimse
;  se num_a < 0 entao
;    escreva("O segundo numero e' negativo.")
;  senao
;    escreva("O segundo numero e' positivo.")
;  senao
;  escreva("Deseja continuar? 1) Sim 2) Nao =>"
;  leia(resp)
;ate resp <> 1
;==============================================================

%include "asm_io.inc"

segment .data
titulo_prog	db	"Programa de Comparacao de Numeros", 0
prompt_a	db	"Digite o primeiro numero: ", 0
prompt_b	db	"Digite o segundo numero:  ", 0
prompt_cont	db	"Deseja continuar? 1) Sim 2) Nao => ", 0
msg_comparacoes db	"Resultado das comparacoes: ", 0
msg_maior	db	"O primeiro numero e' maior que o segundo.", 0
msg_menor	db	"O primeiro numero e' menor que o segundo.", 0
msg_igual	db	"O primeiro numero e' igual ao segundo.", 0
msg_a_zero	db	"O primeiro numero e' zero.", 0
msg_a_nao_zero	db	"O primeiro numero nao e' zero.", 0
msg_b_positivo	db	"O segundo numero e' positivo.", 0
msg_b_negativo	db	"O segundo numero e' negativo.", 0

segment .bss
num_a		resd	1
num_b		resd	1

segment .text
	global	_asm_main
_asm_main:
	enter	0,0
	pusha
	
	; Mensagem de boas vindas
	mov	eax, titulo_prog
	call	print_string
	call	print_nl
	
inicio:	
	call	print_nl
	
	; Recebe o primeiro número
	mov	eax, prompt_a
	call	print_string
	call	read_int
	mov	[num_a], eax
	
	; Recebe o segundo número
	mov	eax, prompt_b
	call	print_string
	call	read_int
	mov	[num_b], eax
	
	call	print_nl
	mov	eax, msg_comparacoes
	call	print_string
	call	print_nl
	call	print_nl
	
	; Comparação entre os números
	mov	eax, [num_a]
	mov	ebx, [num_b]
	cmp	eax, ebx
	jg	maior
	jl	menor
	jmp	igual
maior:
	mov	eax, msg_maior
	jmp	fim_comparacao
menor:
	mov	eax, msg_menor
	jmp	fim_comparacao
igual:
	mov	eax, msg_igual
fim_comparacao:
	call	print_string
	call	print_nl
	
	; Verifica se A é zero com os flags
	mov	eax, [num_a]
	cmp	eax, 0
	jz	zero
	jmp	nao_zero
zero:
	mov	eax, msg_a_zero
	jmp	fim_zero
nao_zero:	
	mov	eax, msg_a_nao_zero
fim_zero:
	call	print_string
	call	print_nl

	; Verifica se sinal de B é positivo ou negativo
	mov	ebx, [num_b]
	cmp	ebx, 0
	js	negativo
	jmp	positivo
negativo:
	mov	eax, msg_b_negativo
	jmp	fim_sinal
positivo:
	mov	eax, msg_b_positivo
fim_sinal:
	call	print_string
	call	print_nl
	
	; Deseja continuar?
	call	print_nl
	mov	eax, prompt_cont
	call	print_string
	call	read_int
	
	cmp	eax, 1	; RESP = 1? (sim)
	je	inicio
	
fim:	
	
	popa
	mov	eax, 0
	leave
	ret
	