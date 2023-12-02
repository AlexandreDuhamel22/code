library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use work.PKG.all;

entity CPU_CND is
    generic (
        mutant      : integer := 0
    );
    port (
        rs1         : in w32;
        alu_y       : in w32;
        IR          : in w32;
        slt         : out std_logic;
        jcond       : out std_logic
    );
end entity;

architecture RTL of CPU_CND is
    signal cable, z, s, signe_x, signe_y : std_logic;
    signal Xus, Yus, res     : unsigned(32 downto 0);
    signal Xs, Ys     : signed(32 downto 0);
begin
    cable <= ((not(IR(13)) and IR(6)) or (not(IR(12)) and not(IR(6))));
    signe_x <= rs1(31) when cable = '1' else '0';
    signe_y <= alu_y(31) when cable = '1' else '0';
    Xus <= signe_x & rs1;
    Yus  <= signe_y & alu_y;

    z <= '1' when (Xus-Yus = 0) else '0';
    res <= Xus - Yus;
    s <= res(32);

    jcond <= ((z xor IR(12)) and (not(IR(14)))) or ((s xor IR(12)) and IR(14));
    slt <= s;

end architecture;
