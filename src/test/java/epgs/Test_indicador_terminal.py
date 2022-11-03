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

# Valores de linha grafo g1 e e_g1
params_g1 = [
    ['3',{1: 2.0, 2: 1.45, 3: 1.3, 5: 1.225, 6: 1.562, 7: 2.0},"#01"]
]

# Valores de linha para grafo lu e e_lu
params_lu = [
    ['1',{192: 1.58, 193: 1.312, 197: 1.644, 137: 1.401, 11: 1.462, 140: 1.918, 206: 1.327, 143: 1.277, 82: 1.379, 84: 2.0, 148: 1.897, 212: 1.519, 278: 1.27, 87: 1.763, 279: 1.839, 281: 1.692, 159: 1.251, 163: 1.429, 298: 1.478, 237: 1.839, 49: 1.712, 113: 1.539, 114: 2.0, 246: 1.603, 185: 1.763},"#01"]
]

# Outros exemplos de grafos para teste
g2 = jgrapht.create_graph(directed=False)
g2.add_vertices_from([0,1])
g2.add_edge(0,1,edge=0)
v_g2 = {0:{'label':'v0'}, 1:{'label':'v1'}}
e_g2 = {0:{'line':'0'}}
atracoes_g2 = {'a0' : 'v0'}

g3 = jgrapht.create_graph(directed=False)
g3.add_vertex(0)
v_g3 = {0:{'label':'v0'}}
e_g3 = {}
atracoes_g3 = {'a0' : 'v0'}

# Classe de testes base
class Test_indicador_terminal_base (ParametrizedTestCase):
  def test_base (self):
    f,g,e_g,atual,saida_esperada,m = self.param
    self.assertDictEqual(f(g,e_g,atual),saida_esperada,m)

# Classe de testes edge
class Test_indicador_terminal_edge (ParametrizedTestCase):
  def test_None (self):
    f,g,e_g = self.param
    self.assertTrue(f(None,e_g2,'0') is None,"Grafo não pode ser None")
    self.assertTrue(f(g2,None,'0') is None,"Dicionário de vértices não pode ser None")

  def test_nulo (self):
    f,g,e_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},'0') is None,"Deve retornar None para grafo nulo")
    self.assertTrue(f(g2,{},'0') is None,"Dicionário não pode ser vazio para grafo não vazio")  

  def test_l_invalida (self):
    f,g,e_g = self.param
    self.assertTrue(f(g,e_g,"20") is None,"Linha inválida")

  def test_edict_invalido (self):
    f,g,e_g = self.param
    self.assertTrue(f(g,e_g2,'1') is None,"Dicionário de arestas inválido")

  def test_trivial (self):
    f,g,e_g = self.param
    self.assertTrue(f(g3,e_g3,'0') is None,"Retorna None para grafo trivial")

  def test_k2 (self):
    f,g,e_g = self.param
    self.assertEqual(f(g2,e_g2,'0'),{0: 2.0, 1: 2.0}) 
