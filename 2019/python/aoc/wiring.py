from typing import List, Tuple, Set, Optional, Iterable
import functools


class SpanDirection:
    UP = "U"
    DOWN = "D"
    LEFT = "L"
    RIGHT = "R"


def parse_path(path: str) -> Tuple[Tuple[str, int], ...]:
    result = []
    for elem in path.split(","):
        result.append((elem[0], int(elem[1:])))
    return tuple(result)


def coords_for_path(path: Iterable[Tuple[str, int]]) -> Iterable[Tuple[int, int]]:
    x, y = (0, 0)
    for (direction, distance) in path:
        while distance > 0:
            if direction == SpanDirection.RIGHT:
                x += 1
            elif direction == SpanDirection.LEFT:
                x -= 1
            elif direction == SpanDirection.UP:
                y += 1
            elif direction == SpanDirection.DOWN:
                y -= 1
            else:
                raise Exception(f"Unknown direction {direction}")

            distance -= 1
            yield x, y


def intersections(paths: Iterable[Iterable[Tuple[str, int]]]) -> Set[Tuple[int, int]]:
    sets = [set(coords_for_path(path)) for path in paths]
    return functools.reduce(lambda x, y: x & y, sets)


def closest_intersection_manhattan(paths: Iterable[Iterable[Tuple[str, int]]]) -> Optional[Tuple[int, int, int]]:
    def manhattan(coord):
        return abs(coord[0]) + abs(coord[1])

    with_distances = [(x, y, manhattan((x, y))) for (x, y) in intersections(paths)]
    return min(with_distances, key=lambda x: x[2])


def closest_intersection_steps(paths: Iterable[Iterable[Tuple[str, int]]]) -> Optional[Tuple[int, int, int]]:
    all_coords = [list(coords_for_path(p)) for p in paths]

    def cost(coord: Tuple[int, int]) -> int:
        return sum(ac.index(coord) for ac in all_coords) + 2

    with_costs = [(x, y, cost((x, y))) for (x, y) in intersections(paths)]
    return min(with_costs, key=lambda x: x[2])


if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser(description="Find the nearest intersection between wire paths")
    parser.add_argument("strategy", choices=("manhattan", "wire-distance"), help="Choose the mode to run the solver in.")
    parser.add_argument("wires", help="The wire path description")

    args = parser.parse_args()

    with open(args.wires) as wires_file:
        paths = [parse_path(line) for line in wires_file.readlines()]

        if args.strategy == "manhattan":
            result = closest_intersection_manhattan(paths)
            print(result)
        else:
            result = closest_intersection_steps(paths)
            print(result)

