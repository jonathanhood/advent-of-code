from aoc import fuel


def test_for_mass():
    assert fuel.for_mass(12, False) == 2
    assert fuel.for_mass(14, False) == 2
    assert fuel.for_mass(1969, False) == 654
    assert fuel.for_mass(100756, False) == 33583


def test_for_loadout():
    loadout = [12, 14, 1969, 100756]
    assert fuel.for_loadout(loadout, False) == 34241


def test_for_loadout_lines():
    loadout = ["12", "14", "1969", "100756"]
    assert fuel.for_loadout_lines(loadout, False) == 34241


def test_for_mass_with_fuel():
    assert fuel.for_mass(12, True) == 2
    assert fuel.for_mass(14, True) == 2
    assert fuel.for_mass(1969, True) == 966
    assert fuel.for_mass(100756, True) == 50346


def test_for_loadout_with_fuel():
    loadout = [12, 14, 1969, 100756]
    assert fuel.for_loadout(loadout, True) == 51316


def test_for_loadout_lines_with_fuel():
    loadout = ["12", "14", "1969", "100756"]
    assert fuel.for_loadout_lines(loadout, True) == 51316


def test_adjust_for_fuel_mass():
    assert fuel.adjust_for_fuel_mass(2) == 2
    assert fuel.adjust_for_fuel_mass(654) == 966
    assert fuel.adjust_for_fuel_mass(33583) == 50346
