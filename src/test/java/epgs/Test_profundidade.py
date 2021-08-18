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
toy1 = """digraph "toy1.jar" {
    // Path: toy1.jar
"A" -> "B";
"A" -> "C";
"B" -> "C";
"C" -> "D";
"C" -> "E";
"E" -> "B";
}
"""

toy2 = """digraph "toy2.jar" {
    // Path: toy2.jar
"A" -> "B";
"A" -> "C";
"B" -> "C";
"C" -> "D";
"C" -> "E";
"E" -> "B";
"D" -> "B";
"D" -> "G";
"G" -> "A";
"A" -> "G"
}
"""

toy3 = """digraph "toy3.jar" {
    // Path: toy3.jar
}
"""

toy4 = """digraph "toy4.jar" {
    // Path: toy4.jar
"A" -> "B"
}
"""

toy5 = """digraph "toy5.jar" {
    // Path: toy5.jar
"A" -> "B";
"B" -> "C";
"C" -> "A";
"B" -> "A";
"C" -> "B"
}
"""

toy6= """digraph "toy5.jar" {
    // Path: toy5.jar
"A" -> "B";
"B" -> "C";
"C" -> "A";
"D" -> "A";
"C" -> "D";
"A" -> "C"
}
"""

import math
import jgrapht
from importutil import read_dot
class Test_prof(ParametrizedTestCase):

  def test_valid01 (self):
    f,input_string,expected = self.param
    g = jgrapht.create_graph (weighted=False)
    v_attrs = {}
    e_attrs = {}
    read_dot(g,input_string,v_attrs,e_attrs)
    result = f(g)
    self.assertEqual(result,expected)
    
params = [[toy1,3],[toy2,5],[toy3,0],[toy4,1],[toy5,2],[toy6,2]]
