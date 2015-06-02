# BeeHive
#
#  This program implement a way to measure the smallest distance
#  between 2 cells in a HexGrid (like in a BeeHive).
#
#  User input should be like:
#  <from> <to>
#  ...
#  0 0
#
#  Where <from> and <to> should be positive integers representing the cells.
#  The coordinates can be repeated any number of times.
#  The (0, 0) pair indicates end of input.
#
#  The cell numbering begins at the middle with 1 and grows clockwise from the bottom left. Ex:
#                   12  13  14
#                 11  04  05  15
#               10  03  01  06  16
#                 09  02  07  17
#                   08  19  18
#
# Author: Felipo Soranz
# Time: 4 hours and 50 minutes
#  __init__ = 45 minutes
#  [gs]et_element, print, fill = 2 hours and 30 minutes
#  find = 15 minutes
#  path = 30 minutes
#  main = 50 minutes

import sys
from math import sqrt, ceil, floor
from pprint import pprint

class Point:
    def __init__(self, lin, col):
        self.lin = lin
        self.col = col

    def __repr__(self):
        return "<lin:" + str(self.lin) + ", col:" + str(self.col) + ">"
    
    def copy(self):
        return Point(self.lin, self.col)

class BeeHive:
    """A class to contain the beehive"""

    def __init__(self, max_value):
        self.max_value = max_value

        # method by deducing number of rings...
        #outer_rings = ceil(sqrt(max_value * 2 / 6))
        #print("outer_rings =", outer_rings)

        # Find the number of outer_rings by reversing the formula for the
        # area of hexagon, since max_value <= total area of hexagon
        self.outer_rings = ceil(sqrt(max_value / (3 * sqrt(3) / 2)))
        
        # HACK: Allow only even number of outer_rings to compensate
        #       for positional calculations in hex grid
        if self.outer_rings % 2 == 1:
            self.outer_rings += 1

        # Size = diameter of the hexagon
        self.size = 1 + 2 * self.outer_rings

        # Middle is the cell where 1 needs to be placed
        self.middle = Point(self.outer_rings, self.outer_rings)

        # Create base matrix
        self.matrix = []
        for line in range(self.size):
            self.matrix.append([])
            for cell in range(self.size):
                #self.matrix[line].append(line * self.size + cell)
                self.matrix[line].append(0)

        self.fill()
        

    def get_element(self, point):
        """Get element at position defined by point"""
        # col will be X.5 for cells in odd rows, compensate!
        return self.matrix[point.lin][floor(point.col)]

    def set_element(self, point, val):
        """Set element at position defined by point, with value val"""
        # col will be X.5 for cells in odd rows, compensate!
        self.matrix[point.lin][floor(point.col)] = val

    def find(self, val):
        """Get coordinates in the hexgrid for a given value"""
        for line in range(self.size):
            for cell in range(self.size):
                pos = Point(line, cell)
                # Odd rows have elements in "intermediate" columns
                if (line % 2 == 1):
                    pos.col += 0.5
                if self.get_element(pos) == val:
                    return pos
        return None

    def print(self):
        """Print a representation of the hex grid"""
        
        # To print elements aligned, find the width of the largest element
        #  and create a formatting string for the elements
        len_cell = len(str(self.max_value))
        form = "%%0%dd" % (len_cell,)

        for line in range(self.size):
            # Print odd lines indented
            print(form % (line,), end=": ")
            if line % 2 == 1:
                print(" "*len_cell, end="")
            for cell in range(self.size):             
                cell_value = self.get_element(Point(line, cell))
                # Keep cells aligned (used for test only, shouldn't happen)
                if len(str(cell_value)) > len_cell:
                    cell_value = int("9"*len_cell)
                if cell_value > 0:
                    print(form % (cell_value,), end=" "*len_cell)
                else:
                    print(" "*len_cell, end=" "*len_cell)
            print()

    def fill(self):
        """Fill the elements in the matrix by 'drawing' them clockwise starting from the bottom-left corner"""
        
        # Draw middle cell (1)
        self.set_element(self.middle, 1)

        # Started with 2. Increment for every number added.
        value = 2

        # Start in ring 1 outside of center
        ring = 1

        # Draw rings around middle until value reached
        while value <= self.max_value:

            # Calculate coordinate for the 6 vertexes of the current ring being drawn
            bottom_left = Point(self.middle.lin + ring,
                                self.middle.col - ring / 2.0)
            left        = Point(self.middle.lin,
                                self.middle.col - ring)
            up_left     = Point(self.middle.lin - ring,
                                self.middle.col - ring / 2.0)
            up_right    = Point(self.middle.lin - ring,
                                self.middle.col + ring / 2.0)
            right       = Point(self.middle.lin,
                                self.middle.col + ring)
            bottom_right= Point(self.middle.lin + ring,
                                self.middle.col + ring / 2.0)

            # Order of 'drawing'
            #       3
            #    2  _  4
            #      / \
            #      \_/
            #    1     5
            #       6

            # Start from bottom_left corner
            pos = Point(bottom_left.lin, bottom_left.col)
            
            # 1 Draw bottom left \
            while pos.lin > left.lin:
                self.set_element(pos, value)
                pos.lin -= 1
                pos.col -= 0.5
                value += 1

            # 2 Draw up left / 
            while pos.lin > up_left.lin:
                self.set_element(pos, value)
                pos.lin -= 1
                pos.col += 0.5
                value += 1                

            # 3 Draw up line -
            while pos.col < up_right.col:
                self.set_element(pos, value)
                pos.col += 1
                value += 1
                
            # 4 Draw up right \
            while pos.lin < right.lin:
                self.set_element(pos, value)
                pos.lin += 1
                pos.col += 0.5
                value += 1
                
            # 5 Draw bottom right /
            while pos.lin < bottom_right.lin:
                self.set_element(pos, value)
                pos.lin += 1
                pos.col -= 0.5
                value += 1
          
            # 6 Draw bottom line -
            while pos.col > bottom_left.col:
                self.set_element(pos, value)
                pos.col -= 1
                value += 1

            # Draw next ring
            ring += 1
        
    def path(self, from_val, to_val):
        """Return a list of coordinates forming a path from from_val to to_val"""
        pos_from = self.find(from_val)
        pos_to = self.find(to_val)

        # Invalid arguments?  
        if pos_from == None or pos_to == None:
            return None
        
        result = []
        result.append(pos_from)

        # Start from pos_from
        pos = pos_from.copy()

        while True:
            diff_lin = pos_to.lin - pos.lin
            diff_col = pos_to.col - pos.col

            if diff_lin == 0:
                # Same line, just update colum
                if diff_col < 0:
                    pos.col -= 1
                else:
                    pos.col += 1
            else:
                # Move diagonally to position
                if diff_col < 0:
                    pos.col -= 0.5
                else:
                    pos.col += 0.5
                if diff_lin < 0:
                    pos.lin -= 1
                else:
                    pos.lin += 1

            # DEBUG: print(pos.lin, pos.col)
                    
            # Update path
            result.append(pos.copy())

            if pos.lin == pos_to.lin and pos.col == pos_to.col:
                break
                    
        #result.append(pos.copy())
        return result

