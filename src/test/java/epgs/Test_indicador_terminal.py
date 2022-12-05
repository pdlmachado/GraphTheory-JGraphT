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
    ['1',{0: 2.0, 2: 1.444, 3: 1.222, 8: 2.0},"#01"],
    ['2',{0: 2.0, 2: 1.5, 3: 1.167, 4: 1.5, 6: 2.0},"#02"],
    ['3',{1: 2.0, 2: 1.45, 3: 1.3, 5: 1.225, 6: 1.562, 7: 2.0},"#03"]
]

# Valores de linha para grafo lu e e_lu
params_lu = [
    ['1',{192: 1.58, 193: 1.312, 197: 1.644, 137: 1.401, 11: 1.462, 140: 1.918, 206: 1.327, 143: 1.277, 82: 1.379, 84: 2.0, 148: 1.897, 212: 1.519, 278: 1.27, 87: 1.763, 279: 1.839, 281: 1.692, 159: 1.251, 163: 1.429, 298: 1.478, 237: 1.839, 49: 1.712, 113: 1.539, 114: 2.0, 246: 1.603, 185: 1.763},"#01"],
    ['2',{256: 1.954, 259: 1.283, 13: 1.302, 16: 1.865, 275: 1.689, 149: 1.393, 24: 1.367, 153: 1.477, 154: 1.502, 28: 1.33, 156: 1.327, 286: 1.802, 158: 1.852, 162: 1.349, 164: 1.394, 37: 1.795, 294: 2.0, 296: 1.554, 173: 1.826, 301: 1.692, 48: 1.253, 177: 1.852, 51: 1.869, 181: 1.636, 186: 1.447, 192: 1.313, 196: 1.765, 68: 1.896, 72: 1.861, 76: 1.604, 208: 1.413, 211: 1.73, 215: 1.828, 88: 2.0, 91: 1.901, 221: 1.941, 98: 1.772, 226: 1.505, 230: 1.632, 103: 1.911, 105: 1.808, 109: 1.938, 239: 1.909, 112: 1.724, 241: 1.669, 247: 1.448, 250: 1.277, 126: 1.255, 127: 1.482},"#02"],
    ['4',{1: 1.899, 129: 1.91, 3: 1.296, 263: 1.289, 265: 1.776, 138: 1.807, 267: 2.0, 268: 1.949, 141: 1.938, 269: 1.678, 270: 1.55, 15: 1.642, 17: 1.664, 273: 1.44, 18: 1.842, 21: 1.714, 25: 1.338, 285: 1.397, 287: 1.7, 33: 1.418, 161: 1.33, 289: 1.483, 36: 1.45, 164: 1.398, 293: 1.631, 166: 1.306, 295: 1.325, 299: 1.989, 44: 1.324, 300: 1.938, 52: 1.86, 186: 1.803, 193: 1.881, 66: 1.822, 67: 1.762, 195: 1.762, 72: 2.0, 200: 1.516, 73: 1.938, 74: 1.463, 205: 1.811, 78: 1.584, 80: 1.849, 209: 1.721, 83: 1.932, 85: 1.871, 213: 1.989, 87: 1.377, 96: 1.725, 99: 1.496, 229: 1.462, 231: 1.887, 108: 1.887, 236: 1.484, 110: 1.686, 242: 1.756, 244: 1.367, 248: 1.418, 122: 1.754, 255: 1.357},"#03"],
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
    resultado = f(g,e_g,atual)
    self.assertDictEqual(resultado,saida_esperada,m)

# Classe de testes edge
class Test_indicador_terminal_edge (ParametrizedTestCase):

  def test_None (self):
    f,g,e_g = self.param
    self.assertTrue(f(None,e_g2,'0') is None,"Grafo não pode ser None")
    self.assertTrue(f(g2,None,'0') is None,"Dicionário de vértices não pode ser None")
    self.assertTrue(f(g2,e_g2,None) is None,"Linha não pode ser None")

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
