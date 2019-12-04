from aoc import wiring
import pytest


def test_parse_path():
    path = wiring.parse_path("U7,R6,D4,L4")
    assert path == (
        (wiring.SpanDirection.UP, 7),
        (wiring.SpanDirection.RIGHT, 6),
        (wiring.SpanDirection.DOWN, 4),
        (wiring.SpanDirection.LEFT, 4)
    )


def test_coords_for_path():
    path = wiring.parse_path("R8,U5,L5,D10")
    coords = wiring.coords_for_path(path)

    assert list(coords) == [
        # Right 8
        (1, 0), (2, 0), (3, 0), (4, 0), (5, 0), (6, 0), (7, 0), (8, 0),

        # Up 5
        (8, 1), (8, 2), (8, 3), (8, 4), (8, 5),

        # Left 5
        (7, 5), (6, 5), (5, 5), (4, 5), (3, 5),

        # Down 10
        (3, 4), (3, 3), (3, 2), (3, 1), (3, 0), (3, -1), (3, -2), (3, -3), (3, -4), (3, -5),
    ]


def test_find_intersections():
    paths = [
        wiring.parse_path("R8,U5,L5,D3"),
        wiring.parse_path("U7,R6,D4,L4")
    ]

    assert wiring.intersections(paths) == {
        (3, 3),
        (6, 5)
    }


@pytest.mark.parametrize("left,right,expected_distance", [
    ("R8,U5,L5,D3", "U7,R6,D4,L4", 6),
    ("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", 159),
    ("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", 135)
])
def test_find_closest_intersection_manhatten(left, right, expected_distance):
    paths = [wiring.parse_path(left), wiring.parse_path(right)]
    closest = wiring.closest_intersection_manhattan(paths)
    assert closest[2] == expected_distance


@pytest.mark.parametrize("left,right,expected_distance", [
    ("R8,U5,L5,D3", "U7,R6,D4,L4", 30),
    ("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", 610),
    ("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", 410)
])
def test_find_closest_intersection_steps(left, right, expected_distance):
    paths = [wiring.parse_path(left), wiring.parse_path(right)]
    closest = wiring.closest_intersection_steps(paths)
    assert closest[2] == expected_distance
