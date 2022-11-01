import jgrapht
from jgrapht.algorithms.shortestpaths import yen_k_loopless
# Funções e pacotes para teste
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
    
#####################################
# Test Data 

# Valores de vértice origem,destino (graphpath) e t para grafo g1 e e_g1
# Path é criado no método de teste
params_g1 = [
  [1,4,5,15,"#01"],
  [0,2,10,11,"#02"],
  [1,1,10,0,"#03"],
  [1,2,10,11,"#04"],
  [3,6,10,12,"#05"],
  [7,8,7,34,"#06"],
  [7,8,0,13,"#07"],
  [7,8,1,16,"#08"],
]

# Valores de vértice origem,destino (graphpath) e t para grafo lu e e_lu
params_lu = [
  [1,4,5,86,"#01"],
  [131,2,10,140,"#02"],
  [1,200,9,130,"#03"],
  [1,200,0,58,"#04"],
  [1,1,10,0,"#05"]  
]

# Outros exemplos de grafos para teste
g2 = jgrapht.create_graph(directed=False)
g2.add_vertices_from([0,1])
g2.add_edge(0,1,edge=0)
e_g2 = {0:{'line':'0'}}
p2 = next(yen_k_loopless(g2,0,1,1))

g3 = jgrapht.create_graph(directed=False)
g3.add_vertex(0)
e_g3 = {}

# Classe de testes base
class Test_tempo_estimado_base (ParametrizedTestCase):
  def test_base (self):
    f,g,e_g,v1,v2,t,saida_esperada,m = self.param
    path = next(yen_k_loopless(g,v1,v2,1))
    self.assertEqual(f(g,e_g,path,t),saida_esperada,m)

# Classe de testes edge
class Test_tempo_estimado_edge (ParametrizedTestCase):
  def test_None (self):
    f,g,e_g = self.param
    self.assertTrue(f(None,e_g2,p2,0) is None,"Grafo não pode ser None")
    self.assertTrue(f(g2,None,p2,0) is None,"Dicionário de arestas não pode ser None")

  def test_nulo (self):
    f,g,e_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},p2,0) is None,"Deve retornar None para grafo nulo")
    self.assertTrue(f(g2,{},e_g2,10) is None,"Dicionário não pode ser vazio para grafo não vazio")  

  def test_t_invalido (self):
    f,g,e_g = self.param
    self.assertTrue(f(g2,e_g2,p2,-1) is None,"t tem que ser um número positivo ou zero")

  def test_edict_invalido (self):
    f,g,e_g = self.param
    self.assertTrue(f(g,e_g2,p2,0) is None,"Dicionário inválido")

  def test_path_invalido (self):
    f,g,e_g = self.param
    self.assertTrue(f(g,e_g,p2,0) is None,"Path não pertence a este grafo")

  def test_trivial (self):
    f,g,e_g = self.param
    self.assertTrue(f(g3,e_g3,None,3) is None,"Deve retornar None para grafo trivial")

  def test_k2 (self):
    f,g,e_g = self.param
    self.assertEqual(f(g2,e_g2,p2,2),3) 
