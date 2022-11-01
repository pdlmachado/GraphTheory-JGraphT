
import jgrapht
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

#########################
# Test Data

# Valores de k e saída desejada para chamadas com g1 e e_g1
# Cada tupla é identificada com #n para facilitar a depuração em caso de erros e falhas
# Exemplo: estacoes_centrais(g1,e_g1,0) deve retornar {0: 2, 1: 1, 2: 3, 3: 4, 4: 1, 5: 2, 6: 3, 7: 2, 8: 2}
params_g1 = [
    [0,{0: 2, 1: 1, 2: 3, 3: 4, 4: 1, 5: 2, 6: 3, 7: 2, 8: 2},"#1"],
    [1,{0: 2, 1: 1, 2: 3, 3: 4, 4: 1, 5: 2, 6: 3, 7: 2, 8: 2},"#2"],
    [2,{0: 2, 2: 3, 3: 4, 5: 2, 6: 3, 7: 2, 8: 2},"#3"],
    [3,{2: 3, 3: 4, 6: 3},"#4"],
    [4,{3: 4},"#5"],
    [5,{},"#6"]
]

# Valores de k e saída desejada para chamadas com lu e e_lu
params_lu = [
    [6,{145: 6},"#1"],
    [5,{11: 5, 145: 6},"#2"],
    [4,{11: 5, 13: 4, 87: 4, 145: 6, 156: 4, 167: 4, 193: 4, 279: 4},"#3"]
]

# Outros exemplos de grafos para teste
g2 = jgrapht.create_graph(directed=False)
g2.add_vertices_from([0,1])
g2.add_edge(0,1,edge=0)
e_g2 = {0:{'line':'0'}}

g3 = jgrapht.create_graph(directed=False)
g3.add_vertex(0)
e_g3 = {}

# Classe de testes base
class Test_estacoes_centrais_base (ParametrizedTestCase):
  def test_base (self):
    f,g,e_g,k,saida_esperada,m = self.param
    self.assertEqual(f(g,e_g,k),saida_esperada,m)

# Classe de testes edge
class Test_estacoes_centrais_edge (ParametrizedTestCase):

  def test_None (self):
    f,g,e_g = self.param
    self.assertTrue(f(None,{},0) is None,"Grafo não pode ser None")
    self.assertTrue(f(g,None,0) is None,"Dicionário de arestas não pode ser None")

  def test_nulo (self):
    f,g,e_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},0) == {},"Deve retornar {} para grafo nulo")
    self.assertTrue(f(g,{},0) is None,"Deve retornar None para dicionário vazio e grafo não nulo")

  def test_k_invalido (self):
    f,g,e_g = self.param
    self.assertTrue(f(g,e_g,-1) is None,"K tem que ser um número positivo ou zero")

  def test_edict_invalido (self):
    f,g,e_g = self.param
    self.assertTrue(f(g,e_g2,5) is None,"Dicionário inválido")

  def test_trivial (self):
    f,g,e_g = self.param
    self.assertEqual(f(g3,e_g3,3),{},"Deve retornar {} para grafo trivial")

  def test_k2 (self):
    f,g,e_g = self.param
    self.assertEqual(f(g2,e_g2,1),{0: 1, 1: 1}) 
