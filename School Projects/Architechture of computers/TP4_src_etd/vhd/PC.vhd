library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity PC is
    port (
        clk  : in std_logic;
        reset: in std_logic;
        start: in std_logic;
        inf  : in std_logic;
        egal : in std_logic;
        getA : out std_logic;
        getB : out std_logic;
        subBA: out std_logic;
        ldA  : out std_logic;
        ldB  : out std_logic;
        done : out std_logic
    );
end PC;

architecture mixte of PC is
    type Etat_type is (vait,nd,init,test,A_B,B_A); 
    signal Etat_courant, Etat_futur: Etat_type;
  

begin

Registre : process (clk,reset) 
	begin
		if rising_edge(clk) and reset = '0' then
		    Etat_courant <= Etat_futur;
		end if;
		if reset = '1' then
		    Etat_courant <= vait;
		end if;
	end process;

Combinatoire : process(start, egal, inf, Etat_courant)
	begin
		done <= '0'; 
		getA <= '0';
		getB <= '0';
		ldA <= '0';
		ldB <= '0';
		subBA <= '0';
		case Etat_courant is  
			when vait =>
				done <= '0'; 
				getA <= '0';
				getB <= '0';
				ldA <= '0';
				ldB <= '0';
				subBA <= '0';
				if start = '1' then 
					Etat_futur <= init;
				elsif start = '0' then 
					Etat_futur <= vait;
				end if;
			when init =>
				getA <= '1';
				getB <= '1';
				ldA <= '1';
				ldB <= '1';
				done <= '0';
				Etat_futur <= test;
			when test =>
				done <= '0'; 
				getA <= '0';
				getB <= '0';
				ldA <= '0';
				ldB <= '0';
				subBA <= '0';
				if egal = '0' and inf = '0' then 
					Etat_futur <= A_B;
				elsif egal = '0' and inf = '1' then 
					Etat_futur <= B_A;
				elsif egal = '1' then
					Etat_futur <= nd;
				end if;
			when A_B => 
				done <= '0'; 
				getA <= '0';
				getB <= '0';
				ldB <= '0';
				subBA <= '0';
				ldA <= '1';
				Etat_futur <= test;
			when B_A => 
				done <= '0'; 
				getA <= '0';
				getB <= '0';
				ldA <= '0';
				ldB <= '1';
				subBA <= '1';
				Etat_futur <= test;
			when nd =>
				getA <= '0';
				getB <= '0';
				ldA <= '0';
				ldB <= '0';
				subBA <= '0';
				done <= '1';
				Etat_futur <= vait;
				
		end case; 
	end process;			
end mixte;






