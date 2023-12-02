COMMON_DIR ?= common
AS       :=./assembleur
MEM_FILE :=boot_defaut.mem
MEM_ID :="i_periph/iRAM/XPM_RAM/xpm_memory_base_inst"
SRCDIR   := src

TOP 	 := system
#Liste des TOP autorisés
TOP_OK	 := system

OPT := true

include $(COMMON_DIR)/TPenv.mk

ENABLE_BACKEND := 0

ifeq ($(shell if [ -f magic.wcfg ] ; then echo "OK" ; fi),OK)
MAGIC_VIEW:=-view magic.wcfg
endif


ifdef PROG
$(MEM_FILE): $(SRCDIR)/$(PROG).as force
	$(call q, $(AS) $<, "  AS     $<",as.log)
force:
endif

copies:
	cp init.mem $(MEM_FILE)
	cp src/compteur.as src/exo2.as

preptgz: copies golden.bit

include $(COMMON_DIR)/TPrules.mk
