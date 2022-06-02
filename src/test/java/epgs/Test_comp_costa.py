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
from jgrapht.generators import grid
g1 = jgrapht.create_graph(directed=False,weighted=False)
grid(g1,5,5)
v_g1 = {
    0 : {'label' : 'A'},
    1 : {'label' : 'A'},
    2 : {'label' : 'A'},
    3 : {'label' : 'A'},
    4 : {'label' : 'A'},
    5 : {'label' : 'A'},
    6 : {'label' : 'T'},
    7 : {'label' : 'T'},
    8 : {'label' : 'A'},
    9 : {'label' : 'A'},
    10 : {'label' : 'A'},
    11 : {'label' : 'A'},
    12 : {'label' : 'A'},
    13 : {'label' : 'T'},
    14 : {'label' : 'A'},
    15 : {'label' : 'A'},
    16 : {'label' : 'A'},
    17 : {'label' : 'T'},
    18 : {'label' : 'T'},
    19 : {'label' : 'T'},
    20 : {'label' : 'A'},
    21 : {'label' : 'A'},
    22 : {'label' : 'A'},
    23 : {'label' : 'T'},
    24 : {'label' : 'A'}
}
draw_graph(g1,vertexid_aslabel=True,layout= "fruchterman_reingold")


class Test_comp_costa(ParametrizedTestCase):
  def test_base (self):
    f,g,v_g,expected = self.param
    result = f(g,v_g)

params = [[g1,v_g1,6]
          ]
