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

toy7= """digraph "toy7.jar" {
    // Path: toy7.jar
"d" -> "e";
"c" -> "a";
}
"""

from jgrapht.algorithms.connectivity import is_weakly_connected
def sameComponent (g,v1,v2):
  w,components = is_weakly_connected(g)
  return any(v1 in c and v2 in c for c in components)

import math
import jgrapht
from importutil import read_dot

class Test_get_clusters(ParametrizedTestCase):

  def test_valid01 (self):
    f,input_string = self.param
    g = jgrapht.create_graph (weighted=False)
    v_attrs = {}
    e_attrs = {}
    read_dot(g,input_string,v_attrs,e_attrs)
    result = f(g)
    self.assertCountEqual([item for sublist in result for item in sublist],g.vertices) 
    self.assertTrue(all(sameComponent(g,v1,v2) for c in result for v1 in c for v2 in c))
    self.assertTrue(all(len(c)>=1 for c in result))

params = [[toy1],
          [toy2],
          [toy3],
          [toy4],
          [toy5],
          [toy6],
          [toy7]
          ]
