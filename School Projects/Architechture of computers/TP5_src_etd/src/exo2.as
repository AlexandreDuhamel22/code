xor r3,r3
li 0, r0
li 15, r1

attente:
 ld 0x0010, r3
 and r3,r3 #MAJ du flags z
 jz choix 
 jmp attente 

choix:
 and r2, r2
 jz fonction1
 jmp fonction2

fonction1:
 st 0x0001, r0
 li 1, r2 
 jmp attente

fonction2:
 st 0x0001, r1
 li 0, r2
 jmp attente