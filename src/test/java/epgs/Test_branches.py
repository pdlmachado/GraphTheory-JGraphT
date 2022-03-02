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

t1 = jgrapht.create_graph(directed=False, weighted=False)
t1.add_vertices_from([x for x in range(0,6)])
t1.add_edge(0,1,edge=0)
t1.add_edge(0,2,edge=1)
t1.add_edge(0,3,edge=2)
t1.add_edge(1,4,edge=3)
t1.add_edge(4,5,edge=4)

t2 = jgrapht.create_graph(directed=False, weighted=False)
t2.add_vertex(100)

class Test_branches_base(ParametrizedTestCase):
  def test_base (self):
    f,t,r,b = self.param
    result = f(t,r)
    self.assertEqual(result,b)

params = [[t1,0,[[0, 1, 4, 5], [0, 2], [0, 3]]],
          [t2,100,[]],
         ]
