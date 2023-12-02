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

  component reggene
    generic (
      n: positive :=8
      );
    port (
      d   : in unsigned(n-1 downto 0);
      clk : in std_logic ;
      rst : in  std_logic;
      q   : out unsigned(n-1 downto 0)
      );
  end component;

    signal Etat_courant, Etat_futur: unsigned(2 downto 0);
    
begin
Q2 : reggene
    generic map(n => 3)

    port map (clk => clk,
              rst => reset,
              d => Etat_futur,
              q => Etat_courant);
              
  Etat_futur(0) <= (not Etat_courant(0) and e) or (not Etat_courant(1) and e);
  Etat_futur(1) <= (Etat_courant(1) and not Etat_courant(0)) or (Etat_courant(2) and not e) or (Etat_courant(0) and not e);
  Etat_futur(2) <= (e and Etat_courant(1) and Etat_courant(0));
  s <= Etat_courant(2);

end structural;

