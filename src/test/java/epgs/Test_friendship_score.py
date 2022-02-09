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

g5 = jgrapht.create_graph(directed=False, weighted=False)
g5.add_vertices_from([0,1,2,3,4])
g5.add_edge(0,1)
g5.add_edge(1,2)
g5.add_edge(2,3)
g5.add_edge(2,4)
g5.add_edge(2,0)
g5.add_edge(3,1)
g5.add_edge(4,1)


class Test_friendship_score(ParametrizedTestCase):
  def test_valid01 (self):
    f,g,p,expected = self.param
    try:
      result = f(g,p)
      self.assertEqual(result,expected)
    except:
      self.assertTrue(f(g,p) is None and g is None)

params = [[g1,0,5],
          [g1,2,3],
          [g1,3,5],
          [g1,1,3],
          [g2,0,2],
          [g2,1,3],
          [g2,2,5],
          [g2,3,2],
          [g2,4,1],
          [g2,5,1],
          [g2,6,3],
          [g2,7,4],
          [g2,8,2],
          [g5,0,3],
          [g5,1,7],
          [g5,2,7],
          [g5,3,3],
          [g5,4,3],
          [g1,4,None],
          [g3,0,None],
          [None,None,None]]

