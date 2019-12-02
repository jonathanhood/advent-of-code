import itertools
import copy
from . import intcode

if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser(description="Scan inputs to the given program for a specified result.")
    parser.add_argument("program", help="The intcode program file.")
    parser.add_argument("output", type=int, help="Result to find proper inputs for.")

    args = parser.parse_args()

    with open(args.program) as program_file:
        initial_memory = intcode.load_program(program_file.read())

        for (noun, verb) in itertools.permutations(range(0, 100), 2):
            memory = copy.deepcopy(initial_memory)
            memory[1] = noun
            memory[2] = verb
            intcode.run_program(memory)
            result = memory[0]

            if result == args.output:
                print(f"Found inputs. Noun -> {noun}, Verb -> {verb}")
                print(f"Result -> {(100*noun) + verb}")
                exit(0)

        print(f"No inputs found")
        exit(1)
