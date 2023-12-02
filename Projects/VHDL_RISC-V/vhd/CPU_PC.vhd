library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.std_logic_signed.all;

library work;
use work.PKG.all;


entity CPU_PC is
    generic(
        mutant: integer := 0
    );
    Port (
        -- Clock/Reset
        clk    : in  std_logic ;
        rst    : in  std_logic ;

        -- Interface PC to PO
        cmd    : out PO_cmd ;
        status : in  PO_status
    );
end entity;

architecture RTL of CPU_PC is
    type State_type is (
        S_Error,
        S_Init,
        S_Pre_Fetch,
        S_Fetch,
        S_Decode,
        S_LUI,
        S_Addi,
        S_Add,
        S_Sll,
        S_Auipc,
        S_Or,
        S_Ori,
        S_And,
        S_Andi,
        S_Xor,
        S_Xori,
        S_Sub,
        S_Srl,
        S_Sra,
        S_Srai,
        S_Slli,
        S_Srli,
        S_Branch,
        S_Set,
        S_LW,
        S_AD,
        S_STORE,
        S_PRE_LOAD,
        S_LH,
        S_LHU,
        S_LB,
        S_LBU,
        -- S_Lw,
        -- S_Lw2,
        -- S_Lw3,
        -- S_Sw,
        -- S_Sw2,
        S_Jal,
        S_Jalr,
        S_inter1,
        S_inter2,
        S_inter3,
        S_csrr1,
        S_csrr2
    );

    signal state_d, state_q : State_type;


