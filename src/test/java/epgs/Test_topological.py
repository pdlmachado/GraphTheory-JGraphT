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

d1 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d1.add_vertices_from([x for x in range(0,6)])
d1.add_edge(0,1,edge=0)
d1.add_edge(0,2,edge=1)
d1.add_edge(3,0,edge=2)
d1.add_edge(1,4,edge=3)
d1.add_edge(4,5,edge=4)

# Árvore enraizada (dag)
d2 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d2.add_vertices_from([x for x in range(8)])
d2.add_edge(0,1,edge=0)
d2.add_edge(0,2,edge=1)
d2.add_edge(3,1,edge=2)
d2.add_edge(2,7,edge=3)
d2.add_edge(2,4,edge=4)
d2.add_edge(5,3,edge=5)
d2.add_edge(7,6,edge=6)
d2.add_edge(7,4,edge=7)

# Árvore comum
d3 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d3.add_vertices_from([x for x in range(9)])
d3.add_edge(0,1,edge=0)
d3.add_edge(0,2,edge=1)
d3.add_edge(1,3,edge=2)
d3.add_edge(2,7,edge=3)
d3.add_edge(4,2,edge=4)
d3.add_edge(3,5,edge=5)
d3.add_edge(7,6,edge=6)
d3.add_edge(7,8,edge=7)

d4 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d4.add_vertex(20)

class Test_topological_base(ParametrizedTestCase):
  def test_base (self):
    f,d = self.param
    top = f(d)
    #print(top)
    self.assertEqual(len(top),len(d.vertices))
    self.assertCountEqual(top,d.vertices)
    self.assertTrue(all(all(top.index(v) < top.index(w) for w in [d.edge_target(e) for e in d.outedges_of(v)]) for v in d.vertices))       

params = [[d1],[d2],[d3],[d4]]
