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

# Valores de atracoes, atual e maxdist para grafo g1 e e_g1
# Path é criado no método de teste
params_g1 = [
]

# Valores de atracoes, atual e maxdist para grafo lu e e_lu
params_lu = [
]

# Outros exemplos de grafos para teste
g2 = jgrapht.create_graph(directed=False)
g2.add_vertices_from([0,1])
g2.add_edge(0,1,edge=0)
v_g2 = {0:{'label':'v0'}, 1:{'label':'v1'}}
e_g2 = {0:{'line':'0'}}

g3 = jgrapht.create_graph(directed=False)
g3.add_vertex(0)
v_g3 = {0:{'label':'v0'}}
e_g3 = {}

# Classe de testes base
class Test_atracoesmaisproximas_base (ParametrizedTestCase):
  def test_base (self):
    f,g,v_g,estacoes,atual,maxdist,saida_esperada,m = self.param
    self.assertCountEqual(f(g,v_g,estacoes,atual,maxdist),saida_esperada,m)

# Classe de testes edge
class Test_atracoesmaisproximas_edge (ParametrizedTestCase):
  def test_None (self):
    f,g,v_g = self.param
    self.assertTrue(f(None,v_g2,["v0"],0,1) is None,"Grafo não pode ser None")
    self.assertTrue(f(g2,None,["v0"],0,1) is None,"Dicionário de vértices não pode ser None")

  def test_nulo (self):
    f,g,v_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},{},["v0"],1) is None,"Deve retornar None para grafo nulo")
    self.assertTrue(f(g2,{},e_g2,["v0"],1) is None,"Dicionário não pode ser vazio para grafo não vazio")  
    self.assertTrue(f(g2,v_g2,{},["v0"],1) is None,"Dicionário não pode ser vazio para grafo não vazio")  

  #def test_s_invalida (self):
  #  f,g,v_g = self.param
  #  self.assertTrue(f(g2,v_g2,e_g2,["a1"],1) is None,"Atracao inválida")

  #def test_vdict_invalido (self):
  #  f,g,v_g = self.param
  #  self.assertTrue(f(g,v_g2,e_g,[""]) is None,"Dicionário de vértices inválido")

  #def test_trivial (self):
  #  f,g,v_g3 = self.param
  #  self.assertEqual(f(g3,v_g3,e_g3,["v0"]),[],"Deve retornar [] para grafo trivial")

  #def test_k2 (self):
  #  f,g,v_g = self.param
  #  self.assertEqual(f(g2,v_g2,e_g2,["v1"]),[('v0', 'v1', '0')]) 
  