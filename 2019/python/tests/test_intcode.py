from aoc import intcode


def test_small_programs():
    assert intcode.run_program([1, 0, 0, 0, 99]) == [2, 0, 0, 0, 99]
    assert intcode.run_program([2, 3, 0, 3, 99]) == [2, 3, 0, 6, 99]
    assert intcode.run_program([2, 4, 4, 5, 99]) == [2, 4, 4, 5, 99, 9801]
    assert intcode.run_program([1, 1, 1, 4, 99, 5, 6, 0, 99]) == [30, 1, 1, 4, 2, 5, 6, 0, 99]


def test_load_program():
    assert intcode.load_program("1, 0, 0, 0, 99") == [1, 0, 0, 0, 99]
