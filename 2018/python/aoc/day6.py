from queue import Queue


def parse_coords(lines):
    parsed = [line.split(",",2) for line in lines if "," in line]
    return [(int(x), (int(y))) for (x,y) in parsed]


def example_coords():
    example = """
    1, 1
    1, 6
    8, 3
    3, 4
    5, 5
    8, 9
    """
    return parse_coords(example.splitlines())


def bounds(coords):
    x_max = max(x for (x,y) in coords)
    x_min = min(x for (x,y) in coords)
    y_max = max(y for (x,y) in coords)
    y_min = min(y for (x,y) in coords)

    return (x_min - 1,y_min - 1,x_max-x_min + 2,y_max-y_min + 2)


def distance(left,right):
    (x1,y1) = left
    (x2,y2) = right
    distance = abs(x1-x2) + abs(y1-y2)
    return (distance,left,right)


def coords_in_bounds(bounds):
    (orig_x, orig_y, width, height) = bounds
    for x in range(orig_x, orig_x + width):
        for y in range(orig_y, orig_y + height):
            yield(x,y)


def adjacency(coords, bounds):
    def generator():
        for (x,y) in coords_in_bounds(bounds):
            distances = [distance((x,y),coord) for coord in coords]
            (score,coord,nearest) = min(distances,key=lambda d: d[0])
            duplicates = [d for d in distances if d[0] == score]
            if len(duplicates) <= 1:
                yield coord, (score,nearest,sum(s[0] for s in distances))
            else:
                yield coord, (score,(-1,-1),sum(s[0] for s in distances))
    return dict(generator())


def edges(bounds):
    (orig_x,orig_y,width,height) = bounds

    for x in range(orig_x,orig_x + width):
        yield (x,orig_y)
        yield (x,orig_y+height-1)
    
    for y in range(orig_y,orig_y + height):
        yield (orig_x,y)
        yield (orig_x+width-1,y)


def bounded_coords(coords, adj, bounds):
    infinite_coords = set(closest for coord, (dist,closest,total) in adj.items() if coord in edges(bounds))
    return [c for c in coords if c not in infinite_coords]


def area(coord, adjacency):
    return len(list(c for (k,c) in adjacency.items() if c[1] == coord))


def contiguous_region(coord, adjacency, threshold):
    open_coords = set()
    closed_coords = set()
    region_coords = set()
    open_coords.add(coord)

    while len(open_coords) > 0:
        (x,y) = open_coords.pop()
        closed_coords.add((x, y))
        if (x,y) in adjacency:
            total = adjacency[(x,y)][2]
            if total < threshold:
                region_coords.add((x,y))
                neighbors = [(x-1,y),(x+1,y),(x,y-1),(x,y+1)]
                for n in neighbors:
                    if n not in closed_coords:
                        open_coords.add(n)


    return region_coords


def all_contiguous_regions(adjacency,threshold):
    remaining_cords = set(adjacency.keys())
    regions = []

    while len(remaining_cords) > 0:
        coord = remaining_cords.pop()
        print(coord)
        region = contiguous_region(coord,adjacency,threshold)
        regions.append(region)
        remaining_cords.difference_update(region)

    return regions


def real_coords():
    raw = """
    118, 274
    102, 101
    216, 203
    208, 251
    309, 68
    330, 93
    91, 179
    298, 278
    201, 99
    280, 272
    141, 312
    324, 290
    41, 65
    305, 311
    198, 68
    231, 237
    164, 224
    103, 189
    216, 207
    164, 290
    151, 91
    166, 250
    129, 149
    47, 231
    249, 100
    262, 175
    299, 237
    62, 288
    228, 219
    224, 76
    310, 173
    80, 46
    312, 65
    183, 158
    272, 249
    57, 141
    331, 191
    163, 359
    271, 210
    142, 137
    349, 123
    55, 268
    160, 82
    180, 70
    231, 243
    133, 353
    246, 315
    164, 206
    229, 97
    268, 94
    """
    return parse_coords(raw.splitlines())


if __name__ == "__main__":
    print("=========> Day 6 Part 1")
    coords = real_coords()
    bds = bounds(coords)
    adj = adjacency(coords,bds)
    bounded = bounded_coords(coords,adj,bds)
    print("Selected finite coordinates {}".format(bounded))
    biggest = max([(c, area(c,adj)) for c in bounded], key=lambda x: x[1])
    print("Maximum sized finite coordinate: {}".format(biggest))

    print("=========> Day 6 Part 2")
    regions = all_contiguous_regions(adj,10000)
    largest_region = max(regions, key=lambda x: len(x))
    print("Largest region size: {}".format(len(largest_region)))
