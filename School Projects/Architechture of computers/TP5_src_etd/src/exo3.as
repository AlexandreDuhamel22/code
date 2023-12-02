li 16, r1
li 1, r2 #motif
li 1, r3

attente:
 ld 0x0010, r0
 and r0, r0
 jz choix 
 jmp attente

choix:
 li 16, r1
 st r2, 0x0001
 shl r2
 and r2, r1
 jz attente
 add r3, r2
 jmp attente