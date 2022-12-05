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

# Valores de estacao atual e maxdist para grafo g1 e e_g1
# Path é criado no método de teste
params_g1 = [
    ['Bodocongó',1,[('UFCG', 'Bodocongó'), ('Treze', 'Prata'), ('Campinense', 'Prata')],"#01"],
    ['Bodocongó',0,[('UFCG', 'Bodocongó')],"#02"],
    ['Centro',0,[('Catedral', 'Centro'),('Teatro Municipal', 'Centro')],"#03"],
    ['Nações',100,[('UFCG', 'Bodocongó'), ('Sítio São João', 'Cruzeiro'), ('Treze', 'Prata'), ('Campinense', 'Prata'), ('Museu de Arte Popular', 'Catolé'), ('Parque da Criança', 'Catolé'), ('Trem do Forró', 'Catolé'), ('Parque do Povo', 'São José'), ('Açude Velho', 'São José'), ('Catedral', 'Centro'), ('Teatro Municipal', 'Centro'), ('Comércio de Couros e Artefatos', 'José Pinheiro')],"#04"],
]

# Valores de estacao atual e maxdist para grafo lu e e_lu
params_lu = [
    ['Gunnersbury',5,[('Kensington Palace', 'High Street Kensington')],'#01'],
    ['Green Park',0,[('Buckingham Palace', 'Green Park'), ('Royal Academy of Arts', 'Green Park')],'#02'],
    ['Green Park',1,[('Christmas market', 'Hyde Park Corner'), ('Victoria Palace Theatre', 'Victoria'), ('High Street Shopping', 'Bond Street'), ('Palace of Westminster', 'Westminster'), ('Big Ben', 'Westminster'), ('Great Portland Street', 'Oxford Circus'), ('Shaftesbury Memorial Fountain', 'Picadilly Circus'), ('SoHo', 'Picadilly Circus'), ('Buckingham Palace', 'Green Park'), ('Royal Academy of Arts', 'Green Park')],'#03']
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
class Test_atracoesmaisproximas_base (ParametrizedTestCase):
  def test_base (self):
    f,g,v_g,estacoes,atual,maxdist,saida_esperada,m = self.param
    resultado = f(g,v_g,estacoes,atual,maxdist)
    self.assertCountEqual(resultado,saida_esperada,m)

# Classe de testes edge
class Test_atracoesmaisproximas_edge (ParametrizedTestCase):
  def test_None (self):
    f,g,v_g = self.param
    self.assertTrue(f(None,v_g2,atracoes_g2,"v0",1) is None,"Grafo não pode ser None")
    self.assertTrue(f(g2,None,atracoes_g2,"v0",1) is None,"Dicionário de vértices não pode ser None")
    self.assertTrue(f(g2,v_g2,None,"v0",1) is None, "Dicionário de atrações não pode ser None")
    self.assertTrue(f(g2,v_g2,atracoes_g2,None,1) is None, "Estação não pode ser None")
    self.assertTrue(f(g2,v_g2,atracoes_g2,"v0",None) is None, "Distância não pode ser None")

  def test_nulo (self):
    f,g,v_g = self.param
    self.assertTrue(f(jgrapht.create_graph(),{},{},"v0",1) is None,"Deve retornar None para grafo nulo")
    self.assertTrue(f(g2,{},atracoes_g2,"v0",1) is None,"Dicionário não pode ser vazio para grafo não vazio")  

  def test_estacao_invalida (self):
    f,g,v_g = self.param
    self.assertTrue(f(g2,v_g2,atracoes_g2,"v10",1) is None,"Estação inválida")

  def test_maxdist_invalida (self):
    f,g,v_g = self.param
    self.assertTrue(f(g2,v_g2,atracoes_g2,"v0",-1) is None,"Distancia inválida")

  def test_vdict_invalido (self):
    f,g,v_g = self.param
    self.assertTrue(f(g,v_g2,atracoes_g2,"v0",1) is None,"Dicionário de vértices inválido")

  def test_adict_invalido (self):
    f,g,v_g = self.param
    self.assertTrue(f(g,v_g,atracoes_g2,"v0",1) is None,"Dicionário de atrações inválido")

  def test_trivial (self):
    f,g,v_g = self.param
    self.assertEqual(f(g3,v_g3,atracoes_g3,"v0",1),[('a0', 'v0')])

  def test_k2 (self):
    f,g,v_g = self.param
    self.assertEqual(f(g2,v_g2,atracoes_g2,"v0",1),[('a0', 'v0')]) 

