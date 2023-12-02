library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity lecture_memoire is
    port (data : out unsigned(31 downto 0);
          clk : in std_logic;
          rst : in std_logic);
end lecture_memoire;

architecture mixte of lecture_memoire is

  component Compteur is
	generic (n: positive := 17);
	port (cpt : out unsigned(n-1 downto 0);
	      max : in unsigned(n-1 downto 0);
	      clk : in std_logic;
	      rst : in std_logic);
  end component;

  component RAM_Video is
    port (
      clk  : in    std_logic; -- horloge
      addr : in    unsigned(14 downto 0); -- bus d'adresse
      do   : out   unsigned(31 downto 0); -- bus de données 
      we   : in    std_logic -- sélecteur lecture (0) /écriture (1)
      );
  end component;
      
  -- Ajouter ici les signaux internes nécessaires
signal curval : unsigned(14 downto 0);
signal dd : unsigned(14 downto 0);
signal data_bis : unsigned(31 downto 0); 
  -- Utiliser les mêmes types de signaux en interne que vu en externe
 
      
begin
    cpt: Compteur
	generic map (n => 15)
	port map (  max => dd,         
                    clk => clk,
                    rst => rst,
                    cpt => curval  );
	
    ram: RAM_Video
	port map (  clk  => clk,
      		    addr => curval,
           	    do  => data_bis,
      		    we => '0');
      		    
    data  <= data_bis;
	
end mixte;
