
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

# Test Data
g1 = jgrapht.create_graph(directed=False,weighted=True, 
                         allowing_multiple_edges=True)
v_g1 = {}
e_g1 = {}
g1.add_vertices_from([x for x in range(8)])
g1.add_edge(0,2,edge=0)
e_g1[0]={'line'='1'}
g1.add_edge(0,2,edge=1)
e_g1[1]={'line'='2'}
g1.add_edge(1,2,edge=2)
e_g1[1]={'line'='3'}
g1.add_edge(2,3,edge=3)
e_g1[1]={'line'='1'}
g1.add_edge(2,3,edge=4)
e_g1[1]={'line'='2'}
g1.add_edge(2,3,edge=5)
e_g1[1]={'line'='3'}
g1.add_edge(3,8,edge=6)
e_g1[1]={'line'='1'}
g1.add_edge(3,8,edge=7)
e_g1[1]={'line'='4'}


# [grafo,dicionário de arestas, limiar,saída esperada]  
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
    f,g,e_g,k,saida_esperada = self.param
    self.assertEqual(f(g,e_g,k),saida_esperada)
