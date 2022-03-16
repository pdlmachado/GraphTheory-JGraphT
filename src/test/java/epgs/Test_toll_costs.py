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

g1 = jgrapht.create_graph(directed=False, weighted=True)
g1.add_vertices_from([x for x in range(0,4)])
g1.add_edge(0,1,edge=0)
g1.add_edge(0,2,edge=1)
g1.add_edge(0,3,edge=2)
g1.add_edge(1,3,edge=3)
g1.add_edge(2,3,edge=4)
g1.set_edge_weight(0,4)
g1.set_edge_weight(1,6)
g1.set_edge_weight(2,1)
g1.set_edge_weight(3,3)
g1.set_edge_weight(4,7)

# Grafo não direcionado conectado
g2 = jgrapht.create_graph(directed=False, weighted=True)
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
g2.set_edge_weight(0,1)
g2.set_edge_weight(1,4)
g2.set_edge_weight(2,9)
g2.set_edge_weight(3,3)
g2.set_edge_weight(4,2)
g2.set_edge_weight(5,5)
g2.set_edge_weight(6,1)
g2.set_edge_weight(7,4)
g2.set_edge_weight(8,2)
g2.set_edge_weight(9,3)

g3 = jgrapht.create_graph(directed=False, weighted=True)
g3.add_vertex(100)

g4 = jgrapht.create_graph(directed=False, weighted=True)
g4.add_vertices_from([x for x in range(5)])
g4.add_edge(0,1)
g4.add_edge(0,2)
g4.add_edge(0,3)
g4.add_edge(0,4)
g4.add_edge(1,2)
g4.add_edge(1,3)
g4.add_edge(1,4)
g4.add_edge(2,3)
g4.add_edge(2,4)
g4.add_edge(3,4)

g5 = jgrapht.create_graph(directed=False, weighted=True)
g5.add_vertices_from([0,1,2,3,4,5,6,7])
g5.add_edge(0,1)
g5.add_edge(1,2)
g5.add_edge(2,3)
g5.add_edge(0,4)
g5.add_edge(4,5)
g5.add_edge(0,6)
g5.add_edge(6,7)


g6 = jgrapht.create_graph(directed=False, weighted=True)
from jgrapht.generators import ring_graph
ring_graph(g6,10)
g6.set_edge_weight(5,20)

g7 = jgrapht.create_graph(directed=False, weighted=True)
from jgrapht.generators import hypercube
hypercube(g7,4)

g8 = jgrapht.create_graph(directed=False, weighted=True)
g8.add_vertices_from([x for x in range(5)])
g8.add_edge(0,1)
g8.add_edge(0,2)
g8.add_edge(0,3)
g8.add_edge(0,4)
g8.add_edge(1,2)
g8.add_edge(1,3)
g8.add_edge(1,4)
g8.add_edge(2,3)
g8.add_edge(2,4)
g8.add_edge(3,4)
g8.set_edge_weight(1,20)


class Test_toll_costs_base(ParametrizedTestCase):
  def test_base (self):
    f,g,ew,ep = self.param
    rw,rp = f(g)
    self.assertEqual(rw,ew)
    self.assertCountEqual(rp,ep)

params = [[g1,10.0,{1,2,3}], 
          [g2,25.0,{0, 2, 3, 4, 6, 7, 8, 9}],
          [g3,0.0,{}],
          [g4,4.0,{0, 1, 2, 3}],
          [g5,7.0,{0, 1, 2, 3, 4, 5, 6}],
          [g6,9.0,{0, 1, 2, 3, 4, 6, 7, 8, 9}],
          [g7,15.0,{0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 14, 16, 18, 19}],
          [g8,4.0,{0, 2, 3, 4}]
          ]
