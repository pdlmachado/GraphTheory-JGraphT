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
g2.add_edge(2,7,edge=4)
g2.add_edge(2,6,edge=5)
g2.add_edge(2,4,edge=6)
g2.add_edge(3,5,edge=7)
g2.add_edge(6,7,edge=8)
g2.add_edge(7,8,edge=9)

g3 = jgrapht.create_graph(directed=False, weighted=False)

class Test_astronautas(ParametrizedTestCase):
  def test_valid01 (self):
    f,g,expected=self.param
    try:
      sresult = map(lambda x : sorted(x),f(g))
      sexpected = map(lambda x : sorted(x),expected)
      self.assertCountEqual(sresult,sexpected)
    except:
      self.assertTrue(f(g) is None and g is None)
    
params = [[g1,[(1,2)]],
          [g2,[(0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (0, 8), (1, 2), (1, 4), (1, 5), (1, 6), (1, 7), (2, 3), (2, 5), (2, 8), (3, 4), (3, 6), (3, 7), (3, 8), (4, 5), (4, 6), (7,4), (4, 8), (5, 6), (5, 7), (5, 8), (6, 8)]],
          [g3,[]],
          [None,None]]
