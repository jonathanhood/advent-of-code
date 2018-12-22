from aoc.day6 import *

def test_parse_coords():
    coords = ["1,2","3,4","5,6"]
    parsed = parse_coords(coords)
    assert(parsed == [(1,2), (3,4), (5,6)])


def test_example_coords():
    assert(example_coords() == [(1,1),(1,6),(8,3),(3,4),(5,5),(8,9)])


def test_bounds():
    assert(bounds(example_coords()) == (0, 0, 9, 10))


def test_distance():
    assert(distance((1,1),(2,2)) == (2,(1,1),(2,2)))


def test_example_adjacency():
    result = list(adjacency(example_coords(), bounds(example_coords())))

    for coord in result:
        print(coord)

    assert( (2,(2,2),(1,1),36) in result)
    assert( (2,(7,8),(8,9),42) in result)


def test_example_edges():
    coords = example_coords()
    bnds = bounds(coords)
    result = list(edges(bnds))
    assert (8,9) in result


def test_example_bounded_coords():
    coords = example_coords()
    bds = bounds(coords)
    adj = list(adjacency(coords, bds))
    bounded = bounded_coords(coords,adj,bds)
    assert( bounded == [(3,4),(5,5)])


def test_example_area():
    coords = example_coords()
    bds = bounds(coords)
    adj = list(adjacency(coords,bds))

    assert(area((3,4), adj) == 9)
    assert(area((5,5), adj) == 17)


def test_example_contiguous():
    coords = example_coords()
    bds = bounds(coords)
    adj = list(adjacency(coords,bds))
    region = contiguous((4,3),adj)
    assert len(region) == 16