begin

    FSM_synchrone : process(clk)
    begin
        if clk'event and clk='1' then
            if rst='1' then
                state_q <= S_Init;
            else
                state_q <= state_d;
            end if;
        end if;
    end process FSM_synchrone;

    FSM_comb : process (state_q, status)
    begin

        -- Valeurs par défaut de cmd à définir selon les préférences de chacun
        cmd.ALU_op            <= UNDEFINED;
        cmd.LOGICAL_op        <= UNDEFINED;
        cmd.ALU_Y_sel         <= UNDEFINED;

        cmd.SHIFTER_op        <= UNDEFINED;
        cmd.SHIFTER_Y_sel     <= UNDEFINED;

        cmd.RF_we             <= 'U';
        cmd.RF_SIZE_sel       <= UNDEFINED;
        cmd.RF_SIGN_enable    <= 'U';
        cmd.DATA_sel          <= UNDEFINED;

        cmd.PC_we             <= 'U';
        cmd.PC_sel            <= UNDEFINED;

        cmd.PC_X_sel          <= UNDEFINED;
        cmd.PC_Y_sel          <= UNDEFINED;

        cmd.TO_PC_Y_sel       <= UNDEFINED;

        cmd.AD_we             <= 'U';
        cmd.AD_Y_sel          <= UNDEFINED;

        cmd.IR_we             <= 'U';

        cmd.ADDR_sel          <= UNDEFINED;
        cmd.mem_we            <= 'U';
        cmd.mem_ce            <= 'U';

        cmd.cs.CSR_we            <= UNDEFINED;

        cmd.cs.TO_CSR_sel        <= UNDEFINED;
        cmd.cs.CSR_sel           <= UNDEFINED;
        cmd.cs.MEPC_sel          <= UNDEFINED;

        cmd.cs.MSTATUS_mie_set   <= 'U';
        cmd.cs.MSTATUS_mie_reset <= 'U';

        cmd.cs.CSR_WRITE_mode    <= UNDEFINED;

        state_d <= state_q;

        case state_q is
            when S_Error =>
                -- Etat transitoire en cas d'instruction non reconnue 
                -- Aucune action
                state_d <= S_Init;

            when S_Init =>
                -- PC <- RESET_VECTOR
                cmd.PC_we <= '1';
                cmd.PC_sel <= PC_rstvec;
                state_d <= S_Pre_Fetch;

            when S_Pre_Fetch =>
                -- mem[PC]
                cmd.mem_we   <= '0';
                cmd.mem_ce   <= '1';
                cmd.ADDR_sel <= ADDR_from_pc;
                state_d      <= S_Fetch;

            when S_Fetch =>
                -- IR <- mem_datain
                cmd.IR_we <= '1';
                state_d <= S_Decode;

            when S_Decode =>
                if status.IR(6 downto 0) = "0110111" then --lui
                    cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                    cmd.PC_sel <= PC_from_pc;
                    cmd.PC_we <= '1';
                    state_d <= S_LUI;
                -- elsif status.IR(6 downto 0) = "0000011" then --lw lb lbu lh lhu
                --     cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                --     cmd.PC_sel <= PC_from_pc;
                --     cmd.PC_we <= '1';
                --     state_d <= S_Lw;                       
                elsif ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "0100011" ) or ( status.IR(14 downto 12) = "000" and status.IR(6 downto 0) = "0100011" ) or ( status.IR(14 downto 12) = "001" and status.IR(6 downto 0) = "0100011" ) or ( status.IR(14 downto 12) = "100" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "000" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "101" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "001" and status.IR(6 downto 0) = "0000011" ) then
                    cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                    cmd.PC_sel <= PC_from_pc;
                    cmd.PC_we <= '1';
                    state_d <= S_AD;
                elsif status.IR(6 downto 0) = "1101111" then --jal
                    state_d <= S_Jal;
                elsif status.IR(6 downto 0) = "1100111" then --jalr
                    state_d <= S_Jalr;
                elsif status.IR(6 downto 0) = "1100011" then 
                    if status.IR(14 downto 12) = "000" then --beq
                        state_d <= S_Branch;
                    elsif status.IR(14 downto 12) = "100" then --blt
                        state_d <= S_Branch;
                    elsif status.IR(14 downto 12) = "001" then --bne
                        state_d <= S_Branch;
                    elsif status.IR(14 downto 12) = "101" then --bge
                        state_d <= S_Branch;
                    elsif status.IR(14 downto 12) = "110" then --bltu
                        state_d <= S_Branch;
                    elsif status.IR(14 downto 12) = "111" then --bgeu
                        state_d <= S_Branch;
                    end if;
                elsif status.IR(6 downto 0) = "0010011" then
                    if status.IR(14 downto 12) = "000" then --addi
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Addi;
                    elsif status.IR(14 downto 12) = "110" then --ori
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Ori;
                    elsif status.IR(14 downto 12) = "111" then --andi
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Andi;
                    elsif status.IR(14 downto 12) = "100" then --xori
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Xori;
                    elsif status.IR(14 downto 12) = "101" then
                        if status.IR(31 downto 25) = "0100000" then --srai
                            cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                            cmd.PC_sel <= PC_from_pc;
                            cmd.PC_we <= '1';
                            state_d <= S_Srai;
                        elsif status.IR(31 downto 25) = "0000000" then --slri
                            cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                            cmd.PC_sel <= PC_from_pc;
                            cmd.PC_we <= '1';
                            state_d <= S_Srli;
                        end if;
                    elsif status.IR(14 downto 12) = "001" then --slli
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Slli;
                    elsif status.IR(14 downto 12) = "010" then --slti
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Set;
                    elsif status.IR(14 downto 12) = "011" then --sltiu
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Set;
                    end if;
                elsif status.IR(6 downto 0) = "0010111" then --auipc
                    state_d <= S_Auipc;
                elsif status.IR(6 downto 0) = "0110011" then
                    if status.IR(14 downto 12) = "000" then 
                        if status.IR(31 downto 25) = "0000000" then --add
                            cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                            cmd.PC_sel <= PC_from_pc;
                            cmd.PC_we <= '1';
                            state_d <= S_Add;
                        elsif status.IR(31 downto 25) = "0100000" then --sub
                            cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                            cmd.PC_sel <= PC_from_pc;
                            cmd.PC_we <= '1';
                            state_d <= S_Sub;
                        end if;
                    elsif status.IR(14 downto 12) = "010" then --slt
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Set;          
                    elsif status.IR(14 downto 12) = "001" then --sll
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Sll;
                    elsif status.IR(14 downto 12) = "111" then --and
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_And;
                    elsif status.IR(14 downto 12) = "110" then --or
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Or;
                    elsif status.IR(14 downto 12) = "100" then --xor
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Xor; 
                    elsif status.IR(14 downto 12) = "011" then --sltu
                        cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                        cmd.PC_sel <= PC_from_pc;
                        cmd.PC_we <= '1';
                        state_d <= S_Set;
                    elsif status.IR(14 downto 12) = "101" then --sra
                        if status.IR(31 downto 25) = "0100000" then --sra
                            cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                            cmd.PC_sel <= PC_from_pc;
                            cmd.PC_we <= '1';
                            state_d <= S_Sra;  
                        elsif status.IR(31 downto 25) = "0000000" then --srl
                            cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                            cmd.PC_sel <= PC_from_pc;
                            cmd.PC_we <= '1';
                            state_d <= S_Srl;
                        end if;
                    end if;     
                elsif ( status.IR(14 downto 12) = "001" and status.IR(6 downto 0) = "1110011" ) or ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "1110011" ) or ( status.IR(14 downto 12) = "011" and status.IR(6 downto 0) = "1110011" ) or ( status.IR(14 downto 12) = "101" and status.IR(6 downto 0) = "1110011" ) or ( status.IR(14 downto 12) = "110" and status.IR(6 downto 0) = "1110011" ) or ( status.IR(14 downto 12) = "111" and status.IR(6 downto 0) = "1110011" ) then
                    cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                    cmd.PC_sel <= PC_from_pc;
                    cmd.PC_we <= '1';
                    state_d <= S_csrr1;   
                else
                    state_d <= S_Error; -- Pour d ́etecter les rat ́es du d ́ecodage
                end if;
    
    
            when S_LUI =>
                -- rd <- ImmU + 0
                cmd.PC_X_sel <= PC_X_cst_x00;
                cmd.PC_Y_sel <= PC_Y_immU;
                cmd.RF_we <= '1';
                cmd.DATA_sel <= DATA_from_pc;
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Addi =>
                -- rd <- rs1 + Imm
                cmd.ALU_Y_sel <= ALU_Y_immI;
                cmd.ALU_op <= ALU_plus;
                cmd.DATA_sel <= DATA_from_alu;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Add =>
                -- rd <- rs1 + rs2
                cmd.ALU_Y_sel <= ALU_Y_rf_rs2;
                cmd.ALU_op <= ALU_plus;
                cmd.DATA_sel <= DATA_from_alu;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Sll =>
                -- rd <- rs1 << imm
                cmd.SHIFTER_Y_sel <= SHIFTER_Y_rs2;
                cmd.SHIFTER_op <= SHIFT_ll;
                cmd.DATA_sel <= DATA_from_shifter;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Auipc =>
                -- rd <- imm || 0¹² + pc
                cmd.PC_Y_sel <= PC_Y_immU;
                cmd.PC_X_sel <= PC_X_pc;
                cmd.DATA_sel <= DATA_from_pc;
                cmd.RF_we <= '1';

                cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                cmd.PC_sel <= PC_from_pc;
                cmd.PC_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state

                state_d <= S_Pre_Fetch;

            when S_Or =>
                -- rd <- rs1 or rs2
                cmd.ALU_Y_sel <= ALU_Y_rf_rs2;
                cmd.LOGICAL_op <= LOGICAL_or;
                cmd.DATA_sel <= DATA_from_logical;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Ori =>
                -- rd <- immI or rs1
                cmd.ALU_Y_sel <= ALU_Y_immI;
                cmd.LOGICAL_op <= LOGICAL_or;
                cmd.DATA_sel <= DATA_from_logical;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;
            
            when S_And =>
                -- rd <- rs1 and rs2
                cmd.ALU_Y_sel <= ALU_Y_rf_rs2;
                cmd.LOGICAL_op <= LOGICAL_and;
                cmd.DATA_sel <= DATA_from_logical;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Andi =>
                -- rd <- immI and rs1
                cmd.ALU_Y_sel <= ALU_Y_immI;
                cmd.LOGICAL_op <= LOGICAL_and;
                cmd.DATA_sel <= DATA_from_logical;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Xor =>
                -- rd <- rs1 xor rs2
                cmd.ALU_Y_sel <= ALU_Y_rf_rs2;
                cmd.LOGICAL_op <= LOGICAL_xor;
                cmd.DATA_sel <= DATA_from_logical;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Xori =>
                -- rd <- immI xor rs1
                cmd.ALU_Y_sel <= ALU_Y_immI;
                cmd.LOGICAL_op <= LOGICAL_xor;
                cmd.DATA_sel <= DATA_from_logical;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Sub =>
                -- rd <- rs1 - rs2
                cmd.ALU_Y_sel <= ALU_Y_rf_rs2;
                cmd.ALU_op <= ALU_minus;
                cmd.DATA_sel <= DATA_from_alu;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Srl =>
                cmd.SHIFTER_Y_sel <= SHIFTER_Y_rs2;
                cmd.SHIFTER_op <= SHIFT_rl;
                cmd.DATA_sel <= DATA_from_shifter;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Sra =>
                cmd.SHIFTER_Y_sel <= SHIFTER_Y_rs2;
                cmd.SHIFTER_op <= SHIFT_ra;
                cmd.DATA_sel <= DATA_from_shifter;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Srai =>
                cmd.SHIFTER_Y_sel <= SHIFTER_Y_ir_sh;
                cmd.SHIFTER_op <= SHIFT_ra;
                cmd.DATA_sel <= DATA_from_shifter;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Slli =>
                cmd.SHIFTER_Y_sel <= SHIFTER_Y_ir_sh;
                cmd.SHIFTER_op <= SHIFT_ll;
                cmd.DATA_sel <= DATA_from_shifter;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Srli =>
                cmd.SHIFTER_Y_sel <= SHIFTER_Y_ir_sh;
                cmd.SHIFTER_op <= SHIFT_rl;
                cmd.DATA_sel <= DATA_from_shifter;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when S_Set => 
                if status.IR(5) = '1' then -- slt ou sltu
                    cmd.ALU_Y_sel <= ALU_Y_rf_rs2;
                else -- slti ou sltiu
                    cmd.TO_PC_Y_sel <= ALU_Y_immI;
                end if;
                cmd.DATA_sel <= DATA_from_slt;
                cmd.RF_we <= '1';
                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch; 
                
            when S_Branch =>
            cmd.ALU_Y_sel <= ALU_Y_rf_rs2;
                state_d <= S_Branch;
                if status.JCOND then 
                    cmd.TO_PC_Y_sel <= TO_PC_Y_immB;
                else
                    cmd.TO_PC_Y_sel <= TO_PC_Y_cst_x04;
                end if;
                cmd.PC_sel <= PC_from_pc;
                cmd.PC_we <= '1';
                state_d <= S_Pre_Fetch;
            
            when S_AD =>
                --ecriture d’un mot dans AD
                cmd.AD_we <= '1';
                -- next state
                --load
                if ( status.IR(14 downto 12) = "100" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "000" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "101" and status.IR(6 downto 0) = "0000011" ) or ( status.IR(14 downto 12) = "001" and status.IR(6 downto 0) = "0000011" ) then
                    cmd.AD_Y_sel <= AD_Y_immI;
                    state_d <= S_PRE_LOAD;
                --store
                else
                    cmd.AD_Y_sel <= AD_Y_immS;
                    state_d <= S_STORE;
                end if;
            
            when S_PRE_LOAD =>
                -- mem[PC]
                cmd.mem_we   <= '0';
                cmd.mem_ce   <= '1';
                cmd.ADDR_sel <= ADDR_from_ad;
                -- next state
                --lw
                if ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "0000011" ) then
                    state_d <= S_LW;
                --lh
                elsif ( status.IR(14 downto 12) = "001" and status.IR(6 downto 0) = "0000011" ) then
                    state_d <= S_LH;                
                --lhu
                elsif ( status.IR(14 downto 12) = "101" and status.IR(6 downto 0) = "0000011" ) then
                    state_d <= S_LHU;
                --lb
                elsif ( status.IR(14 downto 12) = "000" and status.IR(6 downto 0) = "0000011" ) then
                    state_d <= S_LB;
                --lbu
                else
                    state_d <= S_LBU;
                end if;

            when S_LW =>
                --Lecture d’un mot de la mémoire
                cmd.DATA_sel <= DATA_from_mem;
                cmd.RF_SIZE_sel <= RF_SIZE_word;
                cmd.RF_SIGN_enable <= '1';
                cmd.RF_we <= '1';
                -- next state
                state_d <= S_Pre_Fetch;
            
            when S_LH =>
                --Lecture d’un mot de la mémoire
                cmd.DATA_sel <= DATA_from_mem;
                cmd.RF_SIZE_sel <= RF_SIZE_half;
                cmd.RF_SIGN_enable <= '1';
                cmd.RF_we <= '1';
                -- next state
                state_d <= S_Pre_Fetch;
            
            when S_LHU =>
                --Lecture d’un mot de la mémoire
                cmd.DATA_sel <= DATA_from_mem;
                cmd.RF_SIZE_sel <= RF_SIZE_half;
                cmd.RF_SIGN_enable <= '0';
                cmd.RF_we <= '1';
                -- next state
                state_d <= S_Pre_Fetch;
            
            when S_LB =>
                --Lecture d’un mot de la mémoire
                cmd.DATA_sel <= DATA_from_mem;
                cmd.RF_SIZE_sel <= RF_SIZE_byte;
                cmd.RF_SIGN_enable <= '1';
                cmd.RF_we <= '1';
                -- next state
                state_d <= S_Pre_Fetch;
            
            when S_LBU =>
                --Lecture d’un mot de la mémoire
                cmd.DATA_sel <= DATA_from_mem;
                cmd.RF_SIZE_sel <= RF_SIZE_byte;
                cmd.RF_SIGN_enable <= '0';
                cmd.RF_we <= '1';
                -- next state
                state_d <= S_Pre_Fetch;
            
            when S_STORE =>
                --ecriture d’un mot dans mem
                cmd.mem_we <= '1';
                cmd.mem_ce <= '1';
                cmd.ADDR_sel <= ADDR_from_ad;
                if ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "0100011" ) then
                    cmd.RF_SIZE_sel <= RF_SIZE_word;
                elsif ( status.IR(14 downto 12) = "000" and status.IR(6 downto 0) = "0100011" ) then
                    cmd.RF_SIZE_sel <= RF_SIZE_byte;
                else
                    cmd.RF_SIZE_sel <= RF_SIZE_half;
                end if;
                -- next state
                state_d <= S_Pre_Fetch;

            -- when S_Lw => 
            --     cmd.AD_Y_sel <= AD_Y_immI;
            --     cmd.AD_we <= '1';
            --     state_d <= S_Lw2;

            -- when S_Lw2 =>
            --     cmd.mem_ce <= '1';
            --     cmd.mem_we <= '0';
            --     cmd.ADDR_sel <= ADDR_from_ad;
            --     state_d <= S_Lw3;
            
            -- when S_Lw3 =>
            --     if status.IR(14 downto 12) = "010" then --lw
            --         cmd.RF_SIGN_enable <= '1';
            --         cmd.RF_SIZE_sel <= RF_SIZE_word;
            --     elsif status.IR(14 downto 12) = "000" then --lb
            --         cmd.RF_SIGN_enable <= '1';
            --         cmd.RF_SIZE_sel <= RF_SIZE_byte;
            --     elsif status.IR(14 downto 12) = "100" then --lbu
            --         cmd.RF_SIGN_enable <= '0';
            --         cmd.RF_SIZE_sel <= RF_SIZE_byte;
            --     elsif status.IR(14 downto 12) = "001" then --lh
            --         cmd.RF_SIGN_enable <= '1';
            --         cmd.RF_SIZE_sel <= RF_SIZE_half;
            --     elsif status.IR(14 downto 12) = "101" then --lhu
            --         cmd.RF_SIGN_enable <= '0';
            --         cmd.RF_SIZE_sel <= RF_SIZE_half;
            --     end if;
            --     cmd.DATA_sel <= DATA_from_mem;
            --     cmd.RF_we <= '1';
            --     cmd.mem_ce <= '1';
            --     cmd.mem_we   <= '0';
            --     cmd.ADDR_sel <= ADDR_from_pc;
            --     state_d <= S_Fetch;

            -- when S_Sw =>
            --     cmd.AD_we <= '1';
            --     cmd.AD_Y_sel <= AD_Y_immS;
            --     state_d <= S_Sw2;

            -- when S_Sw2 =>
            --     cmd.mem_we <= '1';
            --     cmd.mem_ce <= '1';
            --     cmd.ADDR_sel <= ADDR_from_ad;
            --     if ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "0100011" ) then --sw
            --         cmd.RF_SIZE_sel <= RF_SIZE_word;
            --     elsif ( status.IR(14 downto 12) = "000" and status.IR(6 downto 0) = "0100011" ) then --sb
            --         cmd.RF_SIZE_sel <= RF_SIZE_byte;
            --     else
            --         cmd.RF_SIZE_sel <= RF_SIZE_half;
            --     end if;
            --     -- next state
            --     state_d <= S_Pre_Fetch;
            
            when S_Jal =>
                -- rd <- pc + 4
                cmd.PC_Y_sel <= PC_Y_cst_x04;
                cmd.PC_X_sel <= PC_X_pc;
                cmd.RF_we <= '1';
                cmd.DATA_sel <= DATA_from_pc;
                -- pc <- pc + immJ
                cmd.TO_PC_Y_sel <= TO_PC_Y_immJ;
                cmd.PC_we <= '1';
                cmd.PC_sel <= PC_from_pc;
                -- next state
                state_d <= S_Pre_Fetch;

            when S_Jalr =>
                -- rd <- pc + 4
                cmd.PC_Y_sel <= PC_Y_cst_x04;
                cmd.PC_X_sel <= PC_X_pc;
                cmd.RF_we <= '1';
                cmd.DATA_sel <= DATA_from_pc;
                -- pc <- rs1 + immI
                cmd.RF_SIGN_enable <= '1';
                cmd.ALU_Y_sel <= ALU_Y_immI;
                cmd.ALU_op <= ALU_plus;
                cmd.PC_we <= '1';
                cmd.PC_sel <= PC_from_alu;
                -- next state
                state_d <= S_Pre_Fetch;

            when S_inter1 =>
                -- mepc <= pc
                cmd.cs.MEPC_sel <= MEPC_from_pc;
                cmd.cs.CSR_we <= CSR_mepc;
                cmd.cs.CSR_WRITE_mode <= WRITE_mode_simple;
                -- pc <= mtvec
                cmd.PC_sel <= PC_mtvec;
                cmd.PC_we <= '1';
                --next state
                state_d <= S_inter2;

            when S_inter2 =>
                -- miepc <= '0'
                cmd.cs.MSTATUS_mie_set <= '0';
                --next state
                state_d <= S_Pre_Fetch;

            --mret
            when S_inter3 =>
                -- pc <= mepc
                cmd.cs.MSTATUS_mie_reset <= '1';
                cmd.PC_sel <= PC_from_mepc;
                cmd.PC_we <= '1';
                --next state
                state_d <= S_Pre_Fetch;

            when S_csrr1 =>
                --rd <= csr
                cmd.RF_we <= '1';
                cmd.DATA_sel <= DATA_from_csr;
                -- ou qu'on lit?
                --mie
                if status.IR(31 downto 20) = "001100000100" then
                    cmd.cs.CSR_sel <= CSR_from_mie;
                --mstatus
                elsif status.IR(31 downto 20) = "001100000000" then
                    cmd.cs.CSR_sel <= CSR_from_mstatus;
                --mcause
                elsif status.IR(31 downto 20) = "001101000010" then
                    cmd.cs.CSR_sel <= CSR_from_mcause;
                --mip
                elsif status.IR(31 downto 20) = "001101000100" then
                    cmd.cs.CSR_sel <= CSR_from_mip;
                --mtvec
                elsif status.IR(31 downto 20) = "001100000101" then
                    cmd.cs.CSR_sel <= CSR_from_mtvec;
                --mepc
                else
                    cmd.cs.CSR_sel <= CSR_from_mepc;
                end if;
                -- next state
                state_d <= S_csrr2;

            when S_csrr2 =>
                --csr <= rs ou imm
                -- ou qu'on ecrit?
                --mie
                if status.IR(31 downto 20) = "001100000100" then
                    cmd.cs.CSR_we <= CSR_mie;
                --mstatus
                elsif status.IR(31 downto 20) = "001100000000" then
                    cmd.cs.CSR_we <= CSR_mstatus;
                --mtvec
                elsif status.IR(31 downto 20) = "001100000101" then
                    cmd.cs.CSR_we <= CSR_mtvec;
                --mepc
                else
                    cmd.cs.CSR_we <= CSR_mepc;
                end if;

                --csrrw
                if ( status.IR(14 downto 12) = "001" and status.IR(6 downto 0) = "1110011" ) then
                    cmd.cs.TO_CSR_sel <= TO_CSR_from_rs1;
                    cmd.cs.CSR_WRITE_mode <= WRITE_mode_simple;
                --csrrs
                elsif ( status.IR(14 downto 12) = "010" and status.IR(6 downto 0) = "1110011" ) then
                    cmd.cs.TO_CSR_sel <= TO_CSR_from_rs1;
                    cmd.cs.CSR_WRITE_mode <= WRITE_mode_set;
                --csrrc  
                elsif ( status.IR(14 downto 12) = "011" and status.IR(6 downto 0) = "1110011" ) then
                    cmd.cs.TO_CSR_sel <= TO_CSR_from_rs1;
                    cmd.cs.CSR_WRITE_mode <= WRITE_mode_clear;
                --csrrwi
                elsif ( status.IR(14 downto 12) = "101" and status.IR(6 downto 0) = "1110011" ) then
                    cmd.cs.TO_CSR_sel <= TO_CSR_from_imm;
                    cmd.cs.CSR_WRITE_mode <= WRITE_mode_simple;
                --csrrsi
                elsif ( status.IR(14 downto 12) = "110" and status.IR(6 downto 0) = "1110011" ) then
                    cmd.cs.TO_CSR_sel <= TO_CSR_from_imm;
                    cmd.cs.CSR_WRITE_mode <= WRITE_mode_set;
                --csrrci
                else
                    cmd.cs.TO_CSR_sel <= TO_CSR_from_imm;
                    cmd.cs.CSR_WRITE_mode <= WRITE_mode_clear;
                end if;

                -- lecture mem[PC]
                cmd.ADDR_sel <= ADDR_from_pc;
                cmd.mem_ce <= '1';
                cmd.mem_we <= '0';
                -- next state
                state_d <= S_Fetch;

            when others => null;
        end case;

    end process FSM_comb;

end architecture;