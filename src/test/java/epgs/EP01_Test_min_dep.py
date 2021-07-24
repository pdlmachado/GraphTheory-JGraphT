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

def isCycle(c):
  flag = c[0][0] == c[-1][1]
  if flag == True:
      for i in range(len(c)-1):
        if not c[i][1] == c[i+1][0]:
          flag = False
  return flag

import math
import jgrapht
from importutil import read_dot
from collections import Counter
class Test_min_dep(ParametrizedTestCase):

  def test_min_dep (self):
    f,input_string,expected = self.param
    g = jgrapht.create_graph (weighted=False)
    v_attrs = {}
    e_attrs = {}
    read_dot(g,input_string,v_attrs,e_attrs)
    self.assertTrue(all(isCycle(c) for c in f(g)))
    self.assertTrue(all(any(Counter(e)==Counter(c) for e in expected) for c in f(g)))
    self.assertTrue(all(any(Counter(e)==Counter(c) for c in f(g)) for e in expected))
    
params = [[toy1,[[(1, 2), (2, 4), (4, 1)]]],
          [toy2,[[(1, 2), (2, 3), (3, 1)], [(1, 2), (2, 3), (3, 5), (5, 0), (0, 1)], [(2, 3), (3, 5), (5, 0), (0, 2)], [(5, 0), (0, 5)], [(1, 2), (2, 4), (4, 1)]]],
          [toy3,[]],
          [toy4,[]],
          [toy5,[[(0, 1), (1, 2), (2, 0)], [(1, 2), (2, 1)], [(0, 1), (1, 0)]]]]
