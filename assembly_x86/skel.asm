;====================================================================
;skel.asm 
;Descri��o: Base para cria��o de programas em assembly
;Fonte: Extra�do do livro PC Assembly Language de Paul Carter
;Data: 02/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 NOME.asm
;
;Para gerar o execut�vel (testado com o MingW):
;gcc -o NOME.exe NOME.obj asm_io.obj driver.c
;
;Depend�ncias: asm_io.inc, asm_io.obj, driver.c
;
;Funcionamento:
;A rotina asm_main ser� chamada a partir do programa C.
;Isto � feito para facilitar a inicializa��o e disponibilizar toda
;a biblioteca padr�o de C para ser usada a partir do c�digo assembly
;====================================================================

;Inclui as rotinas b�sicas de E/S
%include "asm_io.inc"

;Segmento de dados inicializados (.data)
segment .data

;TODO: Coloque aqui os dados inicializados


;Segmento de dados n�o-inicializados (.bss)
segment .bss

;TODO: Coloque aqui os dados n�o-inicializados
 

segment .text
        global  _asm_main
_asm_main:
        enter   0,0               ;prepara a rotina
        pusha
	
	;TODO: Coloque aqui seu c�digo assembly principal

        popa
        mov     eax, 0            ;Retorna 0 para a rotina que chamou 
        leave                     ;-> main() em driver.c
        ret

	;TODO: Coloque aqui as demais rotinas
