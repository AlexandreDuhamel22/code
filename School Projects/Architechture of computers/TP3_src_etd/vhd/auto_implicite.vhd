library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity auto is
    port ( clk:     in  std_logic;
           reset:   in  std_logic;
           e:       in  std_logic;
           s:       out std_logic);
end auto;

architecture structural of auto is
	type Etat_type is (A,B,C,D,eE); 
    signal Etat_courant, Etat_futur: Etat_type; -- on définit nos 2 signaux
begin
	
	Registre : process (clk,reset) 
	begin
		if rising_edge(clk) and reset = '0' then
		    Etat_courant <= Etat_futur;
		end if;
		if reset = '1' then
		    Etat_courant <= A;
		end if;
	end process;

    -- Pour décrire la fonction de transition et la fonction de sortie, on peut aussi utiliser un process
	Combinatoire : process(e, Etat_courant)
	begin
		s <= '0'; -- Un processus décrit la comportement séquentiellement. Il est donc possible d'attribuer une valeur par défaut dans la description (ici '0') et de changer cette valeur plus loin (avant le end évidemment).
		case Etat_courant is  -- Dans un process, on peut utiliser une structure de type case (mots clés :  case et is)
			when A => -- comprendre : quand Etat_courant vaut A faire ...
				if e = '0' then -- Dans un process, on peut aussi utiliser un if/then/else
					Etat_futur <= A;
				else
					Etat_futur <= B;
				end if;
			when B => -- comprendre : quand Etat_courant vaut A faire ...
				if e = '0' then -- Dans un process, on peut aussi utiliser un if/then/else
					Etat_futur <= C;
				else
					Etat_futur <= B;
				end if;
			when C => -- comprendre : quand Etat_courant vaut A faire ...
				if e = '0' then -- Dans un process, on peut aussi utiliser un if/then/else
					Etat_futur <= C;
				else
					Etat_futur <= D;
				end if;
			when D => -- comprendre : quand Etat_courant vaut A faire ...
				if e = '0' then -- Dans un process, on peut aussi utiliser un if/then/else
					Etat_futur <= C;
				else
					Etat_futur <= eE;
				end if;
			when eE => -- comprendre : quand Etat_courant vaut A faire ...
				s <= '1';
				if e = '0' then -- Dans un process, on peut aussi utiliser un if/then/else
					Etat_futur <= C;
				else
					Etat_futur <= B;
				end if;
				
		end case; -- fin de la structure case
	end process;
end structural;
