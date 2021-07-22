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


import unittest
import math
class Test_fat(ParametrizedTestCase):

  def test_valid01 (self):
    input_string,expected = self.param
    g = jgrapht.create_graph (weighted=False)
    v_attrs = {}
    e_attrs = {}
    read_dot(g,input_string,v_attrs,e_attrs)
    f = fat(g)
    self.assertTrue(math.isclose(a=f,b=expected,rel_tol=0.01))
    
params = [[toy1,0.30],[toy2,0.33],[toy3,0.0],[toy4,0.5],[toy5,0.83]]

Test_fat_cases = [ParametrizedTestCase.parametrize(Test_fat, param=params[i]) for i in range(len(params))]
