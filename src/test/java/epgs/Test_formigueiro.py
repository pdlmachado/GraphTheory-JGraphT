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

# Grafo não direcionado conectado
g2 = jgrapht.create_graph(directed=False, weighted=True)
g2.add_vertices_from([x for x in range(9)])
g2.add_edge(0,1,edge=0)
g2.add_edge(0,2,edge=1)
g2.add_edge(1,3,edge=2)
g2.add_edge(1,8,edge=3)
g2.add_edge(2,7,edge=4)
g2.add_edge(2,6,edge=5)
g2.add_edge(2,4,edge=6)
g2.add_edge(3,5,edge=7)
g2.add_edge(6,7,edge=8)
g2.add_edge(7,8,edge=9)
g2.add_edge(5,1,edge=10)
## v1_attrs
v1_attrs = {v:{} for v in g2.vertices}
profundidade = [0,30,15,0,100,6,24,35,48]
for v in g2.vertices:
  v1_attrs[v]['profundidade'] = profundidade[v]
cheios = []
for v in g2.vertices:
  if v in cheios:
    v1_attrs[v]['cheio'] = True
  else:
    v1_attrs[v]['cheio'] = False
## v2_attrs
v2_attrs = {v:{} for v in g2.vertices}
profundidade = [0,30,15,0,100,6,24,35,48]
for v in g2.vertices:
  v2_attrs[v]['profundidade'] = profundidade[v]
cheios = range(1,10)
for v in g2.vertices:
  if v in cheios:
    v2_attrs[v]['cheio'] = True
  else:
    v2_attrs[v]['cheio'] = False
## v3_attrs
v3_attrs = {v:{} for v in g2.vertices}
profundidade = [0,30,15,0,100,6,24,35,48]
for v in g2.vertices:
  v3_attrs[v]['profundidade'] = profundidade[v]
cheios = [0]
for v in g2.vertices:
  if v in cheios:
    v3_attrs[v]['cheio'] = True
  else:
    v3_attrs[v]['cheio'] = False
## v4_attrs
v4_attrs = {v:{} for v in g2.vertices}
profundidade = [0,30,15,20,100,6,24,35,48]
for v in g2.vertices:
  v4_attrs[v]['profundidade'] = profundidade[v]
cheios = [6,5,7]
for v in g2.vertices:
  if v in cheios:
    v4_attrs[v]['cheio'] = True
  else:
    v4_attrs[v]['cheio'] = False
## v5_attrs
v5_attrs = {v:{} for v in g2.vertices}
profundidade = [0,30,15,0,100,6,24,35,48]
for v in g2.vertices:
  v5_attrs[v]['profundidade'] = profundidade[v]
cheios = [2,5,7]
for v in g2.vertices:
  if v in cheios:
    v5_attrs[v]['cheio'] = True
  else:
    v5_attrs[v]['cheio'] = False

g3 = jgrapht.create_graph(directed=False, weighted=True)

class Test_formigueiro(ParametrizedTestCase):

  def test_valid01 (self):
    f,g,v_attrs,i,expected = self.param
    result = f(g,v_attrs,i)
    self.assertEqual(result,expected)

params = [[g2,v1_attrs,0,[0, 2, 6, 7, 8]],
          [g2,v2_attrs,0,[0]],
          [g2,v3_attrs,0,[]],
          [g2,v4_attrs,0,[0,2,4]],
          [g2,v5_attrs,3,[3,1,8]],
          [g3,{},0,None],
          [None,{},0,None],
          [g2,v1_attrs,20,None]
           ]
