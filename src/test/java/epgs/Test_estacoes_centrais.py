
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

# Definindo os dados para o teste
# [grafo,dicionário de arestas, limiar,saída esperada]      
g1 = jgrapht.create_graph(directed=False,weighted=True, 
                         allowing_multiple_edges=True)
v_g1 = {}
e_g1 = {}
# Importando sua definição
#read_multiple_CSV(lu,v_lu,e_lu,
#                  'london.stations.csv','id','name',
#                  'london.connections.csv','station1','station2',weightid='time',
#                  delimiter=',')




params = [
    [g1,e_g1,8,{}],
    [g1,e_g1,6,{}],
    [g1,e_g1,5,{}],
    [None,{},0,None],
    [jgrapht.create_graph(),{},0,{}],
    [g1,{},0,None],
    [g1,e_g1,-1,None]
]

# Classe de testes base
class Test_estacoes_centrais (ParametrizedTestCase):
  def test_base (self):
    g,e_g,k,saida_esperada = self.param
    self.assertEqual(estacoes_centrais(g,e_g,k),saida_esperada)

suite1 = unittest.TestSuite()
for i in range(len(params)):
  suite1.addTest(ParametrizedTestCase.parametrize(Test_estacoes_centrais, param=params[i]))
