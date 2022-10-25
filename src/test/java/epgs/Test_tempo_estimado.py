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

# Test Data [k,saída desejada]
# Grafo g1  
params_g1 = [
    [0,{0: 2, 1: 1, 2: 3, 3: 4, 4: 1, 5: 2, 6: 3, 7: 2, 8: 2},"#1"],
    [1,{0: 2, 1: 1, 2: 3, 3: 4, 4: 1, 5: 2, 6: 3, 7: 2, 8: 2},"#2"],
    [2,{0: 2, 2: 3, 3: 4, 5: 2, 6: 3, 7: 2, 8: 2},"#3"],
    [3,{2: 3, 3: 4, 6: 3},"#4"],
    [4,{3: 4},"#5"],
    [5,{},"#6"],
]

# Grafo lu
params_lu = [
    [6,{145: 6},"#7"],
    [5,{11: 5, 145: 6},"#8"]
]

# Classe de testes base
class Test_estacoes_centrais_base (ParametrizedTestCase):
  def test_base (self):
    f,g,e_g,k,saida_esperada,m = self.param
    self.assertEqual(f(g,e_g,k),saida_esperada,m)

# Classe de testes edge
class Test_estacoes_centrais_edge (ParametrizedTestCase):
  def test_edge (self):
    f,g,e_g = self.param
    self.assertTrue(f(None,{},0) is None,"Grafo não pode ser None")
    self.assertTrue(f(jgrapht.create_graph(),{},0) == {},"Deve retornar {} para grafo nulo")
    self.assertTrue(f(g,{},0) is None,"Dicionário não pode ser vazio para grafo não vazio")
    self.assertTrue(f(g,e_g,-1) is None,"K tem que ser um número positivo ou zero")
