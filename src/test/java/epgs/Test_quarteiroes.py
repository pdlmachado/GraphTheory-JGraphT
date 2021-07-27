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
g1.set_edge_weight(0,10)

# Grafo não direcionado conectado
g2 = jgrapht.create_graph(directed=False, weighted=True)
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
g2.add_edge(5,1,edge=10)
g2.set_edge_weight(10,10)

g3 = jgrapht.create_graph(directed=False, weighted=True)

class Test_quarteiroes(ParametrizedTestCase):

  def test_valid01 (self):
    f,g,l,expected = self.param
    try:
      sresult = map(lambda x : sorted(x),f(g,l))
      self.assertCountEqual(sresult,expected)
    except:
      self.assertTrue(f(g,l) is None and (g is None or l < 0))

params = [[g1,11,[[0,1,3],[0,2,3]]],
          [g1,1,[[0,2,3]]],
          [g1,0,[]],
          [g2,1,[[2,6,7]]],
          [g2,10,[[1,3,5],[2,6,7]]],
          [g3,20,[]],
          [None,9,None],
          [g1,-5,None],
          ]
