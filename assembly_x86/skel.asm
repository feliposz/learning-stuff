;====================================================================
;skel.asm 
;Descrição: Base para criação de programas em assembly
;Fonte: Extraído do livro PC Assembly Language de Paul Carter
;Data: 02/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 NOME.asm
;
;Para gerar o executável (testado com o MingW):
;gcc -o NOME.exe NOME.obj asm_io.obj driver.c
;
;Dependências: asm_io.inc, asm_io.obj, driver.c
;
;Funcionamento:
;A rotina asm_main será chamada a partir do programa C.
;Isto é feito para facilitar a inicialização e disponibilizar toda
;a biblioteca padrão de C para ser usada a partir do código assembly
;====================================================================

;Inclui as rotinas básicas de E/S
%include "asm_io.inc"

;Segmento de dados inicializados (.data)
segment .data

;TODO: Coloque aqui os dados inicializados


;Segmento de dados não-inicializados (.bss)
segment .bss

;TODO: Coloque aqui os dados não-inicializados
 

segment .text
        global  _asm_main
_asm_main:
        enter   0,0               ;prepara a rotina
        pusha
	
	;TODO: Coloque aqui seu código assembly principal

        popa
        mov     eax, 0            ;Retorna 0 para a rotina que chamou 
        leave                     ;-> main() em driver.c
        ret

	;TODO: Coloque aqui as demais rotinas
