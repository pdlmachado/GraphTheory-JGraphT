# Definindo os dados para o teste
# [grafo,dicionário de arestas, limiar,saída esperada]
import jgrapht
lu = jgrapht.create_graph(directed=False,weighted=True, 
                         allowing_multiple_edges=True)
v_lu = {}
e_lu = {}
# Importando sua definição
read_multiple_CSV(lu,v_lu,e_lu,
                  'london.stations.csv','id','name',
                  'london.connections.csv','station1','station2',weightid='time',
                  delimiter=',')

params = [
    [lu,e_lu,8,{}],
    [lu,e_lu,6,{145: 6}],
    [lu,e_lu,5,{11: 5, 145: 6}],
    [None,{},0,None],
    [jgrapht.create_graph(),{},0,{}],
    [lu,{},0,None],
    [lu,e_lu,-1,None]
]

# Classe de testes base
class Test_estacoes_centrais (ParametrizedTestCase):
  def test_base (self):
    g,e_g,k,saida_esperada = self.param
    self.assertEqual(estacoes_centrais(g,e_g,k),saida_esperada)

suite1 = unittest.TestSuite()
for i in range(len(params)):
  suite1.addTest(ParametrizedTestCase.parametrize(Test_estacoes_centrais, param=params[i]))
