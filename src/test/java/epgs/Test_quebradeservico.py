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
  [["Cruzeiro"],False,"#01"],
  [["Centro"],True,"#02"],
]

# Grafo lu
params_lu = [
  [["South Kensington", "Baker Street", "Oxford Circus"],True,"#01"],
  [["Bank"],False,"#02"]
]

# Classe de testes base
class Test_quebradeservico_base (ParametrizedTestCase):
  def test_base (self):
    f,g,e_g,l,saida_esperada,m = self.param
    self.assertEqual(f(g,e_g,l),saida_esperada,m)

# Classe de testes edge
class Test_quebradeservico_edge (ParametrizedTestCase):
  def test_edge (self):
    f,g,e_g = self.param
    self.assertTrue(f(None,{},None) is None,"Grafo não pode ser None")