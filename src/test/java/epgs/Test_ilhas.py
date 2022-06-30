import unittest
# From: https://eli.thegreenplace.net/2011/08/02/python-unit-testing-parametrized-test-cases
class ParametrizedTestCase(unittest.TestCase):
    """ TestCase classes that want to be parametrized should
        inherit from this class.
    """
    def __init__(self, methodName='runTest', param=None):
        super(ParametrizedTestCase, self).__init__(methodName)
        self.param = param

    @staticmethod
    def parametrize(testcase_klass, param=None):
        """ Create a suite containing all tests taken from the given
            subclass, passing them the parameter 'param'.
        """
        testloader = unittest.TestLoader()
        testnames = testloader.getTestCaseNames(testcase_klass)
        suite = unittest.TestSuite()
        for name in testnames:
            suite.addTest(testcase_klass(name, param=param))
        return suite
    
###########
# Test Data
import jgrapht
from jgrapht.generators import grid
g1 = jgrapht.create_graph(directed=False,weighted=False)
grid(g1,5,5)
v_g1 = {
    0 : {'label' : 'A'},
    1 : {'label' : 'A'},
    2 : {'label' : 'A'},
    3 : {'label' : 'A'},
    4 : {'label' : 'A'},
    5 : {'label' : 'A'},
    6 : {'label' : 'T'},
    7 : {'label' : 'T'},
    8 : {'label' : 'A'},
    9 : {'label' : 'A'},
    10 : {'label' : 'A'},
    11 : {'label' : 'A'},
    12 : {'label' : 'A'},
    13 : {'label' : 'T'},
    14 : {'label' : 'A'},
    15 : {'label' : 'A'},
    16 : {'label' : 'A'},
    17 : {'label' : 'T'},
    18 : {'label' : 'T'},
    19 : {'label' : 'T'},
    20 : {'label' : 'A'},
    21 : {'label' : 'A'},
    22 : {'label' : 'A'},
    23 : {'label' : 'T'},
    24 : {'label' : 'A'}
}

g2 = jgrapht.create_graph(directed=False,weighted=False)
grid(g2,5,5)
v_g2 = {
    0 : {'label' : 'A'},
    1 : {'label' : 'A'},
    2 : {'label' : 'A'},
    3 : {'label' : 'A'},
    4 : {'label' : 'A'},
    5 : {'label' : 'A'},
    6 : {'label' : 'A'},
    7 : {'label' : 'A'},
    8 : {'label' : 'A'},
    9 : {'label' : 'A'},
    10 : {'label' : 'A'},
    11 : {'label' : 'A'},
    12 : {'label' : 'A'},
    13 : {'label' : 'A'},
    14 : {'label' : 'A'},
    15 : {'label' : 'A'},
    16 : {'label' : 'A'},
    17 : {'label' : 'A'},
    18 : {'label' : 'A'},
    19 : {'label' : 'A'},
    20 : {'label' : 'A'},
    21 : {'label' : 'A'},
    22 : {'label' : 'A'},
    23 : {'label' : 'A'},
    24 : {'label' : 'A'}
}

g3 = jgrapht.create_graph(directed=False,weighted=False)
grid(g3,5,5)
v_g3 = {
    0 : {'label' : 'T'},
    1 : {'label' : 'T'},
    2 : {'label' : 'T'},
    3 : {'label' : 'T'},
    4 : {'label' : 'T'},
    5 : {'label' : 'T'},
    6 : {'label' : 'T'},
    7 : {'label' : 'T'},
    8 : {'label' : 'T'},
    9 : {'label' : 'T'},
    10 : {'label' : 'T'},
    11 : {'label' : 'T'},
    12 : {'label' : 'T'},
    13 : {'label' : 'T'},
    14 : {'label' : 'T'},
    15 : {'label' : 'T'},
    16 : {'label' : 'T'},
    17 : {'label' : 'T'},
    18 : {'label' : 'T'},
    19 : {'label' : 'T'},
    20 : {'label' : 'T'},
    21 : {'label' : 'T'},
    22 : {'label' : 'T'},
    23 : {'label' : 'T'},
    24 : {'label' : 'T'}
}

g4 = jgrapht.create_graph(directed=False,weighted=False)
grid(g4,5,5)
v_g4 = {
    0 : {'label' : 'T'},
    1 : {'label' : 'A'},
    2 : {'label' : 'T'},
    3 : {'label' : 'A'},
    4 : {'label' : 'A'},
    5 : {'label' : 'A'},
    6 : {'label' : 'A'},
    7 : {'label' : 'A'},
    8 : {'label' : 'A'},
    9 : {'label' : 'A'},
    10 : {'label' : 'A'},
    11 : {'label' : 'A'},
    12 : {'label' : 'A'},
    13 : {'label' : 'A'},
    14 : {'label' : 'A'},
    15 : {'label' : 'T'},
    16 : {'label' : 'A'},
    17 : {'label' : 'A'},
    18 : {'label' : 'A'},
    19 : {'label' : 'A'},
    20 : {'label' : 'A'},
    21 : {'label' : 'A'},
    22 : {'label' : 'A'},
    23 : {'label' : 'A'},
    24 : {'label' : 'T'}
}

