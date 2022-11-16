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

# Valores da lista de estacoes para o grafo g1 e v_g1
# Path é criado no método de teste
params_g1 = [
    [['Bodocongó', 'Cruzeiro', 'Prata', 'Catolé', 'Centenário', 'Nações'],
     ['Nações', 'Catolé', 'Centenário', 'Catolé', 'Prata', 'Bodocongó', 'Prata', 'Cruzeiro', 'Prata', 'Catolé', 'Nações'],"#01"],
    [['São José', 'Cruzeiro', 'Nações'],[],"#02"],
    [['São José', 'Centro', 'José Pinheiro'],['José Pinheiro', 'Centro', 'São José', 'Centro', 'José Pinheiro'],"#03"]
]

# Valores de estacao atual e maxdist para grafo lu e e_lu
params_lu = [
    [['Aldgate East', 'Shadwell', 'Shoreditch', 'Tower Gateway', 'Wapping', 'Whitechapel'],
     ['Whitechapel', 'Shoreditch', 'Whitechapel', 'Shadwell', 'Wapping', 'Shadwell', 'Tower Gateway', 'Shadwell', 'Whitechapel', 'Aldgate East', 'Whitechapel'],"#01"],
    [['Barons Court', 'Ealing Broadway', 'Ealing Common', 'Hammersmith', 'North Ealing', 'Ravenscourt Park', 'Stamford Brook', 'Turnham Green', 'West Kensington'],[],"#02"]
    
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
class Test_melhorcircuito_base (ParametrizedTestCase):
  def test_base (self):
    f,g,v_g,estacoes,saida_esperada,m = self.param
    result = f(g,v_g,estacoes)
    self.assertCountEqual(result[1:len(result)],saida_esperada[1:len(result)],m)

# Classe de testes edge
class Test_melhorcircuito_edge (ParametrizedTestCase):
  def test_None (self):
    f,g,v_g = self.param
    self.assertTrue(f(None,v_g,["v0"]) is None,"Grafo não pode ser None")
    self.assertTrue(f(g,None,["v0"]) is None,"Dicionário de vértices não pode ser None")

  def test_nulo (self):
    f,g,v_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},["v0"]) is None,"Deve retornar None para grafo nulo")
    self.assertTrue(f(g2,{},["v0"]) is None,"Dicionário não pode ser vazio para grafo não vazio")  

  def test_s_invalida (self):
    f,g,v_g = self.param
    self.assertTrue(f(g2,v_g2,["v10"]) is None,"Estação inválida")

  def test_vdict_invalido (self):
    f,g,v_g = self.param
    self.assertTrue(f(g,v_g2,["v0"]) is None,"Dicionário de vértices inválido")

  def test_trivial (self):
    f,g,v_g = self.param
    self.assertEqual(f(g3,v_g3,["v0"]),[])

  def test_k2 (self):
    f,g,v_g = self.param
    self.assertEqual(f(g2,v_g2,["v0"]),[]) 
    self.assertCountEqual(set(f(g2,v_g2,["v0","v1"])),{"v0","v1"}) 
