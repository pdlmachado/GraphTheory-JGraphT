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
    
#####################
# Test Data 

# Valores de listas de estações para grafo g1 e e_g1
params_g1 = [
  [["Cruzeiro"],False,"#01"],
  [["Centro"],True,"#02"],
  [["Centenário","São José"],True,"#3"],
  [["Nações","Bodocongó"],False,"#4"],
  [["Prata","Centro"],True,"#5"],
  [["Catolé","Centro","Catolé"],True,"#6"],
  [[],False,"#7"],
]

# Valores de listas de estações para grafo g1 e e_g1
params_lu = [
  [["South Kensington", "Baker Street", "Oxford Circus"],True,"#01"],
  [["Bank"],False,"#02"],
  [["Wimbledon","Beckton"],False,"#03"],
  [["Upminster","Upminster Bridge"],False,"#04"],
  [["Knightsbridge","Hyde Park Corner"],False,"#05"],
  [["Marylebone","Upney"],True,"#06"],
  [[],False,"#07"]
]

# Outros exemplos de grafos para teste
g2 = jgrapht.create_graph(directed=False)
g2.add_vertices_from([0,1])
g2.add_edge(0,1,edge=0)
v_g2 = {0:{'label':'v0'},1:{'label':'v1'},}

g3 = jgrapht.create_graph(directed=False)
g3.add_vertex(0)
v_g3 = {0:{'label':"v0"}}

# Classe de testes base
class Test_quebradeservico_base (ParametrizedTestCase):
  def test_base (self):
    f,g,v_g,l,saida_esperada,m = self.param
    self.assertEqual(f(g,v_g,l),saida_esperada,m)

# Classe de testes edge
class Test_quebradeservico_edge (ParametrizedTestCase):
  def test_None (self):
    f,g,v_g = self.param
    self.assertTrue(f(None,v_g,[]) is None,"Grafo não pode ser None")
    self.assertTrue(f(g,None,[]) is None,"Dicionário de arestas não pode ser None")

  def test_nulo (self):
    f,g,v_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},[]),"Deve retornar True para grafo nulo")
    self.assertTrue(f(g,{},[]) is None,"Dicionário não pode ser vazio para grafo não vazio")  

  def test_stations_invalido (self):
    f,g,v_g = self.param
    self.assertTrue(f(g,v_g,["invalido"]) is None,"Estações devem estar definidas no grafo")

  def test_edict_invalido (self):
    f,g,v_g = self.param
    self.assertTrue(f(g,v_g2,[]) is None,"Dicionário inválido")

  def test_trivial (self):
    f,g,v_g = self.param
    self.assertTrue(f(g3,v_g3,["v0"]),"Grafo trivial fica nulo após remoção")

  def test_k2 (self):
    f,g,v_g = self.param
    self.assertFalse(f(g2,v_g2,["v0"])) 

  def test_todasasestacoes (self):
    f,g,v_g = self.param
    self.assertTrue(f(g,v_g,[v_g[v]['label'] for v in g.vertices])) 