g5 = jgrapht.create_graph(directed=False,weighted=False)
grid(g5,5,5)
v_g5 = {
    0 : {'label' : 'A'},
    1 : {'label' : 'A'},
    2 : {'label' : 'A'},
    3 : {'label' : 'A'},
    4 : {'label' : 'A'},
    5 : {'label' : 'A'},
    6 : {'label' : 'T'},
    7 : {'label' : 'T'},
    8 : {'label' : 'T'},
    9 : {'label' : 'A'},
    10 : {'label' : 'T'},
    11 : {'label' : 'T'},
    12 : {'label' : 'T'},
    13 : {'label' : 'T'},
    14 : {'label' : 'A'},
    15 : {'label' : 'A'},
    16 : {'label' : 'T'},
    17 : {'label' : 'T'},
    18 : {'label' : 'T'},
    19 : {'label' : 'A'},
    20 : {'label' : 'A'},
    21 : {'label' : 'A'},
    22 : {'label' : 'A'},
    23 : {'label' : 'A'},
    24 : {'label' : 'A'}
}

g6 = jgrapht.create_graph(directed=False,weighted=False)
grid(g6,5,5)
v_g6 = {
    0 : {'label' : 'A'},
    1 : {'label' : 'A'},
    2 : {'label' : 'A'},
    3 : {'label' : 'A'},
    4 : {'label' : 'A'},
    5 : {'label' : 'A'},
    6 : {'label' : 'T'},
    7 : {'label' : 'T'},
    8 : {'label' : 'T'},
    9 : {'label' : 'A'},
    10 : {'label' : 'T'},
    11 : {'label' : 'A'},
    12 : {'label' : 'T'},
    13 : {'label' : 'T'},
    14 : {'label' : 'A'},
    15 : {'label' : 'A'},
    16 : {'label' : 'T'},
    17 : {'label' : 'T'},
    18 : {'label' : 'T'},
    19 : {'label' : 'A'},
    20 : {'label' : 'A'},
    21 : {'label' : 'A'},
    22 : {'label' : 'A'},
    23 : {'label' : 'A'},
    24 : {'label' : 'A'}
}

g7 = jgrapht.create_graph(directed=False,weighted=False)
g7.add_vertex(0)
v_g7 = {
    0 : {'label' : 'A'}
}

g8 = jgrapht.create_graph(directed=False,weighted=False)
grid(g8,5,5)
v_g8 = {
    0 : {'label' : 'T'},
    1 : {'label' : 'A'},
    2 : {'label' : 'T'},
    3 : {'label' : 'A'},
    4 : {'label' : 'T'},
    5 : {'label' : 'A'},
    6 : {'label' : 'T'},
    7 : {'label' : 'A'},
    8 : {'label' : 'T'},
    9 : {'label' : 'A'},
    10 : {'label' : 'T'},
    11 : {'label' : 'A'},
    12 : {'label' : 'T'},
    13 : {'label' : 'A'},
    14 : {'label' : 'T'},
    15 : {'label' : 'A'},
    16 : {'label' : 'T'},
    17 : {'label' : 'A'},
    18 : {'label' : 'T'},
    19 : {'label' : 'A'},
    20 : {'label' : 'T'},
    21 : {'label' : 'A'},
    22 : {'label' : 'T'},
    23 : {'label' : 'A'},
    24 : {'label' : 'T'}
}

g9 = jgrapht.create_graph(directed=False,weighted=False)
grid(g9,2,2)
v_g9 = {
    0 : {'label' : 'T'},
    1 : {'label' : 'T'},
    2 : {'label' : 'T'},
    3 : {'label' : 'A'}
}

class Test_ilhas(ParametrizedTestCase):
  def test_base (self):
    f,g,v_g,expected = self.param
    result = f(g,v_g)
    self.assertTrue(all(any(all(x in r for x in e) and all(x in e for x in r) for e in expected) for r in result))
    self.assertTrue(all(any(all(x in r for x in e) and all(x in e for x in r) for r in result) for e in expected))

## Obs: A ordem dos elementos nas listas e das listas no resultado produzido 
## pela sua função não precisa ser igual. Basta conter os mesmos elementos/listas.
params = [[g1,v_g1,[[6, 7], [17, 18, 19, 23, 13]]],
          [g2,v_g2,[]],
          [g3,v_g3,[[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]]],
          [g4,v_g4,[[0], [2], [15], [24]]],
          [g5,v_g5,[[16, 17, 18, 6, 7, 8, 10, 11, 12, 13]]],
          [g6,v_g6,[[10],[6,7,16, 17, 18, 8, 12, 13]]],
          [g7,v_g7,[]],
          [g8,v_g8,[[0], [2], [4], [6], [8], [10], [12], [14], [16], [18], [20], [22], [24]]],
          [g9,v_g9,[[0,1,2]]]
          ]

