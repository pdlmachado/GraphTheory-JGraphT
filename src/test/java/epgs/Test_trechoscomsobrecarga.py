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

# Valores de lista de estações para grafo g1 e e_g1
# Path é criado no método de teste
params_g1 = [
  [['Centro'],[('São José','Centro','4'),('Centenário','Centro','2'),('São José','Centro','3'),('José Pinheiro','Centro','3'), ('José Pinheiro','Centro', '4')],"#01"],
  [['Centro', 'São José'],[('Catolé', 'São José', '3'), ('Catolé', 'São José', '4'), ('Centenário', 'Centro', '2'), ('José Pinheiro', 'Centro', '3'), ('José Pinheiro', 'Centro', '4')],"#02"],
  [['Nações', 'José Pinheiro'],[('Catolé', 'Nações', '1'),('Catolé', 'Nações', '4'),('Centro', 'José Pinheiro', '3'),('Centro', 'José Pinheiro', '4')],"#03"],
  [['Catolé','Cruzeiro'],[('Prata', 'Cruzeiro', '3'),('Prata', 'Catolé', '1'),('Prata', 'Catolé', '2'),('Prata', 'Catolé', '3'),('Nações', 'Catolé', '1'),('Nações', 'Catolé', '4'),('Centenário', 'Catolé', '2'),('São José', 'Catolé', '3'),('São José', 'Catolé', '4')],"#04"],
  [[],[],"#05"]
]

# Valores de lista de estações para grafo lu e e_lu
params_lu = [
  [["Aldgate","Tower Hill"],[('Liverpool Street', 'Aldgate', '3'), ('Monument', 'Tower Hill', '3'), ('Aldgate East', 'Tower Hill', '4'), ('Monument', 'Tower Hill', '4'), ('Liverpool Street', 'Aldgate', '8')],"#01"],
  [[],[],"#02"],
  [['Old Street','Angel'],[("King's Cross St. Pancras", 'Angel', '9'),('Moorgate', 'Old Street', '9')],'#03'],
  [['Euston Square','Brixton'],[('Great Portland Street', 'Euston Square', '3'),("King's Cross St. Pancras", 'Euston Square', '3'),('Great Portland Street', 'Euston Square', '6'),("King's Cross St. Pancras", 'Euston Square', '6'),('Great Portland Street', 'Euston Square', '8'),("King's Cross St. Pancras", 'Euston Square', '8'),('Stockwell', 'Brixton', '11')],'#04']
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
class Test_trechoscomsobrecarga_base (ParametrizedTestCase):
  def test_base (self):
    f,g,v_g,e_g,stations,saida_esperada,m = self.param
    self.assertCountEqual(f(g,v_g,e_g,stations),saida_esperada,m)

# Classe de testes edge
class Test_trechoscomsobrecarga_edge (ParametrizedTestCase):
  def test_None (self):
    f,g,v_g,e_g = self.param
    self.assertTrue(f(None,v_g2,e_g2,["v0"]) is None,"Grafo não pode ser None")
    self.assertTrue(f(g2,None,e_g2,["v0"]) is None,"Dicionário de vértices não pode ser None")
    self.assertTrue(f(g2,v_g2,None,["v0"]) is None,"Dicionário de arestas não pode ser None")

  def test_nulo (self):
    f,g,v_g,e_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},{},["v0"]) is None,"Deve retornar None para grafo nulo")
    self.assertTrue(f(g2,{},e_g2,["v0"]) is None,"Dicionário não pode ser vazio para grafo não vazio")  
    self.assertTrue(f(g2,v_g2,{},["v0"]) is None,"Dicionário não pode ser vazio para grafo não vazio")  

  def test_s_invalida (self):
    f,g,v_g,e_g = self.param
    self.assertTrue(f(g2,v_g2,e_g2,["v0","v3"]) is None,"Estação inválida")

  def test_vdict_invalido (self):
    f,g,v_g,e_g = self.param
    self.assertTrue(f(g,v_g2,e_g,[""]) is None,"Dicionário de vértices inválido")

  def test_edict_invalido (self):
    f,g,v_g,e_g = self.param
    self.assertTrue(f(g,v_g,e_g2,[""]) is None,"Dicionário de arestas inválido")

  def test_trivial (self):
    f,g,v_g,e_g = self.param
    self.assertEqual(f(g3,v_g3,e_g3,["v0"]),[],"Deve retornar [] para grafo trivial")

  def test_k2 (self):
    f,g,v_g,e_g = self.param
    self.assertEqual(f(g2,v_g2,e_g2,["v1"]),[('v0', 'v1', '0')]) 

