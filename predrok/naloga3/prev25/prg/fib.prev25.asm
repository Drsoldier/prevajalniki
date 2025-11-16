#Prologue
_fib:
	sd ra, -32(sp)
	sd sp, -40(sp)
	sd gp, -48(sp)
	sd x4, -56(sp)
	sd x5, -64(sp)
	sd x6, -72(sp)
	sd x7, -80(sp)
	sd fp, -88(sp)
	sd x9, -96(sp)
	sd a0, -104(sp)
	sd x11, -112(sp)
	sd x12, -120(sp)
	sd x13, -128(sp)
	sd x14, -136(sp)
	sd x15, -144(sp)
	sd x16, -152(sp)
	sd x17, -160(sp)
	sd x18, -168(sp)
	sd x19, -176(sp)
	sd x20, -184(sp)
	sd x21, -192(sp)
	sd x22, -200(sp)
	sd x23, -208(sp)
	sd x24, -216(sp)
	sd x25, -224(sp)
	sd x26, -232(sp)
	sd x27, -240(sp)
	sd x28, -248(sp)
	sd x29, -256(sp)
	sd x30, -264(sp)
	sd x31, -272(sp)
#######_fib#####

L0:
	add x5, zero, x6
	li x5, 8
	add x6, x5, x5
	ld x6, 0(x5)
	blt x5, x5
	jal zero, zero
L6:
	jal zero, L3
L2:
	add x6, zero, x5
	li x5, 8
	add x5, x6, x6
	ld x5, 0(x6)
	add x5, zero, x6
	add a0, zero, x5
	jal zero, L1
	jal zero, L3
L3:
	ld x5, 0(x6)
	add x6, zero, x5
	add x5, zero, x6
	li x5, 8
	add x6, x5, x5
	ld x5, 0(x6)
	add x6, zero, x5
	li x5, 1
	sub x6, x5, x5
	add x6, zero, x5
	sd x5, 0(sp)
	addi sp, sp, -8
	sd x5, 0(sp)
	addi sp, sp, -8
#fixing sp
#args size is 2
	addi sp, -8(zero)
	addi sp, -8(zero)
#fixed sp
	jal x5, _fib
	ld x5, 0(x6)
	addi sp, sp, 8
	addi sp, sp, 8
	add x5, zero, x6
	ld x5, 0(x6)
	add x5, zero, x6
	add x5, zero, x6
	li x5, 8
	add x6, x5, x5
	ld x5, 0(x6)
	add x5, zero, x6
	li x5, 2
	sub x6, x5, x5
	add x6, zero, x5
	sd x5, 0(sp)
	addi sp, sp, -8
	sd x5, 0(sp)
	addi sp, sp, -8
#fixing sp
#args size is 2
	addi sp, -8(zero)
	addi sp, -8(zero)
#fixed sp
	jal x5, _fib
	ld x5, 0(x6)
	addi sp, sp, 8
	addi sp, sp, 8
	add x5, zero, x6
	add x5, x6, x6
	add x6, zero, x5
	add a0, zero, x5
	jal zero, L1
	jal zero, L1
L1:
	add a0, zero, T1
	ld ra, -32(sp)
	ld sp, -40(sp)
	ld gp, -48(sp)
	ld x4, -56(sp)
	ld x5, -64(sp)
	ld x6, -72(sp)
	ld x7, -80(sp)
	ld fp, -88(sp)
	ld x9, -96(sp)
	ld a0, -104(sp)
	ld x11, -112(sp)
	ld x12, -120(sp)
	ld x13, -128(sp)
	ld x14, -136(sp)
	ld x15, -144(sp)
	ld x16, -152(sp)
	ld x17, -160(sp)
	ld x18, -168(sp)
	ld x19, -176(sp)
	ld x20, -184(sp)
	ld x21, -192(sp)
	ld x22, -200(sp)
	ld x23, -208(sp)
	ld x24, -216(sp)
	ld x25, -224(sp)
	ld x26, -232(sp)
	ld x27, -240(sp)
	ld x28, -248(sp)
	ld x29, -256(sp)
	ld x30, -264(sp)
	ld x31, -272(sp)
	ret


#-----End of function-----
#Prologue
_main:
	sd ra, -8(sp)
	sd sp, -16(sp)
	sd gp, -24(sp)
	sd x4, -32(sp)
	sd x5, -40(sp)
	sd x6, -48(sp)
	sd x7, -56(sp)
	sd fp, -64(sp)
	sd x9, -72(sp)
	sd a0, -80(sp)
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

L4:
	ld x6, 0(x5)
	add x6, zero, x5
	li x5, 10
	sd x5, 0(sp)
	addi sp, sp, -8
	sd x5, 0(sp)
	addi sp, sp, -8
#fixing sp
#args size is 2
	addi sp, -8(zero)
	addi sp, -8(zero)
#fixed sp
	jal x5, _fib
	ld x5, 0(x6)
	addi sp, sp, 8
	addi sp, sp, 8
	add x5, zero, x6
	add a0, zero, x5
	jal zero, L5
	jal zero, L5
L5:
	ret


#-----End of the main function-----
