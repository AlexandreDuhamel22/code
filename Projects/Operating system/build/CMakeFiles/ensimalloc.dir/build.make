# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.27

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Produce verbose output by default.
VERBOSE = 1

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/homebrew/Cellar/cmake/3.27.6/bin/cmake

# The command to remove a file.
RM = /opt/homebrew/Cellar/cmake/3.27.6/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build

# Include any dependencies generated for this target.
include CMakeFiles/ensimalloc.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/ensimalloc.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/ensimalloc.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/ensimalloc.dir/flags.make

CMakeFiles/ensimalloc.dir/src/mem.c.o: CMakeFiles/ensimalloc.dir/flags.make
CMakeFiles/ensimalloc.dir/src/mem.c.o: /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem.c
CMakeFiles/ensimalloc.dir/src/mem.c.o: CMakeFiles/ensimalloc.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/ensimalloc.dir/src/mem.c.o"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -MD -MT CMakeFiles/ensimalloc.dir/src/mem.c.o -MF CMakeFiles/ensimalloc.dir/src/mem.c.o.d -o CMakeFiles/ensimalloc.dir/src/mem.c.o -c /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem.c

CMakeFiles/ensimalloc.dir/src/mem.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing C source to CMakeFiles/ensimalloc.dir/src/mem.c.i"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem.c > CMakeFiles/ensimalloc.dir/src/mem.c.i

CMakeFiles/ensimalloc.dir/src/mem.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling C source to assembly CMakeFiles/ensimalloc.dir/src/mem.c.s"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem.c -o CMakeFiles/ensimalloc.dir/src/mem.c.s

CMakeFiles/ensimalloc.dir/src/mem_internals.c.o: CMakeFiles/ensimalloc.dir/flags.make
CMakeFiles/ensimalloc.dir/src/mem_internals.c.o: /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_internals.c
CMakeFiles/ensimalloc.dir/src/mem_internals.c.o: CMakeFiles/ensimalloc.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/ensimalloc.dir/src/mem_internals.c.o"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -MD -MT CMakeFiles/ensimalloc.dir/src/mem_internals.c.o -MF CMakeFiles/ensimalloc.dir/src/mem_internals.c.o.d -o CMakeFiles/ensimalloc.dir/src/mem_internals.c.o -c /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_internals.c

CMakeFiles/ensimalloc.dir/src/mem_internals.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing C source to CMakeFiles/ensimalloc.dir/src/mem_internals.c.i"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_internals.c > CMakeFiles/ensimalloc.dir/src/mem_internals.c.i

CMakeFiles/ensimalloc.dir/src/mem_internals.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling C source to assembly CMakeFiles/ensimalloc.dir/src/mem_internals.c.s"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_internals.c -o CMakeFiles/ensimalloc.dir/src/mem_internals.c.s

CMakeFiles/ensimalloc.dir/src/mem_small.c.o: CMakeFiles/ensimalloc.dir/flags.make
CMakeFiles/ensimalloc.dir/src/mem_small.c.o: /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_small.c
CMakeFiles/ensimalloc.dir/src/mem_small.c.o: CMakeFiles/ensimalloc.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object CMakeFiles/ensimalloc.dir/src/mem_small.c.o"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -MD -MT CMakeFiles/ensimalloc.dir/src/mem_small.c.o -MF CMakeFiles/ensimalloc.dir/src/mem_small.c.o.d -o CMakeFiles/ensimalloc.dir/src/mem_small.c.o -c /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_small.c

CMakeFiles/ensimalloc.dir/src/mem_small.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing C source to CMakeFiles/ensimalloc.dir/src/mem_small.c.i"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_small.c > CMakeFiles/ensimalloc.dir/src/mem_small.c.i

CMakeFiles/ensimalloc.dir/src/mem_small.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling C source to assembly CMakeFiles/ensimalloc.dir/src/mem_small.c.s"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_small.c -o CMakeFiles/ensimalloc.dir/src/mem_small.c.s

