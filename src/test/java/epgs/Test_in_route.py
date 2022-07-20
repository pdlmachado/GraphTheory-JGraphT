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
g1.add_vertices_from([x for x in range(0,5)])
g1.add_edge(0,1,edge=0)
g1.add_edge(0,2,edge=1)
g1.add_edge(0,3,edge=2)
g1.add_edge(1,3,edge=3)
g1.add_edge(2,3,edge=4)
g1.add_edge(3,4,edge=5)

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
g3.add_vertex(0)

from jgrapht.generators import complete_graph
g4 = jgrapht.create_graph(directed=False, weighted=False)
complete_graph(g4,5)
g4.add_vertex(1000)

class Test_in_route_base(ParametrizedTestCase):
  def test_base (self):
    f,g,p,e = self.param
    r = f(g,p)
    self.assertEqual(r,e)

params = [[g1,0,True],
          [g1,1,True],
          [g1,2,True],
          [g1,3,True],
          [g1,4,False],
          [g2,0,True],
          [g2,1,True],
          [g2,2,True],
          [g2,3,False],
          [g2,4,False],
          [g2,5,False],
          [g2,6,True],
          [g2,7,True],
          [g2,8,True],
          [g3,0,False],
          [g4,0,True],
          [g4,1000,False]
          ]
