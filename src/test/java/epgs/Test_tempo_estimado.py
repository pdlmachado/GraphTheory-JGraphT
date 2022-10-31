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

# Test Data [v1,v2,t,saída desejada]
# Grafo g1  
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

# Grafo lu
params_lu = [
  [1,4,5,86,"#01"],
  [131,2,10,140,"#02"],
  [1,200,9,130,"#03"],
  [1,200,0,58,"#04"],
  [1,1,10,0,"#05"]  
]

# Classe de testes base
class Test_tempo_estimado_base (ParametrizedTestCase):
  def test_base (self):
    f,g,e_g,v1,v2,t,saida_esperada,m = self.param
    path = next(yen_k_loopless(g,v1,v2,1))
    self.assertEqual(f(g,e_g,path,t),saida_esperada,m)

# Classe de testes edge
class Test_tempo_estimado_edge (ParametrizedTestCase):
  def test_edge (self):
    f,g,e_g = self.param
    path = next(yen_k_loopless(g,1,2,1))
    self.assertTrue(f(None,{},None,0) is None,"Grafo não pode ser None")
    self.assertTrue(f(g,None,None,0) is None,"Dicionário de arestas não pode ser None")
    self.assertTrue(f(jgrapht.create_graph(),{},None,0) is None,"Deve retornar None para grafo nulo")
    self.assertTrue(f(g,{},path,10) is None,"Dicionário não pode ser vazio para grafo não vazio")
    self.assertTrue(f(g,e_g,path,-1) is None,"t tem que ser um número positivo ou zero")
    self.assertTrue(f(g,e_g,None,10) is None,"Path não pode ser None")


