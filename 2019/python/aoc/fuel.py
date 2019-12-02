import math
from typing import Iterable


def for_mass(mass: int, adjust_for_fuel: bool) -> int:
    required_fuel = math.floor(mass / 3.0) - 2
    if adjust_for_fuel:
        return adjust_for_fuel_mass(required_fuel)
    else:
        return required_fuel


def for_loadout(masses: Iterable[int], adjust_for_fuel: bool) -> int:
    return sum(for_mass(m, adjust_for_fuel) for m in masses)


def for_loadout_lines(masses: Iterable[str], adjust_for_fuel: bool) -> int:
    return for_loadout( (int(m) for m in masses), adjust_for_fuel)


def adjust_for_fuel_mass(fuel: int) -> int:
    extra_fuel = for_mass(fuel, False)
    if extra_fuel <= 0:
        return fuel
    else:
        return fuel + adjust_for_fuel_mass(extra_fuel)


if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser(description="Calculate the total fuel for a given loadout.")
    parser.add_argument('--add-fuel-mass',
                        action="store_true",
                        help="Account for the weight of the fuel itself in calculations.")
    parser.add_argument('file',
                        help="The filename to load mass data from.")

    args = parser.parse_args()

    with open(args.file) as loadout_file:
        print(for_loadout_lines(loadout_file.readlines(), args.add_fuel_mass))

