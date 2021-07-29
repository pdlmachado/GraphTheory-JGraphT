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

g2 = jgrapht.create_graph(directed=False, weighted=False)
g2.add_vertices_from([x for x in range(9)])
g2.add_edge(0,1,edge=0)
g2.add_edge(0,2,edge=1)
g2.add_edge(1,3,edge=2)
g2.add_edge(1,8,edge=3)
g2.add_edge(2,7,edge=4)
g2.add_edge(2,6,edge=5)
g2.add_edge(2,4,edge=6)
g2.add_edge(3,5,edge=7)
g2.add_edge(6,7,edge=8)
g2.add_edge(7,8,edge=9)

g3 = jgrapht.create_graph(directed=False, weighted=False)

g4 = jgrapht.create_graph(directed=False, weighted=False)
g4.add_vertices_from([x for x in range(9)])
g4.add_edge(0,1,edge=0)
g4.add_edge(0,2,edge=1)
g4.add_edge(1,3,edge=2)
g4.add_edge(1,8,edge=3)
g4.add_edge(2,7,edge=4)
g4.add_edge(2,6,edge=5)
g4.add_edge(2,4,edge=6)
g4.add_edge(3,5,edge=7)
g4.add_edge(6,7,edge=8)
g4.add_edge(7,8,edge=9)
g4.add_edge(4,5,edge=10)

g5 = jgrapht.create_graph(directed=False,weighted=False) 
jgrapht.generators.complete_graph(g5,9)

g6 = jgrapht.create_graph(directed=False,weighted=False) 
jgrapht.generators.hypercube(g6,8)

g7 = jgrapht.create_graph(directed=False,weighted=False) 
jgrapht.generators.wheel(g7,8)

from jgrapht.algorithms.connectivity import is_strongly_connected_kosaraju
class Test_orientacao(ParametrizedTestCase):
  def test_valid01 (self):
    f,g=self.param
    d=f(g)
    try:
      cond,componentes = is_strongly_connected_kosaraju(d)
      self.assertTrue(cond)
    except:
      self.assertTrue(d is None)
    
params = [[g1],
          [g2],
          [g3],
          [g4],
          [g5],
          [g6],
          [g7],
          [None]
          ]