CMakeFiles/ensimalloc.dir/src/mem_medium.c.o: CMakeFiles/ensimalloc.dir/flags.make
CMakeFiles/ensimalloc.dir/src/mem_medium.c.o: /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_medium.c
CMakeFiles/ensimalloc.dir/src/mem_medium.c.o: CMakeFiles/ensimalloc.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building C object CMakeFiles/ensimalloc.dir/src/mem_medium.c.o"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -MD -MT CMakeFiles/ensimalloc.dir/src/mem_medium.c.o -MF CMakeFiles/ensimalloc.dir/src/mem_medium.c.o.d -o CMakeFiles/ensimalloc.dir/src/mem_medium.c.o -c /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_medium.c

CMakeFiles/ensimalloc.dir/src/mem_medium.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing C source to CMakeFiles/ensimalloc.dir/src/mem_medium.c.i"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_medium.c > CMakeFiles/ensimalloc.dir/src/mem_medium.c.i

CMakeFiles/ensimalloc.dir/src/mem_medium.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling C source to assembly CMakeFiles/ensimalloc.dir/src/mem_medium.c.s"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_medium.c -o CMakeFiles/ensimalloc.dir/src/mem_medium.c.s

CMakeFiles/ensimalloc.dir/src/mem_large.c.o: CMakeFiles/ensimalloc.dir/flags.make
CMakeFiles/ensimalloc.dir/src/mem_large.c.o: /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_large.c
CMakeFiles/ensimalloc.dir/src/mem_large.c.o: CMakeFiles/ensimalloc.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building C object CMakeFiles/ensimalloc.dir/src/mem_large.c.o"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -MD -MT CMakeFiles/ensimalloc.dir/src/mem_large.c.o -MF CMakeFiles/ensimalloc.dir/src/mem_large.c.o.d -o CMakeFiles/ensimalloc.dir/src/mem_large.c.o -c /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_large.c

CMakeFiles/ensimalloc.dir/src/mem_large.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing C source to CMakeFiles/ensimalloc.dir/src/mem_large.c.i"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_large.c > CMakeFiles/ensimalloc.dir/src/mem_large.c.i

CMakeFiles/ensimalloc.dir/src/mem_large.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling C source to assembly CMakeFiles/ensimalloc.dir/src/mem_large.c.s"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/src/mem_large.c -o CMakeFiles/ensimalloc.dir/src/mem_large.c.s

# Object files for target ensimalloc
ensimalloc_OBJECTS = \
"CMakeFiles/ensimalloc.dir/src/mem.c.o" \
"CMakeFiles/ensimalloc.dir/src/mem_internals.c.o" \
"CMakeFiles/ensimalloc.dir/src/mem_small.c.o" \
"CMakeFiles/ensimalloc.dir/src/mem_medium.c.o" \
"CMakeFiles/ensimalloc.dir/src/mem_large.c.o"

# External object files for target ensimalloc
ensimalloc_EXTERNAL_OBJECTS =

libensimalloc.dylib: CMakeFiles/ensimalloc.dir/src/mem.c.o
libensimalloc.dylib: CMakeFiles/ensimalloc.dir/src/mem_internals.c.o
libensimalloc.dylib: CMakeFiles/ensimalloc.dir/src/mem_small.c.o
libensimalloc.dylib: CMakeFiles/ensimalloc.dir/src/mem_medium.c.o
libensimalloc.dylib: CMakeFiles/ensimalloc.dir/src/mem_large.c.o
libensimalloc.dylib: CMakeFiles/ensimalloc.dir/build.make
libensimalloc.dylib: CMakeFiles/ensimalloc.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --bold --progress-dir=/Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Linking C shared library libensimalloc.dylib"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/ensimalloc.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/ensimalloc.dir/build: libensimalloc.dylib
.PHONY : CMakeFiles/ensimalloc.dir/build

CMakeFiles/ensimalloc.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/ensimalloc.dir/cmake_clean.cmake
.PHONY : CMakeFiles/ensimalloc.dir/clean

CMakeFiles/ensimalloc.dir/depend:
	cd /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1 /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1 /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build /Users/alexandreduhamel/Desktop/ensimag/SEPC/sepc-tp1/build/CMakeFiles/ensimalloc.dir/DependInfo.cmake "--color=$(COLOR)"
.PHONY : CMakeFiles/ensimalloc.dir/depend

