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

toy6= """digraph "toy6.jar" {
    // Path: toy6.jar
"d" -> "e";
"c" -> "d";
"a" -> "c";
"b" -> "a";
"c" -> "b";
}
"""

import math
import jgrapht
from importutil import read_dot
class Test_sindep(ParametrizedTestCase):

  def test_valid01 (self):
    f,input_string,c,expectedv,expectede = self.param
    g = jgrapht.create_graph (weighted=False)
    v_attrs = {}
    e_attrs = {}
    read_dot(g,input_string,v_attrs,e_attrs)
    result = f(g,c)
    try:
      self.assertCountEqual(result.vertices,expectedv)
      self.assertCountEqual(result.edges,expectede)
    except:
      self.assertTrue(result is None and (g.vertices==[] or c not in g.vertices))  
params = [[toy1,1,[1, 0, 4, 2],[0, 5, 4, 1, 2]],
          [toy2,5,[5, 3, 0, 2, 1, 4],[7, 9, 3, 8, 1, 2, 0, 5, 6, 4]],
          [toy3,0,None,None],
          [toy4,0,[0],[]],
          [toy5,1,[2,1,0],[1, 0, 4, 2, 3]],
          [toy6,0,[0,2,3,4],[1,2,3,4]]
          ]
