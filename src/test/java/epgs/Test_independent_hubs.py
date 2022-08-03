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

# Test Data
import jgrapht

g1 = jgrapht.create_graph(directed=False, weighted=False)
g1.add_vertices_from([x for x in range(0,4)])
g1.add_edge(0,1,edge=0)
g1.add_edge(0,2,edge=1)
g1.add_edge(0,3,edge=2)
g1.add_edge(1,3,edge=3)
g1.add_edge(2,3,edge=4)

# Grafo não direcionado conectado
g2 = jgrapht.create_graph(directed=False, weighted=False)
g2.add_vertices_from([x for x in range(9)])
g2.add_edge(0,1,edge=0)
g2.add_edge(0,2,edge=1)
g2.add_edge(1,3,edge=2)
g2.add_edge(1,8,edge=3)
g2.add_edge(7,2,edge=4)
g2.add_edge(2,6,edge=5)
g2.add_edge(2,4,edge=6)
g2.add_edge(3,5,edge=7)
g2.add_edge(6,7,edge=8)
g2.add_edge(7,8,edge=9)

g3 = jgrapht.create_graph(directed=False, weighted=False)

g4 = jgrapht.create_graph(directed=False,weighted=False)
from jgrapht.generators import generalized_petersen
generalized_petersen(g4,5,1)

g5 = jgrapht.create_graph(directed=False, weighted=False)
g5.add_vertices_from([0,1,2,3,4])
g5.add_edge(0,1)
g5.add_edge(1,2)
g5.add_edge(2,3)
g5.add_edge(2,4)
g5.add_edge(2,0)
g5.add_edge(3,1)
g5.add_edge(4,1)

g6 = jgrapht.create_graph(directed=False, weighted=False)
from jgrapht.generators import linear
linear(g6,10)

g7 = jgrapht.create_graph(directed=False, weighted=False)
from jgrapht.generators import ring_graph
ring_graph(g7,7)

g8 = jgrapht.create_graph(directed=False, weighted=False)
from jgrapht.generators import grid
grid(g8,7,5)

g9 = jgrapht.create_graph(directed=False, weighted=False)
from jgrapht.generators import star
star(g9,20)

g10 = jgrapht.create_graph(directed=False, weighted=False)
from jgrapht.generators import windmill
windmill(g10,3,4)

g11 = jgrapht.create_graph(directed=False, weighted=False)
from jgrapht.generators import complete_bipartite_graph
complete_bipartite_graph(g11,7,3)

class Test_independent_hubs_base (ParametrizedTestCase):
  def test_base (self):
    f,g,expected = self.param
    result = f(g)
    self.assertEqual(result,expected)

params = [[g1,{0: 0.5}],
          [g2,{2: 10.5,1: 13.0}],
          [g3,{}],
          #[g4,{1: 4.0, 4: 4.0, 7: 4.0, 8: 4.0}],
          [g5,{1: 1.5}],
          [g6,{2: 14.0, 4: 20.0, 6: 18.0, 8: 8.0}],
          [g7,{0: 3.0, 2: 3.0, 4: 3.0}],
          [g8,{1: 20.83,3: 20.83,5: 26.41,7: 75.86,9: 26.41,11: 89.62,13: 89.62,15: 42.44,17: 116.93,19: 42.44,21: 89.62,23: 89.62,25: 26.41,27: 75.86,29: 26.41,31: 20.83,33: 20.83}],
          [g9,{0: 171.0}],
          [g10,{0: 27.0}],
          [g11,{7: 7.0, 8: 7.0, 9: 7.0}]
         ]
