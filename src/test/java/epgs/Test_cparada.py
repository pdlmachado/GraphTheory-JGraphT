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

g1 = jgrapht.create_graph(directed=True, weighted=False)
g1.add_vertices_from([x for x in range(0,4)])
g1.add_edge(0,1,edge=0)
g1.add_edge(0,2,edge=1)
g1.add_edge(0,3,edge=2)
g1.add_edge(1,3,edge=3)
g1.add_edge(2,3,edge=4)

# Grafo não direcionado conectado
g2 = jgrapht.create_graph(directed=True, weighted=False)
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

g3 = jgrapht.create_graph(directed=True, weighted=False)


g4 = jgrapht.create_graph(directed=True, weighted=False)
g4.add_vertices_from([x for x in range(6)])
g4.add_edge(0,1)
g4.add_edge(1,2)
g4.add_edge(2,3)
g4.add_edge(3,4)
g4.add_edge(3,1)
g4.add_edge(2,5)
g4.add_edge(5,3)

g5 = jgrapht.create_graph(directed=True, weighted=False)
g5.add_vertices_from([0,1,2,3,4])
g5.add_edge(0,1)
g5.add_edge(1,2)
g5.add_edge(2,3)
g5.add_edge(2,4)
g5.add_edge(2,0)
g5.add_edge(3,1)
g5.add_edge(4,1)

g6 = jgrapht.create_graph(directed=True, weighted=False)
g6.add_vertices_from([x for x in range(7)])
g6.add_edge(0,1)
g6.add_edge(1,2)
g6.add_edge(2,3)
g6.add_edge(2,4)
g6.add_edge(4,1)
g6.add_edge(3,1)
g6.add_edge(2,0)
g6.add_edge(0,5)
g6.add_edge(0,6)

g7 = jgrapht.create_graph(directed=True, weighted=False)
g7.add_vertices_from([x for x in range(5)])
g7.add_edge(0,1)
g7.add_edge(1,2)
g7.add_edge(2,0)
g7.add_edge(3,1)
g7.add_edge(3,4)


class Test_cparada(ParametrizedTestCase):
  def test_valid01 (self):
    f,g,s,expected = self.param
    try:
      result = f(g,s)
      self.assertCountEqual(result,expected)
    except:
      self.assertTrue(f(g) is None and g is None)

params = [[g1,0,[3]],
          [g2,0,[4,5,8]],
          [g3,0,[]],
          [g4,0,[4]],
          [g5,0,[]],
          [g6,0,[5,6]],
          [g7,0,[]],
          [g7,3,[4]],
          [g7,10,[]]
          ]
