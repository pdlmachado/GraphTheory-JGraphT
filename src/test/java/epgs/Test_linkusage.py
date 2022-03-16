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
g1.set_edge_weight(3,9)
g1.set_edge_weight(4,8)

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
g5.add_vertices_from([0,1,2,3,4,5])
g5.add_edge(0,1)
g5.add_edge(1,2)
g5.add_edge(2,3)
g5.add_edge(0,4)
g5.add_edge(4,5)


g6 = jgrapht.create_graph(directed=False, weighted=True)
from jgrapht.generators import ring_graph
ring_graph(g6,10)

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

class Test_linkusage_base(ParametrizedTestCase):
  def test_base (self):
    f,g,ew,ed = self.param
    rw,rd = f(g)
    self.assertEqual(rw,ew)
    self.assertDictEqual(rd,ed)

params = [[g1,66.0,{0: 6, 1: 6, 2: 6, 3: 0, 4: 0}],
          [g2,616.0,{0: 20, 1: 20, 2: 28, 3: 20, 4: 16, 5: 0, 6: 16, 7: 16, 8: 16, 9: 20}],
          [g3,0.0,{}],
          [g4,20.0,{0: 2, 1: 2, 2: 2, 3: 2, 4: 2, 5: 2, 6: 2, 7: 2, 8: 2, 9: 2}],
          [g5,70.0,{0: 18, 1: 16, 2: 10, 3: 16, 4: 10}],
          [g6,250.0,{0: 26, 1: 26, 2: 26, 3: 26, 4: 26, 5: 24, 6: 24, 7: 24, 8: 24, 9: 24}],
          #[g7,512.0,{0: 18, 1: 16, 2: 14, 3: 22, 4: 16, 5: 14, 6: 22, 7: 14, 8: 20, 9: 16, 10: 20, 11: 16, 12: 20, 13: 17, 14: 13, 15: 17, 16: 13, 17: 14, 18: 13, 19: 13, 20: 28, 21: 19, 22: 15, 23: 19, 24: 15, 25: 10, 26: 15, 27: 15, 28: 10, 29: 12, 30: 12, 31: 14}],
          [g8,22.0, {0: 4, 1: 0, 2: 2, 3: 2, 4: 4, 5: 2, 6: 2, 7: 2, 8: 2, 9: 2}]
          ]


