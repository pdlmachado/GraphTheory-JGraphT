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

d0 = jgrapht.create_graph(directed=True, weighted=False)
d0.add_vertex(0)

d1 = jgrapht.create_graph(directed=True, weighted=False)
d1.add_vertices_from([x for x in range(0,6)])
d1.add_edge(0,1,edge=0)
d1.add_edge(0,2,edge=1)
d1.add_edge(3,0,edge=2)
d1.add_edge(1,3,edge=3)
d1.add_edge(4,5,edge=4)

d2 = jgrapht.create_graph(directed=True, weighted=False)
d2.add_vertices_from([x for x in range(8)])
d2.add_edge(0,1,edge=0)
d2.add_edge(0,2,edge=1)
d2.add_edge(3,1,edge=2)
d2.add_edge(2,7,edge=3)
d2.add_edge(2,4,edge=4)
d2.add_edge(5,3,edge=5)
d2.add_edge(7,6,edge=6)
d2.add_edge(7,4,edge=7)
d2.add_edge(4,3,edge=8)

d3 = jgrapht.create_graph(directed=True, weighted=False)
d3.add_vertices_from([x for x in range(8)])
d3.add_edge(0,1,edge=0)
d3.add_edge(0,2,edge=1)
d3.add_edge(3,1,edge=2)
d3.add_edge(2,7,edge=3)
d3.add_edge(2,4,edge=4)
d3.add_edge(5,3,edge=5)
d3.add_edge(7,6,edge=6)
d3.add_edge(2,5,edge=7)
d3.add_edge(7,4,edge=8)
d3.add_edge(4,3,edge=9)
d3.add_edge(5,4,edge=10)
d3.add_edge(7,3,edge=11)
d3.add_edge(4,0,edge=13)
d3.add_edge(6,5,edge=14)

class Test_existem_caminhos_disjuntos(ParametrizedTestCase):
  def test_base (self):
    f,g,s,t,er,ec = self.param
    result,caminhos = f(g,s,t)
    self.assertEqual(result,er)
    self.assertCountEqual(caminhos,ec)

params = [[d0,0,0,0,[]],
          [d1,0,3,1,[]],
          [d1,0,4,0,[]],
          [d2,0,3,1,[]],
          [d2,2,3,1,[]],
          [d2,2,4,2,[[2, 4], [2, 7, 4]]],
          [d3,2,4,2,[[2, 4], [2, 7, 4], [2, 5, 4]]],
          [d3,7,0,1,[]],
          [d3,2,5,2,[[2,5],[2,7,6,5]]]
          ]
