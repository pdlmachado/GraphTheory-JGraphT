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
d1.add_edge(2,3,edge=2)
d1.add_edge(2,4,edge=3)
d1.add_edge(4,5,edge=4)
e_d1 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'l'},
    3 : {'label': 'r'},
    4 : {'label': 'r'}
}

d2 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d2.add_vertices_from([x for x in range(0,6)])
d2.add_edge(0,1,edge=0)
d2.add_edge(0,2,edge=1)
d2.add_edge(0,3,edge=2)
d2.add_edge(2,4,edge=3)
d2.add_edge(4,5,edge=4)
e_d2 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'l'},
    3 : {'label': 'r'},
    4 : {'label': 'r'}
}


d3 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d3.add_vertex(20)

class Test_is_binary_base(ParametrizedTestCase):
  def test_base (self):
    f,d,e,r = self.param
    result = f(d,e)      

params = [[d1,e_d1,True],[d2,e_d2,False],[d3,{},True]]
