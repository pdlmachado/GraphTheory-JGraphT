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


class Test_visitavel_base(ParametrizedTestCase):
  def test_base (self):
    f,g,p,e = self.param
    r = f(g,p)
    self.assertEqual(r,e)

params = [[g1,0,True]
          ]
