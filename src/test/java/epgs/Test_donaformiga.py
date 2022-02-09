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
v_g1 = {}
v_g1[0] = {} 
v_g1[0]['altura'] = 100
v_g1[1] = {} 
v_g1[1]['altura'] = 50
v_g1[2] = {} 
v_g1[2]['altura'] = -50
v_g1[3] = {} 
v_g1[3]['altura'] = 50

# Grafo não direcionado conectado
g2 = jgrapht.create_graph(directed=False, weighted=True)
g2.add_vertices_from([x for x in range(9)])
g2.add_edge(0,1,edge=0)
g2.add_edge(0,2,edge=1)
g2.add_edge(1,3,edge=2)
g2.add_edge(1,8,edge=3)
g2.add_edge(7,2,edge=4)
g2.add_edge(2,6,edge=5)
g2.add_edge(2,4,edge=6)
g2.add_edge(3,5,edge=7)
g2.add_edge(6,7,edge=8)
g2.add_edge(7,8,edge=9)
v_g2 = {}
v_g2[0] = {} 
v_g2[0]['altura'] = 100
v_g2[1] = {} 
v_g2[1]['altura'] = 50
v_g2[2] = {} 
v_g2[2]['altura'] = -50
v_g2[3] = {} 
v_g2[3]['altura'] = 50
v_g2[4] = {} 
v_g2[4]['altura'] = 0
v_g2[5] = {} 
v_g2[5]['altura'] = 10
v_g2[6] = {} 
v_g2[6]['altura'] = 90
v_g2[7] = {} 
v_g2[7]['altura'] = -30
v_g2[8] = {} 
v_g2[8]['altura'] = 40

g3 = jgrapht.create_graph(directed=False, weighted=True)


class Test_donaformiga(ParametrizedTestCase):
  def test_valid01 (self):
    f,g,v_g,s,expected = self.param
    try:
      result = f(g,v_g,s)
      self.assertEqual(result,expected)
    except:
      self.assertTrue(f(g,v_g,s) is None and g is None)

params = [[g1,v_g1,0,3], # Salões 1,2,3
          [g1,v_g1,2,0], # Salões
          [g1,v_g1,1,0], # Salões
          [g1,v_g1,3,1], # Salões 2
          [g2,v_g2,0,4], # Salões 1,2,8,7
          [g2,v_g2,1,3], # Salões 2,7,8
          [g2,v_g2,2,0], # Salões
          [g2,v_g2,3,1], # Salões 5
          [g2,v_g2,4,1], # Salões 2
          [g2,v_g2,5,0], # Salões 2,7
          [g2,v_g2,6,2], # Salões 2
          [g2,v_g2,7,1], # Salões 2
          [g2,v_g2,8,2], # Salões 7,2
          [g2,v_g2,10,0],
          [g3,{},0,0],
          [None,None,None,None]]
