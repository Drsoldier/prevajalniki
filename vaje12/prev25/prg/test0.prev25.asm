#Prologue
_main
sd ra, -8(sp)
sd sp, -16(sp)
sd gp, -24(sp)
sd x4, -32(sp)
sd x5, -40(sp)
sd x6, -48(sp)
sd t2, -56(sp)
sd fp, -64(sp)
sd x9, -72(sp)
sd x10, -80(sp)
sd x11, -88(sp)
sd x12, -96(sp)
sd x13, -104(sp)
sd x14, -112(sp)
sd x15, -120(sp)
sd x16, -128(sp)
sd x17, -136(sp)
sd x18, -144(sp)
sd x19, -152(sp)
sd x20, -160(sp)
sd x21, -168(sp)
sd x22, -176(sp)
sd x23, -184(sp)
sd x24, -192(sp)
sd x25, -200(sp)
sd x26, -208(sp)
sd x27, -216(sp)
sd x28, -224(sp)
sd x29, -232(sp)
sd x30, -240(sp)
sd x31, -248(sp)
#######_main#####

L0
ld T4, 0(T0)
sd T4, 0(sp)
addi sp, sp, -8
sd t2, -8(sp)
lui t2, 0
addi t2, zero, 1
sd t2, 0(sp)
ld t2, -8(sp)
addi sp, sp, -8
#fixing sp
addi sp, -8(zero)
addi sp, -8(zero)
#fixed sp
jal zero, _x
ld T5, 0(T0)
add T6, zero, T5
#------------Saving t2 register------------
sd t2, -8(sp)
lui t2, 0
addi t2, zero, 1
add T7, t2, zero
ld t2, -8(sp)
#------------Restored t2 register------------
sd T6, 0(sp)
addi sp, sp, -8
sd T7, 0(sp)
addi sp, sp, -8
#fixing sp
#args size is 2
addi sp, -8(zero)
addi sp, -8(zero)
#fixed sp
jal T12, _x
ld T8, 0(T12)
addi sp, sp, 8
addi sp, sp, 8
add T9, zero, T8
add T1, zero, T9
jal zero, L1
jal zero, L1
L1
compiler.phase.memory.MEM$Label@49b0b76 jal x0,compiler.phase.memory.MEM$Label@49b0b76
#-----End of the main function-----
#Prologue
_x
sd ra, -8(sp)
sd sp, -16(sp)
sd gp, -24(sp)
sd x4, -32(sp)
sd x5, -40(sp)
sd x6, -48(sp)
sd t2, -56(sp)
sd fp, -64(sp)
sd x9, -72(sp)
sd x10, -80(sp)
sd x11, -88(sp)
sd x12, -96(sp)
sd x13, -104(sp)
sd x14, -112(sp)
sd x15, -120(sp)
sd x16, -128(sp)
sd x17, -136(sp)
sd x18, -144(sp)
sd x19, -152(sp)
sd x20, -160(sp)
sd x21, -168(sp)
sd x22, -176(sp)
sd x23, -184(sp)
sd x24, -192(sp)
sd x25, -200(sp)
sd x26, -208(sp)
sd x27, -216(sp)
sd x28, -224(sp)
sd x29, -232(sp)
sd x30, -240(sp)
sd x31, -248(sp)
#######_x#####

L2
#------------Saving t2 register------------
sd t2, -8(sp)
lui t2, 0
addi t2, zero, 1
add T10, t2, zero
ld t2, -8(sp)
#------------Restored t2 register------------
add T3, zero, T10
jal zero, L3
jal zero, L3
L3
ld ra, -8(sp)
ld sp, -16(sp)
ld gp, -24(sp)
ld x4, -32(sp)
ld x5, -40(sp)
ld x6, -48(sp)
ld t2, -56(sp)
ld fp, -64(sp)
ld x9, -72(sp)
ld x10, -80(sp)
ld x11, -88(sp)
ld x12, -96(sp)
ld x13, -104(sp)
ld x14, -112(sp)
ld x15, -120(sp)
ld x16, -128(sp)
ld x17, -136(sp)
ld x18, -144(sp)
ld x19, -152(sp)
ld x20, -160(sp)
ld x21, -168(sp)
ld x22, -176(sp)
ld x23, -184(sp)
ld x24, -192(sp)
ld x25, -200(sp)
ld x26, -208(sp)
ld x27, -216(sp)
ld x28, -224(sp)
ld x29, -232(sp)
ld x30, -240(sp)
ld x31, -248(sp)
ret


#-----End of function-----
