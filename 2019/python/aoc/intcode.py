from typing import List


class Opcodes:
    HALT = 99
    ADD = 1
    MULTIPLY = 2

    INSTRUCTION_WIDTH = 4


class ProgramHalted(Exception):
    def __init__(self, pc: int):
        self.pc = pc


class InvalidOpcode(Exception):
    def __init__(self, opcode: int, pc: int):
        self.opcode = opcode
        self.pc = pc


def process_opcode(pc: int, memory: List[int]) -> None:
    code = memory[pc]
    if code == Opcodes.HALT:
        raise ProgramHalted(pc)
    elif code == Opcodes.ADD:
        left_pointer = memory[pc + 1]
        right_pointer = memory[pc + 2]
        dest_pointer = memory[pc + 3]
        result = memory[left_pointer] + memory[right_pointer]
        store_result(memory, dest_pointer, result)
    elif code == Opcodes.MULTIPLY:
        left_pointer = memory[pc + 1]
        right_pointer = memory[pc + 2]
        dest_pointer = memory[pc + 3]
        result = memory[left_pointer] * memory[right_pointer]
        store_result(memory, dest_pointer, result)
    else:
        raise InvalidOpcode(code, pc)


def store_result(memory: List[int], address: int,  value: int) -> None:
    if address >= len(memory):
        memory.extend([0] * (address - len(memory) + 1))
    memory[address] = value


def run_program(memory: List[int]) -> List[int]:
    try:
        pc = 0
        while True:
            process_opcode(pc, memory)
            pc = pc + Opcodes.INSTRUCTION_WIDTH
    except ProgramHalted:
        return memory


def load_program(program: str) -> List[int]:
    return [int(op) for op in program.split(",")]


if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser(description="Execute an intcode program.")
    parser.add_argument("--replace",
                        "-r",
                        help="Replace a given memory position with the given value (e.g. '0,100')",
                        action="append")
    parser.add_argument("--peek",
                        "-p",
                        type=int,
                        help="Peek at the given address after program execution finished",
                        action="append")
    parser.add_argument("program",
                        help="The intcode program file.")

    args = parser.parse_args()

    with open(args.program) as program_file:
        memory = load_program(program_file.read())
        for replacement in args.replace:
            (pos, value) = replacement.split(",")
            store_result(memory, int(pos), int(value))
        run_program(memory)
        print(f"address value")
        for peek_pos in args.peek:
            print(f"{peek_pos} {memory[peek_pos]}")