def test_BeeHive():
    rings = 0
    for ring in range(1, 5):
        rings += (ring - 1) * 6
        b = BeeHive(rings + 1)
        print("max_value =", b.max_value)
        print("rings =", b.rings)
        print("size =", b.size)    

def test_BeeHive_print():
    elements = 0
    for size in range(1, 8):
        print("size =", size)
        elements += (size - 1) * 6
        print("max =", elements + 1)
        b = BeeHive(elements + 1)
        b.print()
    print()

def test_BeeHive_find():
    b = BeeHive(50)
    b.print()
    pos = b.find(30)
    print(pos.lin, pos.col)

def test_BeeHive_path():
    b = BeeHive(50)
    b.print()
    for dest in (13, 25, 20, 34, 19, 50):
        print ("dest =", dest)
        path = b.path(32, dest)
        for p in path:
            print("line = %d, column = %.1f, value = %d" % (p.lin, p.col, b.get_element(p)))
        print("length =", len(path))
    
def main():
    # Keep track of user inputs
    from_to_pairs = []

    # Keep track of largest position entered
    largest = 0
    
    while True:
        # receive line and process
        line = input()

        # ignore empty lines
        if len(line) == 0:
            print("<from> <to>")
            continue

        values = line.split(" ")

        # ignore invalid input
        if len(values) != 2:
            print("<from> <to>")
            continue

        from_val = int(values[0])
        to_val = int(values[1])

        # terminate input on 0 0
        if from_val == 0 and to_val == 0:
            break
        if from_val > largest:
            largest = from_val
        if to_val > largest:
            largest = to_val
        from_to_pairs.append( (from_val, to_val) )

    # DEBUG: pprint(from_to_pairs)
    # DEBUG: print("largest =", largest)
    
    # Create a beehive with the largest value entered
    b = BeeHive(largest)

    # Don't print if screen is too large
    if largest < 500:
        b.print()

    for from_to in from_to_pairs:
        from_val, to_val = from_to
        path = b.path(from_val, to_val)
        print("from = %d, to = %d, length = %d" % (from_val, to_val, len(path)))
        # Don't print too long paths
        # DEBUG: if len(path) < 20:
        # DEBUG:     pprint(path)
        print(",".join([ str(b.get_element(p)) for p in path ]))                    

    return 0
    
if __name__ == '__main__':
    status = main()
    #sys.exit(status)

#test_BeeHive()
#test_BeeHive_print()
#test_BeeHive_find()
#test_BeeHive_path()
